package com.dst.training.bank.utilities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TransactionParser
{
    String line = null;

    public TransactionParser( String fileLine )
    {
        this.line = fileLine;
    }

    public char getTransactionType()
    {
        return line.substring( 11, 12 ).toUpperCase().charAt( 0 );
    }

    public String getFromAccountNumber()
    {
        return line.substring( 0, 11 );
    }

    public String getToAccountNumber()
    {
        return line.substring( 35, 46 );
    }

    public Date getProcessDate()
    {
        Date result = null;
        DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );

        try
        {
            result = df.parse( line.substring( 25, 35 ) );
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
        }

        return result;
    }

    public double getAmount()
    {
        return Double.parseDouble( line.substring( 12, 25 ) );
    }

}
