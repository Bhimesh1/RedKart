package com.redkart.config;

import com.redkart.model.Product;
import com.redkart.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    // This bean runs once when the app starts
    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            // Only add products if DB is empty
            if (productRepository.count() == 0) {
                productRepository.save(new Product("Red T-Shirt", "Comfortable red t-shirt", 19.99, 50));
                productRepository.save(new Product("Blue Jeans", "Classic fit jeans", 39.99, 30));
                productRepository.save(new Product("Sneakers", "White running shoes", 59.99, 20));
            }
        };
    }
}
