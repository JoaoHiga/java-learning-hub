package com.learning;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class Kata01RecordsTest {

    // Modela el resultado de una operación con sealed + records
    sealed interface Result<T> permits Result.Ok, Result.Err {
        record Ok<T>(T value) implements Result<T> {}
        record Err<T>(String message) implements Result<T> {}
    }

    String describe(Result<?> result) {
        return switch (result) {
            case Result.Ok<?> ok   -> "Éxito: " + ok.value();
            case Result.Err<?> err -> "Error: " + err.message();
        };
    }

    @Test
    void okResultShouldDescribeValue() {
        var result = new Result.Ok<>("pedido #42");
        assertThat(describe(result)).isEqualTo("Éxito: pedido #42");
    }

    @Test
    void errResultShouldDescribeMessage() {
        var result = new Result.Err<>("sin stock");
        assertThat(describe(result)).isEqualTo("Error: sin stock");
    }

    @Test
    void recordsShouldHaveValueEquality() {
        var a = new Result.Ok<>(100);
        var b = new Result.Ok<>(100);
        assertThat(a).isEqualTo(b); // records comparan por valor, no por referencia
    }
}