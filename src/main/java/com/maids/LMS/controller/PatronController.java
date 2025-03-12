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
    public ResponseEntity<ApiResponse> getPatrons(@RequestParam(required = false) String page, @RequestParam(required = false) String size){
        return patronService.getPatrons(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPatron(@PathVariable String id){
        return patronService.getPatron(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPatron(@RequestBody PatronDto patronDto){
        return patronService.createPatron(patronDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePatron(@PathVariable String id, @RequestBody PatronDto patronDto){
        return patronService.updatePatron(id, patronDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePatron(@PathVariable String id){
        return patronService.deletePatron(id);
    }
}
