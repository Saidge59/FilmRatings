package com.example.Film_rating.controller;

import com.example.Film_rating.dto.UserRegistrationDTO;
import com.example.Film_rating.service.registration.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserSecurityController {

    private UserService userService;

    public UserSecurityController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/registration")
    public String showRegistrationMapping() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDTO registrationDTO,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (userService.checkNameUser(registrationDTO)) {
            redirectAttributes.addFlashAttribute("errorUsername", registrationDTO.getName());
            return "redirect:/registration";
        } else if (userService.checkEmailUser(registrationDTO)) {
            redirectAttributes.addFlashAttribute("errorEmail", registrationDTO.getEmail());
            return "redirect:/registration";
        }

        userService.save(registrationDTO);

        redirectAttributes.addFlashAttribute("success", "");
        return "redirect:/registration";
    }
}
