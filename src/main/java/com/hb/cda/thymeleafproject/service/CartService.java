package com.hb.cda.thymeleafproject.service;

import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

// Manages the shopping cart.
@Service
@SessionScope
public class CartService {

    // A map to store the products in the cart and their quantities.
    private Map<Product, Integer> productsInCart = new HashMap<>();

    private ProductRepository productRepository;

    // Constructor for dependency injection.
    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Adds a product to the cart or increments its quantity if it already exists.
    public void addProduct(Product product) {
        productsInCart.put(product, productsInCart.getOrDefault(product, 0) + 1);
    }

    // Removes a product from the cart or decrements its quantity.
    public void removeProduct(Product product) {
        if (productsInCart.containsKey(product)) {
            int quantity = productsInCart.get(product);
            if (quantity > 1) {
                productsInCart.put(product, quantity - 1);
            } else {
                productsInCart.remove(product);
            }
        }
    }

    // Clears all items from the cart.
    public void clearCart() {
        productsInCart.clear();
    }

    // Returns the items in the cart.
    public Map<Product, Integer> getCartItems() {
        return productsInCart;
    }

    // Calculates the total price of all items in the cart.
    public Double getTotalPrice() {
        return productsInCart.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    // Validates the cart, checking if the products are in stock.
    // If they are, it updates the stock and clears the cart.
    public boolean validateCart() {
        for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (product.getStock() < quantity) {
                return false;
            }
        }
        for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
        }
        clearCart();
        return true;
    }

}
