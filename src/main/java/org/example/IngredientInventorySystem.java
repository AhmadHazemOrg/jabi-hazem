package org.example;

import java.util.HashMap;
import java.util.Map;

public class IngredientInventorySystem {
    private final Map<String, Integer> inventory = new HashMap<>();
    private final Map<String, Integer> thresholds = new HashMap<>();
    private boolean dashboardOpened = false;
    private boolean usageMonitoringEnabled = false;

    public void connectInventory() {
        inventory.put("Tomatoes", 50);
        inventory.put("Onions", 20);
        inventory.put("Garlic", 10);
        thresholds.put("Garlic", 5);
    }

    public void openDashboard() {
        dashboardOpened = true;
    }

    public boolean isDashboardOpened() {
        return dashboardOpened;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void simulateLowStock(String ingredient, int newQuantity) {
        inventory.put(ingredient, newQuantity);
    }

    public String checkForLowStockAlerts() {
        for (String ingredient : inventory.keySet()) {
            int quantity = inventory.get(ingredient);
            int threshold = thresholds.getOrDefault(ingredient, 5);
            if (quantity < threshold) {
                return "Alert: " + ingredient + " stock is low. Please restock!";
            }
        }
        return "";
    }

    public void enableUsageMonitoring() {
        usageMonitoringEnabled = true;
    }

    public boolean shouldSuggestRestocking(int average, int thisWeek) {
        return usageMonitoringEnabled && thisWeek > average;
    }
}
