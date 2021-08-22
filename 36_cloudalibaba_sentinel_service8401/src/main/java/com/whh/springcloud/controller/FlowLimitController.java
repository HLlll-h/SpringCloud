package com.whh.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {


    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }


    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testD 测试RT");
        return "testD---------";
    }

    @RequestMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")//Sentinel服务降级
    public String testHotKey(String p1, String p2){
        return "-------testHotKey p1:" + p1 + ",p2:" + p2;
    }

    public  String deal_testHotKey(String p1, String p2, BlockException exception){
        return "兜底方法啦！";
    }


}
