package com.mllo.p2evik;

import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.repository.UserRepository;
import com.mllo.p2evik.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_savesAndReturnsUser() {
        User user = new User("John Doe");
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser_updatesAndReturnsUser() {
        User user = new User("John Doe");
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(1L, user);

        assertEquals(user, updatedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser_deletesUserById() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_deletesUser() {
        User user = new User("John Doe");
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void getUsers_returnsListOfUsers() {
        List<User> users = Arrays.asList(new User("John Doe"), new User("Jane Doe"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getUsers();

        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findUser_returnsUserById() {
        User user = new User("John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findUser(1L);

        assertEquals(user, result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findUser_returnsNullWhenUserNotFoundById() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User result = userService.findUser(1L);

        assertNull(result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findUserByEmail_returnsUserByEmail() {
        User user = new User("John Doe");
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(user);

        User result = userService.findUserByEmail("john.doe@example.com");

        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void findUserByEmail_returnsNullWhenUserNotFoundByEmail() {
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(null);

        User result = userService.findUserByEmail("john.doe@example.com");

        assertNull(result);
        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
    }

    @Test
    void findUser_returnsUserByName() {
        User user = new User("John Doe");
        when(userRepository.findByName("John Doe")).thenReturn(user);

        User result = userService.findUser("John Doe");

        assertEquals(user, result);
        verify(userRepository, times(1)).findByName("John Doe");
    }

    @Test
    void findUser_returnsNullWhenUserNotFoundByName() {
        when(userRepository.findByName("John Doe")).thenReturn(null);

        User result = userService.findUser("John Doe");

        assertNull(result);
        verify(userRepository, times(1)).findByName("John Doe");
    }
}