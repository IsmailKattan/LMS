package com.maids.LMS.controller;


import com.maids.LMS.dto.BookDto;
import com.maids.LMS.service.BookService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse> getBooks(@RequestParam(required = false) int page, @RequestParam(required = false) int size) {
        return bookService.getBooks(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable String id, @RequestBody BookDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
    }

}
