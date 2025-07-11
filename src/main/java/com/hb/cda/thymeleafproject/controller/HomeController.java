package com.hb.cda.thymeleafproject.controller;

import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import com.hb.cda.thymeleafproject.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    UserRepository userRepository;
    ProductRepository productRepository;

    public HomeController(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String displayHome(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        if (user == null) {
            return "home";
        }

        model.addAttribute("username", user.getUsername());

        return "home";
    }
}
