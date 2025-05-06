package org.example;

public class Ingredient {
    private String name;
    private int stockLevel;
    private int threshold;

    public Ingredient(String name, int stockLevel, int threshold) {
        this.name = name;
        this.stockLevel = stockLevel;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public int getThreshold() {
        return threshold;
    }

    public boolean isLowStock() {
        return stockLevel <= threshold;
    }
}
