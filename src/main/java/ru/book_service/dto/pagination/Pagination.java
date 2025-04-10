package ru.book_service.dto.pagination;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record Pagination(
        @Parameter(name = "page", description = "Номер страницы", example = "0", required = true)
        int page,
        @Parameter(name = "size", description = "Размер страницы", example = "10", required = true)
        int size
) {

    public static Pagination NONE = new Pagination(0, Integer.MAX_VALUE);

    public Pageable toPageable() {
        return PageRequest.of(page, size, Sort.by("id").descending());
    }
}
