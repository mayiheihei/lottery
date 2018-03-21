package com.example.lottery.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lottery.domain.Mlaft;
import com.example.lottery.domain.MlaftCount;
import com.example.lottery.repository.MlaftCountRepository;
import com.example.lottery.repository.MlaftRepository;
import com.example.lottery.util.HttpClientUtil;
import com.example.lottery.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 马耳他幸运飞艇彩票服务
 *
 * @author 唐明
 * @create 2017-09-03 20:28
 */
//TODO: 重新写
@Service
@Slf4j
public class MlaftService {
    @Autowired
    private MlaftRepository mlaftRepository;
    @Autowired
    private MlaftCountRepository mlaftCountRepository;

//    private static final String host = "a.apiplus.net/newly.do?token=t9bc5205d023876b3k&code=mlaft&rows=1&format=json";
    private static final String host = "e.apiplus.net/newly.do?token=t9bc5205d023876b3k&code=mlaft&rows=1&format=json";

    @Scheduled(cron = "40/5 * * * * ?")
    public void saveMlaftService() throws Exception {
        String parameter = HttpClientUtil.HttpGetDate(host);
        if (parameter != null && !parameter.equals("")) {
            JSONObject jsonObject = JSONObject.parseObject(parameter);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            String code = (String) jsonObject.get("code");
            if (jsonArray != null && !jsonArray.isEmpty()) {
                String expect = (String) jsonArray.getJSONObject(0).get("expect");
                String opencode = (String) jsonArray.getJSONObject(0).get("opencode");
                String[] opencodeArr = parseLottery(opencode);
                String opentime = (String) jsonArray.getJSONObject(0).get("opentime");
                Integer opentimestamp = (Integer) jsonArray.getJSONObject(0).get("opentimestamp");

                Mlaft lotteryMlaft = new Mlaft(code, expect, opencodeArr[0], opencodeArr[1], opencodeArr[2],
                        opencodeArr[3], opencodeArr[4], opencodeArr[5], opencodeArr[6], opencodeArr[7], opencodeArr[8],
                        opencodeArr[9], opentime, opentimestamp);
                //                log.info("lotteryMlaft:" + lotteryMlaft);

                //初始化方法，如果数据库为空，就给插入默认值
                List<Mlaft> list = mlaftRepository.findAll();
                //TODO: 测试代码
                if (list == null) {
                    System.out.println("1111111111111111");
                }
//                int a = list.size();
                //                log.info("a--------------{}{}", a, list);
                if (list.isEmpty()) {
                    Mlaft mlaft = new Mlaft("mlaft", "20170905036", "0", "0",
                            "0", "0", "0", "0", "0",
                            "0", "0", "0", "0", 0);
                    mlaft.setOpencodeBefore(new String[]{"0", "0", "0", "0", "0"});
                    mlaft.setOpencodeAfter(new String[]{"0", "0", "0", "0", "0"});
                    mlaftRepository.save(mlaft);
                }
                List<MlaftCount> listMtCount = mlaftCountRepository.findAll();
                if (listMtCount == null || listMtCount.isEmpty()) {
                    MlaftCount mlaftCount = new MlaftCount(0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0,
                            0, 0);
                    mlaftCountRepository.save(mlaftCount);
                }
                //TODO: 判断是否为空
                if(opentimestamp==null){
                    return;
                }

                //如果接口查询出的时间戳不存在，就执行
                if (mlaftRepository.findByOpentimestamp(opentimestamp) == null) {

                    //logger.info(lotteryMlaft.getOpencodeBefore());
                    //最新数据前5个数
                    String[] opencodeBefore = {opencodeArr[0], opencodeArr[1], opencodeArr[2],
                            opencodeArr[3], opencodeArr[4]};
                    String[] opencodeAfter = {opencodeArr[5], opencodeArr[6], opencodeArr[7], opencodeArr[8],
                            opencodeArr[9]};
                    //log.info("opencodeBefore" + Arrays.toString(opencodeBefore));
                    //log.info("opencodeAfter" + Arrays.toString(opencodeAfter));
                    //获取历史最新数据
                    Mlaft lotteryMlaftHis = mlaftRepository.findFirstByOrderByOpentimestampDesc();

                    //获取计数器
                    MlaftCount mlaftCount = mlaftCountRepository.findFirstData();

                    //获取当前值
                    Integer numOne = mlaftCount.getNumOne();
                    Integer numTwo = mlaftCount.getNumTwo();
                    Integer numThree = mlaftCount.getNumThree();
                    Integer numFour = mlaftCount.getNumFour();
                    Integer numFive = mlaftCount.getNumFive();
                    Integer numSix = mlaftCount.getNumSix();
                    Integer numSeven = mlaftCount.getNumSeven();
                    Integer numEight = mlaftCount.getNumEight();
                    Integer numNine = mlaftCount.getNumNine();
                    Integer numTen = mlaftCount.getNumTen();
                    //取本月历史最大值
                    Integer numOneMaxHis = mlaftCount.getNumOneMax();
                    Integer numTwoMaxHis = mlaftCount.getNumTwoMax();
                    Integer numThreeMaxHis = mlaftCount.getNumThreeMax();
                    Integer numFourMaxHis = mlaftCount.getNumFourMax();
                    Integer numFiveMaxHis = mlaftCount.getNumFiveMax();
                    Integer numSixMaxHis = mlaftCount.getNumSixMax();
                    Integer numSevenMaxHis = mlaftCount.getNumSevenMax();
                    Integer numEightMaxHis = mlaftCount.getNumEightMax();
                    Integer numNineMaxHis = mlaftCount.getNumNineMax();
                    Integer numTenMaxHis = mlaftCount.getNumTenMax();
                    //取当日历史最大值
                    Integer numOneDayMaxHis = mlaftCount.getNumOneDayMax();
                    Integer numTwoDayMaxHis = mlaftCount.getNumTwoDayMax();
                    Integer numThreeDayMaxHis = mlaftCount.getNumThreeDayMax();
                    Integer numFourDayMaxHis = mlaftCount.getNumFourDayMax();
                    Integer numFiveDayMaxHis = mlaftCount.getNumFiveDayMax();
                    Integer numSixDayMaxHis = mlaftCount.getNumSixDayMax();
                    Integer numSevenDayMaxHis = mlaftCount.getNumSevenDayMax();
                    Integer numEightDayMaxHis = mlaftCount.getNumEightDayMax();
                    Integer numNineDayMaxHis = mlaftCount.getNumNineDayMax();
                    Integer numTenDayMaxHis = mlaftCount.getNumTenDayMax();

                    //判断前五个数
                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryMlaftHis.getOpencodeNo1())) {
                        //TODO: 重构
                        //如果1号位数不在后五个数里，就把1号位+1
                        numOne++;
                        //把较大的数放当天最大里。
                        mlaftCount.setNumOneDayMax(whosMax(numOneDayMaxHis, numOne));
                        //把较大的数放历史最大里
                        mlaftCount.setNumOneMax(whosMax(numOneMaxHis, numOne));
                        //把累加后的数放当前值里
                        mlaftCount.setNumOne(numOne);
                        log.info("1号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumOne(0);
                        log.info("1号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryMlaftHis.getOpencodeNo2())) {
                        //如果2号位数不在后五个数里，就把2号位+1
                        numTwo++;
                        //把较大的数放当天最大里。
                        mlaftCount.setNumTwoDayMax(whosMax(numTwoDayMaxHis, numTwo));
                        //把较大的数放历史最大里
                        mlaftCount.setNumTwoMax(whosMax(numTwoMaxHis, numTwo));

                        mlaftCount.setNumTwo(numTwo);
                        log.info("2号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumTwo(0);
                        log.info("2号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryMlaftHis.getOpencodeNo3())) {
                        //如果3号位数在后五个数里，就把3号位+1
                        numThree++;

                        //把较大的数放当天最大里。
                        mlaftCount.setNumThreeDayMax(whosMax(numThreeDayMaxHis, numThree));
                        //把较大的数放历史最大里
                        mlaftCount.setNumThreeMax(whosMax(numThreeMaxHis, numThree));

                        mlaftCount.setNumThree(numThree);
                        log.info("3号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumThree(0);
                        log.info("3号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryMlaftHis.getOpencodeNo4())) {
                        //如果4号位数在后五个数里，就把4号位+1
                        numFour++;

                        //把较大的数放当天最大里。
                        mlaftCount.setNumFourDayMax(whosMax(numFourDayMaxHis, numFour));
                        //把较大的数放历史最大里
                        mlaftCount.setNumFourMax(whosMax(numFourMaxHis, numFour));

                        mlaftCount.setNumFour(numFour);
                        log.info("4号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumFour(0);
                        log.info("4号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeAfter, lotteryMlaftHis.getOpencodeNo5())) {
                        //如果5号位数在后五个数里，就把5号位+1
                        numFive++;

                        //把较大的数放当天最大里。
                        mlaftCount.setNumFiveDayMax(whosMax(numFiveDayMaxHis, numFive));

                        mlaftCount.setNumFiveMax(whosMax(numFiveMaxHis, numFive));

                        mlaftCount.setNumFive(numFive);
                        log.info("5号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumFive(0);
                        log.info("5号清零");
                    }

                    //判断后五个数
                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryMlaftHis.getOpencodeNo6())) {
                        //如果六号位数组在前五个数里，就把6号位+1
                        numSix++;

                        //把较大的数放当天最大里。
                        mlaftCount.setNumSixDayMax(whosMax(numSixDayMaxHis, numSix));

                        mlaftCount.setNumSixMax(whosMax(numSixMaxHis, numSix));
                        mlaftCount.setNumSix(numSix);
                        log.info("6号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumSix(0);
                        log.info("6号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryMlaftHis.getOpencodeNo7())) {
                        //如果七号位数组在前五个数里，就把7号位+1
                        numSeven++;

                        //把较大的数放当天最大里。
                        mlaftCount.setNumSevenDayMax(whosMax(numSevenDayMaxHis, numSeven));

                        mlaftCount.setNumSevenMax(whosMax(numSevenMaxHis, numSeven));

                        mlaftCount.setNumSeven(numSeven);
                        log.info("7号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumSeven(0);
                        log.info("7号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryMlaftHis.getOpencodeNo8())) {
                        //如果八号位数组在前五个数里，就把8号位+1
                        numEight++;

                        //把较大的数放当天最大里。
                        mlaftCount.setNumEightDayMax(whosMax(numEightDayMaxHis, numEight));


                        mlaftCount.setNumEightMax(whosMax(numEightMaxHis, numEight));

                        mlaftCount.setNumEight(numEight);
                        log.info("8号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumEight(0);
                        log.info("8号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryMlaftHis.getOpencodeNo9())) {
                        //如果九号位数组在前五个数里，就把9号位+1
                        numNine++;

                        //把较大的数放当天最大里。
                        mlaftCount.setNumNineDayMax(whosMax(numNineDayMaxHis, numNine));


                        mlaftCount.setNumNineMax(whosMax(numNineMaxHis, numNine));

                        mlaftCount.setNumNine(numNine);
                        log.info("9号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumNine(0);
                        log.info("9号清零");
                    }

                    if (!ServiceUtil.judgeContainCode(opencodeBefore, lotteryMlaftHis.getOpencodeNo10())) {
                        //如果十号位数组在前五个数里，就把10号位+1
                        numTen++;

                        //把较大的数放当天最大里。
                        mlaftCount.setNumTenDayMax(whosMax(numTenDayMaxHis, numTen));


                        mlaftCount.setNumTenMax(whosMax(numTenMaxHis, numTen));

                        mlaftCount.setNumTen(numTen);
                        log.info("10号+1");
                    } else {
                        //否则就清零
                        mlaftCount.setNumTen(0);
                        log.info("10号清零");
                    }
                    //保存计数器
                    mlaftCountRepository.save(mlaftCount);

                    //保存最新数据入库
                    mlaftRepository.save(lotteryMlaft);
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
     * 获取马耳他幸运飞艇计数器实体
     *
     * @return
     */
    public MlaftCount getMlaftService() {
        return mlaftCountRepository.findFirstData();
    }

    /**
     * 获取历史最新一期北京赛车
     *
     * @return
     */
    public Map<String, String> getLastMlaftService() {
        //获取历史最新数据
        Mlaft mlaftHis = mlaftRepository.findFirstByOrderByOpentimestampDesc();
        Map<String, String> map = new HashMap<>();
        map.put("one", mlaftHis.getOpencodeNo1());
        map.put("two", mlaftHis.getOpencodeNo2());
        map.put("three", mlaftHis.getOpencodeNo3());
        map.put("four", mlaftHis.getOpencodeNo4());
        map.put("five", mlaftHis.getOpencodeNo5());
        map.put("six", mlaftHis.getOpencodeNo6());
        map.put("seven", mlaftHis.getOpencodeNo7());
        map.put("eight", mlaftHis.getOpencodeNo8());
        map.put("nine", mlaftHis.getOpencodeNo9());
        map.put("ten", mlaftHis.getOpencodeNo10());
        map.put("expect", mlaftHis.getExpect());
        return map;
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
    @Scheduled(cron = "0 6 0 * * ?")
    public void cleanZeroMonth() {
        //获取计数器
        MlaftCount mlaftCount = mlaftCountRepository.findFirstData();
        //最大值清零
        mlaftCount.setNumOneDayMax(0);
        mlaftCount.setNumTwoDayMax(0);
        mlaftCount.setNumThreeDayMax(0);
        mlaftCount.setNumFourDayMax(0);
        mlaftCount.setNumFiveDayMax(0);
        mlaftCount.setNumSixDayMax(0);
        mlaftCount.setNumSevenDayMax(0);
        mlaftCount.setNumEightDayMax(0);
        mlaftCount.setNumNineDayMax(0);
        mlaftCount.setNumTenDayMax(0);
        mlaftCountRepository.save(mlaftCount);
        log.info("清空当天数据");
    }

}
