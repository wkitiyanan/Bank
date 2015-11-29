package com.dst.training.bank;

import com.dst.training.bank.account.Account;
import com.dst.training.bank.account.Accounts;
import com.dst.training.bank.transaction.ITransaction;
import com.dst.training.bank.transaction.Transaction;
import com.dst.training.bank.utilities.AccountFileUtility;
import com.dst.training.bank.utilities.AccountParser;
import com.dst.training.bank.utilities.TransactionFileUtility;
import com.dst.training.bank.utilities.TransactionParser;

public class Bank
{
    private static Bank     instance;
    private Accounts accounts = new Accounts();
    
    private AccountFileUtility accountFile = new AccountFileUtility();
    private TransactionFileUtility transactionFile = new TransactionFileUtility();
    
    public void process(){
    	System.out.println("Loading...");
    	loadAccounts();
    	
    	System.out.println("\nAccount List(Before):");
    	printAccounts();
    	
    	System.out.println("processing...");
    	processTransactions();
    	
    	System.out.println("\nAccount List(After):");
    	printAccounts();
    }
    
    private void printAccounts() {
    	String header1 = "Account No.";
    	String header2 = "Status";
    	String header3 = "Balance";
    	String divider = "-------------------------------------";
    	System.out.printf("%-15s %-15s %-15s %n", header1, header2, header3);
    	System.out.println(divider);
		accounts.print();
		System.out.println("");
	}

	private void processTransactions() {
		TransactionParser transactionData = transactionFile.getNextTransaction();
		while(transactionData != null){
			ITransaction transaction = Transaction.getTransaction(transactionData);
			if(transaction != null){
				transaction.build(transactionData);
				transaction.process();
			}
			transactionData = transactionFile.getNextTransaction();
		}
	}

	private void loadAccounts(){
		AccountParser accountData = accountFile.getNextAccount();
		while(accountData != null){
			Account account = new Account();
			account.build(accountData);
			addAccount(account);
			accountData = accountFile.getNextAccount();
		}
    }

	public void addAccount(Account account) {
		accounts.addAccount(account);
	}

    public static Bank getInstance()
    {
        if ( instance == null )
        {
            instance = new Bank();
        }

        return instance;
    }
    
    public Account getAccount(String accountNumber){
    	return accounts.getAccount(accountNumber);
    }

}
