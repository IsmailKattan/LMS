package com.maids.LMS.model;

import com.maids.LMS.enums.BookStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    private String id;

    private String title;

    private String author;

    private String publisher;

    @Field("publication_year")
    private Integer publicationYear;

    private String isbn;

    private String language;

    private String category;

    private String location;

    @Field("borrowing_count")
    private Integer borrowingCount;

    private BookStatus status;

    @Field("borrowing_price")
    private Double borrowingPrice;

    @Field("added_date")
    private LocalDate addedDate;

    @Field("max_postpone_count")
    private Integer maxPostponeCount;
}
