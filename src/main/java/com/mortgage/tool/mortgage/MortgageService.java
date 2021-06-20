package com.mortgage.tool.mortgage;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Slf4j
@Singleton
public class MortgageService {
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final BigDecimal MONTHS_PER_YEAR = new BigDecimal("12");
    private static final MathContext CURRENCY_CONTEXT = new MathContext(2, RoundingMode.HALF_UP);
    private static final MathContext CALC_CONTEXT = new MathContext(9, RoundingMode.HALF_UP);

    public MortgageInterest calculateInterest(Mortgage mortgage) {
        final var totalMortgageAmount = mortgage.getAmount();
        final var depositProvided = mortgage.getDeposit();
        final var interestRate = mortgage.getRate();

        final var interestRateDecimal = interestRate.divide(HUNDRED, CALC_CONTEXT);
        log.info("Converted interest {} into decimal format {}", interestRate, interestRateDecimal);

        final var termInMonths = mortgage.getYears() * 12;
        log.info("Mortgage term in months is {}", termInMonths);

        final var interestPerMonth = interestRateDecimal.divide(MONTHS_PER_YEAR, CALC_CONTEXT);
        log.info("Interest per month is {}", interestPerMonth);

        final var amountLoanedByBank = totalMortgageAmount.subtract(depositProvided);
        log.info("Bank provided {} towards the mortgage.", amountLoanedByBank);

        final var paymentPerMonth = calculateInterest(amountLoanedByBank, interestPerMonth, termInMonths);
        final var totalMortgageCost = paymentPerMonth.multiply(new BigDecimal(termInMonths), CALC_CONTEXT);
        final var totalMortgageCostRounded = totalMortgageCost.setScale(CURRENCY_CONTEXT.getPrecision(), CURRENCY_CONTEXT.getRoundingMode());
        log.info("Payment per month was calculated as {}", totalMortgageCostRounded);
        return new MortgageInterest(totalMortgageCostRounded, paymentPerMonth);
    }

    /**
     * Calculates the monthly payment for a given mortgage. Formulae taken from
     * various mortgage news sources and can be written as following:
     *
     *      M = P [ I ( 1 + I )^N ] / [ ( 1 + I )^N – 1 ]
     *
     *          M = Monthly payment
     *          P = The principal amount
     *          I = Interest per month
     *          N = Term of mortgage
     *
     */
    private BigDecimal calculateInterest(BigDecimal principalAmount, BigDecimal monthlyInterest, int termInMonths) {
        final var upperInterest = monthlyInterest.multiply(monthlyInterest.add(BigDecimal.ONE).pow(termInMonths));
        final var lowerInterest = monthlyInterest.add(BigDecimal.ONE).pow(termInMonths).subtract(BigDecimal.ONE);
        final var multiplier = upperInterest.divide(lowerInterest, CALC_CONTEXT);
        final var payment = principalAmount.multiply(multiplier, CALC_CONTEXT);
        log.info("Calculated monthly payment for {} {} {} as {}", principalAmount, monthlyInterest, termInMonths, payment);
        return payment.setScale(CURRENCY_CONTEXT.getPrecision(), CURRENCY_CONTEXT.getRoundingMode());
    }

}
