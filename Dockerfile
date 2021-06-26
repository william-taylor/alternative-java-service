FROM openjdk:16-alpine3.13

WORKDIR /app

COPY target/mortgage-tool-0.1-jar-with-dependencies.jar ./server.jar

CMD ["java", "-cp", "server.jar", "com.mortgage.tool.AppModule"]