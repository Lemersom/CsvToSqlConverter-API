package com.example.csvtosqlconverter.controller;


import com.example.csvtosqlconverter.service.SQLConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convert")
public class SQLConverterController {

    private final SQLConverterService converterService;

    public SQLConverterController(SQLConverterService converterService) {
        this.converterService = converterService;
    }

    @PostMapping
    public ResponseEntity<String> convertCSVtoSQL(@RequestBody String csvFilePath) {
        try {
            String sqlFilePath = converterService.convertCSVToSQL(csvFilePath);
            return ResponseEntity.ok("File successfully converted: " + sqlFilePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to convert CSV file " + csvFilePath + " - Error: " + e.getMessage());
        }
    }

}
