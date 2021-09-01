package com.jiajie.repository;

import com.jiajie.pojo.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface
TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT i FROM Transaction i WHERE i.payer = :payer")
    List<Transaction> findByPayer(@Param("payer") String payer);

    @Query("SELECT i FROM Transaction i ORDER BY i.timestamp ASC")
    List<Transaction> getSortedPoints();

    @Transactional
    @Modifying
    @Query("UPDATE Transaction i SET i.points = :updatePoints WHERE i.id = :id")
    void updateTransactionsPoints(@Param("updatePoints") int updatedPoints,
                                  @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Transaction i WHERE i.id = :id")
    void removeTransaction(@Param("id") Long id);

}
