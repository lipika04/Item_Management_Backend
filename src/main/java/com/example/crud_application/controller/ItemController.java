package com.example.crud_application.controller;

import com.example.crud_application.model.Item;
import com.example.crud_application.model.User;
import com.example.crud_application.service.ItemService;
import com.example.crud_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElseThrow();
        item.setUser(user);
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item itemDetails, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElseThrow();
        Item item = itemService.getItemByIdAndUser(id, user.getId()).orElseThrow();

        item.setName(itemDetails.getName());
        item.setDescription(itemDetails.getDescription());

        return itemService.updateItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElseThrow();
        Item item = itemService.getItemByIdAndUser(id, user.getId()).orElseThrow();
        itemService.deleteItem(item);
    }
}
