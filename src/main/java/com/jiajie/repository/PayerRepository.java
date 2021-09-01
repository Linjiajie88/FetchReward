package com.jiajie.repository;

import com.jiajie.pojo.Payer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;



public interface PayerRepository extends JpaRepository<Payer,Long> {

    @Query("SELECT i FROM Payer i where i.payer = :payer")
    List<Payer> findPayerByName(@Param("payer") String payer);

    @Transactional
    @Modifying
    @Query("UPDATE Payer i SET i.points = i.points + :points WHERE i.payer = :payer")
    void updateBalance(@Param("payer") String payer, @Param("points") int points);

}
