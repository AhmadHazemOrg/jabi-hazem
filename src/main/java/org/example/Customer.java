package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Customer {
    private String dietaryPreferences;
    private String allergies;
    private String username;
    private String password;
    private String loginMessage;
    private boolean loggedIn;



    public void setUsername(String username) {
        this.username = username.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }


    public String getPassword() {
        return this.password;
    }


    public void clear() {
        loggedIn = false;
        loginMessage = "";
    }


    public boolean login() {

        System.out.println("Reading customers file from: " + new java.io.File("customers.txt").getAbsolutePath());

        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    continue;
                }

                String fileUsername = parts[0].trim();
                String filePassword = parts[1].trim();

                if (fileUsername.equals(username)) {
                    if (filePassword.equals(password)) {
                        loggedIn = true;
                        loginMessage = "Login successful";
                        return true;
                    } else {
                        loginMessage = "Incorrect password";
                        return false; // Incorrect password
                    }
                }
            }
            loginMessage = "User not found";
            return false;
        } catch (IOException e) {
            loginMessage = "Error reading file: " + e.getMessage();
            return false;
        }
    }


    public String getUsername() {
        return this.username;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setDietaryPreferences(String preferences) {
        this.dietaryPreferences = preferences;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String  getDietaryPreferences() {
        return this.dietaryPreferences;
    }


    public String getAllergies() {

        return this.allergies;
    }


    private List<Meal> pastOrders = new ArrayList<>();

    public List<Meal> getPastOrders() {
        return pastOrders;
    }

    public void addPastOrder(Meal meal) {
        pastOrders.add(meal);
    }

    public Meal reorderMeal(String mealName) {
        for (Meal meal : pastOrders) {
            if (meal.getName().equals(mealName)) {
                return meal;
            }
        }
        return null;
    }

    public void clearPastOrders() {
        pastOrders.clear();
    }


}
