package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChefManager {
    private String username;
    private String password;
    private String loginMessage;
    private boolean loggedIn;

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
        try (BufferedReader reader = new BufferedReader(new FileReader("chefManager.txt"))) {
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

    public String getLoginMessage() {
        return loginMessage;
    }
}
