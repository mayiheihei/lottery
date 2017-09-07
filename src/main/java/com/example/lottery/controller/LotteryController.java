package com.example.lottery.controller;

import com.example.lottery.domain.MlaftCount;
import com.example.lottery.domain.Pk10Count;
import com.example.lottery.service.MlaftService;
import com.example.lottery.service.Pk10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 彩票API
 *
 * @author 唐明
 * @create 2017-09-03 20:26
 */
@RestController
public class LotteryController {
    @Autowired
    private Pk10Service pk10Service;

    @Autowired
    private MlaftService mlaftService;


    /**
     * 获取北京赛车当前计数器和当月历史最高数
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/pk10Count")
    public Pk10Count getpK10Count() throws Exception {
        return pk10Service.getPk10CntService();
    }

    /**
     * 获取历史最新一期北京赛车
     *
     * @return
     */
    @GetMapping("/pk10")
    public Map<String, String> getPk10() {
        return pk10Service.getLastPK10Service();
    }


    /**
     * 获取历史最新一期马耳他幸运赛艇
     *
     * @return
     */
    @GetMapping("/mlaft")
    public Map<String, String> getMlaft() {
        return mlaftService.getLastMlaftService();
    }


    /**
     * 获取马耳他幸运赛艇当前计数器和当月历史最高数
     *
     * @return
     */
    @GetMapping("/mlaftCount")
    public MlaftCount getMlaftCount() {
        return mlaftService.getMlaftService();
    }
}
