package com.example.Film_rating.repository;

import com.example.Film_rating.entity.registration.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    User findByEmail(String name);
}
