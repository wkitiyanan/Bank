package com.dst.training.apps;

import com.dst.training.bank.Bank;

public class BankApp {

	public static void main(String[] args) {
		Bank.getInstance().process();
	}

}
