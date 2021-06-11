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

import java.util.Collections;
import java.util.Set;

@Singleton
public class JavalinLauncher {

    @Inject(optional = true)
    private Set<MortgageController> controllers = Collections.emptySet();

    public void listen(int port) {
        final var javalin = Javalin.create(this::getJavalinConfig);
        controllers.forEach(controller -> javalin.routes(controller.addRoutes(javalin)));
        javalin.start(port);
    }

    private JavalinConfig getJavalinConfig(JavalinConfig config) {
        config.registerPlugin(getOpenApiPlugin());
        config.defaultContentType = "application/json";
        return config;
    }

    private OpenApiPlugin getOpenApiPlugin() {
        final var apiInfo = new Info().version("0.1").description("Mortgage API");
        final var apiOptions = new OpenApiOptions(apiInfo)
                    .activateAnnotationScanningFor("com.mortgage.tool")
                    .swagger(new SwaggerOptions("/"))
                    .reDoc(new ReDocOptions("/docs"))
                    .path("/swagger-docs");
        return new OpenApiPlugin(apiOptions);
    }
}
