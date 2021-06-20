package com.mortgage.tool.mortgage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MortgageServiceTest {
    private MortgageService mortgageService;

    @BeforeEach
    public void setupTest() {
        mortgageService = new MortgageService();
    }

    @Test
    public void testMortgageScenarioA() {
        final var given = new Mortgage(new BigDecimal("440000"), new BigDecimal("66000"), new BigDecimal("2.39"), 34);
        final var expected = new MortgageInterest(new BigDecimal("546666.96"), new BigDecimal("1339.87"));
        final var result = mortgageService.calculateInterest(given);
        assertThat(result, is(expected));
    }

    @Test
    public void testMortgageScenarioB() {
        final var given = new Mortgage(new BigDecimal("145000"), new BigDecimal("20000"), new BigDecimal("2.0"), 35);
        final var expected = new MortgageInterest(new BigDecimal("173913.60"), new BigDecimal("414.08"));
        final var result = mortgageService.calculateInterest(given);
        assertThat(result, is(expected));
    }
}
