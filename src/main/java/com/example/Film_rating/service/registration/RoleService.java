package com.example.Film_rating.service.registration;

import com.example.Film_rating.entity.Roles;
import com.example.Film_rating.entity.registration.Role;

public interface RoleService {
    public Role findUserByName(Roles roles);
}
