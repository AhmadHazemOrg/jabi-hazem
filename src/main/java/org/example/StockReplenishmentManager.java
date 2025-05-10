package org.example;

import java.util.*;

public class StockReplenishmentManager {

    private static final int CRITICAL_THRESHOLD = 5;

    private final Map<String, Integer> stockLevels = new HashMap<>();
    private final Set<String> pendingOrders = new HashSet<>();
    private final Set<String> notifiedIngredients = new HashSet<>();

    public void setStockLevel(String ingredient, int quantity) {
        stockLevels.put(ingredient, quantity);
    }

    public boolean isCriticallyLow(String ingredient) {
        return stockLevels.getOrDefault(ingredient, 0) < CRITICAL_THRESHOLD;
    }

    public boolean createPurchaseOrderIfNeeded(String ingredient) {
        if (pendingOrders.contains(ingredient)) {
            return false;
        }

        if (isCriticallyLow(ingredient)) {
            pendingOrders.add(ingredient);
            System.out.println("📦 Purchase order created for: " + ingredient);
            return true;
        }

        return false;
    }

    public void sendOrderToSupplier(String ingredient) {
        if (pendingOrders.contains(ingredient)) {
            System.out.println("📨 Order sent to default supplier for: " + ingredient);
        }
    }

    public boolean notifyManager(String ingredient) {
        if (pendingOrders.contains(ingredient) && !notifiedIngredients.contains(ingredient)) {
            notifiedIngredients.add(ingredient);
            System.out.println("📢 Notification sent to kitchen manager for: " + ingredient);
            return true;
        }
        return false;
    }

    public void fulfillOrder(String ingredient) {
        pendingOrders.remove(ingredient);
        notifiedIngredients.remove(ingredient);
    }
}
