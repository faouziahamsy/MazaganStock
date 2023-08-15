# MazaganStock
# Stock Management Application

This is a stock management application developed using Spring Boot for the backend and Angular for the frontend.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## About

The Stock Management Application is designed to help manage inventory and stock data efficiently. It includes both backend and frontend components to provide a seamless user experience.

## Features

- User authentication and authorization.
- Adding, updating, and deleting equipments.
- Adding, updating, and deleting articles.
- Adding, updating, and deleting categories.
- Tracking stock levels and availability.
- Generating reports and summaries.
- Interactive dashboard for data visualization.

## Technologies Used

- Backend: Spring Boot, Spring Security, JWT authentication
- Frontend: Angular
- Database: MySQL
- API Documentation: Swagger (Springdoc)
- Package Management: Maven (Backend), npm (Frontend)

## Getting Started

To get started with this application, follow the installation steps provided below.

## Installation

1. Clone the repository:
   git clone https://github.com/yourusername/app-stock-management.git
2. Configure backend:
- Install Java and Maven.
- Set up your database credentials and connection details in src/main/resources/application.properties.
- Run the Spring Boot backend by executing:
      cd backend
      mvn spring-boot:run
3. Access the application:
Backend API: http://localhost:8084
Frontend: http://localhost:4200

## Usage
Navigate to the frontend URL (http://localhost:4200) in your browser.
Use the provided authentication system to log in.
Explore and interact with the stock management features.

## Contributing
Contributions are welcome! If you find any issues or want to enhance the application, feel free to submit a pull request.

## License
This project is licensed under the MIT License.
