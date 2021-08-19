package com.whh.springcloud.controller;

import com.whh.springcloud.entities.CommonResult;
import com.whh.springcloud.entities.Payment;
import com.whh.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j//打印日志
public class PaymentController {

    @Autowired
    @Qualifier("paymentServiceImpl")
    private PaymentService paymentService;

    //用于服务提供者集群构建，便于分辨是哪台服务提供者提供的服务
    @Value("${server.port}")// 读取yml文件的端口号
    private String serverPost;//8001

    //①对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息
    @Resource
    private DiscoveryClient discoveryClient;


    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        Integer nums = paymentService.create(payment);
//        log.info("============"+nums);

        if (nums > 0){
            CommonResult commonResult = new CommonResult(200,"创建成功"+serverPost,nums);
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
            return new CommonResult(200,"查询成功"+serverPost,payment);
        }else {
            return new CommonResult(444,"没有查询记录");
        }
    }


    //②对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
//            log.info("*****element: "+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
//            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }


    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPost;
    }



}
