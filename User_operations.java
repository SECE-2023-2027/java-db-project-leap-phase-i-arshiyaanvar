package com.org.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_operations {

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
            System.out.println("Error reading user data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        readUsers();
    }
}