package com.hb.cda.thymeleafproject.controller;

import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import com.hb.cda.thymeleafproject.repository.UserRepository;
import com.hb.cda.thymeleafproject.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Handles requests related to the shopping cart.
@Controller
@RequestMapping("/cart")
public class CartController {

    CartService cartService;
    ProductRepository productRepository;

    // Constructor for dependency injection.
    public CartController(CartService cartService, ProductRepository productRepository) {
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    // Displays the cart page with all the items, total price, and validation status.
    @GetMapping("")
    public String showCart(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) Boolean validate
    ) {
        
        model.addAttribute("validate", validate);
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        model.addAttribute("username", user.getUsername());
        return "cart";
    }

    // Adds a product to the cart.
    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable String id) {
        if (productRepository.findById(id).isPresent()) {
            cartService.addProduct(productRepository.findById(id).get());
        }
        return "redirect:/";
    }

    // Removes a product from the cart.
    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable String id) {
        productRepository.findById(id).ifPresent(product -> cartService.removeProduct(product));
        return "redirect:/cart";
    }

    // Validates the cart and redirects with a success or failure message.
    @PostMapping("/validate")
    public String validateCart() {

        if (cartService.validateCart()) {
            return "redirect:/cart?validate=true";
        } else {
            return "redirect:/cart?validate=false";
        }
    }
}
