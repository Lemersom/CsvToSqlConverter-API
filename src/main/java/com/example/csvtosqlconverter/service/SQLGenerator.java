package com.example.csvtosqlconverter.service;

import java.util.List;

public class SQLGenerator {

    public static String generateCreate(String tableName, List<String[]> csvData) {

        StringBuilder query = new StringBuilder("CREATE TABLE ");
        query.append(tableName).append("(");

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

}
