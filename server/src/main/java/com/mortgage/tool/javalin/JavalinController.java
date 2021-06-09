package com.mortgage.tool.javalin;

import io.javalin.Javalin;

public interface JavalinController {
    void bindRoutes(Javalin app);
}
