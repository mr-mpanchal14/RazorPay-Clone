# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot 4.1.1 REST API integrating with Razorpay payment gateway. Uses Java 25, Spring Data JPA with PostgreSQL, and Lombok.

Base package: `com.codingshuttle.razorpay`

## Commands

```bash
# Build
./mvnw clean package

# Run application
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=RazorpayApplicationTests

# Run a single test method
./mvnw test -Dtest=RazorpayApplicationTests#contextLoads
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## Configuration

Database and any Razorpay API credentials must be added to `src/main/resources/application.yaml`. The current file only sets the application name — before running, add:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/<db_name>
    username: <user>
    password: <password>
  jpa:
    hibernate:
      ddl-auto: update
```

Razorpay credentials (key_id, key_secret) should be injected via environment variables or a separate `application-local.yaml` (excluded from git via `.gitignore`).

## Architecture

The project is at initial scaffolding stage — only the entry point class exists. As features are added, follow standard Spring Boot layering:

- `controller/` — REST controllers (`@RestController`)
- `service/` — Business logic and Razorpay SDK calls
- `repository/` — Spring Data JPA repositories
- `entity/` — JPA entities (`@Entity`)
- `dto/` — Request/response DTOs (use Lombok `@Data` / `@Builder`)

## Key Dependencies

- **Spring Web MVC** — servlet-based REST API
- **Spring Data JPA** — database access via Hibernate
- **PostgreSQL** — runtime JDBC driver
- **Lombok** — annotation-based boilerplate reduction (annotation processor is wired in `pom.xml`)
- Test starters: `spring-boot-starter-data-jpa-test` and `spring-boot-starter-webmvc-test`
