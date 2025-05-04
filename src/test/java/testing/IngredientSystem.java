package testing;

import java.util.*;

public class IngredientSystem {

    private final Set<String> outOfStockIngredients = new HashSet<>();

    public void setOutOfStock(String ingredient) {
        outOfStockIngredients.add(ingredient);
    }

    public List<String> getAlternativeProteins(String ingredient) {
        if (!outOfStockIngredients.contains(ingredient)) {
            return Collections.emptyList();
        }


        Map<String, List<String>> alternatives = new HashMap<>();
        alternatives.put("Shrimp", Arrays.asList("Fish", "Tofu"));
        alternatives.put("Chicken", Arrays.asList("Tofu", "Seitan"));

        return alternatives.getOrDefault(ingredient, Arrays.asList("Tofu", "Tempeh"));
    }

    public boolean blockSauceIfAllergic(String sauceContent, String allergy) {
        return allergy.toLowerCase().contains("nut") && sauceContent.equalsIgnoreCase("Nuts");
    }

    public boolean blockCheeseIfNotVegan(String dietaryPreference) {
        return dietaryPreference.equalsIgnoreCase("Vegan");
    }
}
