package com.autentia.pot.service;

import com.autentia.pot.model.Group;
import com.autentia.pot.model.Payment;
import com.autentia.pot.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public void addPayment(Payment payment){
        repository.save(payment);
    }

    public List<Payment> getAllPaymentsOf(Long groupId){
        return repository.findPaymentsByPot(new Group(groupId));
    }
}

