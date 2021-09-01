package com.jiajie;

import com.jiajie.Service.Service;
import com.jiajie.Service.Serviceimpl;
import com.jiajie.pojo.Payer;
import com.jiajie.pojo.Transaction;
import com.jiajie.repository.PayerRepository;
import com.jiajie.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class FetchRewardApplicationTests {

    @Autowired
    private Serviceimpl serviceimpl;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PayerRepository payerRepository;

    @Test
    public void addPoints() {
        Transaction inTransaction = new Transaction(null, "DANNON", 200, new Date());
        Transaction outTransaction = serviceimpl
                .addPoints(inTransaction);

        Assertions.assertEquals(inTransaction, outTransaction);
    }

    @Test
    public void getAllPayerBalances() {

        serviceimpl.addPoints(new Transaction(null, "DANNON", 200, new Date()));
        List<Payer> allPayerBalances = serviceimpl.getAllPayerBalances();
        System.out.println(new Transaction(null, "DANNON", 200, new Date()));
        Assertions.assertEquals(allPayerBalances.get(0), new Payer(1L, "DANNON", 200));
    }

}
