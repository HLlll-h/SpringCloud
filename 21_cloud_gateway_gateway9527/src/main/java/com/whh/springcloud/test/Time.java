package com.whh.springcloud.test;

import java.time.ZonedDateTime;

public class Time {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);

        //2021-08-19T15:25:35.600226500+08:00[Asia/Shanghai]
    }
}
