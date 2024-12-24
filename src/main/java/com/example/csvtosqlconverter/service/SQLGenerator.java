package com.example.csvtosqlconverter.service;

import java.util.List;

public class SQLGenerator {

    // CREATE TABLE table_name (column1 dataType1, column2 dataType2, ...);
    public static String generateCreate(String tableName, List<String[]> csvData) {
        StringBuilder query = new StringBuilder("CREATE TABLE ");
        query.append(tableName)
                .append(" (");

        for(int row = 1; row < csvData.size(); row++) {
            query.append(csvData.get(row)[0])
                    .append(" ")
                    .append(csvData.get(row)[1]);
            if(row < csvData.size() - 1)
                query.append(", ");
        }

        query.append(");");

        return query.toString();
    }

    // INSERT INTO table_name (column1, column2, ...) VALUES (value1, value2, ...);
    public static String generateInsert(String tableName, List<String[]> csvData) {
        StringBuilder queryComplete = new StringBuilder();
        StringBuilder queryStart = new StringBuilder("INSERT INTO ");
        queryStart.append(tableName)
                .append(" (");

        for(int column = 0; column < csvData.getFirst().length; column++){
            queryStart.append(csvData.getFirst()[column]);
            if(column < csvData.getFirst().length - 1)
                queryStart.append(", ");
        }
        queryStart.append(") VALUES (");

        for(int row = 1; row < csvData.size(); row++) {
            StringBuilder queryRow = new StringBuilder(queryStart);

            for(int column = 0; column < csvData.get(row).length; column++) {
                queryRow.append("'")
                        .append(csvData.get(row)[column])
                        .append("'");
                if(column < csvData.get(row).length - 1)
                    queryRow.append(", ");
            }
            queryRow.append(");");

            queryComplete.append(queryRow)
                    .append("\n");
        }

        return queryComplete.toString();
    }

}
