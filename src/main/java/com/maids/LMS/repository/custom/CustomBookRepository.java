package com.maids.LMS.repository.custom;

import com.maids.LMS.model.Book;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

public interface CustomBookRepository {
    List<Book> findBooksByCriteria(Query query);
    long countBooksByCriteria(Query query);
}
