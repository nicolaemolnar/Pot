package com.autentia.pot.repository.integration;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Pot;
import com.autentia.pot.model.Payment;
import com.autentia.pot.repository.FriendRepository;
import com.autentia.pot.repository.PotRepository;
import com.autentia.pot.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PaymentRepositoryIT {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PotRepository potRepository;

    @Autowired
    private FriendRepository friendRepository;

    private Friend db_friend;
    private Pot db_pot;

    @BeforeEach
    void setInitialState(){
        db_friend = new Friend("Payment Tester");
        friendRepository.save(db_friend);

        db_pot = new Pot();
        potRepository.save(db_pot);
    }

    @Test
    void shouldSavePayment() {
        Payment expected_payment = new Payment(BigDecimal.valueOf(12.0), new Date(), db_friend, db_pot);
        paymentRepository.save(expected_payment);

        assertTrue(paymentRepository.findPaymentById(expected_payment.getId()).isPresent());
    }

    @Test
    void shouldFindPaymentsByPot() {
        List<Payment> expected_payments = new ArrayList<>();
        expected_payments.add(new Payment(BigDecimal.valueOf(12.0), new Date(), db_friend, db_pot));
        expected_payments.add(new Payment(BigDecimal.valueOf(25.0), new Date(), db_friend, db_pot));
        expected_payments.add(new Payment(BigDecimal.valueOf(33.0), new Date(), db_friend, db_pot));

        paymentRepository.saveAll(expected_payments);

        List<Payment> payments = paymentRepository.findPaymentsByPot(db_pot);
        assertEquals(expected_payments, payments);
    }

    @Test
    void shouldFindPaymentById() {
        Payment expected_payment = new Payment(BigDecimal.valueOf(12.0), new Date(), db_friend, db_pot);
        paymentRepository.save(expected_payment);

        Payment payment = paymentRepository.findPaymentById(expected_payment.getId()).get();

        assertEquals(expected_payment, payment);
    }
}