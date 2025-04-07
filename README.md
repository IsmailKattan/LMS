


# 📚 Library Management System API

A **Spring Boot** RESTful API to manage a Library system with MongoDB and Mongo Express using Docker Compose.

---

## 🚀 Getting Started

### ✅ Requirements

- Java 17+
- Docker & Docker Compose
- Maven

---

## ⚙️ Setup

### 1. Clone the repository
```bash
git clone https://github.com/your-username/library-api.git
cd library-api
```

### 2. Start services with Docker Compose
```bash
docker compose up -d
```

This will launch:

- MongoDB (`localhost:27017`)
- Mongo Express (`http://localhost:8081`)

> Mongo Express default credentials:
> - **Username:** `admin`
> - **Password:** `admin`

---

## ▶️ Running the Application

```bash
./mvnw spring-boot:run
```

Spring Boot app will run on:  
`http://localhost:8080`

---

## 🌐 API Endpoints

### 🔐 AuthController `/auth`

| Method | Endpoint     | Description         |
|--------|--------------|---------------------|
| POST   | `/register`  | Register a patron   |
| POST   | `/login`     | Patron login        |

---

### 📚 BookController `/api/books`

| Method | Endpoint     | Description                     |
|--------|--------------|---------------------------------|
| GET    | `/`          | Get all books (with filters)    |
| GET    | `/{id}`      | Get book by ID                  |
| POST   | `/`          | Add a new book                  |
| PUT    | `/{id}`      | Update book by ID               |
| DELETE | `/{id}`      | Delete book by ID               |

#### 🔍 Filters (Optional):
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
| GET    | `/`          | Get all patrons (with filters) |
| GET    | `/{id}`      | Get patron by ID       |
| POST   | `/`          | Create new patron      |
| PUT    | `/{id}`      | Update patron by ID    |
| DELETE | `/{id}`      | Delete patron by ID    |

#### 🔍 Filters (Optional):
- `username`
- `email`
- `phone`
- `name`
- `surname`
- `page`
- `size`

---

## 🐳 Docker Services

```yaml
services:
  mongo:
    image: mongo:latest
    container_name: mongo
    environment:
      MONGO_INITDB_ROOT_USERNAME: admin
      MONGO_INITDB_ROOT_PASSWORD: admin
    ports:
      - "0.0.0.0:27017:27017"
    networks:
      - Mongo
    restart: unless-stopped
    volumes:
      - type: volume
        source: MONGO_DATA
        target: /data/db
      - type: volume
        source: MONGO_CONFIG
        target: /data/configdb
      - ./mongo-init.js:/docker-entrypoint-initdb.d/mongo-init.js:ro

  mongo-express:
    image: mongo-express:latest
    container_name: mongo-express
    environment:
      ME_CONFIG_MONGODB_ADMINUSERNAME: admin
      ME_CONFIG_MONGODB_ADMINPASSWORD: admin
      ME_CONFIG_MONGODB_SERVER: mongo
      ME_CONFIG_BASICAUTH_PORT: "27017"
      ME_CONFIG_BASICAUTH_USERNAME: admin
      ME_CONFIG_BASICAUTH_PASSWORD: admin
    ports:
      - "0.0.0.0:8081:8081"
    networks:
      - Mongo
    depends_on:
      - mongo
    restart: unless-stopped
    command: sh -c "echo 'Waiting for MongoDB...'; while ! nc -z mongo 27017; do sleep 1; done; echo 'MongoDB is up!'; node app"

networks:
  Mongo:
    name: mongo-network

volumes:
  MONGO_DATA:
  MONGO_CONFIG:
```

---

## ⚙️ Spring Configuration (`application.yml`)

```yaml
spring:
  application:
    name: LMS
  data:
    mongodb:
      uri: mongodb://admin:admin@localhost:27017/lms?authSource=admin

lms:
  app:
    jwt:
      secret: [REDACTED]
      expiration:
        hours: 24

  borrowing:
    limit: 5
    duration: 14
    delay:
      fine: 1
      rate: 0.5
```

> 🔐 Use a proper secret generator to replace `[REDACTED]` in production.

---


---

## 📄 License

This project is licensed under the MIT License.

---

## 🙋‍♂️ Author

**Ismail kattan** – (https://github.com/IsmailKattan)
```
