package com.mortgage.tool;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.mortgage.tool.config.ConfigModule;
import com.mortgage.tool.javalin.JavalinLauncher;
import com.mortgage.tool.javalin.JavalinModule;
import com.mortgage.tool.mortgage.MortgageModule;

import java.util.Arrays;
import java.util.List;

public class AppModule extends AbstractModule {
    private static final List<Module> MODULES = Arrays.asList(
        new ConfigModule(),
        new JavalinModule(),
        new MortgageModule()
    );

    protected void configure() {
        MODULES.forEach(this::install);
    }

    public static void main(String[] args) {
        final var injector = Guice.createInjector(new AppModule());
        injector.getInstance(JavalinLauncher.class).listen(8080);
     }
}
