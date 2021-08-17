package com.whh.springcloud.service;

import com.whh.springcloud.entities.Payment;

public interface PaymentService {

    Integer create(Payment payment);

    Payment getPaymentById(Long id);
}
