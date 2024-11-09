package com.org.bank;

import java.util.Scanner;
import com.org.bank.dao.AccountDAO;
import com.org.bank.dao.AccountDaoImpl;
import com.org.bank.dao.TransactionDAO;
import com.org.bank.dao.TransactionDAOImpl;
import com.org.bank.model.Account;

public class Main {

    public static void main(String args[]) throws Exception{
    	AccountDAO accountDAO = new AccountDaoImpl();
    	TransactionDAO transactionDAO= new TransactionDAOImpl();
        Scanner sc=new Scanner(System.in);
        while(true ){
            System.out.println("\n==Banking Application Menu===");
            System.out.println("1.Create a New Account");
            System.out.println("2.View Account Details");
            System.out.println("3.Update Account Information");
            System.out.println("4.Deposit Money");
            System.out.println("5.Withdraw Money");
            System.out.println("6.Transfer Money");
            System.out.println("7.View Transaction");
            System.out.println("8.Check Balance");
            System.out.println("9.Exit");
            System.out.println("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Enter Accont Holder Name: ");
                    String account_holder=sc.nextLine();
                    System.out.println("Enter account type (Savings/Current): ");
                    String account_type=sc.nextLine();
                    System.out.println("Enter initial balance: ");
                    double balance=sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter address: ");
                    String address=sc.nextLine();
                    System.out.println("Enter contact Number: ");
                    String contact_number=sc.nextLine();
                    Account newAccount = new Account(0,account_holder,account_type,balance,address,contact_number);
				try {
					accountDAO.createAccount(newAccount);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;
                    
                case 2:
                	System.out.print("Enter account ID to view :");
                	int viewAccountId = sc.nextInt();
                	Account retrievedAccount = accountDAO.viewAccount(viewAccountId);
                	if(retrievedAccount != null) {
                		System.out.println("Account Details: " + retrievedAccount);
                		} else {
                			System.out.println("Account not found");
                		}
                	break;
                case 3:
                	System.out.print("Enter account ID to update:");
                	int updateAccountId = sc.nextInt();
                	sc.nextLine();
                	System.out.print("Enter new address:");
                	String newAddress = sc.nextLine();
                	System.out.print("Enter new contact number: ");
                	String newContact = sc.nextLine();
                	accountDAO.updateAccountInfo(updateAccountId, newAddress, newContact);
                	break;
                case 4:
                	System.out.print("Enter account ID to deposit into:");
                	int depositAccountId = sc.nextInt();
                	System.out.print("Enter amount to deposit:");
                	double depositAmount=sc.nextDouble();
                	transactionDAO.deposit(depositAccountId, depositAmount);
                	break;
                	
                case 5:
                    System.out.println("Enter account ID to withdraw: ");
                    int withdrawAccountId=sc.nextInt();
                    System.out.println("Enter amount to withdraw: ");
                    double withdrawAmount=sc.nextDouble();
                    transactionDAO.withdraw(withdrawAccountId,withdrawAmount);
                    break;
                	
            }


        }
    }
}