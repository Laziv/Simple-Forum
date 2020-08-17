package ua.lviv.lgs.nl.simpleforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.lgs.nl.simpleforum.dto.UserDTO;
import ua.lviv.lgs.nl.simpleforum.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthController {
    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(Principal principal) {
        return principal == null ? "login" : "redirect:/";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("user", new UserDTO());
            return "registration";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (!userDTO.getPassword().equals(userDTO.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "user", "Passwords are different");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.createUser(userDTO);
        return "redirect:/login";
    }

}
