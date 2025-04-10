package ru.book_service.dto.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public interface Filter<T> {
    Predicate criteria(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder);

    default Specification<T> toSpecification() {
        return this::criteria;
    }
}

