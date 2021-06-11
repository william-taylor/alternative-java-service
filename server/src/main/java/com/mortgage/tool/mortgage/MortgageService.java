package com.mortgage.tool.mortgage;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Singleton
public class MortgageService {

    public MortgageInterest calculateInterest(Mortgage mortgage) {
        final var totalMortgageAmount = mortgage.getAmount();
        final var depositProvided = mortgage.getDeposit();
        final var interestRate = mortgage.getRate();

        final var termInMonths = mortgage.getYears() * 12;
        log.info("Mortgage term in months is {}", termInMonths);

        final var amountLoanedByBank = totalMortgageAmount.subtract(depositProvided);
        log.info("Bank provided {} towards the mortgage.", amountLoanedByBank);

        // TODO: Finish implementation

        return new MortgageInterest(BigDecimal.ZERO, BigDecimal.ZERO);
    }

}
