package com.autentia.pot.service;

import com.autentia.pot.model.Payment;
import com.autentia.pot.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public void addPayment(float amount, Long lenderId, Long potId){
        // Payment payment = new Payment()
    }
}
