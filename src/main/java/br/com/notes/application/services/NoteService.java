package br.com.notes.application.services;

import br.com.notes.domain.entities.Note;
import br.com.notes.domain.repositories.NoteRepository;

public class NoteService implements INoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note create(Note note) {
        return this.noteRepository.create(note);
    }

    public Note findById(Long noteId) {
        return this.noteRepository.findById(noteId);
    }
}
