package ru.book_service.dto.create;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.book_service.model.User;

public record CreateUser(
    String username,
    String password
) {

    public User create(PasswordEncoder passwordEncoder) {
        return new User(
                null,
                username,
                passwordEncoder.encode(password)
        );
    }
}
