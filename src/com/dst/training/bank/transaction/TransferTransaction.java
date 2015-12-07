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
		
		if(hasFromAccount() && hasToAccount() && hasEnoughBalance()){
			valid = true;					
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		getFromAccount().decreaseBalance(getAmount());
		getToAccount().increaseBalance(getAmount());
	}

}
