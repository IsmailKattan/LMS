package com.maids.LMS.service;

import com.maids.LMS.dto.BookDto;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<ApiResponse> getBooks(int page, int size);

    ResponseEntity<ApiResponse> getBook(String id);

    ResponseEntity<ApiResponse> addBook(BookDto bookDto);

    ResponseEntity<ApiResponse> updateBook(String id, BookDto bookDto);

    ResponseEntity<ApiResponse> deleteBook(String id);
}
