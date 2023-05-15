package com.example.films_ratings.service.registration;

import com.example.films_ratings.entity.Roles;
import com.example.films_ratings.entity.registration.Role;

public interface RoleService {
    public Role findUserByName(Roles roles);
}
