package com.dst.training.bank.utilities;


public class AccountParser
{
    String line = null;

    public AccountParser( String fileLine )
    {
        this.line = fileLine;
    }

    public String getAccountNumber()
    {
        return line.substring( 0, 11 );
    }

    public char getStatus()
    {
        return line.substring( 11, 12 ).charAt( 0 );
    }

    public double getBalance()
    {
        return Double.parseDouble( line.substring( 12, 27 ) );
    }
}
