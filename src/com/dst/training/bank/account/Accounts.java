package com.dst.training.bank.account;

import java.util.ArrayList;

/**
*
* Class description
*
* A class contains the list of accounts and logic to manage them
* This class is created to reduce complexity of the Bank class
* The Bank class does not need to contain too much logic to control account list
*
* @author  Woranat Kitiyanan
*/

public class Accounts {
	private ArrayList<Account> accountList = new ArrayList<Account>();

	/**
	* Add account to list
	*
	* @param instance of account to be added
	* @return void
	*/
	public void addAccount(Account account){
		accountList.add(account);
	}
	
	/**
	* Remove account from list
	*
	* @param instance of account to be removed
	* @return void
	*/
	public void removeAccount(Account account) {
		accountList.remove(account);
	}
	
	/**
	* Get account from list by account number
	*
	* @param String of account number
	* @return Instance of derived account
	*/
	public Account getAccount(String accountNumber){
		Account resultAccount = null;
		
		for (Account account : accountList) {
			if(account.getAccountNumber().equals(accountNumber)){
				resultAccount = account;
			}
		}
		
		return resultAccount;
	}
	
	/**
	* Print all accounts in the list
	*
	* @return void
	*/
	public void print(){
		for (Account account : accountList) {
			account.print();
		}
	}

	/**
	* Getter for accountList
	*
	* @return ArrayList of accounts
	*/
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
}
