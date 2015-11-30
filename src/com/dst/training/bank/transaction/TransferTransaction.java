package com.dst.training.bank.transaction;

public class TransferTransaction extends Transaction {

	@Override
	protected boolean validate() {
		boolean valid = false;
		
		if(getFromAccount() != null
				&& getToAccount() != null
				&& getFromAccount().getBalance() >= getAmount()){
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
