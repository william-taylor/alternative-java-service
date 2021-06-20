package com.mortgage.tool.javalin;

import io.javalin.core.JavalinConfig;
import io.javalin.plugin.openapi.OpenApiPlugin;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JavalinLauncherTest {

    @Test
    public void testJavalinConfigSetup() {
        final var launcher = new JavalinLauncher();
        final var config = launcher.getJavalinConfig( new JavalinConfig());

        assertThat(config.defaultContentType, is("application/json"));
        assertThat(config.getPlugin(OpenApiPlugin.class), notNullValue());
    }
}
