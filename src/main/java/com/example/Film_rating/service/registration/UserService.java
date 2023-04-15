package com.example.Film_rating.service.registration;

import com.example.Film_rating.dto.UserRegistrationDTO;
import com.example.Film_rating.entity.registration.User;

public interface UserService {
    public User save(UserRegistrationDTO registrationDTO);
    public User save(User user);

    public User findUserByName(String name);
    public boolean checkNameUser(UserRegistrationDTO registrationDTO);
    public boolean checkEmailUser(UserRegistrationDTO registrationDTO);
}
