package com.example.lottery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 马耳他幸运飞艇计数类
 *
 * @author : 唐明
 * @date : Created in 8:29 2017/9/4
 * @modified By:
 */
//TODO: 重新写
@Entity
public class MlaftCount {
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
    private Integer NumOneMax;
    private Integer NumTwoMax;
    private Integer NumThreeMax;
    private Integer NumFourMax;
    private Integer NumFiveMax;
    private Integer NumSixMax;
    private Integer NumSevenMax;
    private Integer NumEightMax;
    private Integer NumNineMax;
    private Integer NumTenMax;
    public MlaftCount() {
    }

    public MlaftCount(Integer numOne, Integer numTwo, Integer numThree, Integer numFour, Integer numFive, Integer
            numSix, Integer numSeven, Integer numEight, Integer numNine, Integer numTen, Integer numOneMax, Integer
            numTwoMax, Integer numThreeMax, Integer numFourMax, Integer numFiveMax, Integer numSixMax, Integer
            numSevenMax, Integer numEightMax, Integer numNineMax, Integer numTenMax) {
        NumOne = numOne;
        NumTwo = numTwo;
        NumThree = numThree;
        NumFour = numFour;
        NumFive = numFive;
        NumSix = numSix;
        NumSeven = numSeven;
        NumEight = numEight;
        NumNine = numNine;
        NumTen = numTen;
        NumOneMax = numOneMax;
        NumTwoMax = numTwoMax;
        NumThreeMax = numThreeMax;
        NumFourMax = numFourMax;
        NumFiveMax = numFiveMax;
        NumSixMax = numSixMax;
        NumSevenMax = numSevenMax;
        NumEightMax = numEightMax;
        NumNineMax = numNineMax;
        NumTenMax = numTenMax;
    }

    @Override
    public String toString() {
        return "MlaftCount{" +
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
                ", NumOneMax=" + NumOneMax +
                ", NumTwoMax=" + NumTwoMax +
                ", NumThreeMax=" + NumThreeMax +
                ", NumFourMax=" + NumFourMax +
                ", NumFiveMax=" + NumFiveMax +
                ", NumSixMax=" + NumSixMax +
                ", NumSevenMax=" + NumSevenMax +
                ", NumEightMax=" + NumEightMax +
                ", NumNineMax=" + NumNineMax +
                ", NumTenMax=" + NumTenMax +
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

    public Integer getNumOneMax() {
        return NumOneMax;
    }

    public void setNumOneMax(Integer numOneMax) {
        NumOneMax = numOneMax;
    }

    public Integer getNumTwoMax() {
        return NumTwoMax;
    }

    public void setNumTwoMax(Integer numTwoMax) {
        NumTwoMax = numTwoMax;
    }

    public Integer getNumThreeMax() {
        return NumThreeMax;
    }

    public void setNumThreeMax(Integer numThreeMax) {
        NumThreeMax = numThreeMax;
    }

    public Integer getNumFourMax() {
        return NumFourMax;
    }

    public void setNumFourMax(Integer numFourMax) {
        NumFourMax = numFourMax;
    }

    public Integer getNumFiveMax() {
        return NumFiveMax;
    }

    public void setNumFiveMax(Integer numFiveMax) {
        NumFiveMax = numFiveMax;
    }

    public Integer getNumSixMax() {
        return NumSixMax;
    }

    public void setNumSixMax(Integer numSixMax) {
        NumSixMax = numSixMax;
    }

    public Integer getNumSevenMax() {
        return NumSevenMax;
    }

    public void setNumSevenMax(Integer numSevenMax) {
        NumSevenMax = numSevenMax;
    }

    public Integer getNumEightMax() {
        return NumEightMax;
    }

    public void setNumEightMax(Integer numEightMax) {
        NumEightMax = numEightMax;
    }

    public Integer getNumNineMax() {
        return NumNineMax;
    }

    public void setNumNineMax(Integer numNineMax) {
        NumNineMax = numNineMax;
    }

    public Integer getNumTenMax() {
        return NumTenMax;
    }

    public void setNumTenMax(Integer numTenMax) {
        NumTenMax = numTenMax;
    }
}
