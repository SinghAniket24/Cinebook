Cinebook – Microservices Cinema Booking System

Cinebook is a microservices-based cinema management and ticket booking system built using Spring Boot and Java. It simulates a real-world movie booking platform where users can browse movies, select seats, complete bookings, and receive digital tickets.

The application is designed using a distributed microservices architecture, where each service handles a specific responsibility such as authentication, movie management, booking, and notifications. This improves scalability, maintainability, and system reliability.

Architecture Overview

The system consists of multiple independent microservices:

Discovery Server – Registers and manages all microservices

API Gateway – Handles routing and authentication

Movie Service – Manages movies, cinemas, seats, and users

Booking Service – Handles seat booking and booking records

Notification Service – Generates and sends ticket emails

Admin Server – Monitors all running services

These services communicate using REST APIs and messaging queues.

Tech Stack

Java 17, Spring Boot, Spring Cloud

Netflix Eureka, Spring Cloud Gateway, OpenFeign

MariaDB, MongoDB

RabbitMQ

JWT Authentication

Stripe API, Gmail SMTP

Purpose

This project demonstrates the implementation of a scalable microservices system with secure authentication, inter-service communication, asynchronous processing, and distributed data management.
