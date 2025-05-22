package org.example;

import java.util.*;

public class MealData {
    public static final List<String> ALL_INGREDIENTS = Arrays.asList(
            "rice", "chicken", "grilled vegetables", "garlic sauce", "pasta", "beef", "soy sauce", "cheese", "tofu", "lamb"
    );

    public static final List<String> GLUTEN_FREE_INGREDIENTS = Arrays.asList(
            "rice", "chicken", "grilled vegetables", "garlic sauce", "gluten-free bread"
    );

    public static final List<String> LACTOSE_FREE_INGREDIENTS = Arrays.asList(
            "rice", "chicken", "tofu", "grilled vegetables", "pasta", "soy sauce"
    );

    public static final Map<String, Double> INGREDIENT_PRICES = new HashMap<>();

    static {
        INGREDIENT_PRICES.put("rice", 2.0);
        INGREDIENT_PRICES.put("chicken", 4.0);
        INGREDIENT_PRICES.put("grilled vegetables", 3.0);
        INGREDIENT_PRICES.put("garlic sauce", 1.5);
        INGREDIENT_PRICES.put("pasta", 2.5);
        INGREDIENT_PRICES.put("beef", 5.0);
        INGREDIENT_PRICES.put("soy sauce", 1.0);
        INGREDIENT_PRICES.put("cheese", 2.5);
        INGREDIENT_PRICES.put("tofu", 3.0);
        INGREDIENT_PRICES.put("lamb", 6.0);
    }


}
