package com.autentia.pot.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Payment {

    private Long id;
    private BigDecimal amount;
    private Date date;
    private Friend lender;
    private Group pot;

    public Payment(){
    }

    public Payment(BigDecimal amount, Date date, Friend lender, Group pot){
        this.amount = amount;
        this.date = date;
        this.lender = lender;
        this.pot = pot;
    }

    // SETTER
    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLender(Friend lender) {
        this.lender = lender;
    }

    public void setPot(Group pot) {
        this.pot = pot;
    }

    // GETTER
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @ManyToOne
    public Friend getLender() {
        return lender;
    }

    @OneToOne
    public Group getPot(){
        return this.pot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(amount, payment.amount) && Objects.equals(date, payment.date) && Objects.equals(lender, payment.lender) && Objects.equals(pot, payment.pot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, date, lender, pot);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", lender=" + lender +
                ", pot=" + pot +
                '}';
    }
}
