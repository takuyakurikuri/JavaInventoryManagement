package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.InventoryWithItemDTO;
import com.example.service.InventoryService;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String listInventories(Model model) {
        List<InventoryWithItemDTO> inventoryItems = inventoryService.getAllInventoryWithItem();
        long understockCount = inventoryService.countUnderstockedItems();
        model.addAttribute("inventoryItems", inventoryItems);
        model.addAttribute("understockCount", understockCount);
        return "inventory/list";
    }
}
