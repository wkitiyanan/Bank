package com.dst.training.bank.utilities;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class FileUtility
{
    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    public void openReadFile( String fileName )
    {
        FileReader input;

        try
        {
            input = new FileReader( fileName );
            reader = new BufferedReader( input );
        }
        catch ( FileNotFoundException e )
        {
            System.out.println( "OPEN OF MASTER FILE FAILED - " + fileName );
            e.printStackTrace();
        }
    }

    public void writeFile( String fileName, List content )
    {
        try
        {
            FileWriter output = new FileWriter( fileName );
            writer = new BufferedWriter( output );

            for ( Object item : content )
            {
                writer.write( item.toString() );
                writer.newLine();
            }
            writer.close();
        }
        catch ( IOException e )
        {
            System.out.println( "WRITING OF NEW MASTER FILE FAILED - "
                    + fileName );

            e.printStackTrace();
        }
    }

    public String getNextLine()
    {
        String result = null;

        if ( reader != null )
        {
            try
            {
                result = reader.readLine();

                if ( result != null )
                {
                    result = result.trim();
                }
                else
                {
                    reader.close();
                }
            }
            catch ( IOException e )
            {
                System.out.println( "ERROR OCCURRED READING NEXT LINE IN FILE" );
                e.printStackTrace();
            }
        }

        return result;
    }
}
