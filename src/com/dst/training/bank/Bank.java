package com.dst.training.bank;

import com.dst.training.bank.account.Account;
import com.dst.training.bank.account.Accounts;
import com.dst.training.bank.transaction.ITransaction;
import com.dst.training.bank.transaction.Transaction;
import com.dst.training.bank.utilities.AccountParser;
import com.dst.training.bank.utilities.FileUtility;
import com.dst.training.bank.utilities.TransactionParser;

/**
*
* Class description
*
* The class that controls the flow of system containing files of accounts and transactions
* Mostly, accounts and transactions are built and process here
* Can be only instantiated by static method getInstance()
* Have only one instance in application (Singleton design pattern)
*
* @author  Woranat Kitiyanan
*/

public class Bank
{
    private static Bank instance;
    private Accounts accounts = new Accounts();
    
    private String accountFileName = "files/Master.txt";
    private FileUtility accountFile = new FileUtility();
    
    private String transactionFileName = "files/Trans.txt";
    private FileUtility transactionFile = new FileUtility();
    
    private Bank(){}
    
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
    	
    	writeMasterFile();
    }

	/**
    * Load all accounts from master file into the list of accounts
    *
    * @return void
    */
    private void loadAccounts(){
    	System.out.println("Loading accounts...");
    	
    	//Open and read account data from the file
    	accountFile.openReadFile(accountFileName);
    	AccountParser accountData = accountFile.getNextAccount();
    	
    	//Instantiate, populate and add all accounts retrieved from the master file
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
		
		transactionFile.openReadFile(transactionFileName);
		TransactionParser transactionData = transactionFile.getNextTransaction();
		
		// Build and execute all transactions retrieved from the file except invalid transactions
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
     * Write to files/Master_New.txt
     *
     * @return void
     */
    private void writeMasterFile() {
		FileUtility fileUtility = new FileUtility();
		fileUtility.writeFile("files/Master_New.txt", accounts.getAccountList());
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
