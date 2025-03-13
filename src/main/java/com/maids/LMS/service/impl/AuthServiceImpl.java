package com.maids.LMS.service.impl;

import com.maids.LMS.dto.LoginPatronRequest;
import com.maids.LMS.dto.RegisterPatronRequest;
import com.maids.LMS.repository.PatronRepository;
import com.maids.LMS.service.AuthService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public ResponseEntity<ApiResponse> register(RegisterPatronRequest patronDto) {
        // check if credentials are valid
        // create a new patron
        // save the patron
        // return the response

        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginPatronRequest loginDto) {
        return null;
    }
}
