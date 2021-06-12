package com.mortgage.tool.mortgage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mortgage {
    private BigDecimal amount;
    private BigDecimal deposit;
    private BigDecimal rate;
    private int years;
}
