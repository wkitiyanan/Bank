package com.dst.training.apps;

import com.dst.training.bank.Bank;

/**
*
* Class description
*
* Main class for Bank system
* Instantiate and process Bank instance
*
* @author  Woranat Kitiyanan
*/

public class BankApp {

	public static void main(String[] args) {
		Bank.getInstance().process();
	}

}
