package com.org.diet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Create a user
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your health goal (Weight Loss / Muscle Gain): ");
        String goalName = scanner.nextLine();
        HealthGoal goal = new HealthGoal(goalName);

        System.out.print("Enter your food preferences: ");
        String preferences = scanner.nextLine();

        User user = new User(name, goal, preferences);

        // Step 2: Create the recommendation system and save user
        DietaryRecommendationSystem system = new DietaryRecommendationSystem();
        system.saveUser(user);

        // Step 3: Generate and print meal plan
        MealPlan mealPlan = system.generateMealPlan(user);
        mealPlan.printMealPlan();

        // Step 4: Update preferences (if needed)
        System.out.print("Would you like to update your preferences? (yes/no): ");
        String updateChoice = scanner.nextLine();
        if (updateChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter new preferences: ");
            String newPreferences = scanner.nextLine();
            system.updatePreferences(user, newPreferences);
        

        // Step 5: Get recommendations based on the user
        system.getRecommendations(user);

        scanner.close();
    }
}}
