package com.maids.LMS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private String title;
    private String author;
    private String publisher;
    @JsonProperty("publication_year")
    private Integer publicationYear;
    private String isbn;
    private String language;
    private String category;
    private String location;
    @JsonProperty("borrowing_price")
    private double borrowingPrice;
    @JsonProperty("max_postpone_count")
    private int maxPostponeCount;
}
