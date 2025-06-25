package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Item;
import com.example.service.CategoryService;
import com.example.service.ItemService;
import com.example.service.SupplierService;



@Controller
@RequestMapping("/items")
public class ItemController {


    private final ItemService itemService;


    private final CategoryService categoryService;


    private final SupplierService supplierService;

    public ItemController(ItemService itemService, CategoryService categoryService, SupplierService supplierService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public String showItemList(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "items/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model, @ModelAttribute Item item) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "items/form";
    }

    @PostMapping("/create")
    public String createItem(@ModelAttribute @Valid Item item,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("suppliers", supplierService.findAll());
            return "items/form";
        }

        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("/edit/{itemId}")
    public String showEditForm(@ModelAttribute Item item, @PathVariable Long itemId, Model model) {
        model.addAttribute("item",itemService.findById(itemId));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "items/edit";
    }

    @PostMapping("/edit/{itemId}")
    public String updateItem(@PathVariable Long itemId,
                             @ModelAttribute @Valid Item item,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("suppliers", supplierService.findAll());
            return "items/edit";
        }

        Item existingItem = itemService.findById(itemId);
        if (existingItem == null) {
            result.reject("notfound", "商品が見つかりません");
            return "items/edit";
        }

        // IDと作成日時は既存のまま維持
        item.setId(itemId);
        item.setCreatedAt(existingItem.getCreatedAt());
        item.setUpdatedAt(LocalDateTime.now());

        itemService.save(item);

        redirectAttributes.addFlashAttribute("successMessage", "商品情報を更新しました");
        return "redirect:/items";
    }

    @PostMapping("/edit/{itemId}/delete")
    public String delete(@PathVariable Long itemId, RedirectAttributes redirectAttributes, Model model) {
        try {
            itemService.deletebyId(itemId);
            redirectAttributes.addFlashAttribute("successMessage", "登録商品を削除しました");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "削除できません。関連する取引履歴が存在します。");
            model.addAttribute("item",itemService.findById(itemId));
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("suppliers", supplierService.findAll());
            return "redirect:/items/edit/{itemId}";
        }
        return "redirect:/items";
    }


}