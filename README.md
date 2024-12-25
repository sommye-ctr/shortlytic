
# Shortlytic

Shortlytic is a URL Shortener API designed to convert long URLs into shortened, more manageable links.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Database Configuration](#database-configuration)
- [Usage](#usage)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [Troubleshooting](#troubleshooting)
- [Contributors](#contributors)
- [License](#license)

## Introduction

Shortlytic provides a simple API for shortening long URLs, making them easier to share and manage.

## Features

- Shorten long URLs into concise links.
- Retrieve original URLs from shortened links.
- Track usage statistics for shortened URLs.

## Installation

To set up Shortlytic locally:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/sommye-ctr/shortlytic.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd shortlytic
   ```

3. **Build the project using Maven:**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

## Database Configuration

Shortlytic uses PostgreSQL as the primary database. Follow these steps to set up the database:

1. **Install PostgreSQL**  
   Install PostgreSQL on your system if not already installed. You can download it from the [official PostgreSQL website](https://www.postgresql.org/download/).

2. **Create a Database**  
   Use the following SQL queries to set up the required database schema and functions for Shortlytic:

   ```sql
   CREATE TABLE users (
       user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
       username VARCHAR(255) NOT NULL UNIQUE,
       email VARCHAR(255) NOT NULL UNIQUE,
       password_hash VARCHAR(255) NOT NULL,
       created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
   );

   CREATE TABLE urls (
       id SERIAL PRIMARY KEY,
       user_id UUID REFERENCES users(user_id) ON DELETE CASCADE,
       url TEXT NOT NULL,
       short_code VARCHAR(255) NOT NULL UNIQUE,
       created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
       expiry_at TIMESTAMPTZ,
       password_protected BOOLEAN DEFAULT FALSE,
       password VARCHAR(255)
   );

   CREATE TABLE analytics (
       id SERIAL PRIMARY KEY,
       url_id INT REFERENCES urls(id) ON DELETE CASCADE,
       clicked_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
       ip_address INET,
       country VARCHAR(50),
       device_type VARCHAR(50)
   );

   CREATE OR REPLACE FUNCTION generate_unique_short_code(length INT)
   RETURNS TEXT AS $$
   DECLARE
       new_code TEXT;
       exists_count INT;
   BEGIN
       LOOP
           new_code := (
               SELECT string_agg(substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789' FROM trunc(random() * 36 + 1)::int FOR 1), '')
               FROM generate_series(1, length)
           );

           SELECT COUNT(*) INTO exists_count FROM public.urls WHERE short_code = new_code;

           EXIT WHEN exists_count = 0;
       END LOOP;

       RETURN new_code;
   END;
   $$ LANGUAGE plpgsql;
   ```

3. **Configure the Database Connection**  
   Update the `application.properties` file in the `src/main/resources` directory with your database credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/shortlytic
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   ```

   Replace `your-username` and `your-password` with your PostgreSQL username and password.

4. **Test the Database Connection**  
   Run the application and ensure that it connects to the PostgreSQL database without errors.

## Usage

Once the application is running, you can interact with the API endpoints to shorten URLs and retrieve original links.

## Dependencies

Shortlytic is built with Java and utilizes the following dependencies:

- Spring Boot
- Maven

## Configuration

Configuration details can be adjusted in the `application.properties` file located in the `src/main/resources` directory.

## Troubleshooting

For common issues and their solutions, please refer to the [issues section](https://github.com/sommye-ctr/shortlytic/issues) of the repository.

## Contributors

- [sommye-ctr](https://github.com/sommye-ctr)

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/sommye-ctr/shortlytic/blob/main/LICENSE) file for details.
