package com.example.lottery.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lottery.domain.LotteryPk10;
import com.example.lottery.domain.Pk10Count;
import com.example.lottery.repository.LotteryPk10Repository;
import com.example.lottery.repository.Pk10CountRepository;
import com.example.lottery.util.HttpClientUtil;
import com.example.lottery.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 北京赛车彩票服务
 *
 * @author 唐明
 * @create 2017-09-03 20:28
 */
@Service
@Slf4j
public class Pk10Service {
    @Autowired
    private LotteryPk10Repository lotteryPk10Repository;
    @Autowired
    private Pk10CountRepository pk10CountRepository;

    //    private final Logger logger = LoggerFactory.getLogger(LotteryService.class);使用@Slf4j了，lombok类
    private static final String host = "a.apiplus.net/newly.do?token=t9bc5205d023876b3k&code=bjpk10&rows=1&format=json";

    @Scheduled(cron = "45/10 * * * * ?")
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
                //System.out.println("opencodeArr:" + opencodeArr);
                String opentime = (String) jsonArray.getJSONObject(0).get("opentime");
                Integer opentimestamp = (Integer) jsonArray.getJSONObject(0).get("opentimestamp");

                LotteryPk10 lotteryPk10 = new LotteryPk10(code, expect, opencodeArr[0], opencodeArr[1], opencodeArr[2],
                        opencodeArr[3], opencodeArr[4], opencodeArr[5], opencodeArr[6], opencodeArr[7], opencodeArr[8],
                        opencodeArr[9], opentime, opentimestamp);
//                log.info("lotteryPk10:" + lotteryPk10);
                //TODO: 判断是否为空
                if (lotteryPk10Repository.findByOpentimestamp(opentimestamp) == null) {

                    //logger.info(lotteryPk10.getOpencodeBefore());
                    //最新数据前5个数
                    String[] opencodeBefore = {opencodeArr[0], opencodeArr[1], opencodeArr[2],
                            opencodeArr[3], opencodeArr[4]};
                    String[] opencodeAfter = {opencodeArr[5], opencodeArr[6], opencodeArr[7], opencodeArr[8],
                            opencodeArr[9]};
//                    log.info("opencodeBefore" + Arrays.toString(opencodeBefore));
//                    log.info("opencodeAfter" + Arrays.toString(opencodeAfter));
                    //获取历史最新数据
                    LotteryPk10 lotteryPk10His = lotteryPk10Repository.findFirstByOrderByOpentimestampDesc();

                    //获取计数器
                    Pk10Count pk10Count = pk10CountRepository.findFirstData();

                    //获取当前值
                    Integer numOne = pk10Count.getNumOne();
                    Integer numTwo = pk10Count.getNumTwo();
                    Integer numThree = pk10Count.getNumThree();
                    Integer numFour = pk10Count.getNumFour();
                    Integer numFive = pk10Count.getNumFive();
                    Integer numSix = pk10Count.getNumSix();
                    Integer numSeven = pk10Count.getNumSeven();
                    Integer numEight = pk10Count.getNumEight();
                    Integer numNine = pk10Count.getNumNine();
                    Integer numTen = pk10Count.getNumTen();
                    //取本月历史最大值
                    Integer numOneMaxHis = pk10Count.getNumOneMax();
                    Integer numTwoMaxHis = pk10Count.getNumTwoMax();
                    Integer numThreeMaxHis = pk10Count.getNumThreeMax();
                    Integer numFourMaxHis = pk10Count.getNumFourMax();
                    Integer numFiveMaxHis = pk10Count.getNumFiveMax();
                    Integer numSixMaxHis = pk10Count.getNumSixMax();
                    Integer numSevenMaxHis = pk10Count.getNumSevenMax();
                    Integer numEightMaxHis = pk10Count.getNumEightMax();
                    Integer numNineMaxHis = pk10Count.getNumNineMax();
                    Integer numTenMaxHis = pk10Count.getNumTenMax();
                    //取当日历史最大值
                    Integer numOneDayMaxHis = pk10Count.getNumOneDayMax();
                    Integer numTwoDayMaxHis = pk10Count.getNumTwoDayMax();
                    Integer numThreeDayMaxHis = pk10Count.getNumThreeDayMax();
                    Integer numFourDayMaxHis = pk10Count.getNumFourDayMax();
                    Integer numFiveDayMaxHis = pk10Count.getNumFiveDayMax();
                    Integer numSixDayMaxHis = pk10Count.getNumSixDayMax();
                    Integer numSevenDayMaxHis = pk10Count.getNumSevenDayMax();
                    Integer numEightDayMaxHis = pk10Count.getNumEightDayMax();
                    Integer numNineDayMaxHis = pk10Count.getNumNineDayMax();
                    Integer numTenDayMaxHis = pk10Count.getNumTenDayMax();

                    //判断前五个数
                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo1())) {
                        //TODO: 重构
                        //如果1号位数在后五个数里，就把1号位+1
                        numOne++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumOneDayMax(whosMax(numOneDayMaxHis, numOne));
                        pk10Count.setNumOneMax(whosMax(numOneMaxHis, numOne));
                        pk10Count.setNumOne(numOne);
                        log.info("1号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumOne(0);
                        log.info("1号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo2())) {
                        //如果2号位数在后五个数里，就把2号位+1
                        numTwo++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumTwoDayMax(whosMax(numTwoDayMaxHis, numTwo));

                        pk10Count.setNumTwoMax(whosMax(numTwoMaxHis, numTwo));

                        pk10Count.setNumTwo(numTwo);
                        log.info("2号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumTwo(0);
                        log.info("2号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo3())) {
                        //如果3号位数在后五个数里，就把3号位+1
                        numThree++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumThreeDayMax(whosMax(numThreeDayMaxHis, numThree));
                        //**********************


                        pk10Count.setNumThreeMax(whosMax(numThreeMaxHis, numThree));

                        pk10Count.setNumThree(numThree);
                        log.info("3号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumThree(0);
                        log.info("3号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo4())) {
                        //如果4号位数在后五个数里，就把4号位+1
                        numFour++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumFourDayMax(whosMax(numFourDayMaxHis, numFour));

                        pk10Count.setNumFourMax(whosMax(numFourMaxHis, numFour));

                        pk10Count.setNumFour(numFour);
                        log.info("4号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumFour(0);
                        log.info("4号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryPk10His.getOpencodeNo5())) {
                        //如果5号位数在后五个数里，就把5号位+1
                        numFive++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumFiveDayMax(whosMax(numFiveDayMaxHis, numFive));

                        pk10Count.setNumFiveMax(whosMax(numFiveMaxHis, numFive));

                        pk10Count.setNumFive(numFive);
                        log.info("5号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumFive(0);
                        log.info("5号清零");
                    }

                    //判断后五个数
                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo6())) {
                        //TODO: 重构
                        //如果六号位数组不在前五个数里，就把6号位+1
                         numSix++;
//                        log.info("numSixMaxHis:{}", numSixMaxHis);
                        //把较大的数放历史最大里。
                        pk10Count.setNumSixMax(whosMax(numSixMaxHis, numSix));
                        //把较大的数放当天最大里。
                        pk10Count.setNumSixDayMax(whosMax(numSixDayMaxHis, numSix));
                        //把实时数存入。
                        pk10Count.setNumSix(numSix);
                        log.info("6号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumSix(0);
                        log.info("6号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo7())) {
                        //如果七号位数组在前五个数里，就把7号位+1
                         numSeven++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumSevenDayMax(whosMax(numSevenDayMaxHis, numSeven));

                        pk10Count.setNumSevenMax(whosMax(numSevenMaxHis, numSeven));

                        pk10Count.setNumSeven(numSeven);
                        log.info("7号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumSeven(0);
                        log.info("7号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo8())) {
                        //如果八号位数组在前五个数里，就把8号位+1
                         numEight++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumEightDayMax(whosMax(numEightDayMaxHis, numEight));
                        //**********************


                        pk10Count.setNumEightMax(whosMax(numEightMaxHis, numEight));

                        pk10Count.setNumEight(numEight);
                        log.info("8号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumEight(0);
                        log.info("8号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo9())) {
                        //如果九号位数组在前五个数里，就把9号位+1
                         numNine++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumNineDayMax(whosMax(numNineDayMaxHis, numNine));
                        //**********************


                        pk10Count.setNumNineMax(whosMax(numNineMaxHis, numNine));

                        pk10Count.setNumNine(numNine);
                        log.info("9号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumNine(0);
                        log.info("9号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryPk10His.getOpencodeNo10())) {
                        //如果十号位数组在前五个数里，就把10号位+1
                         numTen++;
                        //把较大的数放当天最大里。
                        pk10Count.setNumTenDayMax(whosMax(numTenDayMaxHis, numTen));
                        //**********************


                        pk10Count.setNumTenMax(whosMax(numTenMaxHis, numTen));

                        pk10Count.setNumTen(numTen);
                        log.info("10号+1");
                    } else {
                        //否则就清零
                        pk10Count.setNumTen(0);
                        log.info("10号清零");
                    }


                    pk10CountRepository.save(pk10Count);

                    //保存最新数据入库
                    lotteryPk10Repository.save(lotteryPk10);
                    log.info("插入数据-------------");
                } else {
                    log.info("有重复的！");
                }
            } else {
                log.info("jsonArray数组为空");
            }
        } else {
            throw new Exception("获取连接失败");
        }
    }

    /**
     * 获取PK10计数器类
     *
     * @return
     */
    public Pk10Count getPk10CntService() {
        //获取计数器
        return pk10CountRepository.findFirstData();
    }


    /**
     * 获取历史最新一期北京赛车
     *
     * @return
     */
    public Map<String, String> getLastPK10Service() {
        //获取历史最新数据
        LotteryPk10 lotteryPk10His = lotteryPk10Repository.findFirstByOrderByOpentimestampDesc();
        Map<String, String> map = new HashMap<>();
        map.put("one", lotteryPk10His.getOpencodeNo1());
        map.put("two", lotteryPk10His.getOpencodeNo2());
        map.put("three", lotteryPk10His.getOpencodeNo3());
        map.put("four", lotteryPk10His.getOpencodeNo4());
        map.put("five", lotteryPk10His.getOpencodeNo5());
        map.put("six", lotteryPk10His.getOpencodeNo6());
        map.put("seven", lotteryPk10His.getOpencodeNo7());
        map.put("eight", lotteryPk10His.getOpencodeNo8());
        map.put("nine", lotteryPk10His.getOpencodeNo9());
        map.put("ten", lotteryPk10His.getOpencodeNo10());
        map.put("expect", lotteryPk10His.getExpect());
        return map;
    }


/*    *//**
     * 判断数组是否包含某个元素
     *
     * @param arr
     * @param targetValue
     * @return
     *//*
    public static boolean judgeContainCode(String[] arr, String targetValue) throws Exception {
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
    }*/

    /**
     * 解析彩票10个号码为数组
     *
     * @param lottery
     * @return
     */
    public static String[] parseLottery(String lottery) {
        return lottery.split(",");
    }

    /**
     * 比较两个数大小
     *
     * @param a
     * @param b
     * @return
     */
    public static Integer whosMax(Integer a, Integer b) {
        if (a > b)
            return a;
        return b;
    }

    /**
     * 从每天 0秒5分0点 开始清零每天数据最大值，
     */
    @Scheduled(cron = "0 5 0 * * ?")
    public void cleanZeroDay() {
        //获取计数器
        Pk10Count pk10Count = pk10CountRepository.findFirstData();
        //最大值清零
        pk10Count.setNumOneDayMax(0);
        pk10Count.setNumTwoDayMax(0);
        pk10Count.setNumThreeDayMax(0);
        pk10Count.setNumFourDayMax(0);
        pk10Count.setNumFiveDayMax(0);
        pk10Count.setNumSixDayMax(0);
        pk10Count.setNumSevenDayMax(0);
        pk10Count.setNumEightDayMax(0);
        pk10Count.setNumNineDayMax(0);
        pk10Count.setNumTenDayMax(0);
        pk10CountRepository.save(pk10Count);
        log.info("清空当天数据");
    }

    //TODO: 测试方法
    public static void main(String[] args) {
        Pk10Service pk10Service = new Pk10Service();
        //获取计数器
        System.out.println(pk10Service.whosMax(6, 1));
        //        lotteryService.savePk10Service();
    }
}
