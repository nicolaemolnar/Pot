package com.autentia.pot.model.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class DebtDTO {
    private String borrower;
    private String lender;
    private BigDecimal amount;

    public DebtDTO(String borrower, String lender, BigDecimal amount){
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebtDTO debtDTO = (DebtDTO) o;
        return Objects.equals(borrower, debtDTO.borrower) && Objects.equals(lender, debtDTO.lender) && amount.compareTo(debtDTO.getAmount()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrower, lender, amount);
    }

    @Override
    public String toString() {
        return "DebtDTO{" +
                "borrower='" + borrower + '\'' +
                ", lender='" + lender + '\'' +
                ", amount=" + amount +
                '}';
    }
}
