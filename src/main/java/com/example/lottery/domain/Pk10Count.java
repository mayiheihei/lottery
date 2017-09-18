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
    private Integer NumOne; //实时的
    private Integer NumTwo;
    private Integer NumThree;
    private Integer NumFour;
    private Integer NumFive;
    private Integer NumSix;
    private Integer NumSeven;
    private Integer NumEight;
    private Integer NumNine;
    private Integer NumTen;
    private Integer NumOneMax;//历史最高
    private Integer NumTwoMax;
    private Integer NumThreeMax;
    private Integer NumFourMax;
    private Integer NumFiveMax;
    private Integer NumSixMax;
    private Integer NumSevenMax;
    private Integer NumEightMax;
    private Integer NumNineMax;
    private Integer NumTenMax;
    private Integer NumOneDayMax;//当天最高
    private Integer NumTwoDayMax;
    private Integer NumThreeDayMax;
    private Integer NumFourDayMax;
    private Integer NumFiveDayMax;
    private Integer NumSixDayMax;
    private Integer NumSevenDayMax;
    private Integer NumEightDayMax;
    private Integer NumNineDayMax;
    private Integer NumTenDayMax;

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
                ", NumOneDayMax=" + NumOneDayMax +
                ", NumTwoDayMax=" + NumTwoDayMax +
                ", NumThreeDayMax=" + NumThreeDayMax +
                ", NumFourDayMax=" + NumFourDayMax +
                ", NumFiveDayMax=" + NumFiveDayMax +
                ", NumSixDayMax=" + NumSixDayMax +
                ", NumSevenDayMax=" + NumSevenDayMax +
                ", NumEightDayMax=" + NumEightDayMax +
                ", NumNineDayMax=" + NumNineDayMax +
                ", NumTenDayMax=" + NumTenDayMax +
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

    public Integer getNumOneDayMax() {
        return NumOneDayMax;
    }

    public void setNumOneDayMax(Integer numOneDayMax) {
        NumOneDayMax = numOneDayMax;
    }

    public Integer getNumTwoDayMax() {
        return NumTwoDayMax;
    }

    public void setNumTwoDayMax(Integer numTwoDayMax) {
        NumTwoDayMax = numTwoDayMax;
    }

    public Integer getNumThreeDayMax() {
        return NumThreeDayMax;
    }

    public void setNumThreeDayMax(Integer numThreeDayMax) {
        NumThreeDayMax = numThreeDayMax;
    }

    public Integer getNumFourDayMax() {
        return NumFourDayMax;
    }

    public void setNumFourDayMax(Integer numFourDayMax) {
        NumFourDayMax = numFourDayMax;
    }

    public Integer getNumFiveDayMax() {
        return NumFiveDayMax;
    }

    public void setNumFiveDayMax(Integer numFiveDayMax) {
        NumFiveDayMax = numFiveDayMax;
    }

    public Integer getNumSixDayMax() {
        return NumSixDayMax;
    }

    public void setNumSixDayMax(Integer numSixDayMax) {
        NumSixDayMax = numSixDayMax;
    }

    public Integer getNumSevenDayMax() {
        return NumSevenDayMax;
    }

    public void setNumSevenDayMax(Integer numSevenDayMax) {
        NumSevenDayMax = numSevenDayMax;
    }

    public Integer getNumEightDayMax() {
        return NumEightDayMax;
    }

    public void setNumEightDayMax(Integer numEightDayMax) {
        NumEightDayMax = numEightDayMax;
    }

    public Integer getNumNineDayMax() {
        return NumNineDayMax;
    }

    public void setNumNineDayMax(Integer numNineDayMax) {
        NumNineDayMax = numNineDayMax;
    }

    public Integer getNumTenDayMax() {
        return NumTenDayMax;
    }

    public void setNumTenDayMax(Integer numTenDayMax) {
        NumTenDayMax = numTenDayMax;
    }
}
