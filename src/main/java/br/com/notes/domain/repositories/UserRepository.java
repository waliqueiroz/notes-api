package br.com.notes.domain.repositories;

import br.com.notes.domain.entities.User;

public interface UserRepository {
    public User create(User user);
}
