# AndKasir Desktop v2.0 - Modern Desktop POS System

## Project Overview

AndKasir Desktop v2.0 is a modern Point of Sale (POS) application built with Java 21, JavaFX 21, and Spring Boot 3. This version represents a complete migration from the legacy Swing-based application to modern technologies.

## Technology Stack

- **Java 21 LTS** - Latest Java Long-Term Support version
- **JavaFX 21** - Modern UI framework for desktop applications
- **Spring Boot 3** - Application framework with dependency injection
- **Spring Data JPA** - Data access layer with Hibernate
- **PostgreSQL 15+** - Modern relational database (with H2 for development)
- **Maven 3.9+** - Build and dependency management
- **Flyway** - Database version control and migrations

## Key Features

### Modern Architecture
- **MVC Pattern** - Clean separation of concerns with Controllers, Services, and Repositories
- **JPA Entities** - Modern ORM with proper validation and business logic
- **Spring Security** - BCrypt password hashing and role-based authentication
- **FXML Views** - Declarative UI with CSS styling
- **Responsive Design** - Modern dark theme with responsive layout

### Core Functionality
- **User Management** - Admin and Kasir roles with authentication
- **Product Management** - CRUD operations with inventory tracking
- **Transaction Processing** - Complete sales workflow with tax and discounts
- **Reporting** - Daily/monthly sales reports with PDF export
- **Real-time Updates** - Live inventory and dashboard updates

### Database Features
- **Schema Evolution** - Flyway migrations for version control
- **Performance Indexes** - Optimized queries for large datasets
- **JSON Storage** - Flexible transaction detail storage
- **Soft Deletes** - Data retention and recovery options

## Project Structure

```
modern/
├── src/main/java/com/andkasir/
│   ├── AndKasirApplication.java      # Main application entry point
│   ├── config/                       # Configuration classes
│   │   ├── AppConfig.java             # Application properties
│   │   └── ApplicationContextProvider.java # Spring context access
│   ├── controller/                   # JavaFX controllers
│   │   ├── LoginController.java       # Login screen logic
│   │   └── MainController.java       # Main dashboard
│   ├── entity/                       # JPA entities
│   │   ├── User.java                # User entity with roles
│   │   ├── Barang.java              # Product entity
│   │   ├── Transaksi.java           # Transaction entity
│   │   └── DetailTransaksi.java     # Transaction details
│   ├── repository/                   # Spring Data repositories
│   │   ├── UserRepository.java
│   │   ├── BarangRepository.java
│   │   └── TransaksiRepository.java
│   └── service/                      # Business logic services
│       └── AuthService.java           # Authentication logic
├── src/main/resources/
│   ├── css/
│   │   └── modern-dark.css         # Modern dark theme
│   ├── fxml/
│   │   ├── login.fxml              # Login screen layout
│   │   └── main.fxml              # Main dashboard layout
│   └── db/migration/
│       └── V1__Create_initial_schema.sql # Database schema
└── pom.xml                         # Maven configuration
```

## Getting Started

### Prerequisites
- **Java 21** or higher
- **Maven 3.9** or higher
- **PostgreSQL 15+** (for production) or H2 (for development)

### Running the Application

#### Development Mode (H2 Database)
```bash
cd modern
mvn clean javafx:run
```

#### Production Mode (PostgreSQL)
```bash
cd modern
mvn clean javafx:run -Dspring.profiles.active=prod
```

### Building for Distribution
```bash
mvn clean package
# Creates executable JAR in target/ directory
```

## Configuration

### Application Properties
The application uses YAML configuration in `src/main/resources/application.yml`:

- **Database Settings** - Connection strings and credentials
- **PDF Configuration** - Font sizes and margins
- **Backup Settings** - Automated backup schedules
- **UI Configuration** - Theme and display options

### Default Credentials
- **Admin User**: `admin` / `admin`
- **Kasir User**: `kasir` / `kasir`

## Development

### Database Migrations
New database changes should be added as Flyway migration files:
- Location: `src/main/resources/db/migration/`
- Naming: `V{number}__Description.sql`
- Example: `V2__Add_user_audit_fields.sql`

### Adding New Features
1. **Entity** - Create/update JPA entity in `entity/` package
2. **Repository** - Add Spring Data repository in `repository/` package
3. **Service** - Implement business logic in `service/` package
4. **Controller** - Create JavaFX controller in `controller/` package
5. **FXML** - Design UI layout in `fxml/` directory
6. **CSS** - Style components in `css/` directory

### Testing
```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify
```

## Migration from Legacy Version

This modern version maintains compatibility with the original AndKasir data while providing:

### Enhanced Security
- BCrypt password hashing (replacing plain text)
- Role-based access control
- Session management
- Input validation

### Improved Performance
- Optimized database queries with indexes
- Lazy loading for large datasets
- Connection pooling
- Caching strategies

### Better User Experience
- Modern dark theme interface
- Responsive design for different screen sizes
- Real-time form validation
- Keyboard shortcuts and accelerators
- Progress indicators for long operations

### Maintainability
- Clean separation of concerns
- Type-safe database operations
- Comprehensive error handling
- Automated testing support
- Version-controlled database schema

## Deployment

### Windows Installer
```bash
jpackage --name AndKasir --input target/classes --main-jar andkasir-desktop-2.0.0.jar --win-dir-chooser --win-menu
```

### macOS Bundle
```bash
jpackage --name AndKasir --input target/classes --main-jar andkasir-desktop-2.0.0.jar --mac-package-name andkasir --type dmg
```

### Linux Package
```bash
jpackage --name AndKasir --input target/classes --main-jar andkasir-desktop-2.0.0.jar --linux-package-name andkasir --type deb
```

## Future Enhancements

- **Multi-language Support** - Internationalization (i18n)
- **Cloud Sync** - Real-time data synchronization
- **Mobile App** - Companion mobile application
- **Advanced Reporting** - Business intelligence features
- **Plugin System** - Extensible architecture
- **Offline Mode** - Local storage with cloud sync

## Contributing

1. Fork the repository
2. Create feature branch from `modern-desktop-migration`
3. Implement changes with proper testing
4. Submit pull request with detailed description

## License

This project maintains the original license while adding modern development practices and enterprise-ready features.