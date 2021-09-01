package com.jiajie.Service;

import com.jiajie.pojo.Payer;
import com.jiajie.pojo.Points;
import com.jiajie.pojo.Transaction;

import java.util.List;

public interface Service {
    List<Payer> getAllPayerBalances();
    Transaction addPoints(Transaction transaction);
    List<Transaction> getAllTransaction();
    List<Payer> spendPoints(Points points);
}
