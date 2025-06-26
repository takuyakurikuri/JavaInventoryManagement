package com.example.service;

import java.util.List;
import java.util.NoSuchElementException;

//ItemService.java
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id){
        return itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("商品が見つかりませんません" + id));
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public void deletebyId(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> searchItems(String keyword,Long categoryId,Long supplierId){
        if ((keyword == null || keyword.isEmpty()) &&
            categoryId == null && supplierId == null) {
            return itemRepository.findAll();
        }
        return itemRepository.findByConditions(keyword, categoryId, supplierId);
    }
}
