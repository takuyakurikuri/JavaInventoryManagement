package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.InventoryWithItemDTO;
import com.example.entity.Inventory;
import com.example.entity.Item;
import com.example.entity.Store;
import com.example.enums.TransactionType;
import com.example.repository.InventoryRepository;
import com.example.repository.ItemRepository;
import com.example.repository.StoreRepository;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StoreRepository storeRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventory(Long storeId, Long itemId) {
        Optional<Store> storeOpt = storeRepository.findById(storeId);
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (storeOpt.isPresent() && itemOpt.isPresent()) {
            return inventoryRepository.findByItemAndStore(itemOpt.get(), storeOpt.get());
        }
        return Optional.empty();
    }

    public List<InventoryWithItemDTO> getAllInventoryWithItemByStore(Store store) {
        List<Inventory> inventories = inventoryRepository.findByStore(store);//店舗関係なくすべての在庫を取得してしまう。
        List<InventoryWithItemDTO> result = new ArrayList<>();

        for (Inventory inv : inventories) {
            result.add(new InventoryWithItemDTO(inv, inv.getItem()));
        }

        result.sort((a, b) -> {
            int diffA = (int)(a.getItem().getStockStd() - a.getInventory().getQuantity());
            int diffB = (int)(b.getItem().getStockStd() - b.getInventory().getQuantity());
            return Integer.compare(diffB, diffA); // 降順
        });

        return result;
    }

    public List<InventoryWithItemDTO> getAllItem(Store store) {
        List<Item> items = itemRepository.findAll(); // 商品を全件取得
        List<InventoryWithItemDTO> result = new ArrayList<>();

        for (Item item : items) {
            Optional<Inventory> invOpt = inventoryRepository.findByItemAndStore(item, store); // Storeを指定
            Inventory inventory = invOpt.orElse(null);
            result.add(new InventoryWithItemDTO(inventory, item));
        }

        // 在庫不足順に並び替え（Inventoryがnullのものは一番後ろ）
        result.sort((a, b) -> {
            if (a.getInventory() == null && b.getInventory() == null) return 0;
            if (a.getInventory() == null) return 1;
            if (b.getInventory() == null) return -1;

            int diffA = (int)(a.getItem().getStockStd() - a.getInventory().getQuantity());
            int diffB = (int)(b.getItem().getStockStd() - b.getInventory().getQuantity());
            return Integer.compare(diffB, diffA);
        });

        return result;
    }


    public long countUnderstockedItemsBystore(Store store) {
        return getAllInventoryWithItemByStore(store).stream()
            .filter(dto -> dto.getInventory() != null &&
                           dto.getInventory().getQuantity() < dto.getItem().getStockStd())
            .count();
    }

    @Transactional
    public void updateInventoryFromTransaction(Long itemId, Long storeId, Integer quantity, TransactionType type) {
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        Optional<Store> storeOpt = storeRepository.findById(storeId);

        if (itemOpt.isPresent() && storeOpt.isPresent()) {
            Item item = itemOpt.get();
            Store store = storeOpt.get();

            Inventory inventory = inventoryRepository.findByItemAndStore(item, store)
                    .orElseGet(() -> {
                        Inventory newInventory = new Inventory();
                        newInventory.setItem(item);
                        newInventory.setStore(store);
                        newInventory.setQuantity(0); // 初期在庫は0
                        return newInventory;
                    });

            // 在庫を加算 or 減算
            int currentQuantity = inventory.getQuantity();
            if (type == TransactionType.IN) {
                inventory.setQuantity(currentQuantity + quantity);
            } else if (type == TransactionType.OUT) {
                int newQuantity = currentQuantity - quantity;
                inventory.setQuantity(Math.max(newQuantity, 0)); // マイナスにならないように制限
            }

            inventoryRepository.save(inventory);
        }
    }


}
