package com.maids.LMS.service.impl;

import com.maids.LMS.dto.BookDto;
import com.maids.LMS.enums.BookStatus;
import com.maids.LMS.exception.Custom.ResourceNotFoundException;
import com.maids.LMS.model.Book;
import com.maids.LMS.repository.BookRepository;
import com.maids.LMS.service.BookService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ApiResponse getBook(String id) {
        // return book by id
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Book",
                        "id",
                        id,
                        "Book not found with id: " + id
                )
        );
        // create ApiResponse object and return it
        return ApiResponse.builder()
                .message("Book found")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(book)
                .success(true)
                .build();
    }


    @Override
    public ApiResponse addBook(BookDto bookDto) {
        // create book object from bookDto
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .category(bookDto.getCategory())
                .language(bookDto.getLanguage())
                .publisher(bookDto.getPublisher())
                .publicationYear(bookDto.getPublicationYear())
                .borrowingCount(0)
                .status(BookStatus.AVAILABLE)
                .borrowingPrice(bookDto.getBorrowingPrice())
                .maxPostponeCount(bookDto.getMaxPostponeCount())
                .addedDate(LocalDate.now())
                .isbn(bookDto.getIsbn())
                .location(bookDto.getLocation())
                .build();

        // save book in the database
        bookRepository.save(book);

        // create ApiResponse object and return it
        return ApiResponse.builder()
                .message("Book added successfully")
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .data(book)
                .success(true)
                .build();
    }

    @Override
    public ApiResponse updateBook(String id, BookDto bookDto) {
        // get book by id
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Book",
                        "id",
                        id,
                        "Book not found with id: " + id
                )
        );
        // update book object with the new values
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());
        book.setLanguage(bookDto.getLanguage());
        book.setPublisher(bookDto.getPublisher());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setBorrowingPrice(bookDto.getBorrowingPrice());
        book.setMaxPostponeCount(bookDto.getMaxPostponeCount());
        book.setIsbn(bookDto.getIsbn());
        book.setLocation(bookDto.getLocation());

        // save book in the database
        bookRepository.save(book);

        // create ApiResponse object and return it
        return ApiResponse.builder()
                .message("Book updated successfully")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(book)
                .success(true)
                .build();
    }

    @Override
    public ApiResponse deleteBook(String id) {
        // get book by id
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Book",
                        "id",
                        id,
                        "Book not found with id: " + id
                )
        );
        // delete book from the database
        bookRepository.delete(book);

        // create ApiResponse object and return it
        return ApiResponse.builder()
                .message("Book deleted successfully")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();
    }

    @Override
    public ApiResponse getBooks(
            String title,
            String author,
            String category,
            String language,
            String publisher,
            String publicationYear,
            String borrowingCount,
            String status,
            String price,
            String maxPostponeCount,
            int page,
            int size
    ) {
        List<String> titleList = title != null ? List.of(title.split(",")) : null;
        List<String> authorList = author != null ? List.of(author.split(",")) : null;
        List<String> categoryList = category != null ? List.of(category.split(",")) : null;
        List<String> languageList = language != null ? List.of(language.split(",")) : null;
        List<String> publisherList = publisher != null ? List.of(publisher.split(",")) : null;
        List<Integer> publicationYearList = publicationYear != null ? Stream.of(publicationYear.split(",")).map(Integer::parseInt).toList() : null;
        List<Integer> borrowingCountList = borrowingCount != null ? Stream.of(borrowingCount.split(",")).map(Integer::parseInt).toList() : null;
        // convert status to list of BookStatus if it is not null and be sure that all values are valid
        List<BookStatus> statusList = new ArrayList<>();
        if (status != null) {
            for (String statusStr : status.split(",")) {
                if (BookStatus.isValidStatusIgnoreCase(statusStr)) {
                    statusList.add(BookStatus.getStatusIgnoreCase(statusStr));
                }
            }
        }
        List<Double> priceList = price != null ? Stream.of(price.split(",")).map(Double::parseDouble).toList() : null;
        List<Integer> maxPostponeCountList = maxPostponeCount != null ? Stream.of(maxPostponeCount.split(",")).map(Integer::parseInt).toList() : null;

        // list to store all criteria
        List<Criteria> criteriaList = new ArrayList<>();

        // add all criteria to the list
        if (titleList != null && !titleList.isEmpty()) {
            List<Criteria> titleCriteria = titleList.stream()
                    .map(titleStr -> Criteria.where("title").regex(".*" + titleStr + ".*", "i"))
                    .toList();
            criteriaList.add(new Criteria().orOperator(titleCriteria.toArray(new Criteria[0])));
        }

        if (authorList != null && !authorList.isEmpty()) {
            List<Criteria> authorCriteria = authorList.stream()
                    .map(authorStr -> Criteria.where("author").regex(".*" + authorStr + ".*", "i"))
                    .toList();
            criteriaList.add(new Criteria().orOperator(authorCriteria.toArray(new Criteria[0])));
        }

        if (categoryList != null && !categoryList.isEmpty()) {
            List<Criteria> categoryCriteria = categoryList.stream()
                    .map(categoryStr -> Criteria.where("category").regex(".*" + categoryStr + ".*", "i"))
                    .toList();
            criteriaList.add(new Criteria().orOperator(categoryCriteria.toArray(new Criteria[0])));
        }

        if (languageList != null && !languageList.isEmpty()) {
            List<Criteria> languageCriteria = languageList.stream()
                    .map(languageStr -> Criteria.where("language").regex(".*" + languageStr + ".*", "i"))
                    .toList();
            criteriaList.add(new Criteria().orOperator(languageCriteria.toArray(new Criteria[0])));
        }

        if (publisherList != null && !publisherList.isEmpty()) {
            List<Criteria> publisherCriteria = publisherList.stream()
                    .map(publisherStr -> Criteria.where("publisher").regex(".*" + publisherStr + ".*", "i"))
                    .toList();
            criteriaList.add(new Criteria().orOperator(publisherCriteria.toArray(new Criteria[0])));
        }

        if (publicationYearList != null && !publicationYearList.isEmpty()) {
            List<Criteria> publicationYearCriteria = publicationYearList.stream()
                    .map(publicationYearInt -> Criteria.where("publication_year").is(publicationYearInt))
                    .toList();
            criteriaList.add(new Criteria().orOperator(publicationYearCriteria.toArray(new Criteria[0])));
        }

        if (borrowingCountList != null && !borrowingCountList.isEmpty()) {
            List<Criteria> borrowingCountCriteria = borrowingCountList.stream()
                    .map(borrowingCountInt -> Criteria.where("borrowing_count").is(borrowingCountInt))
                    .toList();
            criteriaList.add(new Criteria().orOperator(borrowingCountCriteria.toArray(new Criteria[0])));
        }

        if (!statusList.isEmpty()) {
            List<Criteria> statusCriteria = statusList.stream()
                    .map(statusObj -> Criteria.where("status").is(statusObj))
                    .toList();
            criteriaList.add(new Criteria().orOperator(statusCriteria.toArray(new Criteria[0])));
        }

        if (priceList != null && !priceList.isEmpty()) {
            List<Criteria> priceCriteria = priceList.stream()
                    .map(priceDouble -> Criteria.where("borrowing_price").is(priceDouble))
                    .toList();
            criteriaList.add(new Criteria().orOperator(priceCriteria.toArray(new Criteria[0])));
        }

        if (maxPostponeCountList != null && !maxPostponeCountList.isEmpty()) {
            List<Criteria> maxPostponeCountCriteria = maxPostponeCountList.stream()
                    .map(maxPostponeCountInt -> Criteria.where("max_postpone_count").is(maxPostponeCountInt))
                    .toList();
            criteriaList.add(new Criteria().orOperator(maxPostponeCountCriteria.toArray(new Criteria[0])));
        }

        // query to get books by criteria
        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        long totalCount = bookRepository.countBooksByCriteria(query);
        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        List<Book> books = bookRepository.findBooksByCriteria(query);



        // create ApiResponse object and return it
        return ApiResponse.builder()
                .message(books.isEmpty() ? "No books found" : "Found " + books.size() + " books")
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .page(page)
                .size(size)
                .totalPages((int) Math.ceil((double) totalCount / size))
                .total((int) totalCount)
                .data(books)
                .success(!books.isEmpty())
                .build();

    }

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

}
