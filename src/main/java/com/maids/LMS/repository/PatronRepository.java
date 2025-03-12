package com.maids.LMS.repository;

import com.maids.LMS.model.Patron;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatronRepository extends MongoRepository<Patron, String> {
    Optional<Patron> findByUsername(String username);
}
