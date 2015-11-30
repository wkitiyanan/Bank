package com.dst.training.bank.utilities;

public class AccountFileUtility extends FileUtility {

	private final String masterFileName = "files/Master.txt";
	
	public AccountFileUtility(){
		openReadFile(masterFileName);
	}
	
    public AccountParser getNextAccount()
    {
        AccountParser accountParser = null;
        String line = getNextLine();

        if ( line != null )
        {
            accountParser = new AccountParser( line );
        }

        return accountParser;
    }
}
