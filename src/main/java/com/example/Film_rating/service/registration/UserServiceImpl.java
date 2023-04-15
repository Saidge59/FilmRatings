package com.example.Film_rating.service.registration;

import com.example.Film_rating.dto.UserRegistrationDTO;
import com.example.Film_rating.entity.Roles;
import com.example.Film_rating.entity.registration.Role;
import com.example.Film_rating.entity.registration.User;
import com.example.Film_rating.repository.RoleRepository;
import com.example.Film_rating.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User save(UserRegistrationDTO registrationDTO) {

        Role role = roleRepository.findByName(Roles.USER.name());
        if(role == null) {
            role = new Role(Roles.USER.name());
        }

        User user = new User(
                registrationDTO.getName(),
                registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                Arrays.asList(role));

        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean checkNameUser(UserRegistrationDTO registrationDTO) {
        User user = userRepository.findByName(registrationDTO.getName());
        return user != null;
    }

    @Override
    public boolean checkEmailUser(UserRegistrationDTO registrationDTO) {
        User user = userRepository.findByEmail(registrationDTO.getEmail());
        return user != null;
    }
}
