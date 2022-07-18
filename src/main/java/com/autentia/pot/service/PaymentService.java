package com.autentia.pot.service;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Group;
import com.autentia.pot.model.Payment;
import com.autentia.pot.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private GroupService groupService;

    public void addPayment(Payment payment){
        repository.save(payment);
    }

    public List<Payment> getAllPaymentsOf(Long groupId){
        return repository.findPaymentsByPot(new Group(groupId));
    }

    public Map<String, Double> getBalanceOf(Long groupId){
        Map<String, Double> balance = new HashMap<>();
        Double sharedCost = 0.0;

        List<Payment> payments = repository.findPaymentsByPot(new Group(groupId));
        List<Friend> friends;
        if (!payments.isEmpty())
             friends = payments.get(0).getPot().getFriends();
        else
            friends = groupService.getGroupBy(groupId).getFriends();

        // Init all friends to 0 to avoid exceptions
        for (Friend friend : friends){
            balance.put(friend.getName(), 0.0);
        }

        // Accumulation of total individual cost and total group cost
        for (Payment payment : payments){
            String lenderName = payment.getLender().getName();
            Double previousAmount = balance.get(lenderName);

            balance.put(lenderName, payment.getAmount() + previousAmount);
            sharedCost += payment.getAmount();
        }

        // Split the cost between all friends
        sharedCost = sharedCost/friends.size();

        // Calculate the balance for each friend (amountPayed - sharedCost)
        for (Friend friend : friends) {
            String userName = friend.getName();

            balance.put(userName, balance.get(userName) - sharedCost);
        }

        return balance;
    }
}

