package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.CustomUserDetails;
import com.example.service.TransactionService;

@Controller
@RequestMapping("/expiration")
public class ExpirationController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String showExpirationList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long storeId = userDetails.getUser().getStore().getId();

        Map<String, Object> result = transactionService.getUnconfirmedExpirationsAndCount(storeId);
        model.addAttribute("expirations", result.get("expirations"));
        model.addAttribute("nearExpirationCount", result.get("nearExpirationCount"));

        return "expiration/list";
    }

    @PostMapping("/confirm/{transactionId}")
    public String confirm(@PathVariable Long transactionId) {
        transactionService.markAsChecked(transactionId);
        return "redirect:/expiration";
    }
}
