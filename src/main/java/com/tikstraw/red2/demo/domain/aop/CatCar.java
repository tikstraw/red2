package com.tikstraw.red2.demo.domain.aop;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;

@Component
public class CatCar {

    @Latency
    public String getNow() {
        try {
            Thread.sleep(RandomUtil.randomLong(100, 400));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return DateUtil.now();
    }

}
