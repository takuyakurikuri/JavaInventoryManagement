package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Inventory;
import com.example.entity.Item;
import com.example.entity.Store;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByItemAndStore(Item item, Store store);
    List<Inventory> findByStore(Store store);
    Optional<Inventory> findByItem(Item item);
}

