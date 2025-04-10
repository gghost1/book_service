package ru.book_service.dto.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.book_service.exception.IncorrectInputException;
import ru.book_service.model.Book;

import java.time.LocalDate;

public record CreateBook(
    @NotNull(message = "vendorCode must not be null")
    @NotBlank(message = "vendorCode must not be blank")
    String vendorCode,
    @NotNull(message = "title must not be null")
    @NotBlank(message = "title must not be blank")
    String title,
    @NotNull(message = "year must not be null")
    @NotBlank(message = "year must not be blank")
    Integer year,
    @NotNull(message = "brand must not be null")
    @NotBlank(message = "brand must not be blank")
    String brand,
    @NotNull(message = "stock must not be null")
    @Min(value = 0, message = "stock must not be negative")
    Integer stock,
    @NotNull(message = "price must not be null")
    @Min(value = 0, message = "price must not be negative")
    Double price
) implements Creator<Book> {
    @Override
    public Book create() {
        if (!correctYear()) {
            throw new IncorrectInputException("Incorrect year");
        }
        return new Book(
                null,
                vendorCode(),
                title(),
                year(),
                brand(),
                stock(),
                price()
        );
    }

    private boolean correctYear() {
        return year > 0 && year < LocalDate.now().getYear();
    }
}
