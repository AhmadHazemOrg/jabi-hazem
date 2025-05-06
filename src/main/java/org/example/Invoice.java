package org.example;

import java.util.List;

public class Invoice {
    private String customerName;
    private String date;
    private List<Meal> meals;
    private double totalPrice;


    public Invoice(String customerName, String date, List<Meal> meals, double totalPrice) {
        this.customerName = customerName;
        this.date = date;
        this.meals = meals;
        this.totalPrice = totalPrice;
    }


    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


    @Override
    public String toString() {
        StringBuilder mealsList = new StringBuilder();
        for (Meal meal : meals) {
            mealsList.append(meal.getName()).append(" - ").append(meal.getPrice()).append("\n");
        }
        return "Invoice for " + customerName + " on " + date + "\n" + mealsList.toString() + "Total: " + totalPrice;
    }
}
