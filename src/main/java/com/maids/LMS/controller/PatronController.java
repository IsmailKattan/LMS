package com.maids.LMS.controller;

import com.maids.LMS.dto.PatronDto;
import com.maids.LMS.model.Patron;
import com.maids.LMS.service.PatronService;
import com.maids.LMS.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {


    @Autowired
    private PatronService patronService;

    @GetMapping
    public ResponseEntity<ApiResponse> getPatrons(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(patronService.getPatrons(username,email,phone,name,surname,page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPatron(@PathVariable String id){
        return ResponseEntity.ok(patronService.getPatron(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPatron(@RequestBody PatronDto patronDto){
        return ResponseEntity.status(201).body(patronService.createPatron(patronDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePatron(@PathVariable String id, @RequestBody PatronDto patronDto){
        return ResponseEntity.ok(patronService.updatePatron(id,patronDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePatron(@PathVariable String id){
        return ResponseEntity.ok(patronService.deletePatron(id));
    }
}
