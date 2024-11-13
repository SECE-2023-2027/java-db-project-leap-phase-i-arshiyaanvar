package com.org.diet;

import java.util.ArrayList;

public class MealPlan {
    private ArrayList<String> meals;

    public MealPlan() {
        meals = new ArrayList<>();
    }

    public void addMeal(String meal) {
        meals.add(meal);
    }

    public void printMealPlan() {
        System.out.println("Your Meal Plan:");
        for (String meal : meals) {
            System.out.println(meal);
        }
    }
}
