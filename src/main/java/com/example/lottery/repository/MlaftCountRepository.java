package com.example.lottery.repository;

import com.example.lottery.domain.MlaftCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 马耳他幸运飞艇计数器接口
 *
 * @author : 唐明
 * @date : Created in 9:33 2017/9/4
 * @modified By :
 */

//TODO: 重写
public interface MlaftCountRepository extends JpaRepository<MlaftCount, Integer> {
    @Query("select p from MlaftCount p")
    public MlaftCount findFirstData();
}
