package com.tikstraw.red2.demo.controller;

import cn.hutool.core.date.DateUtil;
import com.tikstraw.red2.demo.domain.aop.CatCar;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class DemoController {

    @Resource
    private CatCar catCar;

    @RequestMapping("/topic1")
    public Object topic1() {

        return catCar.getNow();
    }


}
