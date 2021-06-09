package com.mortgage.tool.javalin;

import com.google.inject.AbstractModule;

public class JavalinModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JavalinLauncher.class);
    }
}
