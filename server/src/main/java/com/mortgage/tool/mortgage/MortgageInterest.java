package com.mortgage.tool.mortgage;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class MortgageInterest {
    private BigDecimal totalAmount;
    private BigDecimal perMonthAmount;
}
