package com.example.InvoiceGenerator.repositorytest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.InvoiceGenerator.entity.User;
import com.example.InvoiceGenerator.entity.UserBuilder;
import com.example.InvoiceGenerator.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User mounika;
    private User pravalika;

    @BeforeEach
    void setUp() {
        mounika = UserBuilder.builder()
                .username("mounika")
                .password("123444")
                .email("mounika@gmail.com")
                .creationDate(LocalDateTime.now())
                .build();

        pravalika = UserBuilder.builder()
                .username("pravalika")
                .password("123455")
                .email("pravalika@gmail.com")
                .creationDate(LocalDateTime.now())
                .build();

        userRepository.save(mounika );
        userRepository.save(pravalika);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testFindByEmail() {
        assertTrue(userRepository.findByEmail("mounika@gmail.com").isPresent());
        assertFalse(userRepository.findByEmail("no user found ").isPresent());
    }

    @Test
    void testFindByUsername() {
        assertTrue(userRepository.findByUserName("pravalika").isPresent());
        assertFalse(userRepository.findByUserName("no user found ").isPresent());
    }

    @Test
    void testFindAll() {
        List<User> userList = userRepository.findAll();
        assertEquals(2, userList.size());
    }

    @Test
    void testFindById() {
        assertTrue(userRepository.findById(mounika.getId()).isPresent());
        assertFalse(userRepository.findById(100L).isPresent());
    }

    @Test
    void testSaveUser() {
    	User newUser = UserBuilder.builder()
                .username("NewUser")
                .password("newuser")
                .email("newuser@gmail.com")
                .creationDate(LocalDateTime.now())
                .build();
        userRepository.save(newUser);
        assertTrue(userRepository.findById(newUser.getId()).isPresent());
    }

    @Test
    void testUpdateUser() {
    	User userToUpdate = userRepository.findByUserName("mounika").orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setUserName("updatedUsername");
            userRepository.save(userToUpdate);
            assertEquals("updatedUsername", userRepository.findById(userToUpdate.getId()).orElse(null).getUserName());
        }
    }

    @Test
    void testDeleteUser() {
    	User userToDelete = userRepository.findByUserName("mounika").orElse(null);
        if (userToDelete != null) {
            userRepository.deleteById(userToDelete.getId());
            assertFalse(userRepository.findById(userToDelete.getId()).isPresent());
        }
    }
}