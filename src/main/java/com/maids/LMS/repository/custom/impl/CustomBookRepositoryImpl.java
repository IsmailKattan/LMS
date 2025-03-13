package com.maids.LMS.repository.custom.impl;

import com.maids.LMS.model.Book;
import com.maids.LMS.repository.custom.CustomBookRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomBookRepositoryImpl implements CustomBookRepository {

    private final MongoTemplate mongoTemplate;

    public CustomBookRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Book> findBooksByCriteria(Query query) {
        return mongoTemplate.find(query, Book.class);
    }

    @Override
    public long countBooksByCriteria(Query query) {
        return mongoTemplate.count(query, Book.class);
    }
}
