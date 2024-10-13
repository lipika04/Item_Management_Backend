package com.example.crud_application.controller;

import com.example.crud_application.model.Item;
import com.example.crud_application.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createItem_Success() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Test Item");

        when(itemController.createItem(any(Item.class), any(Authentication.class))).thenReturn(item);

        Item createdItem = itemController.createItem(item, authentication);

        assertEquals(item.getId(), createdItem.getId());
        assertEquals(item.getName(), createdItem.getName());
        verify(itemService, times(1)).createItem(any(Item.class));
    }
}
