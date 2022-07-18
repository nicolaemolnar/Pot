package com.autentia.pot.controller;

import com.autentia.pot.model.DTO.DebtDTO;
import com.autentia.pot.model.Payment;
import com.autentia.pot.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments/{groupId}")
    public List<Payment> getPaymentsOfGroup(@PathVariable(value = "groupId") Long id){
        return paymentService.getAllPaymentsOf(id);
    }

    @PostMapping("/v2/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPayment(@RequestBody Payment payment){
        paymentService.addPayment(payment);
    }

    @GetMapping("/payments/{groupId}/balance")
    public Map<String, Double> getBalanceOf(@PathVariable("groupId") Long groupId){
        return paymentService.getBalanceOf(groupId);
    }

    @GetMapping("/payments/debtResolution")
    public List<DebtDTO> getDebtResolution(@RequestBody Map<String, Double> balance){
        return paymentService.getDebtResolution(balance);
    }
}
