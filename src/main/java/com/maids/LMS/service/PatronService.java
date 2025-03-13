package com.maids.LMS.service;

import com.maids.LMS.dto.PatronDto;
import com.maids.LMS.model.Patron;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PatronService {

    ApiResponse getPatron(String id);

    ApiResponse createPatron(PatronDto patronDto);

    ApiResponse updatePatron(String id, PatronDto patronDto);

    ApiResponse deletePatron(String id);

    ApiResponse getPatrons(String username, String email, String phone, String name, String surname, int page, int size);

    Patron getPatronById(String patronId);
}
