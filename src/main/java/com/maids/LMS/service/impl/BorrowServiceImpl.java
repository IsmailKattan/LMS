package com.maids.LMS.service.impl;

import com.maids.LMS.enums.BookStatus;
import com.maids.LMS.exception.Custom.InvalidOperationException;
import com.maids.LMS.exception.Custom.ResourceNotFoundException;
import com.maids.LMS.model.Book;
import com.maids.LMS.model.BorrowingRecord;
import com.maids.LMS.model.Patron;
import com.maids.LMS.repository.BorrowingRecordRepository;
import com.maids.LMS.service.BookService;
import com.maids.LMS.service.BorrowService;
import com.maids.LMS.service.PatronService;
import com.maids.LMS.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class BorrowServiceImpl implements BorrowService {

    @Value("${lms.borrowing.limit}")
    private int borrowingLimit;

    @Value("${lms.borrowing.duration}")
    private int borrowingDuration;

    @Value("${lms.borrowing.delay.rate}")
    private double delayFineRate;

    @Value("${lms.borrowing.delay.fine}")
    private double delayFine;

    private final BookService bookService;


    private final PatronService patronService;


    private final BorrowingRecordRepository borrowingRecordRepository;

    public BorrowServiceImpl(BookService bookService, PatronService patronService, BorrowingRecordRepository borrowingRecordRepository) {
        this.bookService = bookService;
        this.patronService = patronService;
        this.borrowingRecordRepository = borrowingRecordRepository;
    }


    @Transactional
    @Override
    public ApiResponse borrowBook(String bookId, String patronId) {
        // 1. get the book by id from book service
        Book book = bookService.getBookById(bookId);
        // 2. get the patron by id from patron service
        Patron patron = patronService.getPatronById(patronId);
        // 3. check if the book is available
        if (book == null) {
            throw new ResourceNotFoundException(
                    "Book",
                    "id",
                    bookId,
                    "Book not found"
            );
        }
        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new InvalidOperationException(
                    "Book borrowing",
                    "Book is not available for borrowing"
            );
        }
        // 4. check if the patron is eligible to borrow
        if (patron == null) {
            throw new ResourceNotFoundException(
                    "Patron",
                    "id",
                    patronId,
                    "Patron not found"
            );
        }
        if (patron.getBorrowingRecords().size()>borrowingLimit) {
            throw new InvalidOperationException(
                    "Book borrowing",
                    "Patron has reached the borrowing limit"
            );
        }
        // 5. create a new borrow record
        BorrowingRecord record = BorrowingRecord.builder()
                .book(book)
                .patron(patron)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(borrowingDuration))
                .extendedDate(LocalDate.now().plusDays(borrowingDuration))
                .isReturned(false)
                .isExtended(false)
                .delayFine(0)
                .borrowingPrice(book.getBorrowingPrice())
                .postponeCount(0)
                .build();
        // 6. save the borrow record
        borrowingRecordRepository.save(record);
        // 7. return the response
        return ApiResponse.builder()
                .message("Book borrowed successfully")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(record)
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Transactional
    @Override
    public ApiResponse returnBook(String bookId, String patronId) {
        // 1. get the book by id from book service
        Book book = bookService.getBookById(bookId);
        // 2. get the patron by id from patron service
        Patron patron = patronService.getPatronById(patronId);
        // 3. check if the book is borrowed by the patron
        if (book == null) {
            throw new ResourceNotFoundException(
                    "Book",
                    "id",
                    bookId,
                    "Book not found"
            );
        }
        if (patron == null) {
            throw new ResourceNotFoundException(
                    "Patron",
                    "id",
                    patronId,
                    "Patron not found"
            );
        }
        BorrowingRecord record = borrowingRecordRepository.findByBookAndPatronAndReturned(book, patron, false)
                .orElseThrow(() -> new InvalidOperationException(
                        "Book returning",
                        "Book is not borrowed by the patron"
                ));
        if (record.getDelayFine() != 0) {
            throw new InvalidOperationException(
                    "Book returning",
                    "Patron has delay fine"
            );
        }
        // 4. update the borrow record
        record.setReturned(true);
        record.setReturnDate(LocalDate.now());
        // 5. save the borrow record
        borrowingRecordRepository.save(record);
        // 6. return the response
        return ApiResponse.builder()
                .message("Book returned successfully")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .data(record)
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse endOfDay() {
        // 1. get all the borrowing records
        List<BorrowingRecord> records = borrowingRecordRepository.findAll();
        // 2. check if the return date is after the borrow date
        for (BorrowingRecord record : records) {
            if (record.getExtendedDate().isAfter(LocalDate.now()) && !record.isReturned()) {
                if (record.getBorrowingPrice() > 0) {
                    // calculate how many days the book is borrowed
                    int days = record.getReturnDate().getDayOfYear() - record.getBorrowDate().getDayOfYear();
                    double price = record.getBorrowingPrice() / days;
                    // calculate how many days the book is delayed
                    int delay = LocalDate.now().getDayOfYear() - record.getReturnDate().getDayOfYear();
                    // calculate the delay fine
                    double fine = price * delay * (1 + delayFineRate);
                    record.setDelayFine(fine);
                } else {
                    // calculate how many days the book is delayed
                    int delay = LocalDate.now().getDayOfYear() - record.getReturnDate().getDayOfYear();
                    // calculate the delay fine
                    double fine = delay * delayFine;
                    record.setDelayFine(fine);
                }
            }
        }
        // 4. update the borrow record
        borrowingRecordRepository.saveAll(records);
        // 6. return the response
        return ApiResponse.builder()
                .message("End of day process completed successfully")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
