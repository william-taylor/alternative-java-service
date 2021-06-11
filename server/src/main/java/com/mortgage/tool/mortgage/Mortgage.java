package com.mortgage.tool.mortgage;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Mortgage {
    private BigDecimal amount;
    private BigDecimal deposit;
    private BigDecimal rate;
    private int years;
}
