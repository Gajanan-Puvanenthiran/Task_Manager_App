# Task Manager Application

A full-stack task management application built with Angular (frontend) and Spring Boot (backend) with MySQL database.

### Features
* Create, read, update, and delete tasks
* Filter tasks by status (TO_DO, IN_PROGRESS, DONE)* 
* User authentication (JWT)
* Responsive UI with Angular Material
* RESTful API with Spring Boot
* Docker containerization for all components


### Prerequisites
* Node.js (latest)
* Angular CLI(19)
* Java JDK (17)
* Maven
* MySQL
* Docker
* Docker Compose

## Getting Started

### Backend Setup

##### 1.Navigate to the backend directory:
`cd backend`

##### 2.Configure database connection in application.properties:


###### For MySQL
`spring.datasource.url=jdbc:mysql://localhost:3307/db_task?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=5555
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`

`spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.generate-ddl=true`

#### 3.Build and run the Spring Boot application:
`mvn spring-boot:run`

The backend will be available at http://localhost:8080

### Frontend Setup

##### 1.Navigate to the frontend directory:
`cd frontend`

##### 2.Install dependencies:
`npm install`

##### 3.Start the Angular development server:
`ng serve`

The frontend will be available at http://localhost:4200

### Database Setup
### MySQL
##### 1.Create a database:
`CREATE DATABASE db_task;`

##### 2.The tables will be automatically created by Hibernate when the application starts.

### Authentication Credentials
Two user accounts are pre-configured:

##### 1.User1:
`Username: gajan
Password: g@123`

##### User2:
`Username: mohan
Password: m@123`

You can also register new users through the registration page.

### API Endpoints

##### 1. Register new user - POST mapping- `/auth/register`,	No Authentication required.
##### 2. Login as existing user - POST mapping- `/auth/login`, No Authentication required.
##### 3. Get all Tasks - GET mapping- `/tasks`, Authentication required.
##### 4. Create new Task - POST mapping- `/tasks`, Authentication required.
##### 5. Get a Task by ID - GET mapping- `/tasks/{id}`,	Authentication required.
##### 6. Update an existing Task - PUT mapping- `/tasks/{id}`, Authentication required.
##### 7. Delete Task - DELETE mapping- `/tasks/{id}`, Authentication required.


### Technologies Used
#### Frontend
Angular 19

Angular Material

RxJS

TypeScript

#### Backend
Spring Boot 3.3.10

Spring Security

JWT Authentication

Spring Data JPA

Maven

#### Database
MySQL

### License
###### This project is licensed under the MIT License.