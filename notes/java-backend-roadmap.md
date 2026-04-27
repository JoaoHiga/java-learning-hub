# 🗺️ Roadmap Intensivo: Java Backend Developer Enterprise
> Perfil: Java general → Especialista Backend | Quarkus-first | 2h/día | WSL2 | SaaS/Enterprise

---

## SECCIÓN 1 — ROADMAP DE FASES

### Vista General

| Fase | Nombre | Duración | Hito Principal | Empleabilidad |
|------|--------|----------|----------------|---------------|
| 0 | Bootstrap WSL2 + Java Moderno | 2 semanas | Entorno funcional, Java 21 fluido | — |
| 1 | Quarkus Core + TDD | 4 semanas | API REST con Quarkus + tests reales | Junior posible |
| 2 | Datos, Resiliencia y Seguridad | 5 semanas | Microservicio productizable | Junior sólido |
| 3 | Arquitectura y Sistemas Distribuidos | 5 semanas | Sistema distribuido con Kafka + Saga | Mid level |
| 4 | Spring Boot + Observabilidad | 3 semanas | Bilingüe Quarkus/Spring | Mid sólido |
| 5 | Kubernetes, CI/CD y Performance JVM | 4 semanas | Sistema deployable en K8s | Mid senior |
| 6 | Proyecto Final SaaS | 4 semanas | Plataforma SaaS completa en portafolio | Senior target |

**Total estimado: ~27 semanas (~6-7 meses a 2h/día)**

---

### FASE 0 — Bootstrap WSL2 + Java Moderno
**Duración:** 2 semanas | **Prerequisito:** ninguno

#### Objetivos
- Entorno WSL2 completamente operativo para desarrollo Java profesional
- Dominio real de Java 11–21: streams, lambdas, Optional, records, pattern matching, concurrencia moderna
- Primera práctica de TDD con JUnit 5

#### Temas
- Setup WSL2: SDKMAN, JDK 21, Quarkus CLI, Maven, Gradle, GraalVM, Docker, VS Code
- Java 17/21: `var`, records, sealed classes, switch expressions, text blocks
- Streams avanzados: `flatMap`, `collect`, `Collectors.groupingBy`, `teeing`
- Optional correcto: cuándo usar, cuándo NO usar
- CompletableFuture: `thenApply`, `thenCompose`, `allOf`, manejo de errores
- Virtual Threads (Project Loom): diferencia con OS threads, cuándo usar

#### Hito al terminar
> "Puedo escribir Java 21 moderno fluido, resolver katas algorítmicas con streams y completar el setup WSL2 sin ayuda."

#### Impacto WSL2
- Todo el desarrollo ocurre dentro de WSL2 desde el día 1 (`/home/tu_usuario/dev/`)
- Docker Desktop configurado con integración WSL2 backend
- VS Code abre con `code .` desde WSL2 terminal

---

### FASE 1 — Quarkus Core + TDD
**Duración:** 4 semanas | **Prerequisito:** Fase 0

#### Objetivos
- Construir APIs REST con Quarkus usando CDI, RESTEasy Reactive
- TDD como práctica diaria, no como tema
- Panache para acceso a datos simple
- Dev Services de Quarkus (PostgreSQL, Redis sin config manual)

#### Temas
- CDI: `@ApplicationScoped`, `@RequestScoped`, `@Inject`, producers, qualifiers
- RESTEasy Reactive: `@GET/@POST/@PUT/@DELETE`, `@PathParam`, `@QueryParam`, `@Valid`
- Panache: `PanacheEntity`, `PanacheRepository`, queries con HQL
- MicroProfile Config: configuración en `application.properties`, profiles, `@ConfigProperty`
- QuarkusTest: `@QuarkusTest`, `RestAssured`, `@InjectMock`
- Testcontainers con Quarkus: `@QuarkusTestResource`
- JUnit 5 completo: `@ParameterizedTest`, `@ExtendWith`, lifecycle, nested tests
- Mockito: `@Mock`, `@Spy`, `when/thenReturn`, `verify`, `ArgumentCaptor`

#### Hito al terminar
> "Tengo una API CRUD con Quarkus + PostgreSQL, documentada con OpenAPI, con tests de unidad e integración, y mi primer repo en GitHub con README profesional."

#### Empleabilidad
Esta fase + un repo bien documentado es suficiente para postular a roles **junior** en empresas con stack moderno.

---

### FASE 2 — Datos, Resiliencia y Seguridad
**Duración:** 5 semanas | **Prerequisito:** Fase 1

#### Objetivos
- Dominar acceso a datos avanzado: JPA/Hibernate, transacciones, N+1, Flyway
- Caché distribuida con Redis
- Resiliencia real con Resilience4j
- Autenticación/Autorización con JWT, OAuth2, Keycloak

#### Temas (semana a semana)
- **Sem 1-2:** JPA avanzado: `@OneToMany`/`@ManyToMany`, `FetchType`, `@EntityGraph`, `JPQL` vs `Criteria API`, transacciones (`@Transactional`, propagation), problema N+1 y soluciones, HikariCP/Agroal config
- **Sem 2:** Flyway: migraciones versionadas, baseline, repair. Liquibase como alternativa.
- **Sem 3:** Redis: caché distribuida con Quarkus Redis client, `cache-aside`, `write-through`, TTL, `pub/sub`, Redis Streams. Caché en memoria con Caffeine.
- **Sem 4:** Resilience4j: `CircuitBreaker` (CLOSED/OPEN/HALF_OPEN), `Retry` con backoff exponencial, `Bulkhead` (semaphore vs thread pool), `RateLimiter`, `TimeLimiter`. Integración con Quarkus SmallRye Fault Tolerance.
- **Sem 5:** Seguridad: JWT generación/validación, OAuth2 flows (authorization code, client credentials), OIDC con Keycloak, RBAC en Quarkus Security, `@RolesAllowed`, OWASP Top 10 para APIs

#### Escenario real de producción — Resilience4j
> **Scenario:** Tu servicio de pagos llama a una API externa de Stripe. En un incidente real, Stripe devuelve 503 durante 8 minutos. Sin circuit breaker, cada request bloquea un thread durante el timeout (30s), consumes connection pool, y tu servicio se cae en cascada. Con `CircuitBreaker` + `TimeLimiter(2s)` + `Retry(3 intentos, backoff exponencial)`, el servicio detecta el patrón de fallos, abre el circuito, devuelve una respuesta degradada ("pago en cola, se procesará cuando Stripe vuelva"), y protege tu sistema completo.

#### Hito al terminar
> "Tengo un microservicio con auth completa via Keycloak, caché Redis, circuit breaker ante fallos externos, migraciones de BD con Flyway, y tests de integración con Testcontainers para todo."

