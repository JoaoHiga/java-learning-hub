# java-learning-hub

Repositorio central de mi proceso de aprendizaje hacia **Java Backend Developer** en entornos enterprise.

Documenta el progreso semana a semana a través de katas, ejercicios y proyectos progresivos — siguiendo un roadmap de ~27 semanas orientado a microservicios, SaaS y sistemas distribuidos.

**Stack principal:** Java 21 · Quarkus · PostgreSQL · Kafka · Redis · Kubernetes  
**Entorno:** WSL2 (Ubuntu 22.04) · VS Code · GraalVM JDK 21

---

## Fases del roadmap

| Fase | Contenido | Estado |
|------|-----------|--------|
| 0 | Java 21 moderno — records, sealed, streams, concurrencia | 🟡 En progreso |
| 1 | Quarkus core — CDI, RESTEasy Reactive, Panache, TDD | ⬜ Pendiente |
| 2 | Datos, Resiliencia, Seguridad — JPA, Redis, Resilience4j, Keycloak | ⬜ Pendiente |
| 3 | Arquitectura — DDD, hexagonal, Kafka, Saga pattern | ⬜ Pendiente |
| 4 | Spring Boot + Observabilidad | ⬜ Pendiente |
| 5 | Kubernetes, CI/CD, JVM internals | ⬜ Pendiente |
| 6 | Proyecto final — Plataforma SaaS completa | ⬜ Pendiente |

---

## Estructura del repositorio

```
java-learning-hub/
├── 01-java-modern/         # Fase 0 — Katas Java 21
├── 02-quarkus-core/        # Fase 1 — Ejercicios Quarkus
├── 03-jvm-internals/       # Fase 5 — Experimentos JFR, GC
├── notes/                  # Notas por área en Markdown
└── reviews/                # Temas pendientes de revisión espaciada
```

---

## Entorno local

```bash
# Requisitos
# - WSL2 con Ubuntu 22.04
# - SDKMAN con GraalVM JDK 21
# - Maven 3.9+
# - Docker Desktop con integración WSL2

# Verificar entorno
java -version   # openjdk 21 GraalVM CE
mvn -version
quarkus --version
```

---

## Convención de commits

```
feat(área):    nueva implementación
test(área):    nuevo test
learn(área):   experimento o exploración
docs(área):    notas y documentación
fix(área):     corrección
refactor(área): mejora sin cambio de comportamiento
```

---

## Progreso

Seguimiento activo en [GitHub Projects](../../projects).