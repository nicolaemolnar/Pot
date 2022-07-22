package com.autentia.pot.repository;

import com.autentia.pot.model.Group;
import com.autentia.pot.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findPaymentsByPot(Group group);

    Optional<Payment> findPaymentById(Long paymentId);
}
