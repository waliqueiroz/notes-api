package br.com.notes.infrastructure.database.mysql.repositories;

import br.com.notes.domain.entities.Note;

import static nu.studer.sample.Tables.NOTES;

import org.jooq.DSLContext;
import org.jooq.types.ULong;

public class NoteRepository implements br.com.notes.domain.repositories.NoteRepository {
    public DSLContext ctx;

    public NoteRepository(DSLContext ctx) {
        this.ctx = ctx;
    }

    public Note create(Note note) {
        return this.ctx.insertInto(NOTES, NOTES.TITLE, NOTES.CONTENT)
                .values(note.getTitle(), note.getContent())
                .returning()
                .fetchOne()
                .into(Note.class);
    }

    public Note findById(Long noteId) {
        return this.ctx.selectFrom(NOTES)
                .where(NOTES.ID.eq(ULong.valueOf(noteId)))
                .fetchOne()
                .into(Note.class);
    }
}
