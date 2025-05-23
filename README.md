# 🛒 RedKart – Spring Boot Shopping Cart Web App

RedKart is a Spring Boot-based e-commerce shopping cart web application built with Java and Spring ecosystem tools. It features user authentication, product browsing, a session-based shopping cart, and a simple in-memory database.

---

## 🎯 Features

- Product catalog with listing
- User login and registration
- Shopping cart (session-based)
- Transactional checkout
- H2 in-memory database
- Thymeleaf server-rendered views

---

## 🧰 Tech Stack

- **Backend:** Java 17, Spring Boot, Spring MVC, Spring Security, Spring Data JPA
- **Frontend:** Thymeleaf
- **Database:** H2 (in-memory)
- **Build Tool:** Maven

---

## 📁 Project Structure

```
RedKart/
├── src/
│   ├── main/
│   │   ├── java/com/redkart/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── ShoppingCartApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── home.html
│   │       └── application.properties
├── pom.xml
└── README.md
```

---

## ▶️ How to Run

1. Clone the project and navigate into it:

```bash
git clone <repo-url>
cd RedKart
```

2. Run the application:

```bash
./mvnw spring-boot:run
```

3. Open in your browser:

[http://localhost:8090](http://localhost:8090)

---

## 🗃 Database Console

You can access the H2 console at:

[http://localhost:8090/h2-console](http://localhost:8090/h2-console)

JDBC URL: `jdbc:h2:mem:redkartdb`

---

## 📌 Configuration

All configuration is located in:

**`src/main/resources/application.properties`**

You can update:
- Server port
- Database URL and credentials
- Enable/disable H2 console

---

## 📜 License

This project is licensed for learning and personal use.