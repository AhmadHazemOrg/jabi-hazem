package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        // Customer customer = new Customer();
        List<Customer> allCustomers = new ArrayList<>();
        final Map<String, List<Meal>> allOrders = new HashMap<>();
        Path ordersPath = Paths.get("orders.txt");
        try {
            Files.deleteIfExists(ordersPath);

        } catch (IOException e) {
            System.out.println("⚠️  Could not clear orders file: " + e.getMessage());
        }
        while (running) {
            System.out.println("\n=== Welcome to Special Cook Management System ===");
            System.out.println("1. Customer");
            System.out.println("2. Chef");
            System.out.println("3. Chef Manager");
            System.out.println("4. Admin");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            String username, password;
            boolean loginResult;

            switch (choice) {
                case 1:
                    System.out.print("Enter customer username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter customer password: ");
                    password = scanner.nextLine();

                    Customer customer = new Customer();
                    customer.setUsername(username);
                    customer.setPassword(password);
                    loginResult = customer.login();


                    if (loginResult) {
                        boolean customerLoggedIn = true;
                        allCustomers.add(customer);
                        while (customerLoggedIn) {
                            System.out.println("\n=== Customer Dashboard ===");
                            System.out.println("1. Enter dietary preferences and allergies");
                            System.out.println("2. order from  menu  ");
                            System.out.println("3. view past orders");
                            System.out.println("4. Customize your meal");
                            System.out.println("5. Print Invoice");
                            System.out.println("6. Logout");
                            System.out.print("Enter your choice: ");

                            int cChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (cChoice) {
                                case 1:
                                    System.out.print("Enter dietary preferences: ");
                                    String preferences = scanner.nextLine();
                                    System.out.print("Enter allergies (comma separated): ");
                                    String allergies = scanner.nextLine();

                                    customer.setDietaryPreferences(preferences);
                                    customer.setAllergies(allergies);

                                    System.out.println("✅ Preferences and allergies saved:");
                                    System.out.println("Preferences: " + customer.getDietaryPreferences());
                                    System.out.println("Allergies: " + customer.getAllergies());

                                    MealRecommender recommender = new MealRecommender();
                                    var recommendedMeals = recommender.recommendMeals(customer);

                                    if (recommendedMeals.isEmpty()) {
                                        System.out.println("No recommended meals found for your preferences/allergies.");
                                    } else {
                                        System.out.println("\nRecommended meals:");
                                        for (int i = 0; i < recommendedMeals.size(); i++) {
                                            System.out.printf("%d. %s\n", i + 1, recommendedMeals.get(i).getName());
                                        }

                                        System.out.println("0. Return to dashboard");
                                        System.out.print("Select a meal number to view details or 0 to return: ");
                                        int mealChoice = scanner.nextInt();
                                        scanner.nextLine();

                                        if (mealChoice > 0 && mealChoice <= recommendedMeals.size()) {
                                            Meal selectedMeal = recommendedMeals.get(mealChoice - 1);
                                            System.out.println("\nMeal Details:");
                                            System.out.println("Name: " + selectedMeal.getName());
                                            System.out.println("Ingredients: " + String.join(", ", selectedMeal.getIngredients()));
                                            System.out.println("Dietary Tags: " + String.join(", ", selectedMeal.getDietaryTags()));
                                        } else if (mealChoice == 0) {
                                            System.out.println("Returning to dashboard...");
                                        } else {
                                            System.out.println("Invalid choice.");
                                        }
                                    }
                                    break;

                                case 2:
                                    MealRecommender suggested = new MealRecommender();
                                    List<Meal> suggestedMeals = suggested.suggestedMeals("suggestedMeals.txt");

                                    if (suggestedMeals.isEmpty()) {
                                        System.out.println("No suggested meals found.");
                                    } else {
                                        System.out.println("📋 Suggested Meals:");
                                        for (int i = 0; i < suggestedMeals.size(); i++) {
                                            Meal meal = suggestedMeals.get(i);
                                            System.out.printf("%d. %s - $%.2f\n", i + 1, meal.getName(), meal.getPrice());
                                            System.out.println("   Ingredients: " + String.join(", ", meal.getIngredients()));

                                        }

                                        System.out.print("\nEnter the name of the meal you want to order: ");
                                        String mealToOrder = scanner.nextLine().trim();

                                        boolean found = false;
                                        for (Meal meal : suggestedMeals) {
                                            if (meal.getName().equalsIgnoreCase(mealToOrder)) {
                                                customer.addPastOrder(meal); // Safe if Customer class handles storage
                                                try (FileWriter fw = new FileWriter("orders.txt", true)) { // true = append
                                                    fw.write(customer.getUsername() + " -> " + meal.getName() + System.lineSeparator());
                                                } catch (IOException e) {
                                                    System.out.println("⚠️ Could not write order to file: " + e.getMessage());
                                                }

                                                System.out.println("✅ '" + meal.getName() + "' has been added to your orders.");
                                                found = true;
                                                break;
                                            }
                                        }

                                        if (!found) {
                                            System.out.println("❌ Meal not found in the menu.");
                                        }
                                    }
                                    break;
                                case 3:
                                    List<Meal> pastOrders = customer.getPastOrders();
                                    if (pastOrders.isEmpty()) {
                                        System.out.println("📭 You have no past orders.");
                                    } else {
                                        System.out.println("🧾 Your Past Orders:");

                                        for (int i = 0; i < pastOrders.size(); i++) {

                                            Meal meal = pastOrders.get(i);
                                            System.out.printf("%d. %s\n", i + 1, meal.getName());
                                            System.out.println("   Ingredients: " + String.join(", ", meal.getIngredients()));


                                        }
                                    }
                                    break;



                                case 4:
                                    System.out.println("\n=== Build Your Custom Meal ===");


                                    System.out.print("Do you want the meal to be gluten-free?  (y/n): ");
                                    boolean wantsGlutenFree  = scanner.nextLine().trim().equalsIgnoreCase("y");

                                    System.out.print("Do you want the meal to be lactose-free? (y/n): ");
                                    boolean wantsLactoseFree = scanner.nextLine().trim().equalsIgnoreCase("y");


                                    System.out.println("\nAvailable ingredients and prices:");
                                    MealData.INGREDIENT_PRICES.forEach((ing, price) ->
                                            System.out.printf("- %-20s : %.2f JD%n", ing, price));

                                    System.out.println("\nGluten-free ingredients:");
                                    MealData.GLUTEN_FREE_INGREDIENTS.forEach(ing -> System.out.println("- " + ing));

                                    System.out.println("\nLactose-free ingredients:");
                                    MealData.LACTOSE_FREE_INGREDIENTS.forEach(ing -> System.out.println("- " + ing));


                                    System.out.print("\nEnter ingredients separated by commas: ");
                                    String input = scanner.nextLine().toLowerCase();
                                    List<String> chosenIngredients = Arrays.asList(input.split(",\\s*"));


                                    boolean glutenConflict  = wantsGlutenFree  &&
                                            chosenIngredients.stream().anyMatch(ing -> !MealData.GLUTEN_FREE_INGREDIENTS.contains(ing));
                                    boolean lactoseConflict = wantsLactoseFree &&
                                            chosenIngredients.stream().anyMatch(ing -> !MealData.LACTOSE_FREE_INGREDIENTS.contains(ing));

                                    if (glutenConflict || lactoseConflict) {
                                        System.out.println("\n❌ Conflict detected:");
                                        if (glutenConflict)  System.out.println("- Some ingredients contain gluten.");
                                        if (lactoseConflict) System.out.println("- Some ingredients contain lactose.");
                                        System.out.println("Please edit your ingredient list and try again.");
                                        break;
                                    }


                                    String customName = "Custom Meal #" + (customer.getPastOrders().size() + 1);
                                    Meal customMeal   = new Meal(customName, chosenIngredients, List.of("custom"));
                                    customer.addPastOrder(customMeal);


                                    double totalPrice = chosenIngredients.stream()
                                            .mapToDouble(ing -> MealData.INGREDIENT_PRICES.getOrDefault(ing, 0.0))
                                            .sum();


                                    try (FileWriter fw = new FileWriter("orders.txt", true)) {
                                        fw.write(customer.getUsername()
                                                + " -> " + customMeal.getName()
                                                + " | " + String.format("%.2f JD", totalPrice)
                                                + " | " + String.join(", ", chosenIngredients)
                                                + System.lineSeparator());
                                    } catch (IOException e) {
                                        System.out.println("⚠️ Could not write order to file: " + e.getMessage());
                                    }

                                    System.out.printf("✅ %s (%.2f JD) created and added to your orders!%n",
                                            customName, totalPrice);
                                    break;


                                case 5:
                                    // ✅ Print invoice logic here
                                    if (customer.getPastOrders().isEmpty()) {
                                        System.out.println("⚠️ You have no past orders to print an invoice for.");
                                    } else {
                                        System.out.println("📦 Your past orders:");
                                        List<Meal> orders = customer.getPastOrders();
                                        double grandTotal = 0.0;

                                        for (int i = 0; i < orders.size(); i++) {
                                            Meal m = orders.get(i);


                                            if (m.getPrice() == 0.0) {
                                                double priceFromFile = 0.0;
                                                try (BufferedReader br = new BufferedReader(new FileReader("orders.txt"))) {
                                                    String line;
                                                    String target = customer.getUsername() + " -> " + m.getName() + " |";
                                                    while ((line = br.readLine()) != null) {
                                                        if (line.startsWith(target)) {
                                                            String[] parts = line.split("\\|");
                                                            if (parts.length >= 2) {
                                                                String pricePart = parts[1].trim();
                                                                if (pricePart.endsWith("JD")) {
                                                                    pricePart = pricePart.substring(0, pricePart.length() - 2).trim();
                                                                }
                                                                priceFromFile = Double.parseDouble(pricePart);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                } catch (IOException | NumberFormatException e) {
                                                    System.out.println("⚠️ Could not read price from file: " + e.getMessage());
                                                }
                                                m.setPrice(priceFromFile);   // تأكد أن لديك setPrice(double)
                                            }

                                            System.out.printf("%d. %s - %.2f JD%n", i + 1, m.getName(), m.getPrice());
                                            grandTotal += m.getPrice();
                                        }


                                        System.out.println("\n🧾 INVOICE SUMMARY");
                                        System.out.println("Customer: " + customer.getUsername());
                                        System.out.println("----------------------------------");
                                        for (int i = 0; i < orders.size(); i++) {
                                            Meal m = orders.get(i);
                                            System.out.printf("%d. %-20s %.2f JD%n", i + 1, m.getName(), m.getPrice());
                                        }
                                        System.out.println("----------------------------------");
                                        System.out.printf("TOTAL: %.2f JD%n", grandTotal);
                                        System.out.println("----------------------------------");
                                    }
                                    break;

                                case 6:

                                    customerLoggedIn = false;
                                    System.out.println("🔓 Logged out. Returning to main menu.");
                                    break;

                                default:
                                    System.out.println("⚠️ Invalid choice. Try again.");
                            }

                        }
                    } else {
                        System.out.println("❌ Invalid customer credentials.");
                    }
                    break;

                case 2:
                    System.out.print("Enter chef username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter chef password: ");
                    password = scanner.nextLine();

                    Chef chef = new Chef();
                    chef.setUsername(username);
                    chef.setPassword(password);
                    loginResult = chef.login();

                    if (loginResult) {
                        boolean chefRunning = true;
                        while (chefRunning) {
                            System.out.println("\n=== Chef Dashboard ===");
                            System.out.println("1. View Customer Dietary Preferences");
                            System.out.println("2.customize meal on customer Preferences");
                            System.out.println("3. view customer past orders");
                            System.out.println("4. Logout");
                            System.out.print("Enter your choice: ");

                            int chefChoice = scanner.nextInt();
                            scanner.nextLine();
                            switch (chefChoice) {
                                case 1:
                                    System.out.print("Enter customer username to view preferences: ");
                                    String custUsername = scanner.nextLine();

                                    Customer matchedCustomer = allCustomers.stream()
                                            .filter(c -> c.getUsername().equalsIgnoreCase(custUsername))
                                            .findFirst()
                                            .orElse(null);

                                    if (matchedCustomer != null) {
                                        System.out.println("✅ Preferences for: " + custUsername);
                                        System.out.println("Preferences: " + matchedCustomer.getDietaryPreferences());
                                        System.out.println("Allergies: " + matchedCustomer.getAllergies());
                                    } else {
                                        System.out.println("❌ Customer not found.");
                                    }
                                    break;

                                case 2:
                                    System.out.print("Enter customer username to customize meal: ");
                                    String custUsernameForMeal = scanner.nextLine();

                                    matchedCustomer = allCustomers.stream()
                                            .filter(c -> c.getUsername().equalsIgnoreCase(custUsernameForMeal))
                                            .findFirst()
                                            .orElse(null);

                                    if (matchedCustomer == null) {
                                        System.out.println("❌ Customer not found.");
                                        break;
                                    }

                                    System.out.println("✅ Preferences for: " + custUsernameForMeal);
                                    System.out.println("Preferences: " + matchedCustomer.getDietaryPreferences());
                                    System.out.println("Allergies: " + matchedCustomer.getAllergies());

                                    System.out.print("Enter custom meal name: ");
                                    String mealName = scanner.nextLine();

                                    System.out.print("Enter ingredients (comma separated): ");
                                    List<String> ingredients = Arrays.asList(scanner.nextLine().split(",\\s*"));

                                    // Allergy check
                                    List<String> customerAllergies = Arrays.asList(matchedCustomer.getAllergies().split(",\\s*"));
                                    boolean containsAllergen = ingredients.stream().anyMatch(ingredient ->
                                            customerAllergies.contains(ingredient.trim()));

                                    if (containsAllergen) {
                                        System.out.println("❌ Cannot create meal! Contains allergens: " + matchedCustomer.getAllergies());
                                    } else {
                                        List<String> dietaryTags = new ArrayList<>();
                                        dietaryTags.add(matchedCustomer.getDietaryPreferences());

                                        Random rand = new Random();
                                        double price = 5.0 + (15.0 * rand.nextDouble()); // $5 - $20 range
                                        price = Math.round(price * 100.0) / 100.0;

                                        Meal customMeal = new Meal(mealName, price);
                                        customMeal.setIngredients(ingredients);
                                        System.out.println("✅ Custom meal created: " + customMeal.getName() + " ($" + price + ")");

                                        try (FileWriter fw = new FileWriter("suggestedMeals.txt", true)) {
                                            fw.write(System.lineSeparator() + customMeal.getName() + ";" +
                                                    String.join(",", customMeal.getIngredients()) + ";" +
                                                    price);
                                        } catch (IOException e) {
                                            System.out.println("⚠️ Could not save custom meal: " + e.getMessage());
                                        }
                                    }
                                    break;

                                case 3:
                                    System.out.print("Enter customer username to view order history: ");
                                    String targetUsername = scanner.nextLine();

                                    matchedCustomer = allCustomers.stream()
                                            .filter(c -> c.getUsername().equalsIgnoreCase(targetUsername))
                                            .findFirst()
                                            .orElse(null);

                                    if (matchedCustomer == null) {
                                        System.out.println("❌ No such customer found.");
                                        break;
                                    }

                                    List<Meal> orderHistory = matchedCustomer.getPastOrders();
                                    if (orderHistory.isEmpty()) {
                                        System.out.println("📭 This customer has no past orders.");
                                    } else {
                                        System.out.println("🧾 Past Orders for " + targetUsername + ":");
                                        Set<String> usedIngredients = new HashSet<>();

                                        for (Meal meal : orderHistory) {
                                            System.out.println("- " + meal.getName() + " (Ingredients: " + String.join(", ", meal.getIngredients()) + ")");
                                            usedIngredients.addAll(meal.getIngredients());
                                        }

                                        MealRecommender suggester = new MealRecommender();
                                        List<Meal> allMeals = suggester.suggestedMeals("suggestedMeals.txt");

                                        System.out.println("\n🍽️ Suggested Personalized Meals Based on Order History:");
                                        boolean suggestionMade = false;

                                        for (Meal meal : allMeals) {
                                            for (String ingredient : meal.getIngredients()) {
                                                if (usedIngredients.contains(ingredient)) {
                                                    System.out.println("- " + meal.getName() + " (Ingredients: " + String.join(", ", meal.getIngredients()) + ")");
                                                    suggestionMade = true;
                                                    break;
                                                }
                                            }
                                        }

                                        if (!suggestionMade) {
                                            System.out.println("No personalized suggestions found based on order history.");
                                        }
                                    }
                                    break;

                                case 4:
                                    chefRunning = false;
                                    System.out.println("🔓 Chef logged out.");
                                    break;

                                default:
                                    System.out.println("⚠️ Invalid choice. Try again.");
                            }
                        }
                    } else {
                        System.out.println("❌ Invalid chef credentials.");
                    }
                    break;
                case 3:
                    System.out.print("Enter chef manager username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter chef manager password: ");
                    password = scanner.nextLine();

                    ChefManager chefManager = new ChefManager();
                    chefManager.setUsername(username);
                    chefManager.setPassword(password);
                    loginResult = chefManager.login();

                    if (loginResult) {
                        System.out.println("✅ Chef Manager login successful.");
                        // Add dashboard logic here if needed
                    } else {
                        System.out.println("❌ Invalid chef manager credentials.");
                    }
                    break;

                case 4:
                    System.out.print("Enter admin username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    password = scanner.nextLine();

                    Admin admin = new Admin();
                    admin.setUsername(username);
                    admin.setPassword(password);
                    loginResult = admin.login();

                    if (loginResult) {
                        System.out.println("✅ Admin login successful.");
                        // Add admin dashboard logic here if needed
                        boolean adminDash = true;
                        while (adminDash) {
                            System.out.println("\n=== Admin Dashboard ===");
                            System.out.println("1. View ALL customers & their orders");
                            System.out.println("2. View customer invoices & total revenue");
                            System.out.println("3. Logout");
                            System.out.print("Choose: ");
                            int ad = scanner.nextInt();
                            scanner.nextLine();

                            switch (ad) {
                                case 1 -> {
                                    System.out.println("\n=== All Customers and Their Orders ===");

                                    Path path = Paths.get("orders.txt");
                                    if (Files.exists(path)) {
                                        try {
                                            List<String> lines = Files.readAllLines(path);
                                            if (lines.isEmpty()) {
                                                System.out.println("📭 No orders have been placed yet.");
                                            } else {
                                                lines.forEach(System.out::println);
                                            }
                                        } catch (IOException e) {
                                            System.out.println("⚠️ Could not read orders file: " + e.getMessage());
                                        }
                                    } else {
                                        System.out.println("⚠️ No orders file found.");
                                    }
                                }

                                case 2 -> {
                                    double totalRevenue = 0.0;

                                    for (Customer currentCustomer : allCustomers) {
                                        List<Meal> orders = currentCustomer.getPastOrders();

                                        if (orders.isEmpty()) {
                                            System.out.println("⚠️ Customer " + currentCustomer.getUsername() + " has no past orders.");
                                            continue;
                                        }

                                        System.out.println("\n📄 Invoice for Customer: " + currentCustomer.getUsername());
                                        double grandTotal = 0.0;

                                        for (int i = 0; i < orders.size(); i++) {
                                            Meal m = orders.get(i);

                                            if (m.getPrice() == 0.0) {
                                                double priceFromFile = 0.0;
                                                try (BufferedReader br = new BufferedReader(new FileReader("orders.txt"))) {
                                                    String line;
                                                    String target = currentCustomer.getUsername() + " -> " + m.getName() + " |";
                                                    while ((line = br.readLine()) != null) {
                                                        if (line.startsWith(target)) {
                                                            String[] parts = line.split("\\|");
                                                            if (parts.length >= 2) {
                                                                String pricePart = parts[1].trim();
                                                                if (pricePart.endsWith("JD")) {
                                                                    pricePart = pricePart.substring(0, pricePart.length() - 2).trim();
                                                                }
                                                                priceFromFile = Double.parseDouble(pricePart);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                } catch (IOException | NumberFormatException e) {
                                                    System.out.println("⚠️ Could not read price from file for meal '" + m.getName() + "': " + e.getMessage());
                                                }
                                                m.setPrice(priceFromFile);
                                            }

                                            System.out.printf("%d. %s - %.2f JD%n", i + 1, m.getName(), m.getPrice());
                                            grandTotal += m.getPrice();
                                        }

                                        // Print invoice summary
                                        System.out.println("\n🧾 INVOICE SUMMARY");
                                        System.out.println("----------------------------------");
                                        for (int i = 0; i < orders.size(); i++) {
                                            Meal m = orders.get(i);
                                            System.out.printf("%d. %-20s %.2f JD%n", i + 1, m.getName(), m.getPrice());
                                        }
                                        System.out.println("----------------------------------");
                                        System.out.printf("TOTAL for %s: %.2f JD%n", currentCustomer.getUsername(), grandTotal);
                                        System.out.println("----------------------------------");

                                        totalRevenue += grandTotal;
                                    }


                                    System.out.println("\n💰 TOTAL REVENUE FROM ALL CUSTOMERS: " + String.format("%.2f JD", totalRevenue));
                                }

                                case 3 -> {
                                    adminDash = false;
                                    System.out.println("🔒 Admin logged out.");
                                }

                                default -> System.out.println("⚠️ Invalid choice. Try again.");
                            }
                        }

                    }

                case 0:


                    running = false;
                    System.out.println("👋 Exiting system. Goodbye!");
                    break;

            }



            //   scanner.close();
        }
    }}
