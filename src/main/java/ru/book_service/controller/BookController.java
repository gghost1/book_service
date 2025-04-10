package ru.book_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.book_service.dto.create.CreateBook;
import ru.book_service.dto.filter.BookFilter;
import ru.book_service.dto.pagination.Pagination;
import ru.book_service.dto.update.UpdateBook;
import ru.book_service.model.Book;
import ru.book_service.service.book.BookService;

import java.util.UUID;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String getAll(@Valid @ParameterObject BookFilter filter, @Valid @ParameterObject Pagination pagination, Model model) {
        Page<Book> books = bookService.getAll(pagination, filter);
        model.addAttribute("booksPage", books);
        model.addAttribute("title", filter.title().orElse(null));
        model.addAttribute("brand", filter.brand().orElse(null));
        model.addAttribute("year", filter.year().orElse(null));
        model.addAttribute("pageSize", pagination.size());
        return "books";
    }

    @GetMapping("/create")
    public String create() {
        return "create_book";
    }

    @PostMapping("/create")
    public String createBook(CreateBook createBook) {
        bookService.add(createBook);
        return "redirect:/books?page=0&size=10";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);

        return "edit_book";
    }

    @PostMapping("/edit")
    public String editBook(UpdateBook updateBook) {
        bookService.update(updateBook);
        return "redirect:/books?page=0&size=10";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable UUID id) {
        bookService.delete(id);
        return "redirect:/books?page=0&size=10";
    }

}
