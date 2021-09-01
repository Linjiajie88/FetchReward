package com.jiajie.controller;

import com.jiajie.Service.Service;
import com.jiajie.Service.Serviceimpl;
import com.jiajie.pojo.Payer;
import com.jiajie.pojo.Points;
import com.jiajie.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private Serviceimpl serviceimpl;

    @PostMapping("/test")
    List<Transaction> allTransaction(){
        return serviceimpl.getAllTransaction();
    }

    @GetMapping("/payer/balances")
    List<Payer> allPayerBalances() {
        return serviceimpl.getAllPayerBalances();
    }

    @PostMapping(path = "/payer/transaction", consumes = "application/json", produces = "application/json")
    Transaction addPoints(@RequestBody Transaction transaction) {
        return serviceimpl.addPoints(transaction);
    }

    @PostMapping("/redeem")
    List<Payer> spendPoints(@RequestBody Points points){
        return serviceimpl.spendPoints(points);
    }






}
