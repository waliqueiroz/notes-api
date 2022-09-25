package br.com.notes.application.services;

import br.com.notes.domain.entities.Note;

public interface INoteService {
    Note create(Note note);
    Note findById(Long noteId);
}
