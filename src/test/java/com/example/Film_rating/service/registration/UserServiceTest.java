package com.example.Film_rating.service.registration;

import com.example.Film_rating.dto.UserRegistrationDTO;
import com.example.Film_rating.entity.Roles;
import com.example.Film_rating.entity.registration.Role;
import com.example.Film_rating.entity.registration.User;
import com.example.Film_rating.repository.RoleRepository;
import com.example.Film_rating.repository.UserRepository;
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