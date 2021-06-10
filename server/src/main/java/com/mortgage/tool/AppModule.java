package com.mortgage.tool;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.mortgage.tool.config.ConfigModule;
import com.mortgage.tool.config.ConfigResolver;
import com.mortgage.tool.javalin.JavalinLauncher;
import com.mortgage.tool.javalin.JavalinModule;
import com.mortgage.tool.mortgage.MortgageModule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Slf4j
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
        final var resolver = injector.getInstance(ConfigResolver.class);
        final var launcher = injector.getInstance(JavalinLauncher.class);

        final var config = resolver.resolve();
        log.info("Resolved configuration: {}", config);
        launcher.listen(config.port());
     }
}
