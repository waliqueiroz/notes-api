package br.com.notes;

import br.com.notes.application.services.NoteService;
import br.com.notes.infrastructure.configurations.Configuration;
import br.com.notes.infrastructure.database.mysql.MySQLConnectionPool;
import br.com.notes.infrastructure.database.mysql.repositories.NoteRepository;
import br.com.notes.infrastructure.http.spark.controllers.NoteController;
import br.com.notes.infrastructure.http.spark.transformers.JsonTransformer;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

import static spark.Spark.*;

public class NotesApplication {
    public static void main(String[] args) {
        Configuration configuration = null;
        try {
            configuration = Configuration.load();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        DataSource dataSource = new MySQLConnectionPool(configuration).getDataSource();
        DSLContext ctx = DSL.using(dataSource, SQLDialect.MYSQL);

        NoteRepository noteRepository = new NoteRepository(ctx);
        NoteService noteService = new NoteService(noteRepository);
        NoteController noteController = new NoteController(noteService);

        port(8080);
        path("/api", () -> {
            before("/*", (request, response) -> response.type("application/json"));
            post("/notes", noteController::create, new JsonTransformer());
            get("/notes/:id", noteController::findById, new JsonTransformer());
        });
    }
}
