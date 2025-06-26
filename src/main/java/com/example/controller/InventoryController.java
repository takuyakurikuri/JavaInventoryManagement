package com.example.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.InventoryWithItemDTO;
import com.example.entity.Store;
import com.example.security.CustomUserDetails;
import com.example.service.InventoryService;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String listInventories(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        Store store = userDetails.getStore();
        List<InventoryWithItemDTO> inventoryItems = inventoryService.getAllInventoryWithItemByStore(store);
        long understockCount = inventoryService.countUnderstockedItemsBystore(store);
        model.addAttribute("inventoryItems", inventoryItems);
        model.addAttribute("understockCount", understockCount);
        return "inventory/list";
    }
}
