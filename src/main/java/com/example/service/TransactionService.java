package com.example.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ExpirationCheckDto;
import com.example.entity.Item;
import com.example.entity.Store;
import com.example.entity.Transaction;
import com.example.entity.User;
import com.example.enums.ShipmentType;
import com.example.enums.TransactionType;
import com.example.repository.ItemRepository;
import com.example.repository.StoreRepository;
import com.example.repository.TransactionRepository;
import com.example.repository.UserRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InventoryService inventoryService;

    public void recordTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(Long storeId, Long itemId) {
        Optional<Store> storeOpt = storeRepository.findById(storeId);
        Optional<Item> itemOpt = itemRepository.findById(itemId);

        if (storeOpt.isPresent() && itemOpt.isPresent()) {
            return transactionRepository.findByStoreAndItem(storeOpt.get(), itemOpt.get());
        }

        return Collections.emptyList();
    }

    public List<ExpirationCheckDto> getUnconfirmedExpirations() {
        return transactionRepository.findAllUnconfirmedExpirations();
    }

    public Map<String, Object> getUnconfirmedExpirationsAndCount(Long storeId) {
        List<ExpirationCheckDto> list = transactionRepository.findUnconfirmedExpirationsByStore(storeId);

        long nearExpirationCount = list.stream()
            .filter(ExpirationCheckDto::isNearExpiration)
            .count();

        Map<String, Object> result = new HashMap<>();
        result.put("expirations", list);
        result.put("nearExpirationCount", nearExpirationCount);

        return result;
    }

    public void markAsChecked(Long transactionId) {
        transactionRepository.findById(transactionId).ifPresent(t -> {
            t.setChecked(true);
            transactionRepository.save(t);
        });
    }

    public void registerInTransaction(Long itemId, Integer quantity, LocalDate expirationDate, String email) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Store store = user.getStore();

        Transaction transaction = new Transaction();
        transaction.setItem(item);
        transaction.setStore(store);
        transaction.setQuantity(quantity);
        transaction.setExpirationDate(expirationDate);
        transaction.setUser(user);
        transaction.setType(TransactionType.IN);
        transaction.setChecked(false);

        transactionRepository.save(transaction);
        inventoryService.updateInventoryFromTransaction(transaction.getItem().getId(), transaction.getStore().getId(), transaction.getQuantity(), transaction.getType());
    }

    public void registerOutTransaction(Long itemId, Integer quantity, ShipmentType shipmentType, String email) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Store store = user.getStore();

        Transaction transaction = new Transaction();
        transaction.setItem(item);
        transaction.setStore(store);
        transaction.setQuantity(quantity);
        transaction.setShipmentType(shipmentType);
        transaction.setUser(user);
        transaction.setType(TransactionType.OUT);
        transaction.setChecked(false);

        transactionRepository.save(transaction);
        inventoryService.updateInventoryFromTransaction(transaction.getItem().getId(), transaction.getStore().getId(), transaction.getQuantity(),transaction.getType());
    }

    public List<Transaction> getAllTransactionsByStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        return transactionRepository.findByStoreOrderByCreatedAtDesc(store);
    }

}
