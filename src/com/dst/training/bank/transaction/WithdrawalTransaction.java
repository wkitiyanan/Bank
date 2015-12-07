
package com.dst.training.bank.transaction;


import com.dst.training.bank.account.Account;


/**
 * 
 * Class description
 * 
 * Class for 'W'ithdrawal transaction
 * 
 * @author Woranat Kitiyanan
 */

public class WithdrawalTransaction extends Transaction
{

    @Override
    protected boolean validate()
    {
        boolean valid = false;

        if ( hasFromAccount() && hasEnoughBalance()){
            valid = true;
        }

        return valid;
    }

    @Override
    protected void operate()
    {
        Account account = getFromAccount();
        account.decreaseBalance( getAmount() );

        // Account is closed when the balance is zero
        /*
         * FIXME I think this is the only place you did not use curly brackets
         * around the if's code block. Make sure to use {} even around single
         * lines of code - it's a coding standard at DST and protects us from
         * logical errors later.
         * 
         * FIXED Filled the braces for block statement
         */
        if ( isClosable()){
        	account.close();
        }
    }
}
