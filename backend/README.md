# Flux Backend

A production-ready Spring Boot 3 backend for a Netflix-like streaming platform.

## Tech Stack

- **Java**: 17
- **Framework**: Spring Boot 3.2.2
- **Build Tool**: Gradle (Groovy DSL)
- **Database**: PostgreSQL
- **Architecture**: Modular Monolith

## Project Structure

```
com.flux.streaming
 ├── auth         # Authentication and authorization
 ├── user         # User management
 ├── catalog      # Content catalog management
 ├── playback     # Video playback services
 ├── media        # Media processing
 ├── admin        # Admin operations
 ├── config       # Application configuration
 └── common       # Shared utilities and controllers
```

## Prerequisites

- Java 17 or higher
- PostgreSQL 14 or higher
- Gradle 8.x (wrapper included)

## Running Locally

### 1. Start PostgreSQL

```bash
docker run --name flux-postgres \
  -e POSTGRES_USER=flux_user \
  -e POSTGRES_PASSWORD=flux_password \
  -e POSTGRES_DB=flux_db \
  -p 5432:5432 \
  -d postgres:14
```

### 2. Run the application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

### 3. Health Check

```bash
curl http://localhost:8080/health
```

Expected response:
```json
{
  "status": "UP",
  "service": "Flux Backend"
}
```

## Building

### Build JAR

```bash
./gradlew clean build
```

The JAR will be available at `build/libs/flux-backend-1.0.0.jar`

### Build Docker Image

```bash
docker build -t flux-backend:latest .
```

### Run Docker Container

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/flux_db \
  -e SPRING_DATASOURCE_USERNAME=flux_user \
  -e SPRING_DATASOURCE_PASSWORD=flux_password \
  flux-backend:latest
```

## Configuration

Configuration is managed through `application.yml`. Key properties:

- `server.port`: Application port (default: 8080)
- `spring.datasource.*`: Database connection settings
- `spring.jpa.hibernate.ddl-auto`: Hibernate DDL mode (update)

## Development

### Hot Reload

Spring Boot DevTools is included for development. Changes to classes will trigger automatic restart.

### Testing

```bash
./gradlew test
```

## API Documentation

### Health Endpoint

- **URL**: `/health`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "status": "UP",
    "service": "Flux Backend"
  }
  ```

## Security

Currently, all endpoints are open for development. Security will be configured as features are added.

## License

Proprietary
