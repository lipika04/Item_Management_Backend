package com.example.crud_application.controller;


// import com.example.crud_application.controller.UserController;
import com.example.crud_application.model.User;
import com.example.crud_application.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.registerUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    void loginUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.authenticate("testuser", "password")).thenReturn(user);

        ResponseEntity<User> response = userController.login("testuser", "password");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    void loginUser_Failure() {
        when(userService.authenticate("testuser", "wrongPassword")).thenThrow(new RuntimeException("Authentication failed"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userController.login("testuser", "wrongPassword");
        });

        assertEquals("Authentication failed", exception.getMessage());
    }
}
