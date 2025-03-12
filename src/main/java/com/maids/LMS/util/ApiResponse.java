package com.maids.LMS.util;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private int statusCode;
    private String status;
    private String message;
    private LocalDateTime timestamp;
    private int page;
    private int size;
    private int total;
    private int totalPages;
    private Object data;
}
