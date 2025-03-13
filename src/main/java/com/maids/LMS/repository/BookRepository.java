package com.maids.LMS.repository;

import com.maids.LMS.model.Book;
import com.maids.LMS.repository.custom.CustomBookRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends MongoRepository<Book, String>, CustomBookRepository {

}
