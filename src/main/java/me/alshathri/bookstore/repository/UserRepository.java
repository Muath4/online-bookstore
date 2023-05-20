package me.alshathri.bookstore.repository;


import java.util.Optional;

import me.alshathri.bookstore.model.User;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUsername(String username);
}