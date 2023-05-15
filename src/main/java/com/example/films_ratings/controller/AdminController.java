package com.example.films_ratings.controller;

import com.example.films_ratings.dto.UserRegistrationDTO;
import com.example.films_ratings.entity.Roles;
import com.example.films_ratings.entity.registration.Role;
import com.example.films_ratings.entity.registration.User;
import com.example.films_ratings.service.registration.RoleService;
import com.example.films_ratings.service.registration.UserService;
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

    @GetMapping("/admin")
    public String pageAdmin(Model model) {

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();

        model.addAttribute("user", userRegistrationDTO);
        return "admin";
    }

    @PostMapping(value = "/admin", params = "save")
    public String main(@ModelAttribute("user") UserRegistrationDTO registrationDTO,
                       RedirectAttributes redirectAttributes) {

        User userByName = userService.findUserByName(registrationDTO.getName());

        if (userByName == null) {
            redirectAttributes.addFlashAttribute("errorUsername", registrationDTO.getName());
            return "redirect:/admin";
        } else {
            redirectAttributes.addFlashAttribute("successUsername", registrationDTO.getName());
        }

        List<Role> listRole = userByName.getRoles();

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

        userByName.setRoles(listRole);

        userService.save(userByName);

        return "redirect:/admin";
    }

    @PostMapping(value = "/admin", params = "delete")
    public String remove(@ModelAttribute("user") UserRegistrationDTO registrationDTO,
                       RedirectAttributes redirectAttributes) {

        User userByName = userService.findUserByName(registrationDTO.getName());

        if(userByName == null) {
            redirectAttributes.addFlashAttribute("errorUsername", registrationDTO.getName());
            return "redirect:/admin";
        } else
            redirectAttributes.addFlashAttribute("deleteUsername", registrationDTO.getName());

        userService.delete(userByName);
        return "redirect:/admin";
    }

}
