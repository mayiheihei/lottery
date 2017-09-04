package com.example.lottery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 北京赛车计数类
 *
 * @author : 唐明
 * @date : Created in 8:29 2017/9/4
 * @modified By:
 */
@Entity
public class Pk10Count {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer NumOne;
    private Integer NumTwo;
    private Integer NumThree;
    private Integer NumFour;
    private Integer NumFive;
    private Integer NumSix;
    private Integer NumSeven;
    private Integer NumEight;
    private Integer NumNine;
    private Integer NumTen;

    public Pk10Count() {
    }


    @Override
    public String toString() {
        return "Pk10Count{" +
                "id=" + id +
                ", NumOne=" + NumOne +
                ", NumTwo=" + NumTwo +
                ", NumThree=" + NumThree +
                ", NumFour=" + NumFour +
                ", NumFive=" + NumFive +
                ", NumSix=" + NumSix +
                ", NumSeven=" + NumSeven +
                ", NumEight=" + NumEight +
                ", NumNine=" + NumNine +
                ", NumTen=" + NumTen +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumOne() {
        return NumOne;
    }

    public void setNumOne(Integer numOne) {
        NumOne = numOne;
    }

    public Integer getNumTwo() {
        return NumTwo;
    }

    public void setNumTwo(Integer numTwo) {
        NumTwo = numTwo;
    }

    public Integer getNumThree() {
        return NumThree;
    }

    public void setNumThree(Integer numThree) {
        NumThree = numThree;
    }

    public Integer getNumFour() {
        return NumFour;
    }

    public void setNumFour(Integer numFour) {
        NumFour = numFour;
    }

    public Integer getNumFive() {
        return NumFive;
    }

    public void setNumFive(Integer numFive) {
        NumFive = numFive;
    }

    public Integer getNumSix() {
        return NumSix;
    }

    public void setNumSix(Integer numSix) {
        NumSix = numSix;
    }

    public Integer getNumSeven() {
        return NumSeven;
    }

    public void setNumSeven(Integer numSeven) {
        NumSeven = numSeven;
    }

    public Integer getNumEight() {
        return NumEight;
    }

    public void setNumEight(Integer numEight) {
        NumEight = numEight;
    }

    public Integer getNumNine() {
        return NumNine;
    }

    public void setNumNine(Integer numNine) {
        NumNine = numNine;
    }

    public Integer getNumTen() {
        return NumTen;
    }

    public void setNumTen(Integer numTen) {
        NumTen = numTen;
    }
}
