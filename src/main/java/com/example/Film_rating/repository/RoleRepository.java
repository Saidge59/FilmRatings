package com.example.Film_rating.repository;

import com.example.Film_rating.entity.registration.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
}
