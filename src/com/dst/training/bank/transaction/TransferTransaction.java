package com.dst.training.bank.transaction;

/**
*
* Class description
*
* Class for 'T'ransfer transaction
*
* @author  Woranat Kitiyanan
*/

public class TransferTransaction extends Transaction {

	@Override
	protected boolean validate() {
		boolean valid = false;
		
		if(getFromAccount() != null						//Debit account exists
				&& getToAccount() != null				//Credit accout exists
				&& getFromAccount().getBalance() >= getAmount()){ //Debit account balance is greater than Credit account balance
			valid = true;								//Transaction is valid
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		getFromAccount().decreaseBalance(getAmount());
		getToAccount().increaseBalance(getAmount());
	}

}
