package com.whh.springcloud;
//SpringBoot启动类

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class PaymentMain8081 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8081.class,args);
    }

}
