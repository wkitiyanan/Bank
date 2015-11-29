package com.dst.training.bank.account;

import com.dst.training.bank.utilities.AccountParser;

public class Account {
	private String accountNumber;
	private char status;
	private double balance;
	
	public void build(AccountParser parser){
		accountNumber = parser.getAccountNumber();
		status = parser.getStatus();
		balance = parser.getBalance();
	}
	public void increaseBalance(double amount){
		setBalance(getBalance() + amount);
	}
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
	
}
