package com.mortgage.tool.javalin;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mortgage.tool.mortgage.MortgageController;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Set;

@Slf4j
@Singleton
public class JavalinLauncher {
    private static final String API_VERSION = "0.1";
    private static final String API_DESCRIPTION = "Simple Mortgage API";
    private static final String API_PACKAGE = "com.mortgage.tool";

    private static final String REDOCS_URL = "/";
    private static final String SWAGGER_URL = "/swagger-ui";
    private static final String SWAGGER_DOCS_URL = "/swagger-docs";

    @Inject(optional = true)
    private Set<MortgageController> controllers = Collections.emptySet();

    public void listen(int port) {
        final var javalin = Javalin.create(this::getJavalinConfig);
        controllers.forEach(controller -> javalin.routes(controller.addRoutes(javalin)));
        javalin.start(port);
    }

    public JavalinConfig getJavalinConfig(JavalinConfig config) {
        config.registerPlugin(getOpenApiPlugin());
        config.defaultContentType = "application/json";
        return config;
    }

    private OpenApiPlugin getOpenApiPlugin() {
        final var apiInfo = new Info()
                .version(API_VERSION)
                .description(API_DESCRIPTION);

        final var apiOptions = new OpenApiOptions(apiInfo)
                    .activateAnnotationScanningFor(API_PACKAGE)
                    .swagger(new SwaggerOptions(SWAGGER_URL))
                    .reDoc(new ReDocOptions(REDOCS_URL))
                    .path(SWAGGER_DOCS_URL);

        return new OpenApiPlugin(apiOptions);
    }
}
