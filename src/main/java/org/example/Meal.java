package org.example;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class Meal {
    private String name;
    private List<String> ingredients;
    private List<String> dietaryTags;
    private double price = 0.0;
    private String category;
    private String date;
    private Date deliveryTime;
    private static final Logger logger = Logger.getLogger(Meal.class.getName());
    public Meal(String name, Date deliveryTime) {
        this.name = name;
        this.deliveryTime = deliveryTime;
    }

    public Meal(String name, double price, String category, String date) {
        this.name = name;
        this.price = price;
        this.ingredients = new ArrayList<>();
        this.dietaryTags = new ArrayList<>();
        this.category = category;
        this.date = date;
        this.deliveryTime= new Date();
    }
    public Meal(String name, List<String> ingredients, List<String> dietaryTags) {
        this.name = name;
        this.ingredients = ingredients;
        this.dietaryTags = dietaryTags;
        this.price = 0.0;
        this.category = "";
        this.date = "";
        this.deliveryTime= new Date();
    }

    public Meal() {

    }
    public Meal(String name, double price) {
        this.name = name;
        this.price = price;
        this.category = "";
        this.date = "";
        this.ingredients = new ArrayList<>();
        this.dietaryTags = new ArrayList<>();
        this.deliveryTime= new Date();
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Meal(String mealName) {
        this.name = mealName;
        this.ingredients = new ArrayList<>();
        this.dietaryTags = new ArrayList<>();
        this.price = 0.0;
        this.category = "";
        this.date = "";
        this.deliveryTime= new Date();


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


    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setDietaryTags(List<String> dietaryTags) {
        this.dietaryTags = dietaryTags;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }




     public List<String> getMealsToNotify(List<Meal> scheduledMeals, Date currentTime, int notificationWindowMinutes) {
        List<String> mealsToNotify = new ArrayList<>();

        for (Meal meal : scheduledMeals) {
            long diffMillis = meal.getDeliveryTime().getTime() - currentTime.getTime();
            long diffMinutes = diffMillis / (60 * 1000);

            if (diffMinutes > 0 && diffMinutes <= notificationWindowMinutes) {
                mealsToNotify.add(meal.getName());
            }
        }

        return mealsToNotify;
    }


    public static List<Meal> filterMealsByDateRange(List<Meal> meals, String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return meals.stream()
                .filter(meal -> {
                    try {
                        Date mealDate = sdf.parse(meal.getDate());
                        Date start = sdf.parse(startDate);
                        Date end = sdf.parse(endDate);
                        return !mealDate.before(start) && !mealDate.after(end);
                    } catch (Exception e) {
                        logger.severe("Error parsing date: " + e.getMessage());
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }







    public double getPrice() {
        return price;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }


}
