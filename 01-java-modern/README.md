# java-modern-katas

Colección de katas para dominar las features de **Java 17–21**: records, sealed classes, pattern matching, streams avanzados, Optional, y concurrencia moderna.

Cada kata está escrita con **TDD** — el test define el comportamiento antes de implementar.

---

## Qué practica este proyecto

| Feature | Kata | Por qué importa |
|---------|------|-----------------|
| Records + Sealed classes | `Kata01RecordsTest` | Reemplazan boilerplate de clases de datos y modelan ADTs con seguridad de tipos |
| Pattern matching (`switch`) | `Kata01RecordsTest` | El compilador garantiza que manejas todos los casos posibles |
| Streams avanzados | `Kata02StreamsTest` _(próximo)_ | Procesar colecciones sin loops imperativos |
| Optional | `Kata03OptionalTest` _(próximo)_ | Eliminar NullPointerException de forma explícita |
| CompletableFuture | `Kata04ConcurrencyTest` _(próximo)_ | Operaciones asíncronas sin bloquear threads |
| Virtual Threads (Loom) | `Kata04ConcurrencyTest` _(próximo)_ | Concurrencia masiva con código de aspecto síncrono |

---

## Stack

| | |
|---|---|
| Lenguaje | Java 21 (GraalVM CE) |
| Testing | JUnit 5 · AssertJ |
| Build | Maven 3.9 |
| Entorno | WSL2 Ubuntu 22.04 |

---

## Ejecutar los tests

```bash
# Todos los tests
mvn test

# Una kata específica
mvn test -Dtest=Kata01RecordsTest

# Con reporte de cobertura
mvn test jacoco:report
```

---

## Katas disponibles

### Kata 01 — Records y Sealed Classes

Modela un tipo `Result<T>` que representa éxito o error sin usar excepciones.

```java
sealed interface Result<T> permits Result.Ok, Result.Err {
    record Ok<T>(T value)         implements Result<T> {}
    record Err<T>(String message) implements Result<T> {}
}
```

**Lo que demuestra:**

- Records generan `equals()`, `hashCode()` y `toString()` automáticamente
- `sealed` restringe las implementaciones posibles al compilador
- `switch` con pattern matching es exhaustivo — el compilador te obliga a manejar todos los casos

---

## Lo que aprendí

- Los records comparan **por valor**, no por referencia — al contrario que las clases normales
- `sealed` + `switch` forman un ADT (Algebraic Data Type): el compilador sabe todos los casos posibles y avisa si te falta uno
- TDD con JUnit 5 + AssertJ: el test describe el comportamiento antes de que exista el código

---

## Próximas katas

- [ ] Kata 02 — Streams: `groupingBy`, `flatMap`, `teeing`, collectors personalizados
- [ ] Kata 03 — Optional: cuándo usarlo, cuándo NO, `flatMap` vs `map`
- [ ] Kata 04 — Concurrencia: `CompletableFuture` vs Virtual Threads, comparativa de performance
