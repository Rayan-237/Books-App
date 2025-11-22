# 📚 Books Web Application

### **Java • Servlets • JDBC • DAO • Singleton • MVC • Front Controller**

A full-stack server-side Java web application that manages book authors using **MySQL**, **Jakarta Servlets**, **DAO pattern**, and **JDBC**.
The app follows a clean multi-layer architecture and demonstrates proper enterprise-style web development.

---

# 🚀 Features

### 🔐 Login Screen

Simple credential validation before accessing the system.

### 🧩 CRUD Operations

Manage authors stored in a MySQL database:

* **Get all authors**
* **Get author by ID**
* **Add author**
* **Update author**
* **Delete author**

Each action displays results in clean HTML tables.

### 🏗️ Multi-Layer Architecture

The app is fully separated into:

* **Presentation Layer** – Servlets generating HTML
* **Business Layer** – Application logic
* **Data Access Layer** – DAO + JDBC + Singleton `DataSource`
* **DTO Layer** – Transfer objects for data transport

### 🧠 Design Patterns Used

* **DAO Pattern**
* **Singleton Pattern**
* **MVC-style separation**
* **Front Controller Pattern**

---

# 🛠️ Tech Stack

| Component   | Technology                    |
| ----------- | ----------------------------- |
| Web Server  | Apache Tomcat 10 / 11         |
| Backend     | Jakarta Servlets (Jakarta EE) |
| Database    | MySQL 8                       |
| Persistence | JDBC                          |
| Build Tool  | Maven                         |
| Language    | Java 17+                      |

---

# 📁 Project Structure

```
src/main/java/
│
├── businesslayer/
│     └── AuthorService.java
│
├── dataaccesslayer/
│     ├── AuthorDAO.java
│     ├── AuthorDAOImpl.java
│     ├── DAOException.java
│     └── DataSource.java
│
├── transferobjects/
│     └── Author.java
│
└── viewlayer/
      ├── FrontController.java
      ├── GetAllAuthorsServlet.java
      ├── GetAuthorByIdServlet.java
      ├── AddAuthorServlet.java
      ├── UpdateAuthorByIdServlet.java
      └── DeleteAuthorByIdServlet.java
```

---

# ⚙️ How to Run

### **1. Import project in any IDE (NetBeans, IntelliJ, Eclipse)**

It’s a standard Maven project.

### **2. Create MySQL database**

Run the provided `books.sql`.

### **3. Configure credentials in:**

`src/main/resources/database.properties`

```
db.url=jdbc:mysql://localhost:3306/books
db.user=YOUR_USER
db.password=YOUR_PASSWORD
```

### **4. Deploy the app**

Add Apache Tomcat → Run Project.

### **5. Open in browser**

```
http://localhost:8080/YourProject/FrontController-URL
```

---

# ✨ Extra Notes

This project is great for:

* Showing backend web development skills
* Demonstrating Java Servlets knowledge
* Using proper software design patterns
* Learning how multi-tier enterprise apps are structured

---
