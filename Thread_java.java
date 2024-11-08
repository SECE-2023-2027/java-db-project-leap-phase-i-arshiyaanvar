package com.org.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Thread_java {
	    // Method to read and display user data
	    public static void readUsers() {
	        String sql = "SELECT * FROM user";
	        try (Connection connection = Database_Manager.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                int id = resultSet.getInt("userId");
	                String name = resultSet.getString("name");
	                String password = resultSet.getString("password");
	                long phoneNumber = resultSet.getLong("phoneNumber");
	                String address = resultSet.getString("address");
	                String email = resultSet.getString("email");

	                System.out.println("User ID: " + id + ", Name: " + name + ", Password: " + password
	                        + ", Phone Number: " + phoneNumber + ", Address: " + address + ", Email: " + email);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Method to insert a new user
	    public static void insertUser(String name, String password, long phoneNumber, String address, String email) {
	        String sql = "INSERT INTO user (name, password, phoneNumber, address, email) VALUES ('Seetha','Seetha@2003', '9896283456', 'Singampatti','seetha@gmail.com')";
	        try (Connection connection = Database_Manager.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setString(1, name);
	            statement.setString(2, password);
	            statement.setLong(3, phoneNumber);
	            statement.setString(4, address);
	            statement.setString(5, email);

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("A new user was inserted successfully!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Method to update a user's information
	    public static void updateUser(int userId, String newName, String newPassword, long newPhoneNumber, String newAddress, String newEmail) {
	        String sql = "UPDATE user SET  password = 'dineshkumar' WHERE userId = '202';";
	        try (Connection connection = Database_Manager.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setString(1, newName);
	            statement.setString(2, newPassword);
	            statement.setLong(3, newPhoneNumber);
	            statement.setString(4, newAddress);
	            statement.setString(5, newEmail);
	            statement.setInt(6, userId);

	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("User with ID " + userId + " was updated successfully!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Method to delete a user by userId
	    public static void deleteUser(int userId) {
	        String sql = "DELETE FROM user WHERE userId = '205';";
	        try (Connection connection = Database_Manager.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setInt(1, userId);

	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("User with ID " + userId + " was deleted successfully!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Main method to demonstrate the use of threads with insert, update, delete, and read operations
	    public static void main(String[] args) {
	        // Thread for reading user data
	        Thread readThread = new Thread(User_operations::readUsers);
	        
	        // Thread for inserting a user
	        Thread insertThread = new Thread(() -> insertUser("John Doe", "password123", 9876543210L, "New York", "johndoe@example.com"));
	        
	        // Thread for updating a user
	        Thread updateThread = new Thread(() -> updateUser(101, "Jane Doe", "newpassword456", 8765432109L, "Los Angeles", "janedoe@example.com"));
	        
	        // Thread for deleting a user
	        Thread deleteThread = new Thread(() -> deleteUser(100));
	        
	        // Start threads
	        readThread.start();
	        insertThread.start();
	        updateThread.start();
	        deleteThread.start();

	        try {
	            // Join threads to ensure they complete before the main method exits
	            readThread.join();
	            insertThread.join();
	            updateThread.join();
	            deleteThread.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println("Database operations completed.");
	    }
	
	    
}
