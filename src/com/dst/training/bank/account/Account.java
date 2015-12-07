package com.dst.training.bank.account;

import com.dst.training.bank.utilities.AccountParser;

/**
*
* Class description
*
* A class represents the structure of a bank account
*
* @author  Woranat Kitiyanan
*/

public class Account {
	private String accountNumber;
	private char status = 'A';
	private double balance = 0;
	
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
	
	public void activate(){
		if(getStatus() == 'C'){
			setStatus('A');
			System.out.println( "[Activation] " + getAccountNumber()
			+ " is activated!" );
		}
	}
	
	public void close(){
		if(getStatus() == 'A'){
			setStatus('C');
		}
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
	public void print(){
		System.out.printf("%-15s %-15s %-15.2f%n", getAccountNumber(), getStatus(), getBalance());
	}
	public String toString(){
		return getAccountNumber()+getStatus()+ String.format("%015d", (int)getBalance());
	}
}
