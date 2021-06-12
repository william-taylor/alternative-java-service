package com.mortgage.tool.mortgage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Mortgage {
    private BigDecimal amount;
    private BigDecimal deposit;
    private BigDecimal rate;
    private int years;
}
