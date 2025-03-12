package com.maids.LMS.service.impl;

import com.maids.LMS.service.BorrowService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Override
    public ResponseEntity<ApiResponse> borrowBook(String bookId, String patronId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> returnBook(String bookId, String patronId) {
        return null;
    }
}
