package com.mortgage.tool.mortgage;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.mortgage.tool.javalin.JavalinController;

public class MortgageModule extends AbstractModule {
    protected void configure() {
        bind(MortgageService.class);

        Multibinder.newSetBinder(binder(), MortgageController.class)
                .addBinding().to(MortgageController.class);
    }
}
