package com.hb.cda.thymeleafproject.controller;

import com.hb.cda.thymeleafproject.dto.LoginFormDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hb.cda.thymeleafproject.dto.UserRegisterDTO;
import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.UserRepository;

import jakarta.validation.Valid;

import java.util.Optional;

// Handles user authentication and registration requests.
@Controller
public class AuthController {

    private UserRepository repo;
    private PasswordEncoder encoder;

    // Constructor for dependency injection.
    public AuthController(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // Displays the registration form.
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("dto", new UserRegisterDTO(null, null, null));
        return "register-form";
    }

    // Processes the registration form.
    @PostMapping("/register")
    public String processRegister(@Valid UserRegisterDTO dto, BindingResult bindingResult, Model model) {
        // Check if the username is already taken.
        if(repo.findByUsername(dto.getUsername()).isPresent()) {
            bindingResult.addError(new FieldError("dto", "username", "already taken"));
        }
        // Check if the passwords match.
        if(!dto.getPassword().equals(dto.getRepeatPassword())) {
            bindingResult.addError(new FieldError("dto", "repeatPassword", "does not match with password"));

        }
        // If there are errors, return to the registration form.
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors",bindingResult.getFieldErrors());
            model.addAttribute("dto", new UserRegisterDTO(null, null, null));
            return "register-form";
        }
        // Create a new user and save it to the database.
        User user = new User(
            dto.getUsername(),
            encoder.encode(dto.getPassword()), 
            "ROLE_USER");
        repo.save(user);

        return "redirect:/login";
    }

    // Displays the login form.
    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginFormDTO", new LoginFormDTO());
        return "login";
    }

    // Processes the login form.
    @PostMapping("/login")
    public String loginUser(@Valid LoginFormDTO loginFormDTO, Model model) {
        model.addAttribute("loginFormDTO", loginFormDTO);
        Optional<User> userOptional = repo.findByUsername(loginFormDTO.getUsername());
        // Check if the user exists and the password is correct.
        if (userOptional.isEmpty() || !encoder.matches(loginFormDTO.getPassword(), userOptional.get().getPassword())) {
            model.addAttribute("error", "Invalid login and/or password");
            return "login";
        }
        return "redirect:/";
    }

}
