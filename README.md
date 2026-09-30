# Campus Green Mobility

A web-based campus mobility platform for browsing EV-shuttle/shared-bike slots, making bookings, preventing overbooking, and tracking estimated CO₂ savings.

> **Academic Project — PBL 6**

## Features

- Student sign-up and login
- Session-based authentication
- Optional remembered-email cookie
- Browse upcoming mobility slots
- Server-side seat-capacity enforcement
- Booking confirmation with estimated CO₂ savings
- Booking history and personal impact tracking
- Student CO₂ leaderboard
- Monthly close-out and archive system
- Responsive interface with Bootstrap
- Offline-friendly vendored frontend assets

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | JSP, HTML, CSS, JavaScript, Bootstrap 5 |
| Backend | Java 17, Jakarta Servlet/JSP |
| Database | MySQL |
| Data access | JDBC + DAO pattern |
| Build | Maven |
| Server | Apache Tomcat 11 |

## Architecture

The project follows a simple **3-tier architecture**:

```text
Presentation Layer
        │
        ▼
 JSP / JSTL / HTML / CSS / JavaScript
        │
        ▼
Business & Data Access Layer
        │
        ├── JavaBeans / Models
        ├── DAO classes
        └── Utility classes
        │
        ▼
MySQL Database
```

See `ppt-assets/2-architecture-3tier.png` and `ppt-assets/1-er-diagram.png` for the project diagrams.

## Project Structure

```text
src/main/java/       Java models, DAO classes and utilities
src/main/webapp/     JSP pages, CSS, JavaScript and frontend assets
db/                  Database schema, seed data and migrations
ppt-assets/          Project diagrams and selected screenshots
pom.xml              Maven configuration
```

## Database Setup

1. Install MySQL.
2. Create the database using:

```bash
mysql -u root -p < db/schema.sql
```

3. Apply the migration files in order if required by your version of the project:

```text
migration_001_points_locator_feedback.sql
migration_002_streak_idempotency.sql
migration_003_admin_rewards_signup.sql
```

4. Configure the application database connection using environment variables:

```bash
export DB_URL="jdbc:mysql://localhost:3306/green_mobility?useSSL=false&serverTimezone=UTC"
export DB_USER="greenmob"
export DB_PASSWORD="your_database_password"
```

The application no longer stores a local database password in source code.

## Build

From the project root:

```bash
mvn clean package
```

This generates:

```text
target/green-mobility.war
```

Deploy the WAR to Apache Tomcat 11 and open:

```text
http://localhost:8080/green-mobility/
```

## Demo Account

The sample database contains a demonstration student account:

```text
Email:    demo.student@woxsen.edu.in
Password: password123
```

These credentials are **demo data only** and should not be reused for real accounts.

## Preventing Overbooking

The booking flow uses an atomic database update in `SlotDAO.tryReserveSeat()`:

```sql
UPDATE vehicle_slots
SET booked_count = booked_count + 1
WHERE slot_id = ?
  AND booked_count < capacity;
```

The booking succeeds only when the database update affects a row. This means two simultaneous requests cannot both reserve the final available seat.

## CO₂ Savings

The application estimates carbon savings from the trip distance and the difference between the assumed conventional-transport and green-mobility emission factors. The calculation is centralized in `CarbonCalculator.java`.

## Screenshots & Diagrams

Selected project visuals are available in `ppt-assets/`:

- ER diagram
- 3-tier architecture
- Booking flow / overbooking handling
- Login
- Slot browsing
- Booking confirmation
- My Impact
- Leaderboard

## Academic Context

This project demonstrates concepts including:

- JSP and JSTL
- Sessions and cookies
- JDBC
- DAO architecture
- Relational database design
- SQL constraints and foreign keys
- Transactional database operations
- Server-side validation
- Maven-based Java web application development

## Contributors

This repository is an individual GitHub copy of a team academic project. Credit should be given to all team members who contributed to the original implementation.
