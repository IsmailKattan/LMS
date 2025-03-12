package com.maids.LMS.service.impl;

import com.maids.LMS.dto.PatronDto;
import com.maids.LMS.service.PatronService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatronServiceImpl implements PatronService {

    @Override
    public ResponseEntity<ApiResponse> getPatrons(String page, String size) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> getPatron(String id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> createPatron(PatronDto patronDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> updatePatron(String id, PatronDto patronDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> deletePatron(String id) {
        return null;
    }
}
