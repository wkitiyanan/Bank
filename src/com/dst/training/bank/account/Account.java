package com.dst.training.bank.account;

import com.dst.training.bank.utilities.AccountParser;

/**
*
*Class description
*
* A class represents the structure of a bank account
*
* @author  Woranat Kitiyanan
*/

public class Account {
	private String accountNumber;
	private char status;
	private double balance;
	
	/**
	* Populate values from AccountParser to itself
	* @param parser the AccountParser
	* @return void
	*/
	public void build(AccountParser parser){
		accountNumber = parser.getAccountNumber();
		status = parser.getStatus();
		balance = parser.getBalance();
	}
	
	/**
	* Increase balance in account by amount
	* @param amount to increase
	* @return void
	*/
	public void increaseBalance(double amount){
		setBalance(getBalance() + amount);
	}
	
	/**
	* Decrease balance in account by amount
	* @param amount to decrease
	* @return void
	*/
	public void decreaseBalance(double amount){
		setBalance(getBalance() - amount);
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String toString(){
		return String.format("%-15s %-15s %-15.2f", getAccountNumber(), getStatus(), getBalance());
	}
}
