package com.jiajie.Service;

import com.jiajie.pojo.Payer;
import com.jiajie.pojo.Points;
import com.jiajie.pojo.Transaction;
import com.jiajie.repository.PayerRepository;
import com.jiajie.repository.TransactionRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class Serviceimpl implements Service{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PayerRepository payerRepository;


    @Override
    public List<Payer> getAllPayerBalances() {
        return payerRepository.findAll();
    }

    @Override
    public List<Payer> spendPoints(Points points) {
        //sort the transaction by time
        List<Transaction> sortedAwardedPointsByDate = transactionRepository.getSortedPoints();
        int pointsToSubtract = points.getPoints();
        SpentPointsHelper spentPointsHelper = new SpentPointsHelper();

        for (Transaction transaction : sortedAwardedPointsByDate) {
            if (pointsToSubtract == 0) {
                break;
            }
            //no payer's points can be negative.
            else if (transaction.getPoints() < pointsToSubtract) {
                pointsToSubtract -= transaction.getPoints();
                spentPointsHelper.subtractPoints(transaction.getPayer(), transaction.getPoints());
                transactionRepository.removeTransaction(transaction.getId());
            }
            else {
                int updatePoints = transaction.getPoints() - pointsToSubtract;
                spentPointsHelper.subtractPoints(transaction.getPayer(), pointsToSubtract);
                transactionRepository.updateTransactionsPoints(updatePoints, transaction.getId());
                pointsToSubtract = 0;
            }
        }

        subtractPointsFromPayerBalances(spentPointsHelper);
        return spentPointsHelper.getSubtractedPointsForPayers();
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction addPoints(Transaction point) {
        if (!payerRepository.findPayerByName(point.getPayer()).isEmpty()) {
            payerRepository.updateBalance(point.getPayer(), point.getPoints());
        } else {
            payerRepository.save(new Payer(point.getPayer(), point.getPoints()));
        }

        return transactionRepository.save(point);
    }

    //update point that been subtracted
    private void subtractPointsFromPayerBalances(SpentPointsHelper sp) {
        for (Map.Entry<String,Integer> entry : sp.getSubtractedPoints().entrySet()) {
            payerRepository.updateBalance(entry.getKey(),entry.getValue());
        }
    }

    public class SpentPointsHelper {

        @Getter
        Map<String, Integer> subtractedPoints = new HashMap<>();

        public void subtractPoints(String payer, int pointsToSubtract) {
            subtractedPoints.computeIfPresent(payer, (k, v) -> v + (-1 * pointsToSubtract));
            subtractedPoints.putIfAbsent(payer, -1 * pointsToSubtract);
        }

        //update
        public List<Payer> getSubtractedPointsForPayers() {
            List<Payer> pointsSpentForPayer = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : subtractedPoints.entrySet()) {
                pointsSpentForPayer.add(new Payer(entry.getKey(), entry.getValue()));
            }

            return pointsSpentForPayer;
        }
    }
}
