# 🛒 RedKart – Spring Boot Shopping Cart Web App

RedKart is a Spring Boot-based e-commerce shopping cart web application built with Java and Spring ecosystem tools. It features user authentication, product browsing, a session-based shopping cart, and a simple in-memory database.

---

## 🎯 Features

* 🛍 Product catalog with listing
* 🔐 User login and registration
* 🧺 Shopping cart (session-based)
* 💳 Transactional checkout
* 🧠 H2 in-memory database with GUI console
* 🌐 Thymeleaf server-rendered HTML views

---

## 🧰 Tech Stack

| Layer      | Technology                       |
| ---------- | -------------------------------- |
| Backend    | Java 17, Spring Boot, Spring MVC |
| Security   | Spring Security                  |
| Database   | H2 (in-memory), Spring Data JPA  |
| Frontend   | Thymeleaf templating engine      |
| Build Tool | Maven                            |

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
│   │   │   ├── service/
│   │   │   ├── config/
│   │   │   └── RedKartApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── home.html
│   │       │   ├── login.html
│   │       │   └── register.html
│   │       ├── static/css/
│   │       │   └── main.css
│   │       └── application.properties
├── pom.xml
└── README.md
```

---

## ▶️ How to Run

Clone the project and navigate into the directory:

```bash
git clone <your-repo-url>
cd RedKart
```

Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

Open your browser and visit:

```
http://localhost:8090
```

---

## 🗃 H2 Database Console

RedKart uses an in-memory H2 database for development.

You can access the console at:

```
http://localhost:8090/h2-console
```

**JDBC URL:** `jdbc:h2:mem:redkartdb`

> No username/password is required (default: `sa`)

---

## 📦 Implemented Functionality

* Spring Boot application runs on port `8090`
* Product list displayed using Thymeleaf
* Product data seeded automatically into DB on startup
* User registration with password encryption
* Login and logout functionality using Spring Security
* Secure authentication with custom user details service
* Dynamic HTML views rendered with Thymeleaf
* Access to H2 console for development and debugging

---

## 📌 Configuration

You can configure all application properties in:

📄 `src/main/resources/application.properties`

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

You can change the port, enable/disable H2, modify database settings, etc.

---

## 📜 License

This project is licensed for educational and personal use under the [MIT License](LICENSE).
