package com.autentia.pot.repository;

import com.autentia.pot.model.Group;
import com.autentia.pot.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findPaymentsByPot(Group group);
}
