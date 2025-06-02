# Price Service – Inditex Technical Exercise

A Spring Boot 3.x micro‑service that exposes product **price** information following Inditex’s requirements. The code is structured using a *hexagonal* (ports & adapters) approach and is fully covered by automated tests.

---
## 0  Tech stack
| Category | Technology |
|----------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3, Spring Data JPA |
| Database | H2 in‑memory (dev/test) |
| Build | Maven 3.9 + Jacoco |
| Helpers | Lombok, AssertJ, JUnit 5 |

---
## 1  How to run
```bash
# Build, test and run coverage
mvn clean verify

# Start the application
mvn spring-boot:run
```
The service will start on **http://localhost:8080**.

---
## 2  Completed milestones
| Step | Conventional commit | Highlights |
|------|---------------------|------------|
| **1** | `chore: bootstrap Spring Boot project with H2 and Jacoco` | Project skeleton, hexagonal packages, Jacoco configured, basic context‑load test |
| **2** | `feat: add Price entity with auto‑DDL and seed data` | `Price` JPA entity, Hibernate auto‑DDL, `data.sql` seeds (4 rows) |
| **3** | `feat: add PriceRepository with priority-based query and tests` | Native query returning a single price for a date/product/brand, repository integration tests |

---
## 3  Pending work
| Upcoming | Description |
|----------|-------------|
| **4** | Domain service (`PriceService`) and DTO mapping |
| **5** | REST controller `/prices` |
| **6** | Global exception handling |
| **7** | Additional unit & integration tests (edge cases, error paths) |
| **8** | Final README polishing and badges |

---
## 4  Architecture in brief
```
┌───────────────┐   query   ┌──────────────────┐
│  REST client  │ ────────▶ │     Controller    │
└───────────────┘           └─────────▲────────┘
                                      │ DTO
                                      │
                                 ┌────▼─────┐  port   ┌──────────────────┐
                                 │ Service  │────────▶│ Repository port   │
                                 └────▲─────┘         └──────────────────┘
                                      │
                                      │ entity
                              ┌───────▼────────┐
                              │  JPA Adapter   │
                              └─────────────────┘
```
*The core domain (service + port) remains completely framework‑agnostic.*

---
## 5  Testing & coverage
Run all tests and view the coverage report:
```bash
mvn clean verify
open target/site/jacoco/index.html
```

---
## 6  Conventional commit rules
```text
feat:     a new feature
fix:      a bug fix
docs:     documentation only changes
test:     adding or refactoring tests
chore:    tooling / build / config
```
