package com.mortgage.tool.javalin;

import io.javalin.Javalin;

public interface JavalinController {
    String getBaseUrl();
    void addRoutes(Javalin app);
}
