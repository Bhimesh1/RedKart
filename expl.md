# 📘 RedKart – Explanation & Phase Logs

This file documents the explanation of each phase in the **RedKart** Spring Boot e-commerce project. Each phase is broken down step-by-step for learning purposes.

---

## 🚀 Project Motivation

The purpose of this project is to build a **realistic, learning-focused e-commerce application** using the Spring ecosystem. The app will be kept simple at first and gradually expanded.

### 🔧 Tech Stack

- Java 17+
- Spring Boot
- Spring MVC + Thymeleaf
- Spring Data JPA
- Spring Security
- H2 In-Memory Database
- Docker (eventually)

---

## 🔄 Project Flow (Phase Plan)

| Phase | Description |
|-------|-------------|
| Phase 1 | Setup project and run Spring Boot app |
| Phase 2 | Create home page (controller + Thymeleaf view) |
| Phase 3 | Product model, repository, and product list |
| Phase 4 | Shopping cart (session-based) |
| Phase 5 | User login/registration (auth) |
| Phase 6 | Checkout flow |
| Phase 7 | Admin panel for product management |
| Phase 8 | Dockerize and deploy |

---

# ✅ Phase 1: Project Setup & App Run

### 1. Project Initialization

Used [Spring Initializr](https://start.spring.io/) with:

- Maven
- Java 17+
- Dependencies:
    - Spring Web
    - Thymeleaf
    - Spring Security
    - Spring Data JPA
    - Spring Data REST
    - H2 Database

Project created with the base package: `com.redkart`.

### 2. Configuration Setup

**File:** `src/main/resources/application.properties`

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

This sets up:

- An in-memory **H2** database
- Console access at `/h2-console`
- Port `8090`
- Auto DDL and SQL logs for development

## 🚀 Run the App

```bash
./mvnw spring-boot:run
```

Navigating to [http://localhost:8090](http://localhost:8090) shows the **Spring Security login screen**, confirming everything is working.

### ✅ Phase 1 Summary

| Item                      | Status |
|---------------------------|--------|
| Spring Boot Setup         | ✅     |
| Port 8090 Configured      | ✅     |
| H2 In-Memory DB Setup     | ✅     |
| Thymeleaf Ready           | ✅     |
| App Runs with Login Prompt| ✅     |

---

# ✅ Phase 2: Home Page Setup with Thymeleaf

## 1. Created Home Controller
**File:** `HomeController.java`

```java
package com.redkart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
```

✅ This controller maps the `/` route to a view called `home.html`.

---

## 2. Created Thymeleaf Template
**File:** `src/main/resources/templates/home.html`

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

✅ This file is rendered via **Thymeleaf** when a user visits `/`.

---

## 3. Disabled Spring Security Temporarily
**File:** `SecurityConfig.java`

```java
package com.redkart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

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

✅ This disables the **login screen** so we can access the home page freely during development.

---

## 4. Rerun and Verify

```bash
./mvnw spring-boot:run
```

Visit [http://localhost:8090](http://localhost:8090) and see the **custom home page** instead of the login screen.

---

## ✅ Phase 2 Summary

| Item                           | Status |
|--------------------------------|--------|
| Controller Created             | ✅     |
| Home Page View Rendered        | ✅     |
| Spring Security Disabled (temp)| ✅     |
| Page Loads Without Login       | ✅     |
















