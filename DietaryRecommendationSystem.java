package com.org.diet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.org.diet.utils.DatabaseConnection;

public class DietaryRecommendationSystem {

    // Save user to database
    public void saveUser(User user) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Users (name, goal, preferences) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getGoal().getGoalName());
            stmt.setString(3, user.getPreferences());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update user preferences
    public void updatePreferences(User user, String newPreferences) {
        user.setPreferences(newPreferences);
        try (Connection conn =DatabaseConnection.getConnection()) {
            String query = "UPDATE Users SET preferences = ? WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newPreferences);
            stmt.setString(2, user.getName());
            stmt.executeUpdate();
            System.out.println("Preferences updated in database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Generate a MealPlan (placeholder implementation)
    public MealPlan generateMealPlan(User user) {
        MealPlan mealPlan = new MealPlan();
        if (user.getGoal().getGoalName().equalsIgnoreCase("Muscle Gain")) {
            mealPlan.addMeal("Grilled Chicken Breast");
            mealPlan.addMeal("Quinoa and Avocado");
            mealPlan.addMeal("Protein Shake");
            mealPlan.addMeal("Egg");
            System.out.println("This is the Recommended meals for your Diet!!!!!");
            System.out.println("Good Luck :) ");
            
        } else if (user.getGoal().getGoalName().equalsIgnoreCase("Weight Loss")) {
            mealPlan.addMeal("Salad with Chicken");
            mealPlan.addMeal("Steamed Vegetables");
            mealPlan.addMeal("Smoothie");
            mealPlan.addMeal("Milk");
            System.out.println("This is the Recommended meals for your Diet!!!!!");
            System.out.println("Good Luck :) ");
            
        }
        return mealPlan;
    }

    public void getRecommendations(User user) {
        System.out.println("Getting recommendations for: " + user.getName());
        System.out.println("Health Goal: " + user.getGoal().getGoalName());
    }
}
