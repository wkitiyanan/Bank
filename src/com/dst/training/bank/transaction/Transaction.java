
package com.dst.training.bank.transaction;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.dst.training.bank.Bank;
import com.dst.training.bank.account.Account;
import com.dst.training.bank.utilities.TransactionParser;


/**
 * 
 * Class description
 * 
 * A class describes the abstraction of transactions validate() and operate()
 * method are needed to be implemented for subclasses Also, handle static method
 * to produce different types of concrete-class objects for Bank
 * 
 * @author Woranat Kitiyanan
 */

public abstract class Transaction implements ITransaction
{
	public static final char TRANSACTION_TYPE_DEPOSIT = 'D';
	public static final char TRANSACTION_TYPE_WITHDRAWAL = 'W';
	public static final char TRANSACTION_TYPE_OPEN = 'O';
	public static final char TRANSACTION_TYPE_CLOSE = 'C';
	public static final char TRANSACTION_TYPE_PURGE = 'P';
	public static final char TRANSACTION_TYPE_TRANSFER = 'T';

    private Account fromAccount;
    private Account toAccount;
    private Date    processDate;
    private double  amount;
    private boolean valid;
    private ArrayList<String> errorMessages = new ArrayList<String>();

    /**
     * Abstraction of validation process Subclasses need to implement this
     * method to describe conditions that will make themselves valid
     * 
     * @return true if transaction is valid
     */
    protected abstract boolean validate();

    /**
     * Abstraction of how operation process Subclasses need to implement this
     * method to describe transaction execution step
     * 
     * @return void
     */
    protected abstract void operate();

    /**
     * Process all steps needed for a transaction Invalid transaction will be
     * not operated
     * 
     * @return true if transaction is valid and processed
     */
    @Override
    public boolean process()
    {
        /*
         * FIXME Missing error messaging/logging. Review specifications
         * regarding when to log error messages (display message). Some of my
         * worst support experiences stems from poor logging: on one web
         * application, all that was logged was "ERROR!". That's it - it wasn't
         * hard to track down, but why anyone bothered to log a message like
         * that is hard to understand. We don't spend enough time discussing
         * good logging practices, but I wanted to explain why it's not enough
         * to just log "success" or "error" in transactionLog.
         * 
         * FIXED Validation process will collect error messages to be shown once error happens 
         */
        valid = validate();
        if ( valid )
        {
            activateAccounts();
            operate();
        }
        logTransaction();
        return valid;
    }

    /**
     * Populate values from transaction parser into itself
     * 
     * @param tp
     *            the TransactionParser
     * @return void
     */
    @Override
    public void build( TransactionParser tp )
    {
        Bank bank = Bank.getInstance();

        setFromAccount( bank.getAccount( tp.getFromAccountNumber() ) );
        setToAccount( bank.getAccount( tp.getToAccountNumber() ) );
        setProcessDate( tp.getProcessDate() );
        setAmount( tp.getAmount() );
    }

    /**
     * Static method to instantiate transaction with different types depends on
     * method getTransactionType() of TransactionParser
     * 
     * @return Interface of transaction
     */
    public static ITransaction getTransaction( TransactionParser parser )
    {
        ITransaction transaction = null;
        switch ( parser.getTransactionType() )
        {
        /*
         * FIXME Use constants in place of "magic values" - characters, numbers,
         * and/or Strings that have business meaning. Replace characters in case
         * statements with constants. Example: public static final char
         * TRANSACTION_TYPE_DEPOSIT = 'D';
         * 
         * FIXED Extract magic values to be constant values
         */
        
            case TRANSACTION_TYPE_DEPOSIT:
            {
                transaction = new DepositTransaction();
                break;
            }
            case TRANSACTION_TYPE_WITHDRAWAL:
            {
                transaction = new WithdrawalTransaction();
                break;
            }
            case TRANSACTION_TYPE_OPEN:
            {
                transaction = new OpenTransaction();
                break;
            }
            case TRANSACTION_TYPE_CLOSE:
            {
                transaction = new CloseTransaction();
                break;
            }
            case TRANSACTION_TYPE_PURGE:
            {
                transaction = new PurgeTransaction();
                break;
            }
            case TRANSACTION_TYPE_TRANSFER:
            {
                transaction = new TransferTransaction();
                break;
            }
            default:
            {
                System.out.printf(
                        "[ERROR]      Transaction type '%s' is not valid!%n%n",
                        parser.getTransactionType() );
            }
        }
        return transaction;
    }

