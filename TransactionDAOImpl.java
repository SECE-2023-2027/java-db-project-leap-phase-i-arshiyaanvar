package com.org.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.org.bank.utils.DatabaseConnection;

public class TransactionDAOImpl implements TransactionDAO{

	private double getCurrentBalance(Connection conn, int accountId) throws SQLException{
    	String checkBalanceQuery = "SELECT BALANCE FROM Account where account_id = ?";
    			try(PreparedStatement ps = conn.prepareStatement(checkBalanceQuery))
    			{
    				ps.setInt(1,accountId);
    				ResultSet rs = ps.executeQuery();
    				if(rs.next())
    				{
    					return rs.getDouble("balance");
    				}
    			}
				return accountId;
    }
    private String getAccountType(Connection conn, int accountId) throws SQLException{
    	String sql = "SELECT account_type from Account where account_id = ?";
    	try (PreparedStatement stmt = conn.prepareStatement(sql))
    	{
    		stmt.setInt(1, accountId);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next())
    		{
    			return rs.getString("account_type");
    		}
    		else
    		{
    			throw new SQLException("Account not found.");
    		}
    	}
    }
    private double getOverdraftLimit(Connection conn,int accountId) throws SQLException{
    	String sql = "select overdraft_limit from currentaccount where account_id = ?";
    			try(PreparedStatement stat = conn.prepareStatement(sql)){
    				stat.setInt(1, accountId);
    				ResultSet rs = stat.executeQuery();
    				if(rs.next()) {
    					return rs.getDouble("overdraft_limit");
    				}else {
    					throw new SQLException("Current account not found or overdraft limit  not set");
    				}
    					
    				}
    			}
	

	@Override
	public void withdraw(int accountId, double amount) throws Exception {
		// TODO Auto-generated method stub
		try(Connection conn = DatabaseConnection.getConnection()){
			conn.setAutoCommit(false);
			String accountType = getAccountType(conn,accountId);
			double currentBalance = getCurrentBalance(conn,accountId);
			
			if(accountType.equals("Current"))
			{
				double overdraftLimit = getOverdraftLimit(conn,accountId);
				if(amount>=overdraftLimit)
				{
					throw new Exception("Insufficient balance and overdraft limit for withdrawal.");
				}
			}
			if(amount>currentBalance)
			{
				throw new Exception("Insufficient balance for withdrawal.");
			}
			
			
			String updateBalanceQuery = "Update Account set balance = balance-? where account_id =?";
			String insertTransactionQuery = "Insert into Transaction(account_id,transaction_type,amount) values(?,'Withdrawal',?)";
			
			executeTransaction(conn,accountId,amount,updateBalanceQuery,insertTransactionQuery);
			conn.commit();
			System.out.println("Withdrawal of $"+ amount+" from account "+ accountId+" successfully");
			
		}
		
	}
	@Override
	public void transfer(int fromAccountId, int toAccountId, double amount) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayTransactionHistory(int accountId) {
		// TODO Auto-generated method stub
		
	}
	private void executeUpdate(Connection conn, String query,double amount, int accountId) throws SQLException{
       try (PreparedStatement ps = conn.prepareStatement(query)){
    	   ps.setDouble(1, amount);
    	   ps.setInt(2, accountId);
    	   ps.executeUpdate();
       }
       }
private void executeTransaction(Connection conn, int accountId, double amount, String updateBalanceQuery, String insertTransactionQuery)
throws SQLException{
executeUpdate(conn, updateBalanceQuery, amount, accountId);
try (PreparedStatement ps = conn.prepareStatement(insertTransactionQuery)){
      ps.setInt(1, accountId);
	  ps.setDouble(2, amount);
		ps.executeUpdate();
}}




@Override
public void deposit(int accountId, double amount) throws Exception {
	 try (Connection conn = DatabaseConnection.getConnection()) {
		 String updateBalanceQuery = "UPDATE Account SET balance = balance + ? WHERE account_id = ?";
	        String insertTransactionQuery = "Insert into Transaction (account_id,transaction_type,amount) VALUES (?,'Deposit',?)";
	        executeTransaction(conn, accountId, amount, updateBalanceQuery, insertTransactionQuery);
	        System.out.println("Deposit of $" + amount + " to account " + accountId + " completed");
	 }
	// TODO Auto-generated method stub
	
}
}