---

### FASE 3 — Arquitectura y Sistemas Distribuidos
**Duración:** 5 semanas | **Prerequisito:** Fase 2

#### Objetivos
- DDD aplicado: no teórico, sino en código real
- Arquitectura hexagonal implementada y validada con ArchUnit
- Kafka para mensajería asíncrona
- Patrones Saga y Outbox implementados

#### Temas (semana a semana)
- **Sem 1:** DDD: Bounded Context, Aggregate (invariantes, root entity), Value Object, Domain Event, Repository pattern, Application Service vs Domain Service. Ubiquitous Language documentado.
- **Sem 2:** Arquitectura hexagonal/Clean: ports & adapters, capa domain sin dependencias externas, infrastructure adapters, Application layer. ArchUnit para validar que no hay violaciones.
- **Sem 3:** CQRS: separación Command/Query, proyecciones. Event Sourcing básico: event store, replay, snapshot.
- **Sem 4:** Apache Kafka: producer/consumer con Quarkus Reactive Messaging, particiones, consumer groups, `@Incoming/@Outgoing`, serialización con Avro/JSON. RabbitMQ como comparativa.
- **Sem 5:** Patrones distribuidos: Saga (coreografía vs orquestación), Outbox Pattern (evita dual-write), Idempotency keys, Dead Letter Queue

#### Hito al terminar
> "Tengo un sistema de dos microservicios comunicándose via Kafka, con Saga implementado para un flujo de orden de compra, arquitectura hexagonal validada por ArchUnit, y documentado con ADRs (Architecture Decision Records)."

#### Empleabilidad
Al terminar esta fase estás en rango **mid level** real para roles de microservicios enterprise.

---

### FASE 4 — Spring Boot + Comparativa + Observabilidad
**Duración:** 3 semanas | **Prerequisito:** Fase 3

#### Objetivos
- Ser funcional en Spring Boot (empleabilidad ampliada)
- Entender cuándo Spring y cuándo Quarkus
- Implementar observabilidad completa (metrics, traces, logs)

#### Temas
- **Sem 1:** Spring Boot: `@RestController`, `@Service`, `@Repository`, Spring Data JPA, `@Transactional`, Spring Security con JWT, Spring WebFlux básico
- **Sem 2:** Comparativa real: startup time, memory footprint, native image, Dev Services vs Spring Boot DevTools, CDI vs Spring IoC. Tabla de decisión.
- **Sem 3:** Observabilidad: OpenTelemetry SDK, Micrometer, Prometheus + Grafana (dashboards con Quarkus metrics), distributed tracing con Jaeger/Tempo, structured logging JSON con `traceId/spanId` correlation, health checks (`/q/health`)

#### Hito al terminar
> "Puedo implementar una feature nueva en Spring Boot en un repo que no escribí. Tengo dashboards Grafana mostrando métricas de mis servicios y trazas distribuidas end-to-end."

---

### FASE 5 — Kubernetes, CI/CD y Performance JVM
**Duración:** 4 semanas | **Prerequisito:** Fase 4

#### Objetivos
- Desplegar en Kubernetes con confianza
- CI/CD production-grade con GitHub Actions
- Entender JVM internals para diagnosticar problemas de producción

#### Temas
- **Sem 1-2:** Kubernetes: `Deployment`, `Service`, `ConfigMap`, `Secret`, `HPA`, liveness/readiness/startup probes, `Namespace`, `ResourceQuota`. Helm: charts básicos, `values.yaml`, `helm upgrade`.
- **Sem 2:** Docker: Dockerfile multi-stage para JVM y para native image GraalVM, `.dockerignore`, tamaño de imagen, `docker-compose` para entornos locales completos.
- **Sem 3:** GitHub Actions: pipeline completo: lint → test → análisis estático (SonarCloud) → build Docker → push registry → deploy K8s. Secrets en GitHub Actions.
- **Sem 4:** JVM internals: GC (G1, ZGC, Shenandoah) — cómo elegirlos según workload, JFR (Java Flight Recorder), async-profiler, heap dumps con `jmap/jcmd`, análisis con VisualVM/JDK Mission Control, GraalVM native image: AOT compilation, reflection config, `native-image.properties`

#### Escenario real de producción — JVM internals
> **Scenario:** Tu servicio en producción empieza a tener latencias de 800ms cada ~45 segundos. Los logs parecen normales. El culpable: Full GC pauses del GC por defecto. Con JFR habilitado (`-XX:StartFlightRecording`) analizas en JDK Mission Control y ves GC pauses de 600ms+. Migras a ZGC (`-XX:+UseZGC`) y las pauses bajan a < 5ms. Sin conocer JVM internals, habrías estado cambiando configuraciones al azar durante días.

#### Hito al terminar
> "Tengo un pipeline CI/CD completo en GitHub Actions que deploya automáticamente a un cluster K8s (minikube local o cluster cloud). Puedo diagnosticar un memory leak con JFR."

---

### FASE 6 — Proyecto Final: Plataforma SaaS
**Duración:** 4 semanas | **Prerequisito:** Fase 5

#### Objetivo
Construir una plataforma SaaS backend completa que sea portafolio real.

Ver Sección 5 para descripción detallada.

#### Hito al terminar
> "Tengo una plataforma SaaS backend completa, multi-tenant, con auth Keycloak, Kafka, rate limiting, CI/CD, K8s, observabilidad, y documentación técnica de nivel profesional. Estoy listo para roles senior/tech lead."

---

## SECCIÓN 2 — PLAN SEMANAL TIPO

### Estructura Diaria (2 horas)

| Bloque | Tiempo | Actividad |
|--------|--------|-----------|
| A | 25 min | Teoría: leer docs oficiales, libro, o ver video (velocidad 1.5x) |
| B | 60 min | **Código con TDD**: escribir test primero, implementar, refactorizar |
| C | 20 min | Commit en GitHub + actualizar issue/project board |
| D | 15 min | Revisión espaciada (ver abajo) |

### TDD Como Práctica Diaria (desde Día 1)

**No es un tema, es el método de trabajo:**

```
Día 1: "Aprendo streams" → escribo tests que demuestran lo que aprendí
Día 5: "Aprendo CDI" → escribo @QuarkusTest primero, luego el @Service
Día 20: "Aprendo Redis" → escribo test de integración con Testcontainers Redis primero
```

**Ritual TDD:**
1. `git checkout -b feat/tema-del-dia`
2. Crear test que falla (`@Test`, `@QuarkusTest`)
3. Escribir código mínimo para pasar
4. Refactorizar
5. `git commit -m "test(streams): groupingBy por categoría con downstream collector"`
6. Push + cerrar issue en GitHub Projects

