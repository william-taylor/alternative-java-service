package com.mortgage.tool.javalin;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public interface JavalinController {
    EndpointGroup addRoutes(Javalin app);
}
