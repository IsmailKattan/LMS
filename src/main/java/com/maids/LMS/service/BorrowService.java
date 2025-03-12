package com.maids.LMS.service;

import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface BorrowService {
    ResponseEntity<ApiResponse> borrowBook(String bookId, String patronId);

    ResponseEntity<ApiResponse> returnBook(String bookId, String patronId);
}
