package com.autentia.pot.core.repository;

import com.autentia.pot.core.model.Payment;
import com.autentia.pot.core.model.Pot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findPaymentsByPot(Pot group);

    Optional<Payment> findPaymentById(Long paymentId);
}
