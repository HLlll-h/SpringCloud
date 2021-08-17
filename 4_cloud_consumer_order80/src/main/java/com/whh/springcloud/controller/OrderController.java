package com.whh.springcloud.controller;

import com.whh.springcloud.entities.CommonResult;
import com.whh.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    //有两个服务提供者 url不能写死-----负载均衡
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/get/" + id,CommonResult.class);
    }


    /**
     * restTemplate中getForEntity的使用
     * 在开发中如果想知道更详细的信息用getForEntity
     * 一般用getForObject得到json字符串即可
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();//getForObject()
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }




}
