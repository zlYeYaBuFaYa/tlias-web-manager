# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**tlias-web-manager** is a talent/employee management RESTful API backend service built with:
- Java 17 + Spring Boot 4.0.2
- Maven for build management
- MySQL database with MyBatis ORM
- PageHelper for pagination

## Build and Run Commands

```bash
# Build the project
mvn clean compile

# Run tests
mvn clean test

# Package as JAR
mvn clean package

# Run the application
mvn spring-boot:run

# Or run the packaged JAR
java -jar target/tlias-web-manager-0.0.1-SNAPSHOT.jar
```

## Architecture

This is a standard **layered Spring Boot application** following a three-tier architecture:

```
Controller Layer (com.itheima.controller)
    ↓
Service Layer (com.itheima.service & com.itheima.service.impl)
    ↓
Mapper Layer (com.itheima.mapper) - MyBatis data access
    ↓
MySQL Database
```

### Domain Entities

The core domain model consists of:
- **Emp** - Employee entity with fields: username, name, gender, job, salary, deptId, entryDate, etc.
- **Dept** - Department entity (id, name, createTime, updateTime)
- **Clazz** - Class entity (for training classes)
- **EmpExpr** - Employee experience/work history
- **PageBean** - Pagination wrapper for API responses
- **Result** - Standard API response wrapper
- **EmpQueryParam** - Query parameters for employee search

### Package Structure

- `com.itheima.entity` - Domain entities and DTOs
- `com.itheima.controller` - REST API controllers (EmpController, DeptController, ClazzController)
- `com.itheima.service` - Business logic interfaces
- `com.itheima.service.impl` - Service implementations
- `com.itheima.mapper` - MyBatis mapper interfaces
- `resources/com/itheima/mapper/` - MyBatis XML mappings

### API Conventions

- Base paths: `/emps`, `/depts`, `/clazzes`
- All responses wrapped in `Result` class with consistent format
- Pagination via `PageBean` wrapper using PageHelper
- Multi-criteria search support (name, gender, date ranges, etc.)

## Database Configuration

Database connection is configured in `application.properties`:
- Uses remote MySQL database (configured in spring.datasource.url)
- MyBatis features enabled:
  - Console SQL logging: `mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl`
  - Camel case mapping: `mybatis.configuration.map-underscore-to-camel-case=true`

MyBatis XML mappers are located in `resources/com/itheima/mapper/` (matching the package structure).

## MyBatis Configuration

- **PageHelper** is configured for pagination - call `PageHelper.startPage()` before queries
- XML mappers follow package structure: `resources/com/itheima/mapper/[Entity]Mapper.xml`
- Camel case mapping automatically converts `column_name` ↔ `columnName`

## Code Style

- Lombok annotations used extensively (`@Data`, `@Slf4j`, etc.)
- Slf4j with `@Slf4j` annotation for logging
- Chinese comments in code
- Component scanning defaults to `com.itheima` package and subpackages
