package com.example.Film_rating.repository;

import com.example.Film_rating.entity.Film;
import com.example.Film_rating.entity.registration.Role;
import com.example.Film_rating.entity.registration.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    @Order(10)
    void saveUserTest() {
        User user  = new User("nameTest", "emailTest", "passwordTest", Arrays.asList(new Role("userTest")));
        User userFromDB = userRepository.save(user);

        assertTrue(userFromDB.getId() > 0);
    }

    @Test
    @Order(20)
    void findUserByNameTest() {
        User userByName  = userRepository.findByName("nameTest");

        assertEquals(userByName.getName(), "nameTest");
    }

    @Test
    @Rollback(value = false)
    @Order(30)
    void deleteUserTest() {
        User userByName  = userRepository.findByName("nameTest");

        userRepository.delete(userByName);

        assertNull(userRepository.findByName("nameTest"));
    }
}