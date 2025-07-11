package com.hb.cda.thymeleafproject.controller;

import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import com.hb.cda.thymeleafproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            @RequestParam(defaultValue = "0") int pageNumber,
            Model model
    ) {
        int pageSize = 5;
        if (pageNumber < 0) {
            pageNumber = 0;
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Product> products = productRepository.findAllByOrderByPriceDesc(pageable);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", products.getTotalPages());

        if (user == null) {
            return "home";
        }

        model.addAttribute("username", user.getUsername());

        return "home";
    }
}
