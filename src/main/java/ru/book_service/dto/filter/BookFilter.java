package ru.book_service.dto.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springdoc.core.annotations.ParameterObject;
import ru.book_service.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record BookFilter(
        Optional<String> title,
        Optional<String> brand,
        @ParameterObject
        Optional<YearFilter> year
) implements Filter<Book> {

    @Override
    public Predicate criteria(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        title.ifPresent(t -> predicates.add(builder.like(root.get("title"), "%" + t + "%")));
        brand.ifPresent(b -> predicates.add(builder.like(root.get("brand"), "%" + b + "%")));
        year.ifPresent(y -> predicates.add(
                switch (y.operator()) {
                    case BEFORE -> builder.lessThan(root.get("year"), y.year());
                    case AFTER -> builder.greaterThan(root.get("year"), y.year());
                    case EQUAL -> builder.equal(root.get("year"), y.year());
                }));
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
