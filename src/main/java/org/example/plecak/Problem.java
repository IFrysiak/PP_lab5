package org.example.plecak;


import java.util.*;
import java.util.stream.IntStream;


public class Problem {

    private int numberOfItemTypes;
    private int seed;
    private int lowerBound;
    private int upperBound;
    private List<Integer> values;
    private List<Integer> weights;

    public Problem(int numberOfItemTypes, int seed, int lowerBound, int upperBound) {
        this.numberOfItemTypes = numberOfItemTypes;
        this.seed = seed;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.values = new ArrayList<>();
        this.weights = new ArrayList<>();

        Random random = new Random(seed);

        for (int i = 0; i < numberOfItemTypes; i++) {
            int value = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int weight = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            values.add(value);
            weights.add(weight);
        }
    }

    public List<Integer> getValues() {
        return values;
    }

    public List<Integer> getWeights() {
        return weights;
    }

    public int getNumberOfItemTypes() {
        return numberOfItemTypes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfItemTypes; i++) {
            sb.append(i + 1)
                    .append(": value = ").append(values.get(i))
                    .append(", weight = ").append(weights.get(i)).append("\n");
        }
        return sb.toString();
    }

    public Result solve(int capacity) {
        int n = numberOfItemTypes;
        List<Integer> values = this.values;
        List<Integer> weights = this.weights;

        Integer[] indices = IntStream.range(0, n).boxed().toArray(Integer[]::new);

        Arrays.sort(indices, (i, j) -> {
            double ratio1 = (double) values.get(i) / weights.get(i);
            double ratio2 = (double) values.get(j) / weights.get(j);
            return Double.compare(ratio2, ratio1);
        });

        int remainingCapacity = capacity;
        int totalValue = 0;
        int totalWeight = 0;
        Map<Integer, Integer> itemCounts = new HashMap<>();

        for (int i : indices) {
            int weight = weights.get(i);
            int value = values.get(i);

            if (weight > remainingCapacity) continue;

            int count = remainingCapacity / weight;

            if (count > 0) {
                itemCounts.put(i + 1, count);

                totalWeight += weight * count;
                totalValue += value * count;
                remainingCapacity -= weight * count;
            }

            if (remainingCapacity == 0) break;
        }

        return new Result(itemCounts, totalValue, totalWeight);
    }


}

