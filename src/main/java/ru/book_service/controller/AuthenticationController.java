package ru.book_service.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.book_service.dto.create.CreateUser;
import ru.book_service.model.User;
import ru.book_service.service.security.AuthenticationService;
import ru.book_service.service.user.UserService;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("createUser", new CreateUser("", ""));
        return "registration";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String register(CreateUser createUser, HttpServletResponse response) {
        User user = userService.add(createUser);
        authenticationService.authenticate(createUser.username(), createUser.password(), response);
        return "redirect:/books?page=0&size=10";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(CreateUser createUser, HttpServletResponse response) {
        authenticationService.authenticate(createUser.username(), createUser.password(), response);
        return "redirect:/books?page=0&size=10";
    }

}
