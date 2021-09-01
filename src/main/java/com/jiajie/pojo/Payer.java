package com.jiajie.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"id"})
public class Payer {
    @Id
    @GeneratedValue
    private Long id;
    private String payer;
    private int points;

    public Payer(String payer, int points) {
        this.payer = payer;
        this.points = points;
    }



}
