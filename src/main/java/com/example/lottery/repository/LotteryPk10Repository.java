package com.example.lottery.repository;

import com.example.lottery.domain.LotteryPk10;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 北京赛车接口
 */
public interface LotteryPk10Repository extends JpaRepository<LotteryPk10, Integer> {
    public  LotteryPk10 findByOpentimestamp(Integer opentimestamp);


 /*   @Query("select LotteryPk10  from lottery_pk10 where opentimestamp = ?1")
    public LotteryPk10 findByOpentimestamp(Integer opentimestamp);*/
 public  LotteryPk10 findFirstByOrderByOpentimestampDesc();
}
