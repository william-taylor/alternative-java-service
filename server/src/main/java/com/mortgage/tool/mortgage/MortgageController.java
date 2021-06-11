package com.mortgage.tool.mortgage;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mortgage.tool.javalin.JavalinController;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;
import lombok.extern.slf4j.Slf4j;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

@Slf4j
@Singleton
public class MortgageController implements JavalinController {
    private static final String BASE_PATH = "/mortgage";

    @Inject
    private MortgageService service;

    @Override
    public EndpointGroup addRoutes(Javalin app) {
        return () -> path(BASE_PATH, () -> post(this::calculateInterest));
    }

    @OpenApi(
        method = HttpMethod.POST,
        path = "/mortgage",
        summary = "Calculates interest for a mortgage",
        operationId = "calculateInterest",
        tags = "Mortgage",
        requestBody = @OpenApiRequestBody(content = @OpenApiContent(from = Mortgage.class)),
        responses = @OpenApiResponse(status = "200", content = @OpenApiContent(from = MortgageInterest.class))
    )
    public void calculateInterest(Context ctx) {
        final var body = ctx.bodyAsClass(Mortgage.class);
        log.info("Calculating interest for {}", body);
        ctx.json(service.calculateInterest(body));
    }
}
