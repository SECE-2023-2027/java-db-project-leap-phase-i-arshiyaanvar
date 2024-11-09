package com.org.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.org.bank.model.Account;
import com.org.bank.utils.DatabaseConnection;

public class AccountDaoImpl implements AccountDAO {

	@Override
	public void createAccount(Account account) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO account (account_holder,account_type,balance,address,contact_number) VALUES(?,?,?,?,?)";
		try(Connection conn=DatabaseConnection.getConnection();
				PreparedStatement stat=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
			stat.setString(1,account.getAccountholder());
			stat.setString(2,account.getAccountType());
			stat.setDouble(3,account.getBalance());
			stat.setString(4,account.getAddress());
			stat.setString(5,account.getContactNumber());
			int affectedRows = stat.executeUpdate();
			if(affectedRows > 0) {
				ResultSet generatedKeys = stat.getGeneratedKeys();
				if(generatedKeys.next()) {
					int accountId = generatedKeys.getInt(1);
					if(account.getAccountType().equals("Savings")) {
						String sqlsavingAccount="INSERT INTO SavingsAccount (account_id,interest_rate) VALUES(?,?)";
						PreparedStatement statSaving = conn.prepareStatement(sqlsavingAccount);
						statSaving.setInt(1, accountId);
						statSaving.setDouble(2, 0.4);
						statSaving.executeUpdate();
					}
					else if (account.getAccountType().equals("Current")) {
						String sqlCurrentAccount="INSERT INTO CurrentAccount (account_id,overdraft_limit) VALUES(?,?)";
						PreparedStatement statCurrent = conn.prepareStatement(sqlCurrentAccount);
						statCurrent.setInt(1, accountId);
						statCurrent.setDouble(2, 5000.00);
						statCurrent.executeUpdate();

						
					}
				}
			}
		}
		System.out.println("Account Created Sucessfully");
	}

	@Override
	public Account viewAccount(int accountId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "Select * from Account where account_id = ?";
		Account account = null;
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, accountId);
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				account= new Account(
						rs.getInt("account_id"),
						rs.getString("account_holder"),
						rs.getString("account_type"),
						rs.getDouble("balance"),
						rs.getString("address"),
						rs.getString("contact_number"));
						
			}
			if(account != null) {
				System.out.println("Account details retrieved successfully");
			}else {
				System.out.println("Account not found");
			}
			
		}
		return account;
	}

	@Override
	public void updateAccountInfo(int accountId, String newAddress, String newContact) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update Account set address = ?, contact_number = ? where account_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, newAddress);
			stat.setString(2, newContact);
			stat.setInt(3, accountId);
			int rowsAffected = stat.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Account information updated successfully");
			}else {
				System.out.println("Account not found or update failed");
			}
			
		}
		
	}

	@Override
	public void getBalance(int accounId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
