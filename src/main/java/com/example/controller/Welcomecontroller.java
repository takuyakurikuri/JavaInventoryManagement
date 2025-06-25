package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.CustomUserDetails;
import com.example.service.InventoryService;
import com.example.service.TransactionService;


@Controller
public class Welcomecontroller {

    @Autowired
    private UserRepository userRepository;

    private final InventoryService inventoryService;
    private final TransactionService transactionService;

    public Welcomecontroller(InventoryService inventoryService, TransactionService transactionService) {
        this.inventoryService = inventoryService;
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if(userDetails != null) {
            User user = userRepository.findByEmail(userDetails.getUsername()).get();
            model.addAttribute("user", user);
            model.addAttribute("understockCount", inventoryService.countUnderstockedItems());

            Long storeId = userDetails.getUser().getStore().getId();
            Map<String, Object> result = transactionService.getUnconfirmedExpirationsAndCount(storeId);
            model.addAttribute("nearExpirationCount", result.get("nearExpirationCount"));
        }
        return "welcome/index";
    }

//


}
