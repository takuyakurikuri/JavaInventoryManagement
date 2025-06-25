package com.example.dto;

import com.example.entity.Inventory;
import com.example.entity.Item;

public class InventoryWithItemDTO {
    private Inventory inventory;
    private Item item;

    public InventoryWithItemDTO(Inventory inventory, Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Item getItem() {
        return item;
    }

    public boolean isUnderStock() {
        return inventory != null && inventory.getQuantity() < item.getStockStd();
    }

    public String getQuantityDisplay() {
        return inventory != null ? String.valueOf(inventory.getQuantity()) : "入荷記録がありません";
    }
}
