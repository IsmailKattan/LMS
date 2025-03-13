package com.maids.LMS.service;

import com.maids.LMS.dto.BookDto;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ApiResponse getBook(String id);

    ApiResponse addBook(BookDto bookDto);

    ApiResponse updateBook(String id, BookDto bookDto);

    ApiResponse deleteBook(String id);

    ApiResponse getBooks(String title, String author, String category, String language, String publisher, String publicationYear, String borrowingCount, String status, String price, String maxPostponeCount, int page, int size);
}
