package com.autentia.pot.service;

import com.autentia.pot.model.DTO.DebtDTO;
import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Group;
import com.autentia.pot.model.Payment;
import com.autentia.pot.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

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

    public List<DebtDTO> getDebtResolution(Map<String, Double> balance){
        ArrayList<DebtDTO> debts = new ArrayList<>();
        List<String> names = balance.keySet().stream().toList();

        ArrayList<String> lenders = new ArrayList<>(names.stream().filter(name -> balance.get(name) > 0).toList());
        ArrayList<String> borrowers = new ArrayList<>(names.stream().filter(name -> balance.get(name) < 0).toList());

        while (!lenders.isEmpty()){
            String greaterLenderName = lenders.stream().reduce((s,s2) -> {
                return (balance.get(s) > balance.get(s2)) ? s : s2;
            }).get();
            String greaterBorrowerName = borrowers.stream().reduce((s, s2) -> {
                return (balance.get(s) < balance.get(s2)) ? s : s2;
            }).get();


            Double diff = balance.get(greaterLenderName) + balance.get(greaterBorrowerName);
            DebtDTO debt = new DebtDTO(greaterBorrowerName, greaterLenderName, 0.0);
            if (diff > 0) {
                debt.setAmount(balance.get(greaterLenderName) - diff);

                // Remove borrower from list and update balances
                balance.put(greaterLenderName, diff);
                balance.put(greaterBorrowerName, 0.0);
                borrowers.remove(greaterBorrowerName);
            } else if (diff < 0){
                debt.setAmount(Math.abs(balance.get(greaterBorrowerName)) + diff);

                // Remove lender from list and update balances
                balance.put(greaterLenderName, 0.0);
                balance.put(greaterBorrowerName, diff);
                lenders.remove(greaterLenderName);
            } else {
                debt.setAmount(balance.get(greaterLenderName));

                // Remove both lender and borrower from lists
                balance.put(greaterLenderName, 0.0);
                balance.put(greaterBorrowerName, 0.0);
                lenders.remove(greaterLenderName);
                borrowers.remove(greaterBorrowerName);
            }
            debts.add(debt);

        }
        return debts;
    }
}

