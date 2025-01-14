# CSV to SQL Converter API

This project is an API developed in Java Spring, designed to convert CSV files into SQL commands. It supports both data insertion and schema creation, depending on the naming pattern of the input CSV file. The generated SQL commands are saved locally in a `.sql` file.

# Technologies

* Back-End: Java with Spring Boot
* File Parsing: OpenCSV library
* Supported Formats: CSV for input, SQL for output

# Usage

* Provide the full file path of the CSV file to the endpoint `POST /api/convert`
  ```
  {
    "csvFilePath": "/path/to/csvfile.csv"
  }
  ```

* File Naming Patterns:
  * `*_data.csv`: Generates **INSERT** SQL commands.
  * `*_schema.csv`: Generates **CREATE TABLE** SQL commands.
  * Others: Generates **INSERT** SQL commands by default.

# CSV and output examples

* **Example 1:** Data File (customers_data.csv)
  
  CSV Content:
  ```
  customerid,name,birthdate,email,isactive
  1,Alice Johnson,1990-05-24,alice.j@example.com,true
  2,Bob Smith,1985-09-12,bob.smith@example.com,false
  3,Carlos Lopez,2001-03-15,carlos.lopez@example.com,true
  4,Diana Prince,1995-11-30,diana.prince@example.com,true
  ```
  Generated SQL:
  ```
  INSERT INTO customers (customerid, name, birthdate, email, isactive) VALUES ('1', 'Alice Johnson', '1990-05-24', 'alice.j@example.com', 'true');
  INSERT INTO customers (customerid, name, birthdate, email, isactive) VALUES ('2', 'Bob Smith', '1985-09-12', 'bob.smith@example.com', 'false');
  INSERT INTO customers (customerid, name, birthdate, email, isactive) VALUES ('3', 'Carlos Lopez', '2001-03-15', 'carlos.lopez@example.com', 'true');
  INSERT INTO customers (customerid, name, birthdate, email, isactive) VALUES ('4', 'Diana Prince', '1995-11-30', 'diana.prince@example.com', 'true');
  ```

* **Example 2:** Schema File (customers_schema.csv)
  
  CSV Content:
  ```
  column_name,data_type
  customerid,int4
  name,varchar
  birthdate,date
  email,varchar
  isactive,bool
  ```
  Generated SQL:
  ```
  CREATE TABLE customers (customerid int4, name varchar, birthdate date, email varchar, isactive bool);
  ```
