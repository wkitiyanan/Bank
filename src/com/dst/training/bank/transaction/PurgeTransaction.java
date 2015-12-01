package com.dst.training.bank.transaction;

import com.dst.training.bank.Bank;

/**
*
* Class description
*
* Class for 'P'urge transaction
*
* @author  Woranat Kitiyanan
*/

public class PurgeTransaction extends Transaction {

	@Override
	protected boolean validate() {
		boolean valid = false;
		
		//Transaction is valid when the account exists and balance is zero
		if(getFromAccount() != null && getFromAccount().getBalance() == 0){
			valid = true;
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Bank.getInstance().removeAccount(getFromAccount());
	}

}
