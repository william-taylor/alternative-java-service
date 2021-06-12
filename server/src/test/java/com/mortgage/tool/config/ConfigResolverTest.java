package com.mortgage.tool.config;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ConfigResolverTest {

    @Test
    public void testResolveConfig() {
        final var resolver = new ConfigResolver();
        final var config = resolver.resolve();
        assertThat(config.getPort(), is(8000));
    }
}
