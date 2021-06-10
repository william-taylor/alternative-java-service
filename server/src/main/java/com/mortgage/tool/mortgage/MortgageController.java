package com.mortgage.tool.mortgage;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mortgage.tool.javalin.JavalinController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import lombok.extern.slf4j.Slf4j;

import static io.javalin.apibuilder.ApiBuilder.*;

@Slf4j
@Singleton
public class MortgageController implements JavalinController {
    private static final String BASE_PATH = "/mortgage";
    private static final String ID_PARAM = ":id";

    @Inject
    private MortgageService service;

    @Override
    public String getBaseUrl() {
        return BASE_PATH;
    }

    @Override
    public void addRoutes(Javalin app) {
        path(ID_PARAM, () -> get(this::getMortgage));
        path(ID_PARAM, () -> delete(this::deleteMortgage));
        post(this::updateMortgage);
        put(this::createMortgage);
    }

    public void getMortgage(Context ctx) {
        final var id = ctx.pathParam(ID_PARAM);
        log.info("Searching for mortgage with id {}", id);
        service.getMortgage(ctx.pathParam(ID_PARAM))
                .map(mortgage -> ctx.json(mortgage))
                .orElseThrow(NotFoundResponse::new);
    }

    public void createMortgage(Context ctx) {
        ctx.status(200);
    }

    public void updateMortgage(Context ctx) {
        ctx.status(200);
    }

    public void deleteMortgage(Context ctx) {
        ctx.status(200);
    }
}
