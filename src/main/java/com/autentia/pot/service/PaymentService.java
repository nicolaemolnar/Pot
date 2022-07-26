package com.autentia.pot.service;

import com.autentia.pot.model.dto.DebtDTO;
import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Pot;
import com.autentia.pot.model.Payment;
import com.autentia.pot.repository.PaymentRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PaymentService {
    private final PaymentRepository repository;
    private final PotService potService;

    public PaymentService(PaymentRepository repository, PotService potService) {
        this.repository = repository;
        this.potService = potService;
    }

    public void addPayment(Payment payment){
        repository.save(payment);
    }

    public List<Payment> getAllPaymentsOf(Long potId){
        return repository.findPaymentsByPot(new Pot(potId));
    }

    public Map<String, BigDecimal> getBalanceOf(Long potId){
        Map<String, BigDecimal> balance = new HashMap<>();
        BigDecimal sharedCost = BigDecimal.valueOf(0.0);

        List<Payment> payments = repository.findPaymentsByPot(new Pot(potId));
        List<Friend> friends;
        if (!payments.isEmpty())
             friends = payments.get(0).getPot().getFriends();
        else
            friends = potService.getPotById(potId).getFriends();

        // Init all friends to 0 to avoid exceptions
        for (Friend friend : friends){
            balance.put(friend.getName(), BigDecimal.valueOf(0.0));
        }

        // Accumulation of total individual cost and total cost of the pot
        for (Payment payment : payments){
            String lenderName = payment.getLender().getName();
            BigDecimal previousAmount = balance.get(lenderName);

            balance.put(lenderName, payment.getAmount().add(previousAmount));
            sharedCost = sharedCost.add(payment.getAmount());
        }

        // Split the cost between all friends
        sharedCost = sharedCost.divide(BigDecimal.valueOf(friends.size()));

        // Calculate the balance for each friend (amountPayed - sharedCost)
        for (Friend friend : friends) {
            String userName = friend.getName();

            balance.put(userName, balance.get(userName).subtract(sharedCost));
        }

        return balance;
    }

    public List<DebtDTO> getDebtResolution(Map<String, BigDecimal> balance){
        List<DebtDTO> debts = new ArrayList<>();
        List<String> names = balance.keySet().stream().toList();

        List<String> lenders = new ArrayList<>(names.stream().filter(name -> balance.get(name).doubleValue() > 0).toList());
        List<String> borrowers = new ArrayList<>(names.stream().filter(name -> balance.get(name).doubleValue() < 0).toList());

        while (!lenders.isEmpty()){
            String greaterLenderName = lenders.stream().reduce((s,s2) -> (balance.get(s).compareTo(balance.get(s2)) > 0) ? s : s2).get();
            String greaterBorrowerName = borrowers.stream().reduce((s, s2) -> (balance.get(s).compareTo(balance.get(s2)) < 0) ? s : s2).get();

            BigDecimal diff = balance.get(greaterLenderName).add(balance.get(greaterBorrowerName));
            DebtDTO debt = new DebtDTO(greaterBorrowerName, greaterLenderName, BigDecimal.valueOf(0.0));
            if (diff.doubleValue() > 0) {
                debt.setAmount(balance.get(greaterLenderName).subtract(diff));

                // Remove borrower from list and update balances
                balance.put(greaterLenderName, diff);
                balance.put(greaterBorrowerName, BigDecimal.valueOf(0.0));
                borrowers.remove(greaterBorrowerName);
            } else if (diff.doubleValue() < 0){
                debt.setAmount(balance.get(greaterBorrowerName).abs().add(diff));

                // Remove lender from list and update balances
                balance.put(greaterLenderName,BigDecimal.valueOf(0.0));
                balance.put(greaterBorrowerName, diff);
                lenders.remove(greaterLenderName);
            } else {
                debt.setAmount(balance.get(greaterLenderName));

                // Remove both lender and borrower from lists
                balance.put(greaterLenderName, BigDecimal.valueOf(0.0));
                balance.put(greaterBorrowerName, BigDecimal.valueOf(0.0));
                lenders.remove(greaterLenderName);
                borrowers.remove(greaterBorrowerName);
            }
            debts.add(debt);

        }
        return debts;
    }
}

