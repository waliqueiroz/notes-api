package br.com.notes.infrastructure.http.spark.controllers;

import br.com.notes.application.services.INoteService;
import br.com.notes.domain.entities.Note;
import br.com.notes.infrastructure.converter.gson.GsonConverter;
import spark.Request;
import spark.Response;

public class NoteController {

    private final INoteService noteService;

    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    public Note create(Request request, Response response) {
        Note note = GsonConverter.fromJson(request.body(), Note.class);
        return this.noteService.create(note);
    }

    public Note findById(Request request, Response response) {
        Long noteId = Long.parseLong(request.params(":id"));
        return this.noteService.findById(noteId);
    }
}
