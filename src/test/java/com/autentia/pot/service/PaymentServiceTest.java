package com.autentia.pot.service;

import com.autentia.pot.model.Friend;
import com.autentia.pot.model.Group;
import com.autentia.pot.model.Payment;
import com.autentia.pot.model.dto.DebtDTO;
import com.autentia.pot.repository.PaymentRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    private final PaymentRepository repository = mock(PaymentRepository.class);
    private final GroupService groupService = mock(GroupService.class);
    private final PaymentService paymentService = new PaymentService(repository, groupService);

    @Test
    public void shouldAddPayment() {
        Payment payment = new Payment(BigDecimal.valueOf(2.23), new Date(), new Friend("Juan"), new Group(1L));
        paymentService.addPayment(payment);
        verify(repository).save(payment);
    }

    @Test
    public void shouldGetAllPayments(){
        Long groupId = 1L;
        Group testGroup = new Group(groupId);

        List<Payment> expected_payments = new ArrayList<>();
        expected_payments.add(new Payment(BigDecimal.valueOf(2.23), new Date(), new Friend("Juan"), testGroup));
        expected_payments.add(new Payment(BigDecimal.valueOf(13.0), new Date(), new Friend("Pepe"), testGroup));
        expected_payments.add(new Payment(BigDecimal.valueOf(12.2), new Date(), new Friend("Luis"), testGroup));

        when(repository.findPaymentsByPot(testGroup)).thenReturn(expected_payments);

        List<Payment> payments = paymentService.getAllPaymentsOf(groupId);

        verify(repository).findPaymentsByPot(testGroup);
        assertEquals(expected_payments, payments);
    }

    @Test
    public void shouldGetBalance(){
        Long groupId = 1L;
        Group group = new Group(groupId);

        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Francisco Buyo"));
        friends.add(new Friend("Alfonso Pérez"));
        friends.add(new Friend("Raúl González"));
        friends.add(new Friend("José María Gutiérrez"));

        group.setFriends(friends);

        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(BigDecimal.valueOf(100.0), new Date(), friends.get(0), group));
        payments.add(new Payment(BigDecimal.valueOf(10.0), new Date(), friends.get(1), group));
        payments.add(new Payment(BigDecimal.valueOf(53.4), new Date(), friends.get(1), group));

        Map<String, BigDecimal> expected_balance = new HashMap<>();
        expected_balance.put("Francisco Buyo", BigDecimal.valueOf(59.15));
        expected_balance.put("Alfonso Pérez", BigDecimal.valueOf(22.55));
        expected_balance.put("Raúl González", BigDecimal.valueOf(-40.85));
        expected_balance.put("José María Gutiérrez", BigDecimal.valueOf(-40.85));

        when(repository.findPaymentsByPot(group)).thenReturn(payments);
        when(groupService.getGroupBy(groupId)).thenReturn(group);

        Map<String, BigDecimal> balance = paymentService.getBalanceOf(groupId);

        verify(repository).findPaymentsByPot(group);

        assertEquals(expected_balance, balance);
    }

    @Test
    public void shouldGetDebt(){
        Map<String, BigDecimal> balance = new HashMap<>();
        balance.put("Francisco Buyo", BigDecimal.valueOf(59.15));
        balance.put("Alfonso Pérez", BigDecimal.valueOf(22.55));
        balance.put("Raúl González", BigDecimal.valueOf(-40.85));
        balance.put("José María Gutiérrez", BigDecimal.valueOf(-40.85));

        List<DebtDTO> expected_debts = new ArrayList<>();
        expected_debts.add(new DebtDTO("José María Gutiérrez", "Francisco Buyo", BigDecimal.valueOf(40.85)));
        expected_debts.add(new DebtDTO("Raúl González", "Alfonso Pérez", BigDecimal.valueOf(22.55)));
        expected_debts.add(new DebtDTO("Raúl González", "Francisco Buyo", BigDecimal.valueOf(18.30)));

        List<DebtDTO> debts = paymentService.getDebtResolution(balance);

        assertEquals(expected_debts, debts);
    }
}