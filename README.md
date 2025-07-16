# Thymeleaf Shopping Cart Project

This project was developed as part of a training exercise to demonstrate proficiency in building a web application using Spring Boot and Thymeleaf. The core objective was to implement a fully functional shopping cart feature, starting from a basic project structure.

## Assignment Overview

The initial task was to build upon a starter project to create a complete shopping cart management system. The required features were:

*   **Database Fixtures**: Create a mechanism (like a `CommandLineRunner`) to populate the database with sample products and users on startup.
*   **Product Display**: Show a paginated list of products, each with an "Add to Cart" button.
*   **Cart Service**: Implement a session-scoped `CartService` to handle cart logic:
    *   Adding products.
    *   Removing products.
    *   Calculating the total price.
    *   Validating the cart (which empties it and updates product stock).
*   **Cart Page**: Create a dedicated `/cart` page to display and manage the cart's contents.

### Bonus Features

*   **Manage Quantities**: Handle product quantities within the cart (e.g., using a `Map` or a dedicated object).
*   **Persistent Cart**: Make the cart persistent for logged-in users across sessions.

## ✅ Features Implemented

- [✅] Database population with sample users and products.
- [✅] Paginated product list on the home page.
- [✅] Display of product stock levels.
- [✅] User registration and login functionality.
- [✅] A fully functional shopping cart that tracks items and quantities.
- [✅] A dedicated cart page showing items, quantities, price per item, and total price.
- [✅] Ability to add and remove items from the cart.
- [✅] Cart validation that checks stock, updates inventory, and clears the cart.
- [✅] User feedback messages for successful or failed cart validation.
- [🚧] **In Progress**: Persistent cart for logged-in users. The database schema and entities have been created, and the service layer refactoring is the next step.

## 🛠️ Tech Stack

*   **Backend**: Java, Spring Boot
*   **Frameworks**: Spring Web, Spring Security, Spring Data JPA
*   **Frontend**: Thymeleaf
*   **Build Tool**: Maven

## 🚀 Getting Started

### Prerequisites

*   JDK 21 or later
*   Apache Maven

### Database Configuration

To connect to a database, you will need to configure the `application.properties` file in `src/main/resources`.

For a MySQL database, for example, you would add the following properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Running the Application

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/timlapov/projet-thymeleaf.git
    cd projet-thymeleaf
    ```

2.  **Run the application using Maven:**
    ```bash
    mvn spring-boot:run
    ```
    The application will start and automatically create and populate the in-memory H2 database.

3.  **Access the application:**
    Open your web browser and navigate to `http://localhost:8081`.

## ⚙️ How It Works

*   **Home Page**: The main page (`/`) displays a paginated list of all available products, along with their price and current stock.
*   **User Authentication**: You can register for a new account or log in with an existing one. The application uses Spring Security for authentication.
*   **Shopping Cart**:
    *   From the home page, you can add any product to your cart.
    *   The `/cart` page provides a detailed view of your shopping cart, including the quantity of each item, the subtotal for each item, and the final total price.
    *   You can remove items from the cart one by one.
*   **Checkout Process**:
    *   Clicking "Validate Cart" simulates the checkout.
    *   The system checks if there is enough stock for all items in the cart.
    *   If successful, the product stock is updated in the database, the cart is cleared, and a success message is displayed.
    *   If an item is out of stock, a warning message is shown.

## 🎓 Core Concepts Demonstrated

This project showcases a solid understanding of modern web application development with the Spring ecosystem:

*   **Spring MVC**: Clear separation of concerns using Controllers, Services, and Repositories.
*   **Dependency Injection**: Use of constructor injection throughout the application.
*   **Spring Data JPA**: Interaction with the database through repositories and entities (`@Entity`, `@OneToOne`, etc.).
*   **Thymeleaf**: Dynamic server-side rendering of web pages, including iteration, conditional logic, and expression handling.
*   **Spring Security**: Secure application access with form-based login, password encoding (`BCryptPasswordEncoder`), and user roles.
*   **Data Transfer Objects (DTOs)**: Use of DTOs for handling form submissions (registration and login) to decouple the web layer from the entity layer.
*   **Validation**: Server-side validation of user input using `@Valid` and `BindingResult`.
*   **Database Initialization**: Use of `CommandLineRunner` to ensure the application has seed data on startup.