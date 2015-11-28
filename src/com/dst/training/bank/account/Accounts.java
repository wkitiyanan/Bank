package com.dst.training.bank.account;

import java.util.ArrayList;

public class Accounts {
	private ArrayList<Account> accountList;
	
	public void addAccount(Account account){
		accountList.add(account);
	}
	
	public Account getAccount(String accountNumber){
		Account resultAccount = null;
		
		for (Account account : accountList) {
			if(account.getAccountNumber().equals(accountNumber)){
				resultAccount = account;
			}
		}
		
		return resultAccount;
	}
}
