package ru.book_service.dto.pagination;

import java.util.List;

public record PaginatedList <T> (
        long total,
        long page,
        long size,
        List<T> items
) {
}
