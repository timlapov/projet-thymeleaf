package com.hb.cda.thymeleafproject.repository;

import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            userRepository.saveAll(List.of(
            new User("tim", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("jean", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("charlie", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("diana", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("eve", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("frank", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("grace", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("henry", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("ivy", passwordEncoder.encode("1234"), "ROLE_USER"),
            new User("jack", passwordEncoder.encode("1234"), "ROLE_USER")
            ));
        }

            if (productRepository.count() == 0) {
                productRepository.saveAll(List.of(
                        new Product("iPhone 14 Pro", 999.99, "The latest and greatest from Apple.", 50),
                        new Product("Samsung Galaxy S23 Ultra", 1199.99, "The ultimate Android experience.", 40),
                        new Product("Google Pixel 7 Pro", 899.00, "Pure Android with a stunning camera.", 60),
                        new Product("OnePlus 11", 699.00, "Flagship killer with fast charging.", 30),
                        new Product("Xiaomi 13 Pro", 950.50, "Leica co-engineered camera system.", 25),
                        new Product("iPhone 14", 799.00, "A fantastic phone for everyone.", 100),
                        new Product("Samsung Galaxy Z Fold 4", 1799.99, "The future of foldable phones.", 15),
                        new Product("Google Pixel 6a", 449.00, "The best budget phone with a great camera.", 120),
                        new Product("Asus ROG Phone 7", 999.00, "The ultimate gaming phone.", 20),
                        new Product("Sony Xperia 1 V", 1399.00, "For the professional photographer.", 10),
                        new Product("Motorola Edge+ (2023)", 799.99, "A sleek design with a clean Android experience.", 35),
                        new Product("Nothing Phone (1)", 499.00, "A unique design with the Glyph Interface.", 55),
                        new Product("Samsung Galaxy A54", 449.99, "A great mid-range option from Samsung.", 80),
                        new Product("iPhone SE (2022)", 429.00, "Classic design with modern power.", 90),
                        new Product("Oppo Find X6 Pro", 1099.00, "A powerful camera phone not widely available.", 5)
                ));
            }

    }
}

