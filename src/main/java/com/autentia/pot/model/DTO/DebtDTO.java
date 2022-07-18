package com.autentia.pot.model.DTO;

import com.autentia.pot.model.Friend;

public class DebtDTO {
    private String borrower;
    private String lender;
    private Double amount;

    public DebtDTO(String borrower, String lender, Double amount){
        this.borrower = borrower;
        this.lender = lender;
        this.amount = amount;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
