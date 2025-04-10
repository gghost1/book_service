package ru.book_service.dto.update;

public interface Updator <T> {
    T update(T object);
}