### Sistema de Revisión Espaciada

Usa el algoritmo SM-2 simplificado:

| Cuándo repasar | Cómo |
|----------------|------|
| 1 día después | Mini kata de 10 min sobre el tema |
| 7 días después | Explicar el concepto como si enseñaras (Rubber Duck) |
| 30 días después | Resolver un ejercicio nuevo que use ese concepto |
| 90 días después | Usarlo en el proyecto actual |

**Implementación práctica:** Crea issues en GitHub con label `review` y fecha en el título: `[REVIEW 2025-02-15] JPA N+1 pattern`

### Estructura Semanal

| Día | Foco |
|-----|------|
| Lunes | Tema nuevo: teoría (Bloque A) + primer ejercicio TDD |
| Martes | Profundizar tema: ejercicio más complejo, casos edge |
| Miércoles | Aplicar al proyecto en curso |
| Jueves | Tema complementario o revisión espaciada |
| Viernes | Aplicar al proyecto + commit de avance semanal |
| Sábado | Review semanal: ¿qué aprendí? ¿qué falta? Actualizar GitHub Projects |
| Domingo | Descanso o lectura ligera (blog post, release notes) |

---

## SECCIÓN 3 — LECCIONES ESTRUCTURADAS POR ÁREA

### ÁREA 1: Java Moderno (Fase 0)

#### Lección 1.1 — Records y Sealed Classes
**Objetivo:** Reemplazar boilerplate con features modernas de Java 17+

```java
// Ejercicio: Modelar un resultado de operación bancaria
public sealed interface TransferResult permits Success, Failure {
    record Success(String transactionId, BigDecimal amount) implements TransferResult {}
    record Failure(String code, String message) implements TransferResult {}
}

// Test que debes escribir primero:
@Test
void shouldHandleTransferResultWithPatternMatching() {
    TransferResult result = new TransferResult.Success("TXN-001", BigDecimal.TEN);
    String message = switch (result) {
        case TransferResult.Success s -> "Transferencia exitosa: " + s.transactionId();
        case TransferResult.Failure f -> "Error " + f.code() + ": " + f.message();
    };
    assertThat(message).startsWith("Transferencia exitosa");
}
```

**Output esperado:** Clase `TransferResult` con tests pasando, en repo GitHub bajo `java-modern/src/test/`

---

#### Lección 1.2 — Streams Avanzados
**Objetivo:** Procesar colecciones complejas sin loops imperativos

```java
// Ejercicio: Dado un List<Order>, obtener el revenue total por categoría
// Solo órdenes del último mes, agrupadas, con subtotal
Map<String, BigDecimal> revenueByCategory = orders.stream()
    .filter(o -> o.createdAt().isAfter(oneMonthAgo))
    .collect(Collectors.groupingBy(
        Order::category,
        Collectors.reducing(BigDecimal.ZERO, Order::amount, BigDecimal::add)
    ));
```

**Output esperado:** Kata completa con 5 ejercicios de streams, todos con tests, subida a GitHub.

---

#### Lección 1.3 — CompletableFuture y Virtual Threads
**Objetivo:** Concurrencia moderna sin callbacks hell

```java
// Ejercicio: Llamar a 3 APIs externas en paralelo y combinar resultado
CompletableFuture<UserProfile> future = CompletableFuture
    .supplyAsync(() -> userService.getUser(userId))
    .thenCombine(
        CompletableFuture.supplyAsync(() -> orderService.getOrders(userId)),
        (user, orders) -> user.withOrders(orders)
    )
    .exceptionally(ex -> UserProfile.empty());

// Con Virtual Threads (Loom):
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    Future<UserProfile> result = executor.submit(() -> buildProfile(userId));
}
```

**Output esperado:** Comparativa de performance entre thread pool fijo, `CompletableFuture`, y Virtual Threads, con métricas medidas.

---

### ÁREA 2: Quarkus Core (Fase 1)

#### Lección 2.1 — CDI y RESTEasy Reactive

**Objetivo:** Construir una API REST reactiva con inyección correcta

```java
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    public Uni<List<ProductDto>> getAll(@QueryParam("category") String category) {
        return productService.findByCategory(category);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> create(@Valid CreateProductRequest request) {
        return productService.create(request)
            .map(product -> Response.created(URI.create("/products/" + product.id())).build());
    }
}

// Test primero:
@QuarkusTest
class ProductResourceTest {
    @Test
    void shouldReturn201WhenCreatingProduct() {
        given()
            .contentType(ContentType.JSON)
            .body("""{"name":"Laptop","price":999.99,"category":"electronics"}""")
        .when()
            .post("/products")
        .then()
            .statusCode(201)
            .header("Location", containsString("/products/"));
    }
}
```

**Output esperado:** API CRUD de productos con 80%+ test coverage, documentada con `@Operation` OpenAPI.

---

#### Lección 2.2 — Panache y Dev Services

**Objetivo:** Acceso a BD real en tests sin configuración manual

```java
@Entity
@Table(name = "products")
public class Product extends PanacheEntity {
    public String name;
    public BigDecimal price;
    public String category;
    public Instant createdAt;

    public static List<Product> findByCategory(String category) {
        return list("category", category);
    }
}

// Dev Services: agrega a application.properties
// quarkus.datasource.db-kind=postgresql
// quarkus.hibernate-orm.database.generation=drop-and-create
// → Quarkus levanta PostgreSQL automáticamente en tests y dev
```

**Output esperado:** Entidad con Panache, repository custom con queries complejas, migraciones Flyway V1 y V2.

---

### ÁREA 3: Resilience4j (Fase 2)

#### Lección 3.1 — Circuit Breaker Real

**Objetivo:** Proteger tu servicio de fallos en cascada

```java
// Anotación en Quarkus (SmallRye Fault Tolerance):
@ApplicationScoped
public class PaymentGatewayClient {

    @CircuitBreaker(
        requestVolumeThreshold = 5,
        failureRatio = 0.5,
        delay = 10000  // 10s en estado OPEN
    )
    @Retry(maxRetries = 3, delay = 500, jitter = 200)
    @Timeout(2000)
    @Fallback(fallbackMethod = "paymentFallback")
    public Uni<PaymentResult> processPayment(PaymentRequest request) {
        return stripeClient.charge(request);
    }

    private Uni<PaymentResult> paymentFallback(PaymentRequest request) {
        // Encolar para procesamiento posterior
        return queueService.enqueue(request)
            .map(queueId -> PaymentResult.pending(queueId));
    }
}
```

