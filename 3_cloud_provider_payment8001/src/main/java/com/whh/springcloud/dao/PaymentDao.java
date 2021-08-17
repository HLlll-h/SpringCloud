package com.whh.springcloud.dao;

import com.whh.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao  {

    /**
     * 增加一条记录
     */
    Integer create(Payment payment);

    /**
     * 根据id查询
     */
    Payment getPaymentById(@Param("id") Long id);

}
