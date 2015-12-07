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
		
		// TODO this might be another place you can reuse an isClosable method.
		if(hasFromAccount() && isClosable()){
			valid = true;
		}
		
		return valid;
	}

	@Override
	protected void operate() {
		Bank.getInstance().removeAccount(getFromAccount());
	}

}
