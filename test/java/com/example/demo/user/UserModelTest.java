package com.example.demo.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.shiksha.model.Role;
import com.example.shiksha.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setRole(Role.STUDENT);
        user.setEmailid("john@example.com");
        user.setPassword("password");
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }

    @Test
    public void testUserGettersAndSetters() {
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals(Role.STUDENT, user.getRole());
        assertEquals("john@example.com", user.getEmailid());
        assertEquals("password", user.getPassword());
    }
}
