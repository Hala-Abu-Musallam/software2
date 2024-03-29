package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class User {
    private Map<String, String> users = new HashMap<>();
    public static boolean loginFlag = false;
    public static int user_type; // 1: admin, 2: user, 3: service provider

    public User() {
        getUsersFromFile();
    }

    public void login(String username, String password) {
        // Reload users from file to get the most updated data
        getUsersFromFile();
        if (users.containsKey(username) && users.get(username).equals(password)) {
            loginFlag = true;
            setUserType(username);
            System.out.println("Login successful!");
        } else {
            loginFlag = false;
            System.out.println("Login failed: check your username or password.");
        }
    }

    public void adduser(String email, String password) {
        // Reload users from file to ensure we have the most updated data
        getUsersFromFile();
        if (users.containsKey(email)) {
            System.out.println("User already exists.");
            loginFlag = false;
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/users.txt", true))) {
                bw.write(email + "," + password + System.lineSeparator());
                // Immediately reflect changes in the current users map
                users.put(email, password);
                System.out.println("User registered successfully.");
                loginFlag = true;
            } catch (IOException e) {
                System.out.println("Error writing to users file: " + e.getMessage());
                loginFlag = false;
            }
        }
    }

    private void getUsersFromFile() {
        users.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("src/users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from users file: " + e.getMessage());
        }
    }

    private void setUserType(String username) {
        if (username.contains("@admin.com")) {
            user_type = 1;
            System.out.println("Welcome Admin :)");
        } else if (username.contains("@user.com")) {
            user_type = 2;
            System.out.println("Welcome User :)");
        } else if (username.contains("@serviceprovider.com")) {
            user_type = 3;
            System.out.println("Welcome Service Provider :)");
        } else {
            user_type = -1;
            System.out.println("Unknown user type.");
        }
    }

    public void clearUsers() {
        // Clear the users map and any other relevant state
        users.clear();
        loginFlag = false;
        // Optionally, reset user_type or other static variables if needed
        user_type = -1; // Resetting to default or initial state
    }

}
