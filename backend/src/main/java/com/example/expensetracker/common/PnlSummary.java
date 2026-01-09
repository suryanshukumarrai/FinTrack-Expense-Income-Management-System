package com.example.expensetracker.common;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PnlSummary {

    private final BigDecimal totalIncome;
    private final BigDecimal totalExpense;
    private final BigDecimal net;

    public PnlSummary(BigDecimal totalIncome, BigDecimal totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.net = totalIncome.subtract(totalExpense);
    }
}


