package com.example.csvtosqlconverter;

import com.example.csvtosqlconverter.service.SQLFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SQLGeneratorTest {

    @Autowired
    SQLFileService sqlFileService;

    @Test
    public void shouldGenerateCreate() {
        String csvFilePath = "./backup-test/testdb/2024-12-17_16-13-26/customers_schema.csv";

        try {
            String sqlFilePath = sqlFileService.convertCsvToSQL(csvFilePath);
            assertTrue(new File(sqlFilePath).exists());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldGenerateInsert() {
        String csvFilePath = "./backup-test/testdb/2024-12-17_16-13-26/customers_data.csv";

        try {
            String sqlFilePath = sqlFileService.convertCsvToSQL(csvFilePath);
            assertTrue(new File(sqlFilePath).exists());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
