package com.example.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dto.TransactionDTO;
import com.example.entity.Inventory;
import com.example.entity.Transaction;
import com.example.enums.ShipmentType;
import com.example.repository.InventoryRepository;
import com.example.repository.TransactionRepository;
import com.example.security.CustomUserDetails;
import com.example.service.InventoryService;
import com.example.service.TransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private final InventoryService inventoryService;
    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;
    private final InventoryRepository inventoryRepository;

    public TransactionController(InventoryService inventoryService,TransactionService transactionService,TransactionRepository transactionRepository,InventoryRepository inventoryRepository) {

        this.inventoryService = inventoryService;
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping
    public String listInventories(Model model) {
        model.addAttribute("inventoryItems", inventoryService.getAllItem());
        return "transactions/list";
    }

    @PostMapping("/in")
    public String registerInTransaction(
            @RequestParam Long itemId,
            @RequestParam Integer quantity,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            RedirectAttributes redirectAttributes
    ) {
        transactionService.registerInTransaction(itemId, quantity, expirationDate, userDetails.getUsername());
        redirectAttributes.addFlashAttribute("successMessage", "入荷登録が完了しました");
        return "redirect:/transaction";
    }

    @PostMapping("/out")
    public String registerOutTransaction(
        @RequestParam Long itemId,
        @RequestParam Integer quantity,
        @RequestParam ShipmentType shipmentType, // Enumで受け取る
        @AuthenticationPrincipal CustomUserDetails userDetails,
        RedirectAttributes redirectAttributes
    ) {
        transactionService.registerOutTransaction(itemId, quantity, shipmentType, userDetails.getUsername());
        redirectAttributes.addFlashAttribute("successMessage", "出荷登録が完了しました");
        return "redirect:/transaction";
    }

    @GetMapping("/history")
    public String showHistory(Model model) {
        List<Transaction> transactions = transactionRepository.findAll();

        List<TransactionDTO> dtos = transactions.stream().map(t -> {
            TransactionDTO dto = new TransactionDTO();
            dto.setTransaction(t);
            // Inventoryを使って在庫数取得
            Optional<Inventory> inventory = inventoryRepository.findByItemAndStore(t.getItem(), t.getStore());
            dto.setCurrentStock(inventory != null ? inventory.get().getQuantity() : 0L);
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("transactionDTOs", dtos);
        return "transactions/history";
    }


}