**Escenario de test:**
```java
@QuarkusTest
class PaymentCircuitBreakerTest {
    @InjectMock
    StripeClient stripeClient;

    @Test
    void shouldOpenCircuitAfterConsecutiveFailures() {
        // Arrange: Stripe falla 5 veces
        when(stripeClient.charge(any()))
            .thenReturn(Uni.createFrom().failure(new StripeException("503")));

        // Act: hacer 6 llamadas
        // Assert: la 6ta debe retornar fallback sin llamar a Stripe
    }
}
```

**Output esperado:** Servicio de pagos con todos los patrones de resiliencia, con tests que simulan fallos reales.

---

### ÁREA 4: Arquitectura Hexagonal (Fase 3)

#### Lección 4.1 — Estructura de Paquetes

```
src/main/java/com/empresa/orders/
├── domain/                          # Cero dependencias externas
│   ├── model/
│   │   ├── Order.java               # Aggregate root
│   │   ├── OrderItem.java
│   │   └── Money.java               # Value object
│   ├── event/
│   │   └── OrderPlacedEvent.java    # Domain event
│   ├── port/
│   │   ├── in/
│   │   │   └── PlaceOrderUseCase.java  # Input port (interface)
│   │   └── out/
│   │       ├── OrderRepository.java    # Output port (interface)
│   │       └── PaymentPort.java        # Output port (interface)
│   └── service/
│       └── OrderService.java        # Domain service
├── application/                     # Orquesta domain
│   └── PlaceOrderService.java       # Implementa input port
└── infrastructure/                  # Adapters
    ├── persistence/
    │   └── JpaOrderRepository.java  # Implementa output port
    ├── web/
    │   └── OrderResource.java       # Adapter REST
    └── messaging/
        └── KafkaOrderProducer.java  # Adapter Kafka
```

**Test ArchUnit:**
```java
@AnalyzeClasses(packages = "com.empresa.orders")
class ArchitectureTest {
    @ArchTest
    static final ArchRule domainShouldNotDependOnInfrastructure =
        noClasses().that().resideInAPackage("..domain..")
            .should().dependOnClassesThat()
            .resideInAPackage("..infrastructure..");

    @ArchTest
    static final ArchRule infrastructureShouldDependOnPorts =
        classes().that().resideInAPackage("..infrastructure..")
            .should().dependOnClassesThat()
            .resideInAPackage("..domain.port..");
}
```

**Output esperado:** Sistema de órdenes con arquitectura hexagonal, ArchUnit tests en verde, diagrama C4 en README.

---

### ÁREA 5: Multi-Tenancy SaaS (Fase 6)

#### Lección 5.1 — Row-Level Security con Hibernate Filters

```java
// Estrategia: shared schema con tenant_id en cada tabla
@Entity
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Product extends PanacheEntity {
    @Column(name = "tenant_id", nullable = false)
    public String tenantId;
    // ...
}

// Activar el filtro automáticamente
@ApplicationScoped
public class TenantAwareInterceptor {
    @Inject
    TenantContext tenantContext;

    @Inject
    EntityManager em;

    public void activateFilter() {
        Session session = em.unwrap(Session.class);
        session.enableFilter("tenantFilter")
               .setParameter("tenantId", tenantContext.getCurrentTenant());
    }
}

// Extraer tenant del JWT:
@ApplicationScoped
public class JwtTenantContext implements TenantContext {
    @Inject
    JsonWebToken jwt;

    public String getCurrentTenant() {
        return jwt.getClaim("tenant_id");
    }
}
```

**Output esperado:** Middleware de multi-tenancy completo con tests que demuestran aislamiento entre tenants.

---

## SECCIÓN 4 — RECURSOS CURADOS

### Java Moderno

