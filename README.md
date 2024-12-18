# **Letter Service Microservice**

## **Overview**

The **Letter Service** microservice manages the creation, validation, updating, and deletion of letters. It integrates with other services like the **Eligibility Microservice** via Kafka for event-driven processing. It provides essential functionality related to letter operations in the system, ensuring compliance with business rules such as item validation and communication with other services.

## **Purpose**

The **Letter Service** performs several key roles within the system:

- **Letter Management**: Handles CRUD operations for letter-related data.
- **Kafka Integration**: Sends and receives events related to letter processing.
- **Event-Driven Communication**: Sends events to the **Eligibility Microservice** when letters are created or updated.

## **Key Responsibilities**

### **CRUD Operations:**

- **Create**: Add new letters with details like content and recipient.
- **Read**: Retrieve information about existing letters.
- **Update**: Modify the details of a letter.
- **Delete**: Remove a letter from the system.

### **Kafka Integration:**

- **Send Event**: When a letter is created or updated, an event is published to the **Eligibility Microservice** via Kafka for further validation.

### **Security and Validation:**

- **DTO Validation**: Ensures data integrity by validating input from the user before processing requests.
- **Authentication**: Routes are secured using JWT authentication to ensure that only authorized users can access letter-related data.

## **Example Workflow**

### **Client Request:**

1. A client application sends a **POST** request to create a new letter:**`POST /letter/create`**

### **Service Handling:**

1. The **LetterController** receives the request.
2. The **LetterServiceImpl** validates the input using the **CreateLetterRequest** DTO.
3. The letter is created, and an event is sent to the **Eligibility Microservice** for validation.

### **Response:**

1. The service responds with a success message or an error, depending on the validation result.

## **System Integration**

The **Letter Service** interacts with the following microservices:

| **Service**            | **Base URI**                | **Secured** | **Role**                                                |
|------------------------|-----------------------------|-------------|---------------------------------------------------------|
| **Authentication**     | **`http://localhost:8081`** | Yes         | Manages user authentication and JWT generation.         |
| **Eligibility Micro**  | **`http://localhost:8085`** | Yes         | Validates item dimensions and sends eligibility events. |
| **Notification Micro** | **`http://localhost:8086`** | Yes         | Sends email notifications based on events.              |

## **Folder Structure**

```rust
com.letterservice
├── common
│   ├── constant
│   │   ├── ApiPathConstants.java
│   │   └── TopicConstants.java
│   ├── dtos
│   │   ├── CreateLetterRequest.java
│   │   ├── CreateLetterResponse.java
│   │   ├── LetterContentResponse.java
│   │   └── UpdateLetterRequest.java
│   └── entities
│       └── LetterModel.java
├── controller
│   ├── impl
│   │   └── LetterController.java
│   └── LetterApi.java
├── repositories
│   └── LetterRepository.java
├── service
│   ├── impl
│   │   └── LetterServiceImpl.java
│   └── LetterService.java
└── resources
    └── application.yaml
```

### **Folder Details:**

- **common/constant**: Contains constants like **`ApiPathConstants`** (API paths) and **`TopicConstants`** (Kafka topics).
- **common/dtos**: Holds DTO classes used for requests and responses (e.g., **`CreateLetterRequest`**, **`LetterContentResponse`**).
- **common/entities**: Contains the **LetterModel** entity that maps to the database.
- **controller**: Implements routes and request handling, with **LetterController** acting as the controller for managing letter endpoints. The **LetterApi** interface defines the endpoints.
- **repositories**: Manages data persistence for letters with **LetterRepository**.
- **service**: Implements the business logic for letters. **LetterServiceImpl** provides the implementation of **LetterService**.

## **Security**

### **JWT Authentication:**

- Routes for letter management are secured by JWT tokens.
- Only authorized users with valid tokens can access and modify letter data.

### **Example of Secured Route Handling:**

1. The client sends a request to the letter API with the JWT token in the Authorization header:

    ```makefile
    Authorization: Bearer <JWT_TOKEN>
    ```

2. The **LetterController** ensures that the token is validated before proceeding with the requested operation.

### **Failure Case:**

- If the token is invalid or missing, the request is rejected with a 401 Unauthorized response.

## **Example Endpoints**

| **HTTP Method** | **Endpoint**        | **Description**                        | **Secured** |
|-----------------|---------------------|----------------------------------------|-------------|
| **POST**        | /letter/create      | Creates a new letter                   | Yes         |
| **GET**         | /letter/{id}        | Retrieves details of a specific letter | Yes         |
| **PUT**         | /letter/update/{id} | Updates an existing letter             | Yes         |
| **DELETE**      | /letter/delete/{id} | Deletes a letter                       | Yes         |

## **Technology Stack**

- **Framework**: Spring Boot (Java 17)
- **Messaging**: Kafka (for event-driven communication)
- **Database**: PostgreSQL (or any other relational database)
- **JWT Library**: JJWT
- **Build Tool**: Maven

## **Installation and Setup**

### **Prerequisites:**

- Java 17 or later
- Maven 3.8+
- Running instances of the dependent microservices (Authentication, Eligibility, Notification)

### **Steps:**

1. Clone the repository.
2. Navigate to the project directory.
3. Run the following Maven command to install dependencies:

    ```
    Copy code
    mvn clean install
    
    ```

4. Start the application:

    ```arduino
    arduino
    Copy code
    mvn spring-boot:run
    
    ```


## **Configuration**

### **application.yaml**

Here is an example of the configuration file:

```yaml
server:
  port: 8082

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/letterdb
    username: user
    password: password
    driver-class-name: org.postgresql.Driver

  kafka:
    consumer:
      group-id: letter-service-group
    producer:
      bootstrap-servers: localhost:9092
    topic:
      letter-events: letter-topic

  security:
    jwt:
      secret-key: secret-key
      token-prefix: Bearer
      header-string: Authorization

```

