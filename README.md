Auth Service (JWT Authentication):

This project is a Spring Boot–based Authentication Service that provides secure user authentication and authorization using JWT (JSON Web Tokens). It is designed as a standalone microservice and follows clean architecture and interview-ready best practices.

 Features:

User authentication with JWT access tokens

Refresh token support

Spring Security configuration

Role-based authorization

Exception-safe authentication flow

Docker & Docker Compose support

Gradle-based build


 Tech Stack:

Java

Spring Boot

Spring Security

JWT (JSON Web Tokens)



Authentication Flow:

User sends login request with credentials

Server validates user

JWT access token is generated

Refresh token is issued

Client uses JWT for secured API access

How to Run-

Using Gradle-

gradlew bootRun

Using Docker-

docker-compose up --build

