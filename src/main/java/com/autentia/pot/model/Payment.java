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

}
