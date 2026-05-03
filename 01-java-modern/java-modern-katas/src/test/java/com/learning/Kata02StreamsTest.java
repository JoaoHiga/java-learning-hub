package com.learning;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class Kata02StreamsTest {

    record Product(String name, String category, double price, boolean available) {
    }

    List<Product> products = List.of(
            new Product("Laptop", "electronics", 999.99, true),
            new Product("Phone", "electronics", 599.99, true),
            new Product("Desk", "furniture", 299.99, true),
            new Product("Chair", "furniture", 199.99, false),
            new Product("Monitor", "electronics", 399.99, true),
            new Product("Lamp", "furniture", 49.99, false));

    // Ejercicio 1: filtrar disponibles y obtener sus nombres en mayúscula
    @Test
    void shouldGetNamesOfAvailableProductsInUpperCase() {
        List<String> result = products.stream()
                .filter(Product::available)
                .map(p -> p.name().toUpperCase())
                .toList();

        assertThat(result).containsExactlyInAnyOrder(
                "LAPTOP", "PHONE", "DESK", "MONITOR");
    }

    // Ejercicio 2: agrupar por categoría
    @Test
    void shouldGroupProductsByCategory() {
        Map<String, List<Product>> result = products.stream()
                .collect(Collectors.groupingBy(Product::category));

        assertThat(result).containsKeys("electronics", "furniture");
        assertThat(result.get("electronics")).hasSize(3);
        assertThat(result.get("furniture")).hasSize(3);
    }

    // Ejercicio 3: precio promedio por categoría
    @Test
    void shouldCalculateAveragePriceByCategory() {
        Map<String, Double> result = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.averagingDouble(Product::price)));

        assertThat(result.get("electronics")).isCloseTo(666.65, within(0.01));
    }

    // Ejercicio 4: el producto más caro disponible
    @Test
    void shouldFindMostExpensiveAvailableProduct() {
        Optional<Product> result = products.stream()
                .filter(Product::available)
                .max(Comparator.comparingDouble(Product::price));

        assertThat(result).isPresent();
        assertThat(result.get().name()).isEqualTo("Laptop");
    }

    // Ejercicio 5: revenue total de productos disponibles
    @Test
    void shouldCalculateTotalRevenueOfAvailableProducts() {
        double result = products.stream()
                .filter(Product::available)
                .mapToDouble(Product::price)
                .sum();

        assertThat(result).isCloseTo(2299.96, within(0.01));
    }

    // Reto: sumar precios de todos los productos (disponibles o no)
    @Test
    void summatoryOfProductsPricesAreCalculatedCorrectly() {
        double result = products.stream()
                .mapToDouble(Product::price)
                .sum();
        assertThat(result).isCloseTo(2549.94, within(0.0001));
    }

    @Test
    void nameOfProductsThatCostMoreThan300AreCollected() {
        List<String> result = products.stream()
                .filter(p -> p.price() > 300)
                .map(Product::name)
                .toList();
        assertThat(result).containsExactlyInAnyOrder("Laptop", "Phone", "Monitor");
    }

    @Test
    void totalPricesOfAvailableProductsAreCalculatedCorrectlyByCategory() {
        Map<String, Double> result = products.stream()
                .filter(Product::available)
                .collect(Collectors.toMap(Product::category, Product::price, Double::sum));

        assertThat(result).containsKeys("electronics", "furniture");
        assertThat(result.get("electronics")).isCloseTo(1999.97, within(0.0001));
        assertThat(result.get("furniture")).isCloseTo(299.99, within(0.0001));
    }

    @Test
    void test3() {
        Map<Boolean, List<String>> result = products.stream()
                .collect(Collectors.partitioningBy(Product::available,
                        Collectors.mapping(Product::name, Collectors.toList())));

        assertThat(result).containsKeys(true, false);
        assertThat(result.get(true)).containsExactlyInAnyOrder("Laptop", "Phone", "Desk", "Monitor");
        assertThat(result.get(false)).containsExactlyInAnyOrder("Chair", "Lamp");
    }

}