## **Example Workflow**

1. **Client Request**: A client application sends a POST request to create a new letter.
2. **Processing**: The service validates the input and stores the letter in the database.
3. **Event Emission**: Once the letter is successfully created, an event is sent to the Kafka topic **`letter_created`**.
4. **Response**: The service responds with a confirmation, including the letter's details.

## **System Integration**

The **Letter Service API** interacts with the following microservices:

| **Service**                | **Base URI**                | **Secured** | **Role**                                              |
|----------------------------|-----------------------------|-------------|-------------------------------------------------------|
| **Authentication Service** | **`http://localhost:8081`** | Yes         | Manages user registration, login, and JWT generation. |
| **Users Service**          | **`http://localhost:8084`** | Yes         | Handles user-related data retrieval and operations.   |
| **Notification Service**   | **`http://localhost:8085`** | Yes         | Sends notifications based on triggered events.        |
| **Kafka (Event-driven)**   | **`localhost:9092`**        | No          | Handles messaging for letter-related events.          |

## **Features**

- **CRUD Operations**: Create, retrieve, and update letters.
- **Kafka Event Integration**: Sends events like **`letter_created`** for other services to process.
- **Security**: JWT-based authentication to protect sensitive routes.
- **Database Storage**: Stores letters in a PostgreSQL database.

## **Table of Contents**

- [Technology Stack](https://chatgpt.com/c/6736297a-b448-8011-919c-786085c83bb4#technology-stack)
- [Installation and Setup](https://chatgpt.com/c/6736297a-b448-8011-919c-786085c83bb4#installation-and-setup)
- [Configuration](https://chatgpt.com/c/6736297a-b448-8011-919c-786085c83bb4#configuration)
- [Endpoints](https://chatgpt.com/c/6736297a-b448-8011-919c-786085c83bb4#endpoints)
- [Folder Structure](https://chatgpt.com/c/6736297a-b448-8011-919c-786085c83bb4#folder-structure)
- [Security](https://chatgpt.com/c/6736297a-b448-8011-919c-786085c83bb4#security)
- [Related Microservices](https://chatgpt.com/c/6736297a-b448-8011-919c-786085c83bb4#related-microservices)

## **Technology Stack**

- **Framework**: Spring Boot
- **Language**: Java 17
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Event Bus**: Kafka
- **Authentication**: JWT
- **Documentation**: Swagger (OpenAPI)

## **Installation and Setup**

### **Prerequisites**

- Java 17 or later
- Maven 3.8+
- A running instance of the **Authentication Service** (port: 8081)
- A running instance of the **Users Service** (port: 8084)
- Kafka instance (localhost:9092)

### **Steps**

1. Clone the repository.
2. Navigate to the project directory.
3. Install dependencies:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```


## **Endpoints**

### **POST /letters**

Creates a new letter in the system.

### **Request Body:**

```json
{
  "recipientName": "John Doe",
  "recipientAddress": "123 Main St, City, Country",
  "content": "This is a test letter."
}
```

### **Response:**

```json
{
  "id": 1,
  "recipientName": "John Doe",
  "recipientAddress": "123 Main St, City, Country",
  "content": "This is a test letter.",
  "status": "created"
}
```

### **PUT /letters/{id}**

Updates the details of an existing letter.

### **Request Body:**

```json
{
  "recipientName": "Jane Doe",
  "recipientAddress": "456 Oak St, City, Country",
  "content": "Updated content for the letter."
}
```

### **Response:**

```json
{
  "id": 1,
  "recipientName": "Jane Doe",
  "recipientAddress": "456 Oak St, City, Country",
  "content": "Updated content for the letter.",
  "status": "updated"
}
```

### **GET /letters**

Retrieves a list of all letters.

### **Response:**

```json
{
    "id": 1,
    "recipientName": "John Doe",
    "recipientAddress": "123 Main St, City, Country",
    "content": "This is a test letter.",
    "status": "created"
  }
```

## **Security**

The **Letter Service API** uses JWT-based authentication for secure routes. All requests that interact with letters must include a valid JWT token in the Authorization header.

### **Example of a Secured Request**

```

POST /letters
Authorization: Bearer <JWT_TOKEN>

```

### **JWT Validation Flow:**

1. The **`AuthenticationFilter`** intercepts the request and checks for a valid JWT token.
2. The token is validated by the **`JwtUtils`** service.
3. If the token is valid, the request proceeds; otherwise, a **`401 Unauthorized`** response is returned.

> [!Note]
> You must have the related microservices along with this service since they all work together. Please find them below this line

## Related Microservices

The system consists of multiple microservices that work together to provide comprehensive functionality. Below is a list of all the microservices in the system, with links to their respective repositories:

- [**users-service-api**](https://github.com/juansebstt/users-service-api): Handles user management, including registration, profile updates, and account data.
- [**email-kafka-microservice**](https://github.com/juansebstt/email-kafka-microservice): Manages asynchronous email event processing using Kafka for reliable messaging.
- [**notifications-microservice-api**](https://github.com/juansebstt/notifications-microservice-api): Sends notifications based on triggered events from other services.
- [**email-authentication-service-api**](https://github.com/juansebstt/email-authentication-service-api): Manages email-based authentication and verification processes.
- [**email-api-gateway**](https://github.com/juansebstt/email-api-gateway): Serves as the entry point for routing requests to various microservices.
- [**letter-service-api**](https://github.com/juansebstt/letter-service-api): Manages letters, including creation, storage, and retrieval.
- [**packages-service-api**](https://github.com/juansebstt/packages-service-api): Manages package-related operations, including tracking and status updates.
