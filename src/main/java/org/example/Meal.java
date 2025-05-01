package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String name;
    private List<String> ingredients;
    private List<String> dietaryTags;


    public Meal(String name, List<String> ingredients, List<String> dietaryTags) {
        this.name = name;
        this.ingredients = ingredients;
        this.dietaryTags = dietaryTags;
    }

    public Meal() {

    }

    public Meal(String mealName) {
        this.name = mealName;
        this.ingredients = new ArrayList<>();
        this.dietaryTags = new ArrayList<>();

    }


    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getDietaryTags() {
        return dietaryTags;
    }

    // Setter methods for ingredients and dietaryTags
    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setDietaryTags(List<String> dietaryTags) {
        this.dietaryTags = dietaryTags;
    }

    @Override
    public String toString() {
        return "Meal{name='" + name + "', ingredients=" + ingredients + ", dietaryTags=" + dietaryTags + "}";
    }


    public void addIngredient(String ingredient) {
    }
    public List<String> readIngredients(String filename) {
        List<String> ingredients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    ingredients.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading ingredient file: " + e.getMessage());
        }
        return ingredients;
    }
}
