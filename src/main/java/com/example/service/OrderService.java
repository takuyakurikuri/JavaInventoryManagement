package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dto.OrderItemDTO;
import com.example.entity.Inventory;
import com.example.entity.Item;
import com.example.entity.OrderRequest;
import com.example.entity.OrderRequestDetail;
import com.example.entity.Store;
import com.example.repository.InventoryRepository;
import com.example.repository.ItemRepository;
import com.example.repository.OrderRequestRepository;

@Service
public class OrderService {

    private final OrderRequestRepository orderRequestRepository;

    private final InventoryRepository inventoryRepository;

    private final ItemRepository itemRepository;

    public OrderService(InventoryRepository inventoryRepository, OrderRequestRepository orderRequestRepository, ItemRepository itemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.orderRequestRepository = orderRequestRepository;
        this.itemRepository = itemRepository;
    }

    public List<OrderItemDTO> getOrderItemDTOsByStore(Store store) {
        List<Item> items = itemRepository.findAll(); // 全商品を取得
        List<OrderItemDTO> result = new ArrayList<>();

        for (Item item : items) {
            Optional<Inventory> inventoryOpt = inventoryRepository.findByItemAndStore(item, store);

            OrderItemDTO dto = new OrderItemDTO();
            dto.setItemId(item.getId());
            dto.setItemName(item.getName());
            dto.setCategoryName(item.getCategory().getName());
            dto.setOrderUnit(item.getOrderUnit());
            dto.setStockStd(item.getStockStd());
            dto.setCurrentQuantity(inventoryOpt.map(Inventory::getQuantity).orElse(null)); // 在庫がなければnull
            dto.setOrderQuantity(0);

            result.add(dto);
        }

        return result;
    }


    public List<OrderItemDTO> getOrderDetailsByRequestId(Long requestId) {

        if (requestId == null) {
            return Collections.emptyList();  // ← ここで null をはじく
        }

        Optional<OrderRequest> optionalRequest = orderRequestRepository.findById(requestId);

        if (optionalRequest.isEmpty()) {
            return Collections.emptyList(); // or throw new RuntimeException("Not found");
        }

        OrderRequest request = optionalRequest.get();
        List<OrderItemDTO> dtoList = new ArrayList<>();

        for (OrderRequestDetail detail : request.getDetails()) {
            Item item = detail.getItem();
            Store store = request.getStore();

            OrderItemDTO dto = new OrderItemDTO();
            dto.setItemId(item.getId());
            dto.setItemName(item.getName());
            dto.setCategoryName(item.getCategory().getName());
            dto.setOrderUnit(item.getOrderUnit());
            dto.setStockStd(item.getStockStd());

            // 現在庫（Inventory）を item から取得
            Optional<Inventory> inventoryOpt = inventoryRepository.findByItemAndStore(item, store);
            int currentQty = inventoryOpt.map(Inventory::getQuantity).orElse(0);
            dto.setCurrentQuantity(currentQty);

            dto.setOrderQuantity(detail.getQuantity());

            dtoList.add(dto);
        }

        return dtoList;
    }

}
