package com.maids.LMS.service.impl;

import com.maids.LMS.dto.LoginPatronRequest;
import com.maids.LMS.dto.RegisterPatronRequest;
import com.maids.LMS.service.AuthService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Override
    public ResponseEntity<ApiResponse> register(RegisterPatronRequest book) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginPatronRequest loginDto) {
        return null;
    }
}
