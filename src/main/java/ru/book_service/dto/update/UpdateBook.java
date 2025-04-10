package ru.book_service.dto.update;

import ru.book_service.model.Book;

import java.util.Optional;
import java.util.UUID;

public record UpdateBook(
        UUID id,
        Optional<String> vendorCode,
        Optional<String> title,
        Optional<Integer> year,
        Optional<String> brand,
        Optional<Integer> stock,
        Optional<Double> price
) implements Updator<Book> {

    @Override
    public Book update(Book object) {
        return new Book(
                object.getId(),
                vendorCode.orElse(object.getVendorCode()),
                title.orElse(object.getTitle()),
                year.orElse(object.getYear()),
                brand.orElse(object.getBrand()),
                stock.orElse(object.getStock()),
                price.orElse(object.getPrice())
        );
    }
}
