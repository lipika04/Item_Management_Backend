package com.example.crud_application.service;

import com.example.crud_application.model.Item;
import com.example.crud_application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Optional<Item> getItemByIdAndUser(Long itemId, Long userId) {
        return itemRepository.findByIdAndUserId(itemId, userId);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }
}
