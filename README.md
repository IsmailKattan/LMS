


# 📚 Library Management System API

This project is a **Spring Boot** based REST API for managing a library system. It allows patrons to register and log in, manage books, borrow and return books, and manage patron details.

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Docker & Docker Compose
- Maven

### 🔧 Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/library-api.git
   cd library-api
   ```

2. **Start all services using Docker**
   ```bash
   docker compose up -d
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

> The API will be available at: `http://localhost:8080`

---

## 📘 API Reference

### 🔐 AuthController `/auth`

| Method | Endpoint     | Description         |
|--------|--------------|---------------------|
| POST   | `/register`  | Register a patron   |
| POST   | `/login`     | Login for a patron  |

---

### 📚 BookController `/api/books`

| Method | Endpoint     | Description           |
|--------|--------------|-----------------------|
| GET    | `/`          | Get all books (with filters & pagination) |
| GET    | `/{id}`      | Get a book by ID      |
| POST   | `/`          | Add a new book        |
| PUT    | `/{id}`      | Update a book by ID   |
| DELETE | `/{id}`      | Delete a book by ID   |

**Filter Parameters (optional):**

- `title`
- `author`
- `category`
- `language`
- `publisher`
- `publication-year`
- `borrowing-count`
- `status`
- `price`
- `max-postpone-count`
- `page`
- `size`

---

### 🔄 BorrowRecordController

| Method | Endpoint                                     | Description           |
|--------|----------------------------------------------|-----------------------|
| PUT    | `/api/borrow/{bookId}/patron/{patronId}`     | Borrow a book         |
| PUT    | `/api/return/{bookId}/patron/{patronId}`     | Return a book         |
| PUT    | `/api/EOD`                                   | End-of-day operations |

---

### 👤 PatronController `/api/patrons`

| Method | Endpoint     | Description            |
|--------|--------------|------------------------|
| GET    | `/`          | Get all patrons (with filters & pagination) |
| GET    | `/{id}`      | Get a patron by ID     |
| POST   | `/`          | Create a new patron    |
| PUT    | `/{id}`      | Update a patron by ID  |
| DELETE | `/{id}`      | Delete a patron by ID  |

**Filter Parameters (optional):**

- `username`
- `email`
- `phone`
- `name`
- `surname`
- `page`
- `size`

---

## 🛠 Technologies Used

- Spring Boot
- Spring Web
- Spring Security (optional)
- Docker & Docker Compose
- Java 17
- Maven

---

## 📦 Project Structure

```
src/
├── controller/
│   ├── AuthController.java
│   ├── BookController.java
│   ├── BorrowRecordController.java
│   └── PatronController.java
├── service/
├── dto/
├── model/
├── repository/
└── ...
```

---

## 🐳 Docker Overview

Ensure your `docker-compose.yml` is set up with services like:

- MongoDB (or other DB)
- Backend (Spring Boot)

Start containers with:

```bash
docker compose up -d
```

---

## 📝 Author

**Ismail Kattan** – [@yourhandle](https://github.com/yourhandle)

---

## 🏷 License

This project is licensed under the MIT License.
```
