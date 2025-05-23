package com.redkart.controller;

import com.redkart.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductRepository productRepository;

    // Injecting the product repository via constructor
    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Fetch all products from the DB
        model.addAttribute("products", productRepository.findAll());
        return "home"; // returns home.html
    }
}
