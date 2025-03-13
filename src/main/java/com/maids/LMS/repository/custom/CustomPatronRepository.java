package com.maids.LMS.repository.custom;

import com.maids.LMS.model.Patron;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CustomPatronRepository {
    long countPatronsByQuery(Query query);

    List<Patron> findPatronsByQuery(Query query, Pageable pageable);
}
