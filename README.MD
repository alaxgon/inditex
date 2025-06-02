# Inditex Price Service – Commit 1

Spring Boot application bootstrap.

## What's included in this commit?

- **pom.xml** with essential dependencies: Web, Data JPA, H2, Lombok and Jacoco for test coverage.
- **Initial package structure** following a hexagonal pattern (`com.inditex.prices`).
- **Main class** `PriceServiceApplication`.
- **Smoke test** `PriceServiceApplicationTests` verifying that the Spring context starts up.
- **application.properties** minimal configuration to run an in‐memory H2 database.
- **Java 17** build.

## How to run

```bash
# Run the application
./mvnw spring-boot:run

# Execute tests and open the Jacoco report
./mvnw test
# The HTML report is generated at: target/site/jacoco/index.html
```

## Next steps

1. Create the `Price` entity and the `schema.sql` / `data.sql` scripts.
2. Define `PriceRepository` and queries.
3. Implement the domain service, DTOs and REST controller.
4. Add exception handling and tests with > 90 % coverage.