package ru.book_service.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.book_service.model.User;
import ru.book_service.model.UserEntityDetails;
import ru.book_service.repository.UserRepository;


import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserEntityDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserById(UUID userId) throws UsernameNotFoundException {
        Optional<User> userEntityOpt = userRepository.findById(userId);
        if (userEntityOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserEntityDetails(userEntityOpt.get());
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userEntityOpt = userRepository.findByName(name);
        if (userEntityOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserEntityDetails(userEntityOpt.get());
    }
}
