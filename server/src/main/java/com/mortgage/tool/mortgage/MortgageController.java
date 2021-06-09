package com.mortgage.tool.mortgage;

import com.google.inject.Singleton;
import com.mortgage.tool.javalin.JavalinController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

@Singleton
public class MortgageController implements JavalinController {
    private static final String API_PATH = "/mortgage";

    @Override
    public void bindRoutes(Javalin app) {
        app.routes(() -> path(API_PATH, () -> get(ctx -> ctx.result("Dummy Response"))));
    }
}
