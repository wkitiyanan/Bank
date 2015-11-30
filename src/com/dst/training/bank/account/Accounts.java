package com.dst.training.bank.account;

import java.util.ArrayList;

/**
*
* Class description
*
* A class contains the list of accounts and logic to manage them
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
	
	public void print(){
		for (Account account : accountList) {
			System.out.println(account.toString());
		}
	}
}
