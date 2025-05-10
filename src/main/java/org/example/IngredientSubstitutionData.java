package org.example;

import java.util.*;

public class IngredientSubstitutionData {

    public static final Map<String, List<String>> PROTEIN_ALTERNATIVES = new HashMap<>();
    public static final List<String> NUT_FREE_SAUCES = Arrays.asList("Tomato Sauce", "Garlic Sauce", "BBQ Sauce");
    public static final List<String> VEGAN_CHEESE_ALTERNATIVES = Arrays.asList("Vegan Cheese", "Cashew Cheese", "Almond Cheese");

    static {
        PROTEIN_ALTERNATIVES.put("Shrimp", Arrays.asList("Fish", "Tofu"));
        PROTEIN_ALTERNATIVES.put("Chicken", Arrays.asList("Beef", "Tofu"));
        PROTEIN_ALTERNATIVES.put("Lamb", Arrays.asList("Beef", "Tofu"));
    }


    public static List<String> getProteinAlternatives(String selectedProtein, String unavailableIngredient) {
        if (selectedProtein.equalsIgnoreCase(unavailableIngredient)) {
            return PROTEIN_ALTERNATIVES.get(selectedProtein);
        }
        return Collections.emptyList();
    }


    public static List<String> getNutFreeSauces(String allergy, boolean isNutAllergy) {
        if ("Nut Allergy".equalsIgnoreCase(allergy) && isNutAllergy) {
            return NUT_FREE_SAUCES;
        }
        return Collections.emptyList();
    }


    public static List<String> getVeganCheeseAlternatives(String dietaryPreference, boolean isVegan) {
        if ("Vegan".equalsIgnoreCase(dietaryPreference) && isVegan) {
            return VEGAN_CHEESE_ALTERNATIVES;
        }
        return Collections.emptyList();
    }
}
