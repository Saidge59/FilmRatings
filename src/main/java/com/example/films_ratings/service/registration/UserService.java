package com.example.films_ratings.service.registration;

import com.example.films_ratings.dto.UserRegistrationDTO;
import com.example.films_ratings.entity.registration.User;

public interface UserService {
    public User save(UserRegistrationDTO registrationDTO);
    public User save(User user);
    public void delete(User user);

    public User findUserByName(String name);
    public User findByEmail(String name);
}
