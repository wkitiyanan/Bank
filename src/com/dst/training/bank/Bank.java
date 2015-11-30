package com.dst.training.bank;

import com.dst.training.bank.account.Account;
import com.dst.training.bank.account.Accounts;
import com.dst.training.bank.transaction.ITransaction;
import com.dst.training.bank.transaction.Transaction;
import com.dst.training.bank.utilities.AccountFileUtility;
import com.dst.training.bank.utilities.AccountParser;
import com.dst.training.bank.utilities.TransactionFileUtility;
import com.dst.training.bank.utilities.TransactionParser;

/**
*
* Class description
*
* The class that controls the flow of system containing files of accounts and transactions
* Mostly, accounts and transactions are built and process here
* Have only one instance in application (Singleton design pattern)
*
* @author  Woranat Kitiyanan
*/

public class Bank
{
    private static Bank instance;
    private Accounts accounts = new Accounts();
    
    private AccountFileUtility accountFile = new AccountFileUtility();
    private TransactionFileUtility transactionFile = new TransactionFileUtility();
    
    /**
    * Proceed the flow of system
    *
    * @return void
    */
    public void process(){
    	loadAccounts();
    	
    	System.out.println("\n           Account List(Before)");
    	printAccounts();
    	
    	processTransactions();
    	
    	System.out.println("\n           Account List(After)");
    	printAccounts();
    }

    /**
    * Load all accounts from master file into the list of accounts
    *
    * @return void
    */
    private void loadAccounts(){
    	System.out.println("Loading accounts...");
    	AccountParser accountData = accountFile.getNextAccount();
    	
    	while(accountData != null){
    		Account account = new Account();
    		account.build(accountData);
    		addAccount(account);
    		accountData = accountFile.getNextAccount();
    	}
    }
    
    /**
     * Build concrete instance and process all transactions read from the file
     *
     * @return void
     */
	private void processTransactions() {
		System.out.println("Processing transactions...");
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
	
	/**
     * Static method to get Bank instance (Singleton)
     *
     * @return void
     */
    public static Bank getInstance()
    {
        if ( instance == null )
        {
            instance = new Bank();
        }

        return instance;
    }
    
    public void addAccount(Account account) {
    	accounts.addAccount(account);
    }
    public void removeAccount(Account account) {
    	accounts.removeAccount(account);
    }
    public Account getAccount(String accountNumber){
    	return accounts.getAccount(accountNumber);
    }

    private void printAccounts() {
        
    	String header1 = "Account No.";
    	String header2 = "Status";
    	String header3 = "Balance";
    	String divider = "--------------------------------------------";
    	System.out.printf("%-15s %-15s %-15s %n", header1, header2, header3);
    	System.out.println(divider);
		accounts.print();
		System.out.println("");
	}
}
