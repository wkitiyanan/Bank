package com.dst.training.bank;

import com.dst.training.bank.account.Account;
import com.dst.training.bank.account.Accounts;
import com.dst.training.bank.utilities.AccountFileUtility;
import com.dst.training.bank.utilities.AccountParser;

public class Bank
{
    private static Bank     instance;
    private Accounts accounts = new Accounts();
    
    private AccountFileUtility accountFile = new AccountFileUtility();
    
    public void process(){
    	loadAccounts();
    }
    
    private void loadAccounts(){
    	AccountParser nextAccount = null;
    	do{
    		nextAccount = accountFile.getNextAccount();
    		if(nextAccount != null) accounts.addAccount(new Account(nextAccount));
    	} while(nextAccount != null);
    }

    public static Bank getInstance()
    {
        if ( instance == null )
        {
            instance = new Bank();
        }

        return instance;
    }


}
