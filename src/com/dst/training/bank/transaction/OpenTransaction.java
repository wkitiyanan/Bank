
package com.dst.training.bank.transaction;


import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;
import com.dst.training.bank.utilities.TransactionParser;


/**
 * 
 * Class description
 * 
 * Class for 'O'pen transaction
 * 
 * @author Woranat Kitiyanan
 */

public class OpenTransaction extends Transaction
{
	private String accountNumber;
	
    @Override
    protected boolean validate()
    {
        /*
         * TODO: Consider: should this log an error for trying to open an
         * account with an account number already in use? It's not a stated
         * requirement, but I often recommend it as a "stretch" goal to
         * associates who have a good grasp on the other business requirements.
         * 
         * FIXED No validation for open transactions
         */

        return true;
    }

    @Override
    protected void operate()
    {
    	Account account = new Account();
    	account.setAccountNumber(getAccountNumber());
    	account.setBalance(getAmount());
    	
    	setFromAccount(account);
    	
        Bank.getInstance().addAccount( account );
    }

    @Override
    public void build( TransactionParser tp )
    {
        // Instantiate account because the opened account does not exist yet
        /*
         * FIXME The behavior below is really more of the "operate"
         * implementation of an open transaction. It's what this transaction is
         * suppose to accomplish, as compared to a Deposit transaction's
         * "operate" activity that increases an account's balance.
         */
        /*
         * Also consider: the super.build searches for an account using the tp's
         * account number. You replace anything found with the account created
         * here (setFromAccount(account) below replaces anything found in
         * super.build(tp)), and therefore have to repeat the search in
         * validate(). If creating the account is moved to operate, you can
         * reduce the number of searches, and which reduces any access overhead
         * associated with that search.
         * 
         * FIXED move logic to instantiate account into operate method
         */
    	setAccountNumber(tp.getToAccountNumber());
        setProcessDate( tp.getProcessDate() );
        setAmount( tp.getAmount() );
    }

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
