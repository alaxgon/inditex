# Price Service – Inditex Technical Exercise

A Spring Boot 3.x micro‑service that exposes product **price** information following Inditex’s requirements. The code is structured using a *hexagonal* (ports & adapters) approach and is fully covered by automated tests.

---
## 0  Tech stack
| Category | Technology |
|----------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3, Spring Data JPA |
| Database | H2 in‑memory (dev/test) |
| Build | Maven 3.9 + Jacoco |
| Helpers | Lombok, AssertJ, JUnit 5, Mockito |

---
## 1  How to run
```bash
# Build, run all tests and generate coverage report
mvn clean verify

# Start the application (dev profile)
mvn spring-boot:run
```
The service starts on **http://localhost:8080**.

---
## 2  Completed milestones
| Step | Conventional commit | Highlights |
|------|---------------------|------------|
| **1** | `chore: bootstrap Spring Boot project with H2 and Jacoco` | Skeleton, hexagonal packages, Jacoco configured, context‑load test |
| **2** | `feat: add Price entity with auto‑DDL and seed data` | `Price` JPA entity, Hibernate auto‑DDL, `data.sql` seeds (4 rows) |
| **3** | `feat: add PriceRepository with priority-based query and tests` | Native query returning a single price for a date/product/brand, repository integration tests |
| **4** | `feat: add PriceService with DTO mapping and unit tests` → `fix: correct parameter order for findApplicablePrice` | Domain service, `PriceDto`, happy path & error tests, parameter‑order fix |

---
## 3  Pending work
| Upcoming | Description |
|----------|-------------|
| **5** | REST controller `/prices` ↔︎ maps request params to `PriceService` |
| **6** | Global exception handling via `@ControllerAdvice` |
| **7** | Extra edge‑case, integration & WebMvc tests; enforce Jacoco ≥ 90 % |
| **8** | Final README polish, badges and Dockerfile (optional) |

---
## 4  Architecture in brief
```
┌───────────────┐   query    ┌──────────────────┐
│  REST client  │ ─────────▶ │     Controller    │
└───────────────┘            └─────────▲────────┘
                                       │ DTO
                                       │
                                  ┌────▼─────┐  port   ┌──────────────────┐
                                  │ Service  │────────▶│ Repository port   │
                                  └────▲─────┘         └──────────────────┘
                                       │                    ▲
                                       │ entity             │ JPA adapter
                               ┌───────▼────────┐           │
                               │  H2 database   │◀──────────┘
                               └─────────────────┘
```
*The domain (service + port) remains completely framework‑agnostic.*

---
## 5  Testing & coverage
Run all tests and view the Jacoco report:
```bash
mvn clean verify
open target/site/jacoco/index.html
```
A build will fail if coverage drops below the threshold (configured once we reach Step 7).

---
## 6  Conventional commit rules
```text
feat:     a new feature
fix:      a bug fix
docs:     documentation only changes
test:     adding or refactoring tests
chore:    tooling / build / config
```