    /**
     * Try to activate involved accounts (fromAccount and toAccount)
     * 
     * @return void
     */
    private void activateAccounts()
    {
        activateAccount( getFromAccount() );
        activateAccount( getToAccount() );
    }

    /**
     * Activate account if not null and status is 'C'lose
     * 
     * @return void
     */
    private void activateAccount( Account account )
    {
        /*
         * FIXME Constants. Think about which class should really "own" status
         * codes 'C' and 'A'.
         */
        /*
         * TODO By extension: maybe consider who should own the behavior and
         * business logic to activate an account. Account "owns" the status
         * property, should/could it also own activating the account? Putting
         * the implementation in Account is a bit of a paradigm shift (I've
         * heard it referred to as a "fat model") that results in a more
         * reusable Account class that knows how to manage it's own properties.
         * I saw you already did that with methods related to balance, so this
         * suggestion is just a continuation of the idea you already started to
         * implement.
         * 
         * FIXED Move activation logic to account class
         */
        if ( account != null ) {
        	account.activate();
        }
    }

    /**
     * Log transaction results
     * 
     * @return void
     */
    /*
     * FIXME Method should being with the action word.
     * FIXED change to logTransacion()
     */
    protected void logTransaction()
    {
        String transactionName = this.getClass().getSimpleName()
                .replace( "Transaction", "" );
        String resultMessage = valid ? "[SUCCESS]" : "[ERROR]";
        String toAccountNumber = getToAccount() != null ? "TO "
                + getToAccount().getAccountNumber() : "";
        DateFormat dateFormatter = new SimpleDateFormat( "MM/dd/yyyy" );
        String formattedDate = dateFormatter.format( getProcessDate() );
        String formatString = "%-13s%-13s %-11s %11s %14s Amount: %s %n";
        
        System.out.printf( formatString, resultMessage, formattedDate,
                transactionName, getFromAccount().getAccountNumber(),
                toAccountNumber, getAmount() );
        
        if(!valid){
        	for (String errorMessage : errorMessages) {
        		System.out.printf("%-13s%-30s%n", resultMessage, errorMessage);
			}
        }
        
        System.out.println();
    }
    
    /**
     * Check if from account closable
     * If not, collect error message
     * 
     * @return true if from account is closable
     */
    public boolean isClosable(){
    	boolean isClosable = getFromAccount().getBalance() == 0;
    	
    	if(!isClosable){
    		errorMessages.add("Account cannot be closed");
    	}
    	
		return isClosable;
    }
    
    /**
     * check if from account exists
     * If not, collect error message
     * 
     * @return true if from account does exist
     */
    public boolean hasFromAccount(){
    	boolean hasFromAccount = getFromAccount() != null;
    	if(!hasFromAccount){
    		errorMessages.add("From account does not exist");
    	}
		return hasFromAccount;
    }
    
    /**
     * check if to account exists
     * If not, collect error message
     * 
     * @return true if to account does exist
     */
    public boolean hasToAccount(){
    	boolean hasToAccount = getToAccount() != null;
    	
    	if(!hasToAccount){
    		errorMessages.add("To account does not exist");
    	}
    	
		return hasToAccount;
    }
    
    /**
     * check if account has enough balance for transaction amount
     * If not, collect error message
     * 
     * @return true if balance is enough
     */
    public boolean hasEnoughBalance(){
    	boolean hasEnoughBalance = getFromAccount().getBalance() >= getAmount();
    	
    	if(!hasEnoughBalance){
    		errorMessages.add("The balance is not enough");
    	}
    	
    	return hasEnoughBalance;
    }

    public Account getFromAccount()
    {
        return fromAccount;
    }

    public void setFromAccount( Account fromAccount )
    {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount()
    {
        return toAccount;
    }

    public void setToAccount( Account toAccount )
    {
        this.toAccount = toAccount;
    }

    public Date getProcessDate()
    {
        return processDate;
    }

    public void setProcessDate( Date processDate )
    {
        this.processDate = processDate;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount( double amount )
    {
        this.amount = amount;
    }
}
