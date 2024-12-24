package com.example.csvtosqlconverter.service;

import com.example.csvtosqlconverter.util.CsvUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SQLFileService {

    @Value("${sql.directory}")
    private String sqlDirectory;

    public String convertCsvToSQL(String csvFilePath) {
        int lastSlashIndex = csvFilePath.lastIndexOf('/');
        String csvFileName = csvFilePath.substring(lastSlashIndex + 1);
        String pathWithoutCsvFile = csvFilePath.substring(0, lastSlashIndex + 1);
        String csvFileWithoutExtension = csvFileName.split("\\.")[0];

        String query = "";
        String tableName = "";

        if(csvFileName.contains("_")) {
            tableName = csvFileName.split("_")[0];
        } else {
            tableName = csvFileName;
        }

        if(csvFileName.contains("_") && csvFileName.contains("schema")) {
            query = handleCreateGenerator(csvFilePath, tableName);  // SQL CREATE file
        } else {
            query = handleInsertGenerator(csvFilePath, tableName);  // SQL INSERT file
        }

        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
        String sqlConvertedDirectory = sqlDirectory + File.separator + pathWithoutCsvFile;
        String sqlFilePath = sqlConvertedDirectory + csvFileWithoutExtension + "_" + date + ".sql";

        try {
            Files.createDirectories(Paths.get(sqlConvertedDirectory));

            FileWriter fw = new FileWriter(sqlFilePath);
            fw.write(query);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sqlFilePath;
    }

    private String handleCreateGenerator(String csvFilePath, String tableName) {
        try {
            List<String[]> csvData = CsvUtil.readFromCsv(csvFilePath);

            return SQLGenerator.generateCreate(tableName, csvData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String handleInsertGenerator(String csvFilePath, String tableName) {
        try {
            List<String[]> csvData = CsvUtil.readFromCsv(csvFilePath);

            return SQLGenerator.generateInsert(tableName, csvData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
