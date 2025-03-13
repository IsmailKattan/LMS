package com.maids.LMS.repository.custom.impl;

import com.maids.LMS.model.Patron;
import com.maids.LMS.repository.custom.CustomPatronRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomPatronRepositoryImpl implements CustomPatronRepository {

    private final MongoTemplate mongoTemplate;

    public CustomPatronRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public long countPatronsByQuery(Query query) {
        return mongoTemplate.count(query, Patron.class);
    }

    @Override
    public List<Patron> findPatronsByQuery(Query query, Pageable pageable) {
        return mongoTemplate.find(query, Patron.class);
    }
}
