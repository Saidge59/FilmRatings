package com.example.films_ratings.service.registration;

import com.example.films_ratings.entity.Roles;
import com.example.films_ratings.entity.registration.Role;
import com.example.films_ratings.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findUserByName(Roles roles) {
        return roleRepository.findByName(roles.name());
    }
}
