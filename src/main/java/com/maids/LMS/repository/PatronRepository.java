package com.maids.LMS.repository;

import com.maids.LMS.model.Patron;
import com.maids.LMS.repository.custom.CustomPatronRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatronRepository extends MongoRepository<Patron, String>, CustomPatronRepository {
    Optional<Patron> findByUsername(String username);

    boolean existsByUsername(String attr0);

    boolean existsByEmail(String email);
}
