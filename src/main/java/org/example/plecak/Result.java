package org.example.plecak;

import java.util.Map;

public class Result {
    private Map<Integer, Integer> itemCounts;
    private int totalValue;
    private int totalWeight;

    public Result(Map<Integer, Integer> itemCounts, int totalValue, int totalWeight) {
        this.itemCounts = itemCounts;
        this.totalValue = totalValue;
        this.totalWeight = totalWeight;
    }

    public Map<Integer, Integer> getItemCounts() {
        return itemCounts;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Selected items:\n");
        for (Map.Entry<Integer, Integer> entry : itemCounts.entrySet()) {
            sb.append("item ").append(entry.getKey()).append(": quantity = ").append(entry.getValue()).append("\n");
        }
        sb.append("Value: ").append(totalValue).append("\n");
        sb.append("Weight: ").append(totalWeight).append("\n");
        return sb.toString();
    }
}
