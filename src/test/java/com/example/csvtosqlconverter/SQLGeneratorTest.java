package com.example.csvtosqlconverter;

import com.example.csvtosqlconverter.service.SQLGenerator;
import com.example.csvtosqlconverter.util.CsvUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SQLGeneratorTest {

    @Test
    public void shouldGenerateCreate() {
        String csvFilePath = "./backup-test/testdb/2024-12-17_16-13-26/customers_schema.csv";

        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
        String sqlFileName = "customers-" + date + ".sql";
        File sqlFile = new File("./sql-test/" + sqlFileName);

        try {
            List<String[]> csvData = CsvUtil.readFromCsv(csvFilePath);

            String query = SQLGenerator.generateCreate("customers", csvData);

            FileWriter fw = new FileWriter(sqlFile);
            fw.write(query);
            fw.close();

            assertTrue(sqlFile.exists());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
