package com.example.Film_rating.service.registration;

import com.example.Film_rating.entity.Roles;
import com.example.Film_rating.entity.registration.Role;
import com.example.Film_rating.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
