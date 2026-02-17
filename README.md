# Cinebook – Microservices Cinema Booking System

Cinebook is a microservices-based cinema management and ticket booking system built using Spring Boot and Java. It simulates a real-world movie booking platform where users can browse movies, select seats, complete bookings, and receive digital tickets.

The application follows a distributed microservices architecture, where each service is responsible for a specific functionality such as authentication, movie management, booking, and notifications. This makes the system scalable, modular, and easy to maintain.

---

## Architecture Overview

The system consists of the following microservices:

- **Discovery Server** – Registers and manages all microservices  
- **API Gateway** – Handles request routing and authentication  
- **Movie Service** – Manages movies, cinemas, seats, and user data  
- **Booking Service** – Handles seat booking and booking records  
- **Notification Service** – Generates PDF tickets and sends email notifications  
- **Admin Server** – Monitors service health and system status  

All services communicate using REST APIs and messaging queues.

---

## Tech Stack

- Java 17  
- Spring Boot  
- Spring Cloud (Eureka, Gateway, OpenFeign)  
- MariaDB and MongoDB  
- RabbitMQ  
- JWT Authentication  
- Stripe API  
- Gmail SMTP  

---

## Project Purpose

This project demonstrates the implementation of a real-world microservices architecture with secure authentication, distributed services, asynchronous communication, and scalable system design.
