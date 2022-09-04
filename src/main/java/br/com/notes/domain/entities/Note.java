package br.com.notes.domain.entities;

import java.time.LocalDateTime;

public class Note {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
