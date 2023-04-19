package com.example.Film_rating.controller;

import com.example.Film_rating.dto.UserRegistrationDTO;
import com.example.Film_rating.entity.Roles;
import com.example.Film_rating.entity.registration.Role;
import com.example.Film_rating.entity.registration.User;
import com.example.Film_rating.service.registration.RoleService;
import com.example.Film_rating.service.registration.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/admin-page")
    public String pageAdmin(Model model) {

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();

        model.addAttribute("user", userRegistrationDTO);
        return "admin-page";
    }

    @PostMapping("/admin-page")
    public String main(@ModelAttribute("user") UserRegistrationDTO registrationDTO,
                       RedirectAttributes redirectAttributes) {

        if (!userService.checkNameUser(registrationDTO)) {
            redirectAttributes.addFlashAttribute("errorUsername", "");
            return "redirect:/admin-page";
        } else {
            redirectAttributes.addFlashAttribute("success", "");
        }

        User user_db = userService.findUserByName(registrationDTO.getName());

        List<Role> listRole = user_db.getRoles();

        Role role = roleService.findUserByName(Roles.USER);
        if(registrationDTO.isRoleUser() && !listRole.contains(role)) {
            listRole.add(roleService.findUserByName(Roles.USER));
        } else if(!registrationDTO.isRoleUser() && listRole.contains(role)){
            listRole.remove(roleService.findUserByName(Roles.USER));
        }

        role = roleService.findUserByName(Roles.ADMIN);
        if(registrationDTO.isRoleAdmin() && !listRole.contains(role)) {
            listRole.add(roleService.findUserByName(Roles.ADMIN));
        } else if(!registrationDTO.isRoleAdmin() && listRole.contains(role)){
            listRole.remove(roleService.findUserByName(Roles.ADMIN));
        }

        user_db.setRoles(listRole);
        System.out.println("user_db: " + user_db);

        userService.save(user_db);

        return "redirect:/admin-page";
    }

}
