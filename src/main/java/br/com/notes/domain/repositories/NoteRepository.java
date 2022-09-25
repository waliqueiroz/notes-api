package br.com.notes.domain.repositories;

import br.com.notes.domain.entities.Note;

public interface NoteRepository {
    Note create(Note note);
    Note findById(Long noteId);
}
