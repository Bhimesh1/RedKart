# 📘 RedKart – Explanation & Phase Logs

This file documents the explanation of each phase in the RedKart Spring Boot e-commerce project. Each phase is broken down step-by-step for learning purposes.

---

## 🚀 Project Motivation

The purpose of this project is to build a realistic, learning-focused e-commerce application using the Spring ecosystem. The app will be kept simple at first and gradually expanded.

---

## 🔧 Tech Stack

* Java 17+
* Spring Boot
* Spring MVC + Thymeleaf
* Spring Data JPA
* Spring Security
* H2 In-Memory Database
* Docker (eventually)

---

## 🔄 Project Flow (Phase Plan)

| Phase   | Description                               |
| ------- | ----------------------------------------- |
| Phase 1 | Setup project and run Spring Boot app     |
| Phase 2 | Create home page (controller + Thymeleaf) |
| Phase 3 | Product model, repository, and listing    |
| Phase 4 | User login/registration (auth)            |
| Phase 5 | Shopping cart (session-based)             |
| Phase 6 | Checkout flow                             |
| Phase 7 | Admin panel for product management        |
| Phase 8 | Dockerize and deploy                      |

---

## ✅ Phase 1: Project Setup & App Run

### 1. Project Initialization

**Used Spring Initializr with:**

* Maven
* Java 17+
* Dependencies:

  * Spring Web
  * Thymeleaf
  * Spring Security
  * Spring Data JPA
  * Spring Data REST
  * H2 Database

**Base package:** `com.redkart`

### 2. Configuration Setup

📄 File: `src/main/resources/application.properties`

```properties
server.port=8090
spring.datasource.url=jdbc:h2:mem:redkartdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 🔧 Purpose of Config

* Set app port to 8090
* Enable in-memory H2 DB
* Enable SQL logging
* Auto-create DB schema
* Allow H2 console access

### ▶️ Run the App

```bash
./mvnw spring-boot:run
```

Access the app: [http://localhost:8090](http://localhost:8090)

✅ Default Spring login screen appears, app runs successfully.

### ✅ Phase 1 Summary

| Item                  | Status |
| --------------------- | ------ |
| Spring Boot Setup     | ✅      |
| Port 8090 Configured  | ✅      |
| H2 In-Memory DB Setup | ✅      |
| Thymeleaf Ready       | ✅      |
| App Runs with Login   | ✅      |

---

## ✅ Phase 2: Home Page Setup with Thymeleaf

### 1. Created Home Controller

📄 File: `HomeController.java`

```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
```

✅ Maps `/` to return `home.html`

### 2. Created Home View Template

📄 File: `src/main/resources/templates/home.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>RedKart Home</title>
</head>
<body>
    <h1>🛒 Welcome to RedKart</h1>
    <p>Start shopping your favorite products now.</p>
</body>
</html>
```

✅ Displays welcome page

### 3. Disabled Spring Security Temporarily

📄 File: `SecurityConfig.java`

```java
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .formLogin().disable();
        return http.build();
    }
}
```

✅ Let home page load without login

### 4. Rerun and Verify

```bash
./mvnw spring-boot:run
```

Visit [http://localhost:8090](http://localhost:8090) → Should display `home.html`

### ✅ Phase 2 Summary

| Item                     | Status |
| ------------------------ | ------ |
| Controller Created       | ✅      |
| Home Page View Rendered  | ✅      |
| Spring Security Disabled | ✅      |
| Page Loads Without Login | ✅      |

---



## 📘 Phase 3 Explanation – Product Entity, Repository & Listing

This document explains everything done in Phase 3 of the RedKart project — defining the product model, seeding data, and rendering the product list in a Thymeleaf view.

### ✅ Step 1: Created Product Entity

📄 File: `Product.java`

```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock;

    // Constructor, Getters, Setters...
}
```

✅ This maps to a Product table in the H2 database.
✅ Fields become columns in the DB.

### ✅ Step 2: Created Product Repository

📄 File: `ProductRepository.java`

```java
public interface ProductRepository extends JpaRepository<Product, Long> {}
```

✅ Gives us access to common CRUD methods like `findAll()`, `save()` etc.
✅ No SQL or implementation needed — Spring Data JPA handles it.

### ✅ Step 3: Seeded Product Data

📄 File: `DataInitializer.java`

```java
@Bean
public CommandLineRunner loadData(ProductRepository productRepository) {
    return args -> {
        if (productRepository.count() == 0) {
            productRepository.save(new Product("T-Shirt", "Red cotton t-shirt", 499.99, 10));
            productRepository.save(new Product("Shoes", "Running shoes", 1299.50, 5));
        }
    };
}
```

✅ Automatically runs at app startup.
✅ Adds products only if DB is empty.
✅ Verifiable via `/h2-console`

### ✅ Step 4: Displayed Products on Home Page

📄 Updated Controller: `HomeController.java`

```java
@GetMapping("/")
public String home(Model model) {
    model.addAttribute("products", productRepository.findAll());
    return "home";
}
```

📄 Updated View: `home.html`

```html
<div th:each="product : ${products}">
    <h2 th:text="${product.name}">Product Name</h2>
    <p th:text="${product.description}">Product description</p>
    <p th:text="${product.price}">Product price</p>
</div>
```

✅ Dynamically loops through and displays products.
✅ Product data now flows from DB → Controller → UI.

### 🧪 Phase 3 Summary

| Task                       | Status |
| -------------------------- | ------ |
| Product entity created     | ✅      |
| Repository ready           | ✅      |
| Seeded startup data        | ✅      |
| Product listing view built | ✅      |

---


## ✅ Phase 4: User Registration and Login

### 1. Created User Entity

📄 File: `User.java`

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

    // Getters and setters
}
```

✅ Basic fields for storing user info.
✅ Password will be stored as encrypted hash.

### 2. User Repository

📄 File: `UserRepository.java`

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
```

✅ Allows fetching user by email for authentication.

### 3. Registration Controller

📄 File: `AuthController.java`

```java
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
```

✅ Handles GET/POST for `/register`
✅ Displays form and processes new user registration

### 4. Service Layer for Registration

📄 File: `UserService.java`

```java
public interface UserService {
    void registerUser(User user);
}
```

📄 File: `UserServiceImpl.java`

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
    }
}
```

✅ Encrypts password with `BCrypt`
✅ Saves user with role `USER`

### 5. Security Configuration

📄 File: `SecurityConfig.java`

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/", "/css/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/").permitAll())
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .headers(headers -> headers.frameOptions().disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
```

✅ Enables custom login + registration
✅ CSRF and H2 console adjustments

### 6. Custom UserDetailsService

📄 File: `CustomUserDetailsService.java`

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
```

✅ Authenticates user from DB
✅ Uses email for login (via username param)

### 🧪 Phase 4 Summary

| Task                         | Status |
| ---------------------------- | ------ |
| User registration form added | ✅      |
| User service + DB            | ✅      |
| Login config and security    | ✅      |
| H2 console access restored   | ✅      |

---

*Phase 5: Shopping cart coming next...*

