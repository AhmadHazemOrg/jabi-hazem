package testing;

import java.util.*;

public class InventorySystem {

    private static final int CRITICAL_THRESHOLD = 5;

    private final Map<String, Integer> stockLevels = new HashMap<>();
    private final Set<String> pendingOrders = new HashSet<>();
    private final Set<String> notifiedIngredients = new HashSet<>();


    public void setStockLevel(String item, int quantity) {
        stockLevels.put(item, quantity);
    }


    public boolean isCriticallyLow(String item) {
        return stockLevels.getOrDefault(item, 0) < CRITICAL_THRESHOLD;
    }


    public boolean createPurchaseOrderIfNeeded(String item) {
        if (pendingOrders.contains(item)) {
            return false;
        }
        pendingOrders.add(item);
        return true;
    }


    public void sendOrderToSupplier(String item) {
        if (!pendingOrders.contains(item)) {
            throw new IllegalStateException("No pending order for " + item + " to send.");
        }

    }


    public boolean notifyManager(String item) {
        if (!pendingOrders.contains(item)) return false;
        if (notifiedIngredients.contains(item)) return false;

        notifiedIngredients.add(item);
        return true;
    }
}

