package org.example;

import java.util.*;

public class RealTimePricingManager {
    private String selectedIngredient;
    private final Map<String, Double> currentPrices = new HashMap<>();
    private final Map<String, Double> previousPrices = new HashMap<>();
    private final List<SupplierInfo> supplierComparisons = new ArrayList<>();

    public void selectIngredient(String ingredient) {
        this.selectedIngredient = ingredient;
    }

    public String getSelectedIngredient() {
        return selectedIngredient;
    }

    public void querySuppliers() {
        // Simulate querying suppliers' APIs for current prices
        currentPrices.clear();
        currentPrices.put("Supplier A", 2.5);
        currentPrices.put("Supplier B", 2.7);
    }

    public Map<String, Double> getCurrentPrices() {
        return new HashMap<>(currentPrices);
    }

    public boolean hasSignificantPriceChange(double newPrice, int thresholdPercent) {
        double oldPrice = previousPrices.getOrDefault(selectedIngredient, newPrice);
        previousPrices.put(selectedIngredient, newPrice); // simulate price update
        double changePercent = Math.abs((newPrice - oldPrice) / oldPrice) * 100;
        return changePercent > thresholdPercent;
    }

    public List<SupplierInfo> getComparisonTable() {
        supplierComparisons.clear();

        // Simulate supplier comparison data
        supplierComparisons.add(new SupplierInfo("Supplier A", 1.20, "2 days", "In Stock"));
        supplierComparisons.add(new SupplierInfo("Supplier B", 1.10, "3 days", "In Stock"));

        return new ArrayList<>(supplierComparisons);
    }

    // New method to alert manager when price changes significantly
    public void alertManagerIfPriceChangeSignificant(double newPrice, int thresholdPercent) {
        if (hasSignificantPriceChange(newPrice, thresholdPercent)) {
            System.out.println("ALERT: Significant price change detected!");
            System.out.println("Old Price: " + previousPrices.get(selectedIngredient));
            System.out.println("New Price: " + newPrice);
        }
    }

    public static class SupplierInfo {
        private final String name;
        private final double price;
        private final String deliveryTime;
        private final String availability;

        public SupplierInfo(String name, double price, String deliveryTime, String availability) {
            this.name = name;
            this.price = price;
            this.deliveryTime = deliveryTime;
            this.availability = availability;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public String getAvailability() {
            return availability;
        }

        @Override
        public String toString() {
            return String.format("%s: $%.2f, %s, %s", name, price, deliveryTime, availability);
        }
    }
}
