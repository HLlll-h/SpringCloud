package com.whh.springcloud.controller;

import com.whh.springcloud.entities.CommonResult;
import com.whh.springcloud.entities.Payment;
import com.whh.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j//打印日志
public class PaymentController {

    @Autowired
    @Qualifier("paymentServiceImpl")
    private PaymentService paymentService;


    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        Integer nums = paymentService.create(payment);
//        log.info("============"+nums);

        if (nums > 0){
            CommonResult commonResult = new CommonResult(200,"创建成功",nums);
            return commonResult;
        }else {
            return new CommonResult(444,"创建失败");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult query(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
//        log.info("============"+payment);

        if(payment != null){
            return new CommonResult(200,"查询成功",payment);
        }else {
            return new CommonResult(444,"没有查询记录");
        }
    }

}
