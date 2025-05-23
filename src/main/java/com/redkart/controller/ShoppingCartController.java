package com.redkart.controller;

import com.redkart.model.CartItem;
import com.redkart.model.Product;
import com.redkart.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ProductRepository productRepository;

    public ShoppingCartController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // View cart
    @GetMapping
    public String showCart(HttpSession session, Model model) {
        List<CartItem> cart = getCart(session);
        model.addAttribute("cartItems", cart);
        model.addAttribute("total", calculateTotal(cart));
        return "cart";
    }

    // Add item to cart
    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, HttpSession session) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return "redirect:/";

        List<CartItem> cart = getCart(session);

        // Check if item already exists in cart
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(productId)) {
                item.incrementQuantity();
                session.setAttribute("cart", cart);
                return "redirect:/";
            }
        }

        // Otherwise, add new item
        cart.add(new CartItem(product));
        session.setAttribute("cart", cart);
        return "redirect:/";
    }

    // Get or initialize cart
    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {
        Object cart = session.getAttribute("cart");
        if (cart == null) {
            List<CartItem> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            return newCart;
        }
        return (List<CartItem>) cart;
    }

    private double calculateTotal(List<CartItem> cart) {
        return cart.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
