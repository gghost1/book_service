package ru.book_service.dto.filter;

public record YearFilter(
        String year,
        Operator operator
) {

    public enum Operator {
        BEFORE,
        AFTER,
        EQUAL
    }
}
