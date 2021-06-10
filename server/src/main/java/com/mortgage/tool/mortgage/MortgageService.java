package com.mortgage.tool.mortgage;


import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;

@Singleton
public class MortgageService {

    @Inject
    private MortgageRepository repository;

    public Optional<Mortgage> getMortgage(String id) {
        return Optional.empty();
    }

    public Mortgage createMortgage(Mortgage mortgage) {
        return mortgage;
    }

    public Mortgage updateMortgage(Mortgage mortgage) {
        return mortgage;
    }

    public void deleteMortgage(Mortgage mortgage) {

    }
}
