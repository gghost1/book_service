package ru.book_service.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.book_service.dto.create.CreateBook;
import ru.book_service.dto.filter.BookFilter;
import ru.book_service.dto.pagination.Pagination;
import ru.book_service.dto.update.UpdateBook;
import ru.book_service.exception.NotFoundException;
import ru.book_service.model.Book;
import ru.book_service.repository.BookRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Book add(CreateBook createBook) {
        return bookRepository.save(createBook.create());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Book update(UpdateBook updateBook) {
        Book book = bookRepository.findById(updateBook.id()).orElseThrow(() -> new NotFoundException("Book not found"));
        return bookRepository.save(updateBook.update(book));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(UUID id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        bookRepository.delete(book);
    }

    @Transactional(readOnly = true)
    public Book getById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Transactional(readOnly = true)
    public Page<Book> getAll(Pagination pagination, BookFilter filter) {
        Pageable pageable = pagination.toPageable();
        Specification<Book> specification = filter.toSpecification();

        return bookRepository.findAll(specification, pageable);
    }

}
