# 📚 Library API – Project Java (Spring Boot & PostgreSQL)
🇺🇸 English | 🇧🇷 [Português](README.pt-BR.md)
### About the Project

This project simulates the complete management of a digital bookstore, including:

- Book and multiple copy control
- Book rental with deadlines and late fee calculation
- Reservation system for unavailable books
- Authentication and authorization with JWT
- Secure password encryption with BCrypt

The project was developed as an advanced study, focusing on best practices in architecture, security, and data persistence.

### Technologies and Tools
- Back-end: Java 17+, Spring Boot, Spring Security
- Persistence: JPA + Hibernate + PostgreSQL
- Security: JWT, BCrypt, Roles and permissions
- Build and dependency management: Maven
- Design principles: Controller / Service / Repository / DTOs

### Architecture and Best Practices
The system follows a layered architecture, ensuring separation of responsibilities and scalability:

- Controller Layer – RESTful API, request validation, exposes endpoints.
- Service Layer – Contains business rules, rental logic, reservations, and fee calculations.
- Repository Layer (JPA/Hibernate) – Access to PostgreSQL, entity mapping, and complex queries.
- Security Layer – JWT authentication, role-based requirement, secure password hashing.

## Applied Patterns and Best Practices:

- Centralized exception handling
- DTOs to separate data model from front-end
- Use of Optional and Streams for secure data manipulation
- Input validations with Bean Validation

### Main Features
- CRUD for books and copies
- Book rental with deadline and fine calculation
- Reservations when stock runs out
- User registration and authentication with roles (ADMIN/USER)
- Advanced security: JWT + password encryption

### Possible improvements

- Notifications for upcoming or overdue returns.
- Detailed loan history per user.
- Automatic notification when a reserved book becomes available.
- Improved review interface with star ratings and more visible comments.
- Internationalization to support multiple languages.
  
----
 
 ## Class Diagram

<img width="5217" height="7080" alt="Library User Management-2026-03-21-094514" src="https://github.com/user-attachments/assets/9d5fc097-189e-443e-9f9c-ec667e2d4c02" />

