package com.dst.training.bank.transaction;

import com.dst.training.bank.account.Account;

/**
*
* Class description
*
* Class for 'D'eposit transaction
*
* @author  Woranat Kitiyanan
*/

public class DepositTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		
		if(hasFromAccount()){
			valid = true;
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Account fromAccount = getFromAccount();
		fromAccount.increaseBalance(getAmount());
	}

}
