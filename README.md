# My-market


My Market is a project done for our thesis, our intention was to centralize in one place all the offers from small/medium markets which can't afford an own website. Also we took this opportunity to learn Cloud Computing using AWS, and focus deeply in Docker and Microservices.

In this repository you will find the backend code of our application, attached with the technologies used and instructions to run the microservices.

## Stack: :computer:
- Java 11
- Spring Boot 2.4.2
- Maven
- Docker
- MySQL 8
- AWS X-Ray
- Swagger

## How to Use :pencil:
- You will need to create a local database in MySQL with the name you wish, then run the queries from schema.sql
- In application.properties you have the Environment Variables DB_URL, DB_USERNAME and DB_PASSWORD
- Configure the Environment Variables in your IDE and then run the application.
- As default vaules you can use:
  - DB_URL = jdbc:mysql://localhost:3306/exampleDBName
  - DB_USERNAME = myMarketUser
  - DB_PASSWORD = 9f1e2b88-fa7f-418f-9406-799d52abb3b3
- Use Swagger to access all the endpoints : https://app.swaggerhub.com/apis/EnzoRoselli/MyMarket/1.0.0
---
By Facundo Mateu, Enzo Roselli, and Matias Nicoletti
