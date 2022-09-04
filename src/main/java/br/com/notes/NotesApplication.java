package br.com.notes;

import br.com.notes.infrastructure.http.spark.controllers.UserController;
import br.com.notes.infrastructure.http.spark.transformers.JsonTransformer;

import static spark.Spark.*;

public class NotesApplication {
    public static void main(String[] args) {
        UserController userController = new UserController();

        port(8080);
        path("/api", () -> {
            before("/*", (request, response) -> response.type("application/json"));
            get("/hello", userController::create, new JsonTransformer());
        });
    }
}
