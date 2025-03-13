package com.maids.LMS.service;

import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface BorrowService {
    ApiResponse borrowBook(String bookId, String patronId);

    ApiResponse returnBook(String bookId, String patronId);

    ApiResponse endOfDay();
}
