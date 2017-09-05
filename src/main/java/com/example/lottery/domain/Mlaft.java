package com.example.lottery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 马耳他幸运飞艇彩票实体类
 *
 * @author 唐明
 * @create 2017-09-03 20:14
 */
//TODO: 重新写
@Entity
public class Mlaft {
    @Id
    @GeneratedValue
    private Integer id;//序号
    private String code;//彩票代码
    private String expect;//彩票序号
    private String opencodeNo1;//彩票号码1
    private String opencodeNo2;//彩票号码2
    private String opencodeNo3;//彩票号码3
    private String opencodeNo4;//彩票号码4
    private String opencodeNo5;//彩票号码5
    private String opencodeNo6;//彩票号码6
    private String opencodeNo7;//彩票号码7
    private String opencodeNo8;//彩票号码8
    private String opencodeNo9;//彩票号码9
    private String opencodeNo10;//彩票号码10
    private String opentime;//开彩时间
    private Integer opentimestamp;//开彩时间戳（可能是从0开始的时间戳）
    private String[] opencodeBefore = {opencodeNo1, opencodeNo2, opencodeNo3, opencodeNo4, opencodeNo5};
    private String[] opencodeAfter = {opencodeNo6, opencodeNo7, opencodeNo8, opencodeNo9, opencodeNo10};

    protected Mlaft() {
    }

    public Mlaft(String code, String expect, String opencodeNo1, String opencodeNo2, String opencodeNo3, String
            opencodeNo4, String opencodeNo5, String opencodeNo6, String opencodeNo7, String opencodeNo8, String
                               opencodeNo9, String opencodeNo10, String opentime, Integer opentimestamp) {
        this.code = code;
        this.expect = expect;
        this.opencodeNo1 = opencodeNo1;
        this.opencodeNo2 = opencodeNo2;
        this.opencodeNo3 = opencodeNo3;
        this.opencodeNo4 = opencodeNo4;
        this.opencodeNo5 = opencodeNo5;
        this.opencodeNo6 = opencodeNo6;
        this.opencodeNo7 = opencodeNo7;
        this.opencodeNo8 = opencodeNo8;
        this.opencodeNo9 = opencodeNo9;
        this.opencodeNo10 = opencodeNo10;
        this.opentime = opentime;
        this.opentimestamp = opentimestamp;
    }

    @Override
    public String toString() {
        return "LotteryMlaft{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", expect='" + expect + '\'' +
                ", opencodeNo1='" + opencodeNo1 + '\'' +
                ", opencodeNo2='" + opencodeNo2 + '\'' +
                ", opencodeNo3='" + opencodeNo3 + '\'' +
                ", opencodeNo4='" + opencodeNo4 + '\'' +
                ", opencodeNo5='" + opencodeNo5 + '\'' +
                ", opencodeNo6='" + opencodeNo6 + '\'' +
                ", opencodeNo7='" + opencodeNo7 + '\'' +
                ", opencodeNo8='" + opencodeNo8 + '\'' +
                ", opencodeNo9='" + opencodeNo9 + '\'' +
                ", opencodeNo10='" + opencodeNo10 + '\'' +
                ", opentime='" + opentime + '\'' +
                ", opentimestamp=" + opentimestamp +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getOpencodeNo1() {
        return opencodeNo1;
    }

    public void setOpencodeNo1(String opencodeNo1) {
        this.opencodeNo1 = opencodeNo1;
    }
    public String getOpencodeNo2() {
        return opencodeNo2;
    }

    public void setOpencodeNo2(String opencodeNo2) {
        this.opencodeNo2 = opencodeNo2;
    }
    public String getOpencodeNo3() {
        return opencodeNo3;
    }

    public void setOpencodeNo3(String opencodeNo3) {
        this.opencodeNo3 = opencodeNo3;
    }

    public String getOpencodeNo4() {
        return opencodeNo4;
    }

    public void setOpencodeNo4(String opencodeNo4) {
        this.opencodeNo4 = opencodeNo4;
    }

    public String getOpencodeNo5() {
        return opencodeNo5;
    }

    public void setOpencodeNo5(String opencodeNo5) {
        this.opencodeNo5 = opencodeNo5;
    }
    public String getOpencodeNo6() {
        return opencodeNo6;
    }

    public void setOpencodeNo6(String opencodeNo6) {
        this.opencodeNo6 = opencodeNo6;
    }

    public String getOpencodeNo7() {
        return opencodeNo7;
    }

    public void setOpencodeNo7(String opencodeNo7) {
        this.opencodeNo7 = opencodeNo7;
    }

    public String getOpencodeNo8() {
        return opencodeNo8;
    }

    public void setOpencodeNo8(String opencodeNo8) {
        this.opencodeNo8 = opencodeNo8;
    }

    public String getOpencodeNo9() {
        return opencodeNo9;
    }

    public void setOpencodeNo9(String opencodeNo9) {
        this.opencodeNo9 = opencodeNo9;
    }

    public String getOpencodeNo10() {
        return opencodeNo10;
    }

    public void setOpencodeNo10(String opencodeNo10) {
        this.opencodeNo10 = opencodeNo10;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public Integer getOpentimestamp() {
        return opentimestamp;
    }

    public void setOpentimestamp(Integer opentimestamp) {
        this.opentimestamp = opentimestamp;
    }

    public String[] getOpencodeBefore() {
        return opencodeBefore;
    }

    public void setOpencodeBefore(String[] opencodeBefore) {
        this.opencodeBefore = opencodeBefore;
    }

    public String[] getOpencodeAfter() {
        return opencodeAfter;
    }

    public void setOpencodeAfter(String[] opencodeAfter) {
        this.opencodeAfter = opencodeAfter;
    }
}
