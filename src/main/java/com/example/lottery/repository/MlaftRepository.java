package com.example.lottery.repository;

import com.example.lottery.domain.Mlaft;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 马耳他幸运飞艇接口
 */
//TODO: 重写
public interface MlaftRepository extends JpaRepository<Mlaft, Integer> {

    public Mlaft findByOpentimestamp(Integer opentimestamp);

    public Mlaft findFirstByOrderByOpentimestampDesc();
}
