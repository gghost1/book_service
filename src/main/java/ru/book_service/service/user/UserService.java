package ru.book_service.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.book_service.dto.create.CreateUser;
import ru.book_service.model.User;
import ru.book_service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User add(CreateUser createUser) {
        return userRepository.save(createUser.create(passwordEncoder));
    }

}
