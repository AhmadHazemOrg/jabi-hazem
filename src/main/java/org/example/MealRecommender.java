package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealRecommender {

    private List<Meal> allMeals = new ArrayList<>();

    public MealRecommender() {
        loadMealsFromFile("meals.txt");
    }

    private void loadMealsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 3) continue; // skip invalid lines

                String name = parts[0].trim();
                List<String> ingredients = Arrays.asList(parts[1].trim().split(",\\s*"));
                List<String> tags = Arrays.asList(parts[2].trim().split(",\\s*"));

                allMeals.add(new Meal(name, ingredients, tags));
            }

        } catch (IOException e) {
            System.err.println("Error reading meals from file: " + e.getMessage());
        }
    }

    public List<Meal> recommendMeals(Customer customer) {
        List<Meal> recommended = new ArrayList<>();
        String preference = customer.getDietaryPreferences();
        List<String> allergens = Arrays.asList(customer.getAllergies().split(",\\s*"));

        for (Meal meal : allMeals) {
            if (!meal.getDietaryTags().contains(preference)) continue;

            boolean containsAllergen = false;
            for (String allergen : allergens) {
                if (meal.getIngredients().contains(allergen)) {
                    containsAllergen = true;
                    break;
                }
            }

            if (!containsAllergen) {
                recommended.add(meal);
            }
        }

        return recommended;
    }
    public List<Meal> suggestedMeals(String filePath) {
        List<Meal> simpleMeals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 3) continue;

                String name = parts[0].trim();
                List<String> ingredients = Arrays.asList(parts[1].trim().split(",\\s*"));

                double price;
                try {
                    price = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid price for meal '" + name + "': " + parts[2]);
                    continue; // skip this line if price is invalid
                }

                Meal meal = new Meal(name, ingredients, new ArrayList<>()); // empty tags
                meal.setPrice(price);
                simpleMeals.add(meal);
            }

        } catch (IOException e) {
            System.err.println("Error reading simple meals from file: " + e.getMessage());
        }

        return simpleMeals;
    }

}