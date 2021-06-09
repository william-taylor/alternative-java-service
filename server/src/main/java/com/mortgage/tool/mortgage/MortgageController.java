package com.mortgage.tool.mortgage;

import com.google.inject.Singleton;
import com.mortgage.tool.javalin.JavalinController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

@Singleton
public class MortgageController implements JavalinController {
    @Override
    public void bindRoutes(Javalin app) {
        app.routes(() -> path("/", () -> get(ctx -> ctx.result("Hello, World"))));
    }
}
