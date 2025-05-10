package org.example;

import java.time.LocalDate;
import java.util.*;

public class IngredientInventoryManager {
    private final Map<String, Integer> stockLevels = new HashMap<>();
    private final Map<String, Integer> thresholds = new HashMap<>();
    private final Map<String, LocalDate> lastSuggestionDate = new HashMap<>();
    private final Map<String, Integer> usageRates = new HashMap<>();

    public void setThreshold(String ingredient, int threshold) {
        thresholds.put(ingredient, threshold);
    }

    public void updateStock(String ingredient, int amount) {
        stockLevels.put(ingredient, amount);
    }

    public void setUsageRate(String ingredient, int rate) {
        usageRates.put(ingredient, rate);
    }

    public boolean isLowStock(String ingredient) {
        return stockLevels.getOrDefault(ingredient, 0) < thresholds.getOrDefault(ingredient, Integer.MAX_VALUE);
    }

    public boolean canSuggestToday(String ingredient) {
        LocalDate today = LocalDate.now();
        return !today.equals(lastSuggestionDate.get(ingredient));
    }

    public boolean generateSuggestion(String ingredient) {
        if (isLowStock(ingredient) && canSuggestToday(ingredient)) {
            lastSuggestionDate.put(ingredient, LocalDate.now());
            return true;
        }
        return false;
    }

    public List<String> getPrioritizedSuggestions() {
        List<String> lowIngredients = new ArrayList<>();
        for (String ingredient : stockLevels.keySet()) {
            if (isLowStock(ingredient)) {
                lowIngredients.add(ingredient);
            }
        }
        lowIngredients.sort((a, b) -> usageRates.getOrDefault(b, 0) - usageRates.getOrDefault(a, 0));
        return lowIngredients;
    }

    public Map<String, Integer> getStockLevels() {
        return stockLevels;
    }
}
