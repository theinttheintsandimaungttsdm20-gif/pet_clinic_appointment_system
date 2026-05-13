# Pet Clinic Appointment System

A Java web application built with **Spring MVC 5 + Hibernate 5** for managing pet clinic operations — owner and pet registration, doctor scheduling, appointment booking, and service management. The system features an intelligent **SJF (Shortest Job First) appointment scheduling algorithm**, automated email reminders, and dynamic Excel analytics reports exported as bar charts.

---

## 🏗️ Architecture Overview

**Spring MVC 5** + **Hibernate 5** + **JSP** deployed on **Apache Tomcat**

```
┌─────────────────────────────────────────────────────────────┐
│                Spring MVC Web Application                   │
├──────────────────┬──────────────────┬───────────────────────┤
│   Controller     │    Service       │      DAO              │
│  (HTTP Handlers) │  (Business Logic)│  (Hibernate ORM)      │
├──────────────────┴──────────────────┴───────────────────────┤
│              Model / Form / Exal (Apache POI)               │
├─────────────────────────────────────────────────────────────┤
│         MySQL Database  ·  JSP Views  ·  JavaMail           │
└─────────────────────────────────────────────────────────────┘
```

---

## 📦 Project Structure

```
com/coder/
├── config/          # Spring MVC + Hibernate configuration
├── controller/      # HTTP request handlers
├── service/         # Business logic layer
├── dao/             # Database access layer
├── exal/            # Excel bar chart report exports
├── form/            # Spring form binding objects
└── model/           # Hibernate entity classes

WEB-INF/
├── lib/             # JAR dependencies
└── views/           # JSP templates
```

---

## ✨ Key Features

### 🗓️ SJF Appointment Scheduling
Appointments are sorted by **service duration** using a **Shortest Job First (SJF)** algorithm. The system prioritises shorter-duration services first, reducing overall patient wait time. Within the day before the appointment date, the scheduler automatically reorders the queue to process the shortest appointments first — optimising daily clinic throughput.

### 📧 Automated Email Reminders
`AutoMailServic` automatically sends appointment reminder emails to pet owners before their scheduled visit — reducing no-shows without any manual effort from clinic staff.

### 📊 Dynamic Excel Analytics Reports
The `exal` module generates **bar chart Excel reports** using Apache POI, available in both **Normal** and **SJF** sorted views:

| Report | Description |
|---|---|
| By Owner | Appointment volume per owner |
| By Schedule | Appointments per doctor schedule |
| By Service Type | Breakdown by clinic service type |

Each report is exportable in both **Normal** (standard order) and **SJF** (duration-optimised) modes, allowing the clinic to visually compare scheduling approaches.

### 🐾 Full Clinic Management
- Owner and pet registration with login
- Doctor registration and schedule management
- Species, rank, service, and service type configuration
- Appointment booking and filtering by service type

---

## 📚 Core Libraries

| Library | Version | Why |
|---|---|---|
| **Spring MVC** | 5.0.9 | Core web framework — handles HTTP routing, dependency injection, and MVC structure |
| **Hibernate Core** | 5.2.17 | ORM layer — maps Java model classes to MySQL tables without writing raw SQL |
| **Spring ORM** | 5.0.9 | Integrates Hibernate with Spring's transaction management and dependency injection |
| **Hibernate Validator** | 5.1.2 | Validates form inputs and model constraints declaratively with annotations |
| **Apache POI** | 4.1.2 | Generates dynamic Excel bar chart reports for appointment and sales analytics |
| **JavaMail** | 1.5.5 | Sends automated appointment reminder emails to pet owners |
| **MySQL Connector** | 5.1.20 | JDBC driver for connecting the application to the MySQL database |
| **JSTL Taglibs** | 1.2.5 | Enables dynamic rendering in JSP views with standard tags (loops, conditionals) |
| **Log4j** | 1.2.17 | Application-level logging for debugging and monitoring runtime behaviour |
| **JUnit** | 4.12 | Unit testing framework for service and DAO layer tests |

---

## 🚀 Setup & Running

**1. Import the database** into MySQL and update DB credentials in `com/coder/config/`.

**2. Configure email** — set SMTP credentials in `AutoMailServic` for outgoing reminder emails.

**3. Deploy** the WAR to Apache Tomcat. `WEB-INF/` and `resources/admin` are included in the build output.

**4. Access** the app at `http://localhost:8080/<app-name>`.

---

## 💡 What I Learned

- Applying a **SJF scheduling algorithm** to a real-world domain to minimise patient wait times
- Building a clean **3-layer Spring MVC architecture** (Controller → Service → DAO) with Hibernate ORM
- Generating **dynamic Excel bar chart reports** with Apache POI for clinic analytics
- Integrating **automated email notifications** with JavaMail to streamline clinic communication
- Designing **Normal vs SJF report modes** to make the scheduling algorithm's impact visible and measurable
