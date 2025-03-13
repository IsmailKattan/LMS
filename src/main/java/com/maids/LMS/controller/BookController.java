package com.maids.LMS.controller;


import com.maids.LMS.dto.BookDto;
import com.maids.LMS.service.BookService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false, name = "publication-year") String publicationYear,
            @RequestParam(required = false, name = "borrowing-count") String borrowingCount,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String price,
            @RequestParam(required = false, name = "max-postpone-count") String maxPostponeCount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ) {
        return ResponseEntity.status(200).body(bookService.getBooks(title, author, category, language, publisher, publicationYear, borrowingCount, status, price, maxPostponeCount, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBook(@PathVariable String id) {
        return ResponseEntity.status(200).body(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.status(201).body(bookService.addBook(bookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable String id, @RequestBody BookDto bookDto) {
        return ResponseEntity.status(200).body(bookService.updateBook(id, bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable String id) {
        return ResponseEntity.status(200).body(bookService.deleteBook(id));
    }

}
