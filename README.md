# p2evik

## Overview
p2evik is a work-in-progress application featuring a Spring Boot RESTful API resource server, an Angular client, and OAuth2.0 authentication using Keycloak.
### API Documentation
The API documentation is available via Swagger.
`/api-docs/index.html`

### Docker Compose
The project includes a docker-compose setup to run the backend, MariaDB, PostgreSQL for Keycloak, and Keycloak itself.

#### Services
- backend: The Spring Boot application.
- maria-db: MariaDB database for the application.
- postgres-keycloak: PostgreSQL database for Keycloak.
- keycloak: Keycloak server for authentication and authorization.

Usage
To start the services, run:
```shell
docker-compose up
```

### Angular Client UI
The project includes an Angular client UI that interacts with the backend API. The client runs on http://localhost:4200.

### API Endpoints
- /api-docs/index.html: Swagger API documentation.

### Environment Variables
The project uses environment variables to configure the services. Create a `.env` file in the root directory with the following variables and chane the values accordingly.:

```dotenv
DB_ROOT_PASSWORD=your_root_password
DB_USER=your_username
DB_PASSWORD=your_password
DB_DATABASE=your_database
DB_PORT=3306
DB_HOST=maria-db

KEYCLOAK_ADMIN_PASSWORD=your_password
KEYCLOAK_DB_ROOT_PASSWORD=your_root_password
KEYCLOAK_DB_PASSWORD=your_password
KEYCLOAK_DB_USER=postgres
KEYCLOAK_DB_DATABASE=keycloak_db
KEYCLOAK_DB_PORT=5432
KEYCLOAK_DB_HOST=postgres-keycloak
```