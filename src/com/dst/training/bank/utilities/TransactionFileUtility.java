package com.dst.training.bank.utilities;

public class TransactionFileUtility extends FileUtility {

	private final String transactionFileName = "files/Trans.txt";
	
	public TransactionFileUtility(){
		openReadFile(transactionFileName);
	}
	
    public TransactionParser getNextTransaction()
    {
        TransactionParser transactionParser = null;
        String line = getNextLine();

        if ( line != null )
        {
            transactionParser = new TransactionParser( line );
        }

        return transactionParser;
    }
}
