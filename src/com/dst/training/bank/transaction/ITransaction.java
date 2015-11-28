package com.dst.training.bank.transaction;

import com.dst.training.bank.utilities.TransactionParser;

public interface ITransaction
{
    boolean process();

    void build( TransactionParser tp );
}
