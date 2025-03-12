package com.maids.LMS.controller;

import com.maids.LMS.service.BorrowService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BorrowRecordController {


    @Autowired
    private BorrowService borrowService;

    @PutMapping("/api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<ApiResponse> borrowBook(@PathVariable String  bookId,@PathVariable String patronId) {
        return borrowService.borrowBook(bookId, patronId);
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<ApiResponse> returnBook(@PathVariable String bookId,@PathVariable String patronId) {
        return borrowService.returnBook(bookId, patronId);
    }
}
