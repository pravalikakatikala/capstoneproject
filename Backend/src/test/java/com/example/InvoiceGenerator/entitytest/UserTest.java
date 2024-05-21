package com.example.InvoiceGenerator.entitytest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.InvoiceGenerator.entity.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @InjectMocks
    private User user;

    @BeforeEach
    public void setUp() {
    	user.setId(1L);
    	user.setUserName("pravalika");
    	user.setPassword("12344");
    	user.setEmail("pravalika@gmail.com");
    	user.setCreationDate(LocalDateTime.now());
    }

    @Test
    public void testGetId() {
        assertEquals(1L, user.getId());
    }

    @Test
    public void testGetUsername() {
        assertEquals("pravalika", user.getUserName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("12344", user.getPassword());
    }

    @Test
    public void testGetEmail() {
        assertEquals("pravalika@gmail.com", user.getEmail());
    }

    @Test
    public void testGetCreationDate() {
        assertEquals(LocalDateTime.now().getDayOfMonth(), user.getCreationDate().getDayOfMonth());
    }
}