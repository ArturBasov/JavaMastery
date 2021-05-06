**_Simple web application_** - Rest service (based on Spring Boot and Spring JDBC). Web Application Architecture see in Architecture.jpg.

Used – Java 8, Maven, Spring JDBC, Spring Boot (+ DevTools), Lombok, DB - PostgreSQL.

Tests (service layer) – JUnit, Mockito.

**How to use?**

First, check application.properties – DB url, DB username, DB password.
Run application (Shift+F10).

DB table fields:

    employee_id (BIGINT, PRIMARY KEY, NOT NULL)
    first_name (VARCHAR(50), NOT NULL)
    last_name (VARCHAR(50), NOT NULL)
    department_id (INT, NOT NULL)
    job_title (VARCHAR(100), NOT NULL)
    gender (VARCHAR(50), NOT NULL)
    date_of_birth (DATE, NOT NULL)

**In browser:**

http://localhost:8080/api/employees – to get all employees

http://localhost:8080/api/employees/5 – to get employee (for example) with id=5

**Rest Client (for example Postman):**

http://localhost:8080/api/employees -> http method POST – to add new employees

http://localhost:8080/api/employees -> http method PUT – to update employee

http://localhost:8080/api/employees/5 -> http method DELETE – to delete employee (for example) with id=5
