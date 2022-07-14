package com.autentia.pot.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Payment {

    private Long id;
    private float amount;
    private Date date;
    private Friend lender;
    private Group pot;

    // SETTER
    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(float amount) {
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

    public float getAmount() {
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
