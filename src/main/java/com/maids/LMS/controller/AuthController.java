package com.maids.LMS.controller;

import com.maids.LMS.dto.LoginPatronRequest;
import com.maids.LMS.dto.RegisterPatronRequest;
import com.maids.LMS.service.AuthService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerBook(@RequestBody RegisterPatronRequest book) {
        return authService.register(book);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginPatronRequest loginDto) {
        return authService.login(loginDto);
    }

}
