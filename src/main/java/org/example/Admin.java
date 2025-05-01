package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin {
    private String username;
    private String password;
    private String loginMessage;
    private boolean loggedIn;
    private Map<String, List<Meal>> customerOrderHistoryDB = new HashMap<>();

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void clear() {
        loggedIn = false;
        loginMessage = "";
    }

    public boolean login() {
        try (BufferedReader reader = new BufferedReader(new FileReader("admins.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String fileUsername = parts[0].trim();
                String filePassword = parts[1].trim();

                if (fileUsername.equals(username)) {
                    if (filePassword.equals(password)) {
                        loggedIn = true;
                        return true;
                    } else {
                        loginMessage = "Incorrect password";
                        return false;
                    }
                }
            }
            loginMessage = "User not found";
            return false;
        } catch (IOException e) {
            loginMessage = "Error reading file";
            return false;
        }
    }
    public void storeCustomerOrderHistory(Customer customer) {
        if (loggedIn) {
            customerOrderHistoryDB.put(customer.getUsername(), customer.getPastOrders());
        }
    }

    public List<Meal> retrieveCustomerOrderHistory(String customerUsername) {
        return customerOrderHistoryDB.getOrDefault(customerUsername, new ArrayList<>());
    }

    public String getLoginMessage() {
        return loginMessage;
    }
}
