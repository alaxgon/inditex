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
Service runs at **http://localhost:8080**.

---
## 2  Completed milestones
| Step | Conventional commit | Highlights |
|------|---------------------|------------|
| **1** | `chore: bootstrap Spring Boot project with H2 and Jacoco` | Skeleton, hexagonal packages, Jacoco configured, context‑load test |
| **2** | `feat: add Price entity with auto‑DDL and seed data` | `Price` JPA entity, Hibernate auto‑DDL, `data.sql` seeds (4 rows) |
| **3** | `feat: add PriceRepository with priority-based query and tests` | Native query returning single price, repository integration tests |
| **4** | `feat: add PriceService with DTO mapping and unit tests` → `fix: correct parameter order` | Domain service, happy/error tests |
| **5** | `feat: add PriceController REST endpoint and web tests` | `/prices` endpoint, MockMvc test |
| **6** | `feat: add global exception handler and error tests` | `@ControllerAdvice` → 404 & 400 JSON |
| **7** | `test: add edge‑case integration tests and enforce Jacoco ≥90%` | Five official scenarios, 404 product/brand, Jacoco **check** rule |

---
## 3  Pending work
| Upcoming | Description |
|----------|-------------|
| **8** | Final README polish, coverage badge, optional Dockerfile |

---
## 4  Architecture (overview)
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
*The domain remains framework‑agnostic.*

---
## 5  Testing & coverage
* Full suite (unit + integration + web):
  ```bash
  mvn clean verify
  open target/site/jacoco/index.html
  ```
* **Build fails if line coverage < 90 %** (`jacoco-maven-plugin` *check* rule).

---
## 6  Conventional commit rules
```text
feat:     a new feature
fix:      a bug fix
docs:     documentation changes
test:     adding/refactoring tests
chore:    tooling / build / config
```
