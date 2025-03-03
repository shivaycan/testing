
package com.example.demo.user;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.shiksha.repository.UserRepository;
import com.example.shiksha.model.User;
import com.example.shiksha.service.UserService;
import com.example.shiksha.model.Role;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest{

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    // @BeforeAll runs once before all tests
    @BeforeAll
    public static void init() {
        System.out.println("before all");
    }

    // @BeforeEach runs before each test
    @BeforeEach
    public void setup() {
        System.out.println("before each");
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmailid("john.doe@example.com");
        user.setPassword("password");
        user.setRole(Role.INSTRUCTOR);
    }

    // @AfterEach runs after each test
    @AfterEach
    public void tearDown() {
        user = null;
    }

    // @AfterAll runs once after all tests
    @AfterAll
    public static void afterAll() {
        System.out.println("after All");
    }

    @Test
    public void registerUserSuccessfully() {
        System.out.println("unit 1 test");

        // Mocking the save method of userRepository to return the provided user
        when(userRepository.save(user)).thenReturn(user);

        // Call the method to be tested
        User registeredUser = userService.registerUser(user);

        // Assertions to verify the result
        Assertions.assertEquals(user, registeredUser);
        Assertions.assertNotNull(registeredUser);
    }

    @Test
    public void getAllUsersSuccessfully() {
        System.out.println("unit 2 test");

        // Mocking the findAll method of userRepository to return a list containing the user
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        // Call the method to be tested
        List<User> result = userService.getAllUsers();

        // Assertions to verify the result
        Assertions.assertEquals(users, result);
    }

    @Test
    public void updateUserSuccessfully() {
        System.out.println("unit 3 test");

        // Mocking the findById and save methods of userRepository
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Call the method to be tested
        User updatedUser = userService.update(user);

        // Assertions to verify the result
        Assertions.assertEquals(user, updatedUser);
    }

    @Test
    public void updateUserNotFound() {
        System.out.println("unit 4 test");

        // Mocking the findById method of userRepository to return an empty Optional
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        // Expecting a RuntimeException to be thrown
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.update(user);
        });

        // Assertions to verify the exception message
        Assertions.assertNotEquals("User not found with id " + user.getId(), exception.getMessage());
    }

    @Test
    public void deleteUserSuccessfully() {
        System.out.println("unit 5 test");

        // Mocking the existsById method of userRepository to return true
        when(userRepository.existsById(user.getId())).thenReturn(true);

        // Call the method to be tested
        userService.DeleteUser(user.getId());

        // Verify that deleteById was called once with the correct id
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    public void deleteUserNotFound() {
        System.out.println("unit 6 test");

        // Mocking the existsById method of userRepository to return false
        when(userRepository.existsById(user.getId())).thenReturn(false);

        // Expecting a RuntimeException to be thrown
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.DeleteUser(user.getId());
        });

        // Assertions to verify the exception message
        Assertions.assertNotEquals("User not found with id: " + user.getId(), exception.getMessage());
    }
}
