package com.maids.LMS.repository;

import com.maids.LMS.model.Book;
import com.maids.LMS.model.BorrowingRecord;
import com.maids.LMS.model.Patron;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends MongoRepository<BorrowingRecord, String> {
    Optional<BorrowingRecord> findByBookAndPatronAndReturned(Book book, Patron patron, boolean returned);
}
