# 🚀 Virtual Threads Task Orchestrator

> A production-inspired backend application built with **Java 21 Virtual Threads** and **Spring Boot** to execute, monitor, and manage asynchronous background tasks efficiently.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-green?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8-blue?style=for-the-badge&logo=mysql)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green?style=for-the-badge&logo=swagger)

---

## 📖 Overview

**Virtual Threads Task Orchestrator** is a backend system that demonstrates how modern Java applications can execute long-running tasks asynchronously using **Java 21 Virtual Threads**.

Instead of blocking HTTP requests, tasks are executed in the background while clients receive an immediate response. The application supports task tracking, retry mechanisms, multiple task handlers, API documentation, and clean layered architecture.

This project was developed to explore modern Java concurrency and backend architecture using Spring Boot.

---

# ✨ Features

- 🚀 Java 21 Virtual Threads
- ⚡ Asynchronous Task Execution
- 📌 Task Status Tracking
- 🔄 Retry Failed Tasks
- 🧩 Strategy Pattern for Multiple Task Types
- 📚 RESTful APIs
- 🗄️ Spring Data JPA + MySQL
- 📝 Bean Validation
- 📖 Swagger OpenAPI Documentation
- 🏗️ Layered Architecture
- 🔥 Exception Handling
- 📊 Clean Project Structure

---

# 🛠 Tech Stack

| Technology | Description |
|------------|-------------|
| Java 21 | Virtual Threads |
| Spring Boot | REST API Development |
| Spring Data JPA | ORM |
| Hibernate | Persistence |
| MySQL | Database |
| Maven | Build Tool |
| Lombok | Boilerplate Reduction |
| Swagger (OpenAPI) | API Documentation |

---

# 🏛️ Project Architecture

```
                Client
          (Swagger / Postman)
                    │
                    ▼
           TaskController
                    │
                    ▼
             TaskService
                    │
                    ▼
          TaskRepository (JPA)
                    │
                    ▼
                 MySQL
                    │
                    ▼
     ExecutorService (Virtual Threads)
                    │
                    ▼
          TaskHandlerFactory
        ┌─────────┼─────────┐
        ▼         ▼         ▼
 EmailHandler ReportHandler ApiSyncHandler
```

---

# 📂 Project Structure

```
task-orchestrator
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.pushkar.taskorchestrator
│   │   │
│   │   ├── config
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── repository
│   │   ├── service
│   │   ├── taskhandler
│   │   │     ├── impl
│   │   │     └── TaskHandlerFactory
│   │   ├── exception
│   │   └── TaskOrchestratorApplication
│   │
│   └── resources
│        └── application.properties
│
├── pom.xml
└── README.md
```

---

# ⚙️ How It Works

### 1️⃣ Create Task

The client submits a task request.

```http
POST /api/tasks
```

Example

```json
{
  "taskName": "Generate Sales Report",
  "taskType": "REPORT",
  "payload": "June Sales Report"
}
```

---

### 2️⃣ Task Stored

The task is immediately stored in the database with status:

```
PENDING
```

---

### 3️⃣ Background Execution

A **Java 21 Virtual Thread** starts executing the task asynchronously.

```
PENDING
      ↓
RUNNING
```

---

### 4️⃣ Handler Selection

Based on the task type, the appropriate handler is selected.

```
REPORT
      ↓
ReportTaskHandler

EMAIL
      ↓
EmailTaskHandler

API_SYNC
      ↓
ApiSyncTaskHandler
```

---

### 5️⃣ Completion

After execution:

```
SUCCESS
```

or

```
FAILED
```

The result is stored in the database.

---

# 📊 Task Lifecycle

```
        CREATE TASK
             │
             ▼
          PENDING
             │
             ▼
          RUNNING
        ┌────┴────┐
        ▼         ▼
    SUCCESS     FAILED
                    │
                    ▼
               RETRYING
                    │
                    ▼
                 RUNNING
```

---

# 📡 REST APIs

## Create Task

```
POST /api/tasks
```

---

## Get Task By Id

```
GET /api/tasks/{id}
```

---

## Get All Tasks

```
GET /api/tasks
```

---

## Retry Failed Task

```
POST /api/tasks/{id}/retry
```

---

## Get Tasks By Status

```
GET /api/tasks/status/{status}
```

Example

```
SUCCESS
FAILED
RUNNING
PENDING
RETRYING
```

---

# 📖 Swagger Documentation

After starting the application

```
http://localhost:8080/swagger-ui/index.html
```

Swagger allows you to:

- View all REST APIs
- Execute APIs directly from the browser
- Inspect Request & Response models
- Explore API documentation

---

# 🗄️ Database Schema

Table

```
tasks
```

Columns

```
id
task_name
task_type
status
payload
result
error_message
retry_count
created_at
started_at
completed_at
```

---

# ▶️ Running the Project

## Clone Repository

```bash
git clone https://github.com/PrajapatiPushkar/task-orchestrator.git
```

---

## Navigate

```bash
cd task-orchestrator
```

---

## Configure Database

Create MySQL Database

```sql
CREATE DATABASE task_orchestrator_db;
```

Update

```
application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_orchestrator_db
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## Run Application

```bash
mvn spring-boot:run
```

Application will start on

```
http://localhost:8080
```

---

# 🧪 API Testing

### Using Swagger

```
http://localhost:8080/swagger-ui/index.html
```

### Using Postman

Import endpoints and start testing.

---

# 📷 Screenshots

<img width="1511" height="730" alt="image" src="https://github.com/user-attachments/assets/5ac8b1e0-5656-41b3-9024-00873e3d6a73" />
- Swagger UI

 <img width="1521" height="720" alt="image" src="https://github.com/user-attachments/assets/3b97dfc6-225e-4520-beca-efe6d208608b" />
 
- Create Task API
  <img width="1522" height="727" alt="image" src="https://github.com/user-attachments/assets/33ae108c-287d-450b-9c5b-319d675396b7" />

- Task Execution
  

---

# 💡 Key Concepts Demonstrated

- Java 21 Virtual Threads
- Asynchronous Processing
- Strategy Design Pattern
- REST API Development
- Layered Architecture
- DTO Pattern
- Global Exception Handling
- Bean Validation
- Spring Data JPA
- Swagger/OpenAPI

---

# 🚀 Future Improvements

- JWT Authentication
- Role-Based Authorization
- Docker Support
- Redis Queue
- Kafka Integration
- Email Notifications
- Scheduler
- Monitoring Dashboard
- Unit & Integration Testing
- CI/CD Pipeline

---

# 👨‍💻 Author

**Pushkar Prajapati**

📧 Email: kppusakar2018@gmail.com

🔗 GitHub: https://github.com/PrajapatiPushkar

---

## ⭐ If you found this project helpful, don't forget to Star the repository!
