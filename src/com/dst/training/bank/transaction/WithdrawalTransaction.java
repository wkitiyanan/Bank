package com.dst.training.bank.transaction;

import com.dst.training.bank.account.Account;

/**
*
* Class description
*
* Class for 'W'ithdrawal transaction
*
* @author  Woranat Kitiyanan
*/

public class WithdrawalTransaction extends Transaction{

	@Override
	protected boolean validate() {
		boolean valid = false;
		Account account = getFromAccount();
		
															//This transaction will be valid if
		if(account != null 									// the account is existing
				&& getAmount() > 0 							// amount is greater than zero
				&& account.getBalance() >= getAmount()){ 	// balance is greater than or equals to withdrawal amount
			valid = true;
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Account account = getFromAccount();
		account.decreaseBalance(getAmount());
		
		//Account is closed when the balance is zero
		if(account.getBalance() == 0) account.setStatus('C');
	}
}
