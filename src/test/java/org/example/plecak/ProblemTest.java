package org.example.plecak;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProblemTest {

    @Test
    void testAtLeastOneItemSelected() {
        Problem problem = new Problem(10, 1, 1, 5);
        Result result = problem.solve(10);
        assertFalse(result.getItemCounts().isEmpty(), "At least one item");

    }
    @Test
    void allItemsTooHeavy() {
        Problem problem = new Problem(5, 1, 10, 20);
        Result result = problem.solve(5);
        assertEquals("Selected items:\nValue: 0\nWeight: 0\n", result.toString());
    }
    @Test
    void testValuesAndWeightsWithinBounds() {
        Problem problem = new Problem(100, 1, 1, 10);

        List<Integer> values = problem.getValues();
        List<Integer> weights = problem.getWeights();

        assertAll("Check if all values and weights are within bounds",
                () -> values.forEach(v -> assertTrue(v >= 1 && v <= 10, "Value out of bounds: " + v)),
                () -> weights.forEach(w -> assertTrue(w >= 1 && w <= 10, "Weight out of bounds: " + w))
        );
    }
    @Test
    void ifCorrectResult() {
        Problem problem = new Problem(3, 1, 1, 1); // All values and weights will be 1
        Result result = problem.solve(5);
        // Since all weights = 1 and values = 1, it should take 5 items
        assertEquals(5, result.toString().lines()
                .filter(line -> line.startsWith("item")).mapToInt(line -> {
                    String[] parts = line.split(" = ");
                    return Integer.parseInt(parts[1]);
                }).sum(), "Total quantity of selected items should be 5");
        assertEquals("Value: 5", result.toString().lines().filter(line -> line.startsWith("Value")).findFirst().orElse(""));
        assertEquals("Weight: 5", result.toString().lines().filter(line -> line.startsWith("Weight")).findFirst().orElse(""));
    }
}