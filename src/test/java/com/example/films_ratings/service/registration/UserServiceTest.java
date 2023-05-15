package com.example.films_ratings.service.registration;

import com.example.films_ratings.dto.UserRegistrationDTO;
import com.example.films_ratings.entity.Roles;
import com.example.films_ratings.entity.registration.Role;
import com.example.films_ratings.entity.registration.User;
import com.example.films_ratings.repository.RoleRepository;
import com.example.films_ratings.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("nameTest", "emailTest", "passwordTest", false, false);
        User user = new User(registrationDTO.getName(), registrationDTO.getEmail(), registrationDTO.getPassword(), Arrays.asList(new Role("userTest")));

        given(userRepository.save(any(User.class))).willReturn(user);
        given(passwordEncoder.encode(registrationDTO.getPassword())).willReturn("passwordTest");
        given(roleRepository.findByName(anyString())).willReturn(new Role(Roles.USER.name()));

        System.out.println(passwordEncoder.encode(registrationDTO.getPassword()));
        User user1 = userService.save(registrationDTO);

        assertEquals(user1.getName(), "nameTest");
        assertEquals(user1.getEmail(), "emailTest");
        assertEquals(user1.getPassword(), "passwordTest");
    }

    @Test
    void deleteUserTest() {
        userRepository.deleteById(1);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1);
    }
}