package com.mortgage.tool.javalin;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.javalin.Javalin;

import java.util.Collections;
import java.util.Set;

@Singleton
public class JavalinLauncher {

    @Inject(optional = true)
    private Set<JavalinController> controllers = Collections.emptySet();

    public void listen(int port) {
        final var javalin = Javalin.create();
        controllers.forEach(controllers -> controllers.bindRoutes(javalin));
        javalin.start(port);
    }
}
