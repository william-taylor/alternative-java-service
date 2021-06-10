package com.mortgage.tool.mortgage;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Mortgage {
    private UUID id;
    private BigDecimal balance;
    private BigDecimal deposit;
    private LocalDate openDate;
    private int termInMonths;
}
