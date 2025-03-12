package com.maids.LMS.repository;

import com.maids.LMS.model.BorrowingRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowingRecordRepository extends MongoRepository<BorrowingRecord, String> {
}
