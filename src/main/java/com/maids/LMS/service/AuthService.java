package com.maids.LMS.service;

import com.maids.LMS.dto.LoginPatronRequest;
import com.maids.LMS.dto.RegisterPatronRequest;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ApiResponse> register(RegisterPatronRequest book);

    ResponseEntity<ApiResponse> login(LoginPatronRequest loginDto);
}
