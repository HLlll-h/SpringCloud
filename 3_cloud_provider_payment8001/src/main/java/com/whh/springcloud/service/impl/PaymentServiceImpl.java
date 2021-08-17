package com.whh.springcloud.service.impl;

import com.whh.springcloud.dao.PaymentDao;
import com.whh.springcloud.entities.Payment;
import com.whh.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("paymentServiceImpl")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    @Qualifier("paymentDao")
    private PaymentDao paymentDao;

    @Override
    public Integer create(Payment payment) {
        Integer nums = paymentDao.create(payment);
        return nums;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment payment = paymentDao.getPaymentById(id);
        return payment;
    }


}
