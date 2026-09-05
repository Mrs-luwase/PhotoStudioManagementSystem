# Photography & Videography Studio Management System

A JSF and Hibernate web application built for a photography and videography studio that currently coordinates client bookings and staff assignments informally (phone calls, WhatsApp groups), with no central record of what's confirmed or who's assigned. This project replaces that with a simple, centralized system for managing **Bookings** and **Staff Members**.

Built as Assignment 3 for the Web Technology course at AUCA.

## Features

- Full CRUD (Create, Read, Update, Delete) on two entities: `Booking` and `StaffMember`
- A dashboard landing page for navigating between the two
- Auto-generated readable IDs (e.g. `BK001`, `ST001`)
- Validation across three layers:
  - Standard JSF validators (`f:validateLength`, `f:validateRegex`)
  - Method-based custom validators (event date can't be in the past, phone number format)
  - A class-based custom validator (`NotBlankValidator`)
  - A class-based custom converter (`LocalDateConverter`) for date handling
- CSS applied in all three forms: external stylesheet, internal `<style>` blocks, and inline styles
- Shared `Audit` base class (`createdAt` / `updatedAt`) automatically tracked on every record

## Tech Stack

- **Java 8**
- **JSF (Mojarra 2.2.20)** – presentation layer
- **Hibernate 5.6** – persistence layer (plain `SessionFactory`/`Session` API, not JPA)
- **MySQL** (via XAMPP) – database
- **Maven** – build tool
- **Apache Tomcat 9** – application server

## Project Structure

```
src/main/java/rw/ac/auca/photostudiomanagementsystem/
├── model/        # Audit, Booking, StaffMember (entities)
├── dao/          # BookingDao, StaffMemberDao (Hibernate CRUD)
├── bean/         # BookingBean, StaffMemberBean (JSF managed beans)
├── converter/    # LocalDateConverter (class-based custom converter)
├── validator/    # NotBlankValidator (class-based custom validator)
└── util/         # HibernateUtil (SessionFactory setup)

src/main/webapp/
├── dashboard.xhtml
├── bookingList.xhtml / bookingForm.xhtml / confirmation.xhtml
├── staffList.xhtml / staffForm.xhtml / staffConfirmation.xhtml
└── css/style.css
```

## Running Locally

1. **Prerequisites:** JDK 8, Apache Tomcat 9, XAMPP (for MySQL), Maven (or use the included `mvnw` wrapper)
2. **Database:** Start MySQL in XAMPP, then create a database named `photo_studio_db` (via phpMyAdmin or the MySQL CLI). No tables need to be created manually, Hibernate builds them automatically on first run (`hibernate.hbm2ddl.auto=update`).
3. **Configure:** Check `src/main/resources/hibernate.cfg.xml`, update the username/password if your MySQL setup differs from the default (`root`, no password).
4. **Build:** `./mvnw clean compile` (or `mvnw.cmd` on Windows)
5. **Deploy:** Run the project on Tomcat 9 through your IDE, or build the WAR and deploy it manually.
6. Visit `http://localhost:8080/PhotoStudioManagementSystem/dashboard.xhtml`

## Author

Leiss Uwase — 27064
Web Technology, AUCA
