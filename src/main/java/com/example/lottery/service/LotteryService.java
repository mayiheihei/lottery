package com.example.lottery.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lottery.domain.LotteryPk10;
import com.example.lottery.repository.LotteryPk10Repository;
import com.example.lottery.utils.HttpConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 彩票服务
 *
 * @author 唐明
 * @create 2017-09-03 20:28
 */
@Service
public class LotteryService {
    @Autowired
    private LotteryPk10Repository lotteryPk10Repository;

    @Scheduled(cron = "0/3 * * * * ?")
    public void savePk10Service() {
        String parameter = HttpConnectionUtil.requestMethod("GET", "http://a.apiplus.net/newly" +
                ".do?token=t9bc5205d023876b3k&code=bjpk10&rows=1&format=json", null, "GBK");
        JSONObject jsonObject = JSONObject.parseObject(parameter);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        String code = (String) jsonObject.get("code");
        String expect = (String) jsonArray.getJSONObject(0).get("expect");
        String opencode = (String) jsonArray.getJSONObject(0).get("opencode");
        String[] opencodeArr = parseLottery(opencode);
        String opentime = (String) jsonArray.getJSONObject(0).get("opentime");
        Integer opentimestamp = (Integer) jsonArray.getJSONObject(0).get("opentimestamp");
        LotteryPk10 lotteryPk10 = new LotteryPk10(code, expect, opencodeArr[0], opencodeArr[1], opencodeArr[2],
                opencodeArr[3], opencodeArr[4], opencodeArr[5], opencodeArr[6], opencodeArr[7], opencodeArr[8],
                opencodeArr[9], opentime, opentimestamp);
        //        System.out.println(lotteryPk10);
        //        System.out.println("opentimestamp::::"+opentimestamp);
        //        System.out.println(lotteryPk10Repository.findByOpentimestamp(opentimestamp));
        //TODO: 判断是否为空
        if (lotteryPk10Repository.findByOpentimestamp(opentimestamp) == null) {
            //获取历史最新数据
            LotteryPk10 lotteryPk10His = lotteryPk10Repository.findFirstByOrderByOpentimestampDesc();
            //历史数据前5个数
            String[] opencodeBefore = lotteryPk10His.getOpencodeBefore();
            //如果第六个数在前五个之中，就把6号位+1
            if(isContainCode(opencodeBefore,opencodeArr[5])){
                //TODO: 6号位+1 ，创建计数的实体类
            }
            //保存最新数据入库
            lotteryPk10Repository.save(lotteryPk10);
            System.out.println("插入数据-------------");
        } else {
            System.out.println("有重复的！");
        }

    }

    /**
     * 判断数组是否包含某个元素
     *
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean isContainCode(String[] arr, String targetValue) {
        for (String s1 : arr) {
            if (s1.equals(targetValue))
                return true;
        }
        return false;
    }

    /**
     * 解析彩票10个号码为数组
     *
     * @param lottery
     * @return
     */
    public static String[] parseLottery(String lottery) {
        return lottery.split(",");
    }


    //TODO: 测试方法
    public static void main(String[] args) {
        LotteryService lotteryService = new LotteryService();
        lotteryService.savePk10Service();
    }
}
