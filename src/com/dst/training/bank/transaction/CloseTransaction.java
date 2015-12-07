
package com.dst.training.bank.transaction;


import com.dst.training.bank.account.Account;


/**
 * 
 * Class description
 * 
 * Class for 'C'lose transaction
 * 
 * @author Woranat Kitiyanan
 */

public class CloseTransaction extends Transaction
{

    @Override
    protected boolean validate()
    {
        boolean valid = false;

        /*
         * TODO Consider an isClosable method for reuse in Withdrawal, and
         * eliminate a little repeated code.
         * 
         * FIXED Already extracted to isClosable() method
         */
        if ( hasFromAccount() && isClosable()) {
            valid = true;
        }
        return valid;
    }

    @Override
    protected void operate()
    {
        getFromAccount().close();;
    }

}
