# Crypto Exchanges Dashboard Spring

## Project Overview

The `Crypto Exchanges Dashboard Spring` project is designed to create APIs for managing multiple cryptocurrency exchanges from a single dashboard. It enables streamlined access to different exchanges and helps manage trading operations, balances, and other exchange-related functions.

## Features

- **Unified Exchange Management**: Handle multiple crypto exchanges in one place.
- **API-Driven**: Provides RESTful APIs for managing various crypto exchange operations.
- **Secure and Scalable**: Built with Spring Boot to ensure security and scalability.
- **Modular Design**: Easily extendable to support additional exchanges in the future.

## Tech Stack

- **Backend Framework**: Spring Boot `3.3.3`
- **Java Version**: `22`
- **Database**: PostgreSQL (runtime dependency)
- **Security**: Spring Security
- **API Documentation**: Springdoc OpenAPI UI `2.5.0`
- **Build Tool**: Maven

## Dependencies

The project includes the following key dependencies:

- `spring-boot-starter-data-jpa`: To handle database operations using JPA.
- `spring-boot-starter-web`: For building RESTful web services.
- `spring-boot-starter-security`: To secure the application.
- `spring-boot-devtools`: To enhance development productivity.
- `postgresql`: PostgreSQL database driver.
- `lombok 1.18.34`: To reduce boilerplate code with annotations.
- `jaxb-api 2.3.1`: For XML binding support.
- `jjwt 0.9.1`: For handling JWT (JSON Web Tokens) in authentication.
- `springdoc-openapi-starter-webmvc-ui 2.5.0`: For generating API documentation.

## Getting Started

### Prerequisites

- **Java 22**
- **Maven 3.6+**
- **PostgreSQL** (or any supported database)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/crypto-exchanges-dashboard-spring.git


### Todo List

- Add XChange Library for interacting with different cryptocurrency exchanges.
- Create Accounts Overview APIs to manage account balances and related data.
- Manage Trades APIs for creating, viewing, and managing trades across exchanges.