package com.example.lottery.repository;

import com.example.lottery.domain.Pk10Count;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 北京赛车计数器接口
 *
 * @author : 唐明
 * @date : Created in 9:33 2017/9/4
 * @modified By :
 */
public interface Pk10CountRepository extends JpaRepository<Pk10Count,Integer>{
    @Query("select p from Pk10Count p")
    public Pk10Count findFirstData();
}
