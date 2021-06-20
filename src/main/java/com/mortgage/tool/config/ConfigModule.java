package com.mortgage.tool.config;

import com.google.inject.AbstractModule;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConfigResolver.class);
    }
}
