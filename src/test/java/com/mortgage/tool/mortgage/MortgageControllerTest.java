package com.mortgage.tool.mortgage;

import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MortgageControllerTest {

    @Mock
    private MortgageService service;

    @Mock
    private Context context;

    private MortgageController controller;

    @BeforeEach
    public void setupTest() {
        controller = new MortgageController(service);
    }

    @Test
    public void testCalculateInterest() {
        final var interest = new MortgageInterest(new BigDecimal("86843"), new BigDecimal("241"));
        final var mortgage = new Mortgage(new BigDecimal("100000"), new BigDecimal("25000"), new BigDecimal("1.0"), 30);;

        when(service.calculateInterest(mortgage)).thenReturn(interest);
        when(context.bodyAsClass(Mortgage.class)).thenReturn(mortgage);

        controller.calculateInterest(context);

        verify(context).bodyAsClass(Mortgage.class);
        verify(context).json(interest);
    }
}
