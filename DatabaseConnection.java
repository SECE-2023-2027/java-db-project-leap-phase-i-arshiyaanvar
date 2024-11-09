package com.org.bank.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {	
 private static final String URL="jdbc:mysql://localhost:3306/bank_app";
 private static final String USERNAME = "root";
 private static final String PASSWORD ="Arsh@2006";
   public static Connection getConnection() {
	   Connection connection = null;
	   try { 
		   connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				   System.out.println("Database connected successfully");
	   }catch (SQLException e) {
		   e.printStackTrace();
	   }
	return connection;
	   
   } 
   public static void main(String[] args) {
		DatabaseConnection.getConnection();
	}
}
