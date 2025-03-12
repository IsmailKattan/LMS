package com.maids.LMS.service.impl;

import com.maids.LMS.dto.BookDto;
import com.maids.LMS.service.BookService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public ResponseEntity<ApiResponse> getBooks(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> getBook(String id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> addBook(BookDto bookDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> updateBook(String id, BookDto bookDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> deleteBook(String id) {
        return null;
    }
}
