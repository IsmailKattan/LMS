package com.maids.LMS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatronResponseDto {
    private String id;
    private String username;
    private String email;
    @JsonProperty("phone-number")
    private String phoneNumber;
    private String name;
    private String surname;
}
