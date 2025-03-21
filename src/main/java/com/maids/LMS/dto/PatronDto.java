package com.maids.LMS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatronDto {
    private String username;
    private String surname;
    private String name;
    private String email;
    private String password;
    @JsonProperty("phone-number")
    private String phoneNumber;
}
