package br.com.notes.domain.entities;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
