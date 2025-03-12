package com.maids.LMS.service;

import com.maids.LMS.dto.PatronDto;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PatronService {
    ResponseEntity<ApiResponse> getPatrons(String page, String size);

    ResponseEntity<ApiResponse> getPatron(String id);

    ResponseEntity<ApiResponse> createPatron(PatronDto patronDto);

    ResponseEntity<ApiResponse> updatePatron(String id, PatronDto patronDto);

    ResponseEntity<ApiResponse> deletePatron(String id);
}