| Recurso | Tipo | Costo | Prioridad |
|---------|------|-------|-----------|
| [JEP Dashboard](https://openjdk.org/jeps/0) | Docs oficiales | Gratis | Alta |
| "Modern Java in Action" (Urma, Fusco, Mycroft) | Libro | ~$50 | **Alta** |
| Curso "Java Masterclass" - Tim Buchalka (Udemy) | Curso | ~$15-20 | Media |
| JetBrains Academy Java track | Plataforma | Gratis/Pago | Media |
| Baeldung.com | Blog | Gratis | **Alta** |
| Inside Java Podcast | Podcast | Gratis | Media |

### Quarkus

| Recurso | Tipo | Costo | Prioridad |
|---------|------|-------|-----------|
| [quarkus.io/guides](https://quarkus.io/guides/) | Docs oficiales | Gratis | **Imprescindible** |
| "Quarkus in Action" (Manning) | Libro | ~$50 | **Alta** |
| Quarkus YouTube channel | Video | Gratis | **Alta** |
| Quarkusio/quarkus GitHub | Código | Gratis | Alta |
| Red Hat Developer Blog | Blog | Gratis | Media |

### Spring Boot

| Recurso | Tipo | Costo | Prioridad |
|---------|------|-------|-----------|
| [spring.io/guides](https://spring.io/guides) | Docs oficiales | Gratis | **Alta** |
| "Spring in Action" - Craig Walls | Libro | ~$50 | Alta |
| Amigoscode Spring Boot (YouTube) | Video | Gratis | Alta |
| Spring Tips (Josh Long, YouTube) | Video | Gratis | Alta |

### Arquitectura y DDD

| Recurso | Tipo | Costo | Prioridad |
|---------|------|-------|-----------|
| "Implementing DDD" - Vaughn Vernon | Libro | ~$55 | **Imprescindible** |
| "Clean Architecture" - Robert Martin | Libro | ~$35 | **Alta** |
| "Designing Data-Intensive Applications" - Kleppmann | Libro | ~$55 | **Imprescindible** para senior |
| "Building Microservices" - Sam Newman | Libro | ~$55 | Alta |
| "Architecture Patterns with Python" (adaptable a Java) | Libro | Gratis online | Media |
| DDD Community (dddcommunity.org) | Comunidad | Gratis | Media |

### Kafka

| Recurso | Tipo | Costo | Prioridad |
|---------|------|-------|-----------|
| [Confluent Kafka Fundamentals](https://developer.confluent.io/) | Curso | Gratis | **Alta** |
| "Kafka: The Definitive Guide" (O'Reilly) | Libro | Gratis con registro | **Alta** |
| Kafka con Quarkus (quarkus.io/guides/kafka) | Docs | Gratis | Alta |

### Kubernetes

| Recurso | Tipo | Costo | Prioridad |
|---------|------|-------|-----------|
| [kubernetes.io/docs](https://kubernetes.io/docs/) | Docs | Gratis | Alta |
| "Kubernetes in Action" - Marko Luksa | Libro | ~$55 | Alta |
| TechWorld with Nana (YouTube) | Video | Gratis | **Alta** |
| KodeKloud Kubernetes course | Curso | ~$15/mes | Alta |

### Entrevistas Técnicas

| Recurso | Tipo | Costo | Prioridad |
|---------|------|-------|-----------|
| "System Design Interview" - Alex Xu (Vol 1 y 2) | Libro | ~$35 | **Imprescindible** |
| LeetCode (medium problems, Java) | Plataforma | Gratis/Pago | Alta |
| [Java Brains (YouTube)](https://www.youtube.com/@JavaBrains) | Video | Gratis | Alta |
| GitHub: `kdn251/interviews` | Repo | Gratis | Media |
| GitHub: `donnemartin/system-design-primer` | Repo | Gratis | **Alta** |
| Pramp.com (mock interviews) | Plataforma | Gratis | Alta |

### Observabilidad

| Recurso | Tipo | Costo | Prioridad |
|---------|------|-------|-----------|
| [opentelemetry.io/docs](https://opentelemetry.io/docs/) | Docs | Gratis | Alta |
| Grafana Labs tutorials | Docs/Video | Gratis | Alta |
| "Observability Engineering" - O'Reilly | Libro | ~$55 | Media |

---

## SECCIÓN 5 — PROYECTOS PRÁCTICOS PROGRESIVOS

### Proyecto 0 — Java Kata Collection
**Fase:** 0 | **Duración:** 1-2 semanas

**Descripción:** Repositorio de katas algorítmicas y de Java moderno

**Stack:** Java 21, JUnit 5, Maven

**Conceptos:** Streams, records, sealed classes, pattern matching, CompletableFuture, Optional

**Estructura:**
```
java-katas/
├── src/test/java/
│   ├── streams/StreamsExercisesTest.java      # 10 ejercicios con streams
│   ├── records/RecordsExercisesTest.java      # Records + sealed + pattern matching
│   ├── concurrency/VirtualThreadsTest.java    # Virtual threads vs CompletableFuture
│   └── optional/OptionalBestPracticesTest.java
└── README.md                                  # Documenta qué aprendiste en cada kata
```

**Criterios de "listo":**
- [ ] 30+ tests en verde
- [ ] Cada clase tiene Javadoc explicando el concepto
- [ ] README con ejemplos y aprendizajes
- [ ] Pipeline GitHub Actions ejecuta tests en cada push

---

### Proyecto 1 — QuarkuShop: API de E-commerce
**Fase:** 1-2 | **Duración:** 4-6 semanas

**Descripción:** Backend de tienda online con todas las features de Quarkus core

**Stack:**
- Quarkus 3.x, RESTEasy Reactive, CDI
- PostgreSQL (via Dev Services en tests), Panache
- Flyway para migraciones
- JWT para auth básica
- Redis para caché de catálogo
- Resilience4j para llamadas a servicio de shipping externo (mock)
- OpenAPI/Swagger auto-generado
- JUnit 5 + Mockito + Testcontainers

**Dominios:**
- Products (catálogo con caché Redis)
- Orders (CRUD + estado máquina de estados)
- Users (registro, login JWT)
- Shipping (integración externa con circuit breaker)

**Criterios de "listo":**
- [ ] 70%+ test coverage (mix unitarios + integración)
- [ ] Circuit breaker demostrable: test que muestra fallback cuando shipping falla
- [ ] OpenAPI spec exportable (`/q/openapi`)
- [ ] docker-compose.yml que levanta todo el stack local
- [ ] README con arquitectura, setup, y ejemplos de API calls con curl

---

### Proyecto 2 — TaskFlow: Sistema de Gestión con Keycloak
**Fase:** 2-3 | **Duración:** 4-5 semanas

**Descripción:** Sistema de gestión de tareas multi-usuario con auth enterprise

**Stack:**
- Quarkus + Keycloak (OIDC)
- PostgreSQL + Flyway
- Redis para sesiones y caché
- Resilience4j
- WebSockets con Quarkus para notificaciones real-time
- Testcontainers con contenedor Keycloak real

**Features:**
- Multi-user con roles (ADMIN, MANAGER, USER)
- RBAC: admin ve todo, manager ve su equipo, user ve sus tareas
- Notificaciones real-time vía WebSocket
- Rate limiting por usuario

**Criterios de "listo":**
- [ ] RBAC completamente testeado con usuarios reales en Keycloak
- [ ] Audit log de todas las acciones (quién hizo qué y cuándo)
- [ ] Tests de contrato con Pact (si hay frontend mock)
- [ ] README incluye diagrama de seguridad y flujo OAuth2

---

### Proyecto 3 — EventBus: Sistema de Órdenes Distribuido
**Fase:** 3 | **Duración:** 5-6 semanas

**Descripción:** Sistema de microservicios que implementa un flujo de compra completo

**Stack:**
- 3 microservicios Quarkus: `order-service`, `inventory-service`, `notification-service`
- Apache Kafka para comunicación asíncrona
- PostgreSQL por servicio (database-per-service pattern)
- Saga coreografiada para el flujo de compra
- Outbox Pattern para garantía de entrega
- Arquitectura hexagonal en cada servicio
- ArchUnit validando la arquitectura
- docker-compose con todos los servicios + Kafka + Zookeeper

**Flujo Saga:**
```
OrderPlaced → InventoryReserved → PaymentProcessed → OrderConfirmed
                    ↓ fallo
              InventoryReservationFailed → OrderCancelled → NotificationSent
```

**Criterios de "listo":**
- [ ] Saga completa con compensación ante fallos
- [ ] Outbox pattern implementado (no dual-write)
- [ ] ArchUnit tests en verde en los 3 servicios
- [ ] README con diagrama de secuencia del flujo completo
- [ ] Tests de integración que demuestran la saga end-to-end

---

### Proyecto Final — SaaSTenant: Plataforma SaaS Backend Completa
**Fase:** 6 | **Duración:** 4 semanas

**Descripción:** Backend completo de una plataforma SaaS B2B (ej: herramienta de gestión de proyectos SaaS)

**Stack Exacto:**

| Componente | Tecnología |
|------------|------------|
| Framework | Quarkus 3.x |
| Auth | Keycloak + OIDC + JWT |
| BD principal | PostgreSQL con Row-Level Security |
| Caché | Redis (Redisson) |
| Mensajería | Apache Kafka |
| Migraciones | Flyway |
| Resiliencia | SmallRye Fault Tolerance (Resilience4j) |
| Observabilidad | OpenTelemetry + Micrometer + Prometheus + Grafana + Jaeger |
| API Docs | OpenAPI 3.0 (API-first con spec check) |
| Testing | JUnit 5 + Testcontainers + ArchUnit + Pact |
| Containerización | Docker multi-stage + docker-compose |
| Orquestación | Kubernetes (manifests + Helm chart) |
| CI/CD | GitHub Actions |
| Análisis estático | SonarCloud |

**Módulos del sistema:**

1. **Tenant Management:** registro de tenant, configuración, plan subscription
2. **User Management:** usuarios por tenant, roles, invitaciones
3. **Core Domain:** la feature principal (ej: gestión de proyectos)
4. **Billing Awareness:** quotas por plan, feature flags por tenant
5. **Public API:** API key management, rate limiting por tenant/plan
6. **Audit Log:** registro inmutable de acciones
7. **Webhook System:** notificaciones salientes a integraciones del cliente
8. **Admin Panel API:** endpoints para gestión operacional

**Criterios de "listo":**
- [ ] Multi-tenancy demostrable: mismo endpoint, diferente tenant, datos aislados
- [ ] Rate limiting por API key con Redis
- [ ] Pipeline CI/CD completo en GitHub Actions (test → sonar → build → push → deploy)
- [ ] Helm chart deployable en minikube
- [ ] Dashboard Grafana con métricas clave: latencia p99, error rate, requests/s
- [ ] Distributed tracing end-to-end visible en Jaeger
- [ ] README de nivel profesional con: arquitectura, decisiones de diseño, ADRs, setup local, deploy guide
- [ ] Mínimo 1 ADR (Architecture Decision Record) por decisión importante

---

## SECCIÓN 6 — ESTRATEGIA DE GITHUB

### Estructura de Repositorios

```
GitHub: @tu-usuario
├── java-learning-hub/          (mono-repo de notas y katas — PRIVADO)
│   ├── 00-setup/               # Scripts de setup WSL2
│   ├── 01-java-modern/         # Katas Java 21
│   ├── 02-quarkus-core/        # Ejercicios Quarkus
│   ├── 03-jvm-internals/       # Experimentos JFR, GC
│   ├── notes/                  # Notas en Markdown por área
│   └── reviews/                # Issues de revisión espaciada
│
├── quarkushop/                 (Proyecto 1 — PÚBLICO)
├── taskflow/                   (Proyecto 2 — PÚBLICO)
├── eventbus-saga/              (Proyecto 3 — PÚBLICO)
└── saastenant/                 (Proyecto Final — PÚBLICO ⭐)
```

### Template README para Repos de Práctica

```markdown
# [Nombre del Proyecto]

> [Una línea que describe qué hace y qué demuestra técnicamente]

## 🎯 Qué practica este proyecto

- [Concepto 1]: [por qué importa]
- [Concepto 2]: [cómo está implementado aquí]

## 🏗️ Arquitectura

[Diagrama ASCII o enlace a diagrama C4]

## 🛠️ Stack

| Capa | Tecnología | Versión |
|------|------------|---------|
| Framework | Quarkus | 3.x |
| BD | PostgreSQL | 15 |
| ...

## 🚀 Setup Local

### Prerequisitos
- WSL2 con Ubuntu 22.04
- Docker Desktop con integración WSL2
- JDK 21 (via SDKMAN)

### Levantar el stack
\`\`\`bash
docker-compose up -d
./mvnw quarkus:dev
\`\`\`

## ✅ Tests

\`\`\`bash
./mvnw test                    # Unit tests
./mvnw verify                  # Integration tests (Testcontainers)
./mvnw verify -Pnative         # Native compilation test
\`\`\`

## 📚 Decisiones de Diseño

- **[Decisión 1]:** [Alternativas consideradas] → [Por qué elegí esta]
- **[Decisión 2]:** ...

## 📖 Lo que aprendí

[2-3 párrafos honestos sobre los retos y aprendizajes]
```

### Convención de Commits

Usa **Conventional Commits** con prefijo de área de aprendizaje:

```
feat(quarkus): add circuit breaker to payment service
test(resilience): verify fallback activates after 5 failures
docs(arch): add C4 diagram for order bounded context
learn(kafka): implement outbox pattern for order events
fix(n+1): add @EntityGraph to avoid lazy loading issue
refactor(hex): extract payment port from service layer
chore(ci): add sonarcloud analysis to pipeline
```

### GitHub Projects para Tracking Semanal

**Board: "Java Roadmap"** con columnas:
- `Backlog` — todos los temas pendientes como issues
- `Esta semana` — máximo 3 issues
- `En progreso` — máximo 1
- `Review pendiente` — completado, pendiente de revisión espaciada
- `Completado` — con fecha y link al commit/PR

**Labels:**
- `phase-1`, `phase-2`, ... `phase-6`
- `theory`, `practice`, `project`
- `review` (para revisión espaciada)
- `interview-prep`

### Convertir Repos en Portafolio

**Lo que los empleadores buscan:**
1. README que explica el problema que resuelve, no solo el tech stack
2. Decisiones de arquitectura documentadas (ADRs)
3. Tests reales (no `assertTrue(true)`)
4. CI/CD en verde (badge en README)
5. Commits limpios e incrementales (no un solo commit gigante)
6. Issues cerrados que muestran pensamiento iterativo

**Checklist pre-publicación de repo:**
- [ ] README completo con diagrama de arquitectura
- [ ] Badge de CI/CD en README (`![CI](https://github.com/...`)
- [ ] docker-compose funcional probado en máquina limpia
- [ ] `.env.example` en lugar de `.env` con secrets
- [ ] Código sin comentarios TODO sin resolver
- [ ] Al menos 1 ADR documentado

---

## SECCIÓN 7 — SETUP INICIAL EN WSL2

### Paso 1: Configurar WSL2

```bash
# En PowerShell (admin):
wsl --install -d Ubuntu-22.04
wsl --set-default-version 2

# Verificar:
wsl --list --verbose
# Debe mostrar Ubuntu-22.04 con VERSION 2
```

**Archivo `%USERPROFILE%\.wslconfig` (en Windows):**
```ini
[wsl2]
memory=8GB          # Ajusta según tu RAM total
processors=4
swap=4GB

[experimental]
autoMemoryReclaim=gradual   # Libera memoria cuando WSL2 no la necesita
```

### Paso 2: Setup Base Ubuntu

```bash
# Actualizar:
sudo apt update && sudo apt upgrade -y

# Herramientas base:
sudo apt install -y curl wget git unzip zip build-essential

# Instalar zsh + Oh My Zsh (opcional pero muy cómodo):
sudo apt install -y zsh
sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
```

### Paso 3: SDKMAN + JDK

```bash
# Instalar SDKMAN:
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Ver JDKs disponibles:
sdk list java

# Instalar GraalVM JDK 21 (incluye native-image):
sdk install java 21.0.3-graalce

# Verificar:
java -version
# openjdk version "21.0.3" 2024-04-16
# OpenJDK Runtime Environment GraalVM CE 21.0.3+7.1

# Instalar Maven y Gradle:
sdk install maven
sdk install gradle

# Instalar Quarkus CLI:
sdk install quarkus
```

### Paso 4: Variables de Entorno

**Añadir a `~/.zshrc` (o `~/.bashrc`):**

```bash
# SDKMAN (ya agregado por el installer, verificar que está)
export SDKMAN_DIR="$HOME/.sdkman"
[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"

# Java home se gestiona vía SDKMAN, pero puedes forzar:
# export JAVA_HOME="$SDKMAN_DIR/candidates/java/current"
# export PATH="$JAVA_HOME/bin:$PATH"

# Maven opts (más memoria para compilaciones pesadas)
export MAVEN_OPTS="-Xmx2g -XX:+TieredCompilation -XX:TieredStopAtLevel=1"

# Para native image de GraalVM:
export GRAALVM_HOME="$SDKMAN_DIR/candidates/java/current"

# Alias útiles:
alias mvnq="./mvnw quarkus:dev"
alias mvnt="./mvnw test"
alias mvnv="./mvnw verify"
alias dcu="docker compose up -d"
alias dcd="docker compose down"
```

### Paso 5: Docker Desktop con WSL2

1. Descargar [Docker Desktop para Windows](https://www.docker.com/products/docker-desktop/)
2. Instalar con opción **WSL2 backend** habilitada
3. En Docker Desktop → Settings → Resources → WSL Integration → habilitar Ubuntu-22.04
4. Verificar desde WSL2: `docker run hello-world`

### Paso 6: VS Code

```bash
# Desde WSL2 terminal:
code .
# VS Code se abre automáticamente con el servidor WSL remoto

# Extensiones esenciales (instalar desde VS Code):
# - Extension Pack for Java (Microsoft)
# - Quarkus (Red Hat)
# - Spring Boot Extension Pack (VMware) — para la fase Spring
# - GitLens
# - Docker
# - YAML
# - REST Client (alternativa a Postman)
# - SonarLint
```

### Paso 7: Herramientas de Análisis

```bash
# async-profiler (para profiling JVM):
wget https://github.com/async-profiler/async-profiler/releases/latest/download/async-profiler-3.0-linux-x64.tar.gz
tar -xzf async-profiler-*.tar.gz -C ~/tools/

# jq (para procesar JSON en terminal):
sudo apt install -y jq

# httpie (alternativa a curl más legible):
sudo apt install -y httpie

# k9s (UI para Kubernetes en terminal):
curl -sS https://webinstall.dev/k9s | bash
```

### Tips de Performance WSL2 para Java

1. **Trabaja siempre en el filesystem de Linux** (`~/dev/`), NUNCA en `/mnt/c/`. El I/O cross-filesystem es 5-10x más lento.
2. **Excluir carpetas de builds del antivirus Windows:** en Windows Security → exclusiones → agrega `\\wsl$\Ubuntu-22.04\home\tu-usuario\dev`
3. **Aumenta memoria en `.wslconfig`** si usas Docker + Quarkus + PostgreSQL simultáneamente (mínimo 6GB recomendado)
4. **Usar BuildKit para Docker:** `export DOCKER_BUILDKIT=1` en `.zshrc`

---

## SECCIÓN 8 — MÉTRICAS DE AVANCE Y AUTOEVALUACIÓN

### Checklist Fase 0 (Java Moderno)

- [ ] Puedo implementar un `Collector` personalizado con `Collector.of()`
- [ ] Puedo explicar la diferencia entre `thenApply` y `thenCompose` en `CompletableFuture`
- [ ] Sé cuándo usar `Optional.flatMap` vs `Optional.map`
- [ ] Puedo modelar un ADT con sealed classes y pattern matching
- [ ] Virtual threads: puedo explicar por qué no bloquean OS threads

### Checklist Fase 1 (Quarkus Core)

- [ ] Puedo crear una extensión de Quarkus desde cero (aunque sea trivial)
- [ ] Entiendo la diferencia entre `@ApplicationScoped` y `@RequestScoped` en contextos reactivos
- [ ] Sé por qué `@Transactional` en Quarkus requiere una llamada desde fuera del bean
- [ ] Puedo escribir un `@QuarkusTest` que levanta Keycloak via Testcontainers y testea RBAC
- [ ] Dev Services: sé qué hace internamente y cómo deshabilitarlo para ambientes específicos

### Checklist Fase 2 (Datos + Resiliencia + Seguridad)

- [ ] Puedo diagnosticar un N+1 query usando las estadísticas de Hibernate en los logs
- [ ] Sé cuándo usar `EAGER` vs `LAZY` y las implicaciones de cada uno en queries
- [ ] Puedo explicar el problema del "dual-write" y por qué Outbox Pattern lo resuelve
- [ ] Circuit Breaker: sé la diferencia entre el estado HALF_OPEN y cuándo transicionar
- [ ] Redis: puedo elegir entre `cache-aside` y `write-through` según el patrón de lectura/escritura
- [ ] OAuth2: puedo dibujar el flujo `authorization_code` con PKCE en una pizarra

### Checklist Fase 3 (Arquitectura)

- [ ] Puedo identificar si un Bounded Context está mal delimitado en un diagrama
- [ ] Sé la diferencia entre un Domain Event y un Integration Event
- [ ] ArchUnit: puedo escribir una regla que valide que los domain objects no importan Jackson
- [ ] Kafka: entiendo qué pasa con mensajes no procesados y cómo configurar DLQ
- [ ] CQRS: puedo explicar por qué las queries no pasan por los aggregates

### Mini-retos por área

```java
// RETO Streams: Dado List<Transaction>, obtener el top-3 de vendedores
// por revenue total, solo transacciones del último trimestre, excluyendo
// transacciones canceladas. Hazlo en una expresión de stream.

// RETO Resilience4j: Escribe un test que demuestra que tu CircuitBreaker
// pasa de CLOSED → OPEN → HALF_OPEN → CLOSED correctamente.

// RETO JVM: Reproduce un memory leak con una colección estática,
// analízalo con JFR, identifica la clase culpable, y corrígelo.

// RETO Architecture: Dado este PR diff, identifica qué regla de
// arquitectura hexagonal se viola y propón el fix.

// RETO Kafka: Tu consumer group se queda atrás (lag creciente).
// Lista 5 posibles causas y cómo diagnosticar cada una.
```

### Señales de que estás listo para el siguiente nivel

**De Fase 0 a Fase 1:** Resuelves katas de streams en < 5 min sin buscar la API

**De Fase 1 a Fase 2:** Puedes leer un PR de Quarkus y dar feedback técnico útil

**De Fase 2 a Fase 3:** Puedes diseñar el schema de BD y API de un feature nuevo sin ayuda

**De Fase 3 a Fase 4:** Puedes hacer onboarding en una codebase Spring Boot existente en < 1 día

**De Fase 5 a Fase 6:** Puedes estimar esfuerzo de features con razonamiento técnico fundado

### Calibrar tu nivel con el mercado

**Ofertas junior (busca estas keywords):** "1-3 years experience", "Spring Boot OR Quarkus", "REST APIs", "SQL basics", "Git"

**Ofertas mid (busca estas keywords):** "microservices", "Kafka OR RabbitMQ", "Docker/Kubernetes", "CI/CD", "TDD"

**Ofertas senior (busca estas keywords):** "system design", "distributed systems", "mentoring", "architecture decisions", "performance optimization", "multi-tenant SaaS"

**Pasa las fases 0-3 y puedes postular a mid.** Termina el proyecto final y puedes postular a senior con argumentos sólidos.

---

## SECCIÓN 9 — PRIMERAS 48 HORAS: PLAN DE ARRANQUE

### Hora 0-2: Setup WSL2 (Día 1 mañana)

```bash
# 1. Verificar WSL2 version
wsl --list --verbose

# 2. Instalar SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# 3. Instalar GraalVM JDK 21
sdk install java 21.0.3-graalce

# 4. Instalar Maven, Gradle, Quarkus CLI
sdk install maven && sdk install gradle && sdk install quarkus

# 5. Verificar
java -version && mvn -version && quarkus --version
```

### Hora 2-4: Primer repositorio y primer commit (Día 1 tarde)

```bash
# Crear estructura en WSL2 filesystem (NO en /mnt/c/)
mkdir -p ~/dev/java-learning-hub
cd ~/dev/java-learning-hub
git init

# Crear primer proyecto Maven de katas
mvn archetype:generate \
  -DgroupId=com.learning \
  -DartifactId=java-modern-katas \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false

# Primer test (Java 21 moderno):
# Abrir en VS Code:
code .
```

**Crear `src/test/java/com/learning/RecordsTest.java`:**

```java
package com.learning;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RecordsTest {

    sealed interface Shape permits Circle, Rectangle {}
    record Circle(double radius) implements Shape {}
    record Rectangle(double width, double height) implements Shape {}

    double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
        };
    }

    @Test
    void circleAreaShouldBeCorrect() {
        var circle = new Circle(5.0);
        assertThat(area(circle)).isCloseTo(78.53, within(0.01));
    }

    @Test
    void rectangleAreaShouldBeCorrect() {
        var rect = new Rectangle(4.0, 6.0);
        assertThat(area(rect)).isEqualTo(24.0);
    }
}
```

```bash
# Ejecutar tests:
mvn test

# Primer commit:
git add .
git commit -m "feat(java21): implement Shape ADT with sealed classes and records"

# Crear repo en GitHub y pushear:
gh repo create java-learning-hub --private --push --source=.
# O manual: crear en github.com y git remote add origin + git push
```

### Hora 4-6: Primer proyecto Quarkus (Día 1 noche)

```bash
# Crear proyecto Quarkus con extensiones clave:
quarkus create app com.learning:quarkushop \
  --extension="resteasy-reactive-jackson,hibernate-orm-panache,jdbc-postgresql,smallrye-openapi,quarkus-junit5,rest-assured" \
  --no-code

cd quarkushop

# Levantar en modo dev:
quarkus dev
# → Abre http://localhost:8080/q/swagger-ui
```

### Hora 6-8+: Día 2 — Primer feature con TDD

```bash
# Crear tu primer test ANTES de escribir código:
```

**Crear `src/test/java/com/learning/ProductResourceTest.java`:**

```java
@QuarkusTest
class ProductResourceTest {

    @Test
    void shouldReturn200WhenGettingProducts() {
        given()
            .when().get("/products")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

    @Test
    void shouldReturn201WhenCreatingProduct() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                  "name": "Test Product",
                  "price": 29.99,
                  "category": "electronics"
                }
                """)
            .when().post("/products")
            .then()
            .statusCode(201);
    }
}
```

```bash
# El test falla (404) → escribe el endpoint → el test pasa
# Commit:
git commit -m "test(products): add endpoint tests before implementation"
git commit -m "feat(products): implement GET and POST endpoints"

# Abrir GitHub → crear Project Board → crear issues para las próximas tareas
```

### Checklist Fin de 48 horas

- [ ] WSL2 configurado con JDK 21, Maven, Quarkus CLI
- [ ] Docker Desktop funcionando desde WSL2 (`docker ps`)
- [ ] VS Code abre desde WSL2 con extensiones Java instaladas
- [ ] Repositorio `java-learning-hub` en GitHub con al menos 2 commits
- [ ] Proyecto Quarkus `quarkushop` creado y con `quarkus dev` funcionando
- [ ] Al menos 2 tests escritos y pasando en TDD style
- [ ] GitHub Project Board creado con issues para la semana 1

---

## APÉNDICE — Recursos de Referencia Rápida

### Comandos WSL2/Java más usados

```bash
# Quarkus dev con debug:
./mvnw quarkus:dev -Ddebug

# Compilar a native image:
./mvnw package -Pnative

# Docker build para JVM:
docker build -f src/main/docker/Dockerfile.jvm -t myapp:latest .

# Docker build para native:
docker build -f src/main/docker/Dockerfile.native -t myapp-native:latest .

# Activar JFR en dev:
java -XX:StartFlightRecording=filename=recording.jfr,duration=60s -jar myapp.jar

# Ver GC logs:
java -Xlog:gc*:file=gc.log -jar myapp.jar

# Listar beans CDI activos en Quarkus:
curl http://localhost:8080/q/arc/beans
```

### Decisión: ¿Quarkus o Spring Boot?

| Criterio | Quarkus | Spring Boot |
|----------|---------|-------------|
| Startup time | < 100ms | 1-5s |
| Memory footprint | Bajo (nativo: < 50MB) | Alto (200-500MB+) |
| Native image | Primera clase | Experimental (AOT) |
| Ecosistema | Creciendo rápido | Enorme y maduro |
| Curva de aprendizaje | Menor para nuevos | Mayor pero más recursos |
| Dominancia mercado | ~30% | ~70% |
| Kuberentes/Serverless | Diseñado para ello | Adaptado |
| **Cuándo elegir** | Microservicios, K8s, serverless, bajo consumo | Legacy, equipo con experiencia Spring, ecosistema amplio |

---

*Documento generado como guía de estudio personalizada. Actualiza las versiones de herramientas según el momento de lectura. Versiones base: Java 21, Quarkus 3.x, Spring Boot 3.x, Kafka 3.x, Kubernetes 1.28+*
