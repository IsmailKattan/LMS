package com.maids.LMS.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "borrowing_records")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowingRecord {
    @Id
    private String id;

    @DBRef(lazy = true)
    private Book book;

    @DBRef(lazy = true)
    private Patron patron;

    @Field("borrow_date")
    private LocalDate borrowDate;

    @Field("return_date")
    private LocalDate returnDate;

    @Field("is_returned")
    private boolean isReturned;

    @Field("is_extended")
    private boolean isExtended;

    @Field("extended_date")
    private LocalDate extendedDate;

    @Field("delay_fine")
    private double delayFine;

    @Field("borrowing_price")
    private double borrowingPrice;

    @Field("postpone_count")
    private Integer postponeCount;
}
