package com.org.diet;

public class User {
	private String name;
    private HealthGoal goal;
    private String preferences;

    public User(String name, HealthGoal goal, String preferences) {
        this.name = name;
        this.goal = goal;
        this.preferences = preferences;
    }

    public String getName() {
        return name;
    }

    public HealthGoal getGoal() {
        return goal;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

}
