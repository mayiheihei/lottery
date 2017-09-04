package com.example.lottery.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lottery.domain.LotteryPk10;
import com.example.lottery.domain.Pk10Count;
import com.example.lottery.repository.LotteryPk10Repository;
import com.example.lottery.repository.Pk10CountRepository;
import com.example.lottery.utils.HttpClientUtil;
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
    @Autowired
    private Pk10CountRepository pk10CountRepository;
    private static final String host = "a.apiplus.net/newly.do?token=t9bc5205d023876b3k&code=bjpk10&rows=1&format=json";

    @Scheduled(cron = "0 1/4 * * * ?")
    public void savePk10Service() throws Exception {
        /*String parameter = HttpConnectionUtil.requestMethod("GET", "http://a.apiplus.net/newly" +
                ".do?token=t9bc5205d023876b3k&code=bjpk10&rows=1&format=json", null, "GBK");*/
        String parameter = HttpClientUtil.HttpGetDate(host);
        if (parameter != null && !parameter.equals("")) {
            JSONObject jsonObject = JSONObject.parseObject(parameter);


            JSONArray jsonArray = jsonObject.getJSONArray("data");
            String code = (String) jsonObject.get("code");
            if (jsonArray != null && !jsonArray.isEmpty()) {
                String expect = (String) jsonArray.getJSONObject(0).get("expect");
                String opencode = (String) jsonArray.getJSONObject(0).get("opencode");
                String[] opencodeArr = parseLottery(opencode);
//                System.out.println("opencodeArr:" + opencodeArr);
                String opentime = (String) jsonArray.getJSONObject(0).get("opentime");
                Integer opentimestamp = (Integer) jsonArray.getJSONObject(0).get("opentimestamp");

                LotteryPk10 lotteryPk10 = new LotteryPk10(code, expect, opencodeArr[0], opencodeArr[1], opencodeArr[2],
                        opencodeArr[3], opencodeArr[4], opencodeArr[5], opencodeArr[6], opencodeArr[7], opencodeArr[8],
                        opencodeArr[9], opentime, opentimestamp);

                //TODO: 判断是否为空
                if (lotteryPk10Repository.findByOpentimestamp(opentimestamp) == null) {

//                    System.out.println(lotteryPk10.getOpencodeBefore());
                    //最新数据前5个数
                    String[] opencodeBefore = lotteryPk10.getOpencodeBefore();
                    String[] opencodeAfter = lotteryPk10.getOpencodeAfter();
                    //获取历史最新数据
                    LotteryPk10 lotteryPk10His = lotteryPk10Repository.findFirstByOrderByOpentimestampDesc();

                    //获取计数器
                    Pk10Count pk10Count = pk10CountRepository.findFirstData();

                    //判断后五个数
                    if (isContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo6())) {
                        //TODO: 重构
                        //如果六号位数组在前五个数里，就把6号位+1
                        Integer numSix = pk10Count.getNumSix() + 1;
                        pk10Count.setNumSix(numSix);
                    } else {
                        //否则就清零
                        pk10Count.setNumSix(0);
                    }

                    if (isContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo7())) {
                        //如果七号位数组在前五个数里，就把7号位+1
                        Integer numSeven = pk10Count.getNumSeven() + 1;
                        pk10Count.setNumSeven(numSeven);
                    } else {
                        //否则就清零
                        pk10Count.setNumSeven(0);
                    }

                    if (isContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo8())) {
                        //如果八号位数组在前五个数里，就把8号位+1
                        Integer numEight = pk10Count.getNumEight() + 1;
                        pk10Count.setNumEight(numEight);
                    } else {
                        //否则就清零
                        pk10Count.setNumEight(0);
                    }

                    if (isContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo9())) {
                        //如果九号位数组在前五个数里，就把9号位+1
                        Integer numNine = pk10Count.getNumNine() + 1;
                        pk10Count.setNumNine(numNine);
                    } else {
                        //否则就清零
                        pk10Count.setNumNine(0);
                    }

                    if (isContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo10())) {
                        //如果十号位数组在前五个数里，就把10号位+1
                        Integer numTen = pk10Count.getNumTen() + 1;
                        pk10Count.setNumTen(numTen);
                    } else {
                        //否则就清零
                        pk10Count.setNumTen(0);
                    }

                    //判断前五个数
                    if (isContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo1())) {
                        //TODO: 重构
                        //如果1号位数在前五个数里，就把6号位+1
                        Integer numOne = pk10Count.getNumOne() + 1;
                        pk10Count.setNumOne(numOne);
                    } else {
                        //否则就清零
                        pk10Count.setNumOne(0);
                    }

                    if (isContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo2())) {
                        //TODO: 重构
                        //如果1号位数在前五个数里，就把6号位+1
                        Integer numTwo = pk10Count.getNumTwo() + 1;
                        pk10Count.setNumTwo(numTwo);
                    } else {
                        //否则就清零
                        pk10Count.setNumTwo(0);
                    }

                    if (isContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo3())) {
                        //TODO: 重构
                        //如果1号位数在前五个数里，就把6号位+1
                        Integer numThree = pk10Count.getNumThree() + 1;
                        pk10Count.setNumThree(numThree);
                    } else {
                        //否则就清零
                        pk10Count.setNumThree(0);
                    }

                    if (isContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo4())) {
                        //TODO: 重构
                        //如果1号位数在前五个数里，就把6号位+1
                        Integer numFour = pk10Count.getNumFour() + 1;
                        pk10Count.setNumFour(numFour);
                    } else {
                        //否则就清零
                        pk10Count.setNumFour(0);
                    }

                    if (isContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo5())) {
                        //TODO: 重构
                        //如果1号位数在前五个数里，就把6号位+1
                        Integer numFive = pk10Count.getNumFive() + 1;
                        pk10Count.setNumFive(numFive);
                    } else {
                        //否则就清零
                        pk10Count.setNumFive(0);
                    }
                    pk10CountRepository.save(pk10Count);
                    pk10CountRepository.save(pk10Count);

                    //保存最新数据入库
                    lotteryPk10Repository.save(lotteryPk10);
                    System.out.println("插入数据-------------");
                } else {
                    System.out.println("有重复的！");
                }
            } else {
                System.out.println("jsonArray数组为空");
            }
        } else {
            throw new Exception("获取连接失败");
        }
    }

    /**
     * 判断数组是否包含某个元素
     *
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean isContainCode(String[] arr, String targetValue) throws Exception {
        if (arr != null && arr.length > 0) {
            if (targetValue != null && !targetValue.equals("")) {
                for (String s1 : arr) {
                    if (s1 != null) {
                        if (s1.equals(targetValue))
                            return true;
                    }
                }
                return false;
            } else {
                throw new Exception("targetValue为空");
            }
        } else {
            throw new Exception("arr为空");
        }
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
//        lotteryService.savePk10Service();
    }
}
