package com.ketul.avro.generic;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.*;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericRecordExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericRecordExample.class);

    public static void main(String[] args) {

        // Create a schema
        Schema employeeSchema = getSchema();

        // Create a generic record
        GenericData.Record employee = getEmployee(employeeSchema);
        LOGGER.info(employee.toString());

        // Write generic record to a file
        File file = new File("target//generic-employee.avro");
        boolean isWritten = writeToFile(file, employee);

        if(isWritten) {
            // Read from file
            List<GenericRecord> employeeRecords = readFromFile(file);
            employeeRecords.forEach((employeeRecord -> LOGGER.info(employeeRecord.toString())));
        }

    }

    private static List<GenericRecord> readFromFile(File file) {
        LOGGER.info("Reading from file {}", file.getAbsolutePath());
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        List<GenericRecord> employees = new ArrayList<>();
        try(DataFileReader<GenericRecord> employeeReader = new DataFileReader<GenericRecord>(file, datumReader)) {
            while (employeeReader.hasNext()) {
                GenericRecord employeeRecord = employeeReader.next();
                employees.add(employeeRecord);
            }
        } catch (IOException e) {
            LOGGER.info("Exception occurred while reading employee data from file {} : ", file.getAbsolutePath(), e);
        }
        return employees;
    }

    private static boolean writeToFile(File file, GenericData.Record employee) {
        LOGGER.info("Writing from file {}", file.getAbsolutePath());
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>();
        try(DataFileWriter<GenericRecord> employeeDataWriter = new DataFileWriter<>(datumWriter)) {
            employeeDataWriter.create(employee.getSchema(), file);
            employeeDataWriter.append(employee);
            LOGGER.info("Written to {}", file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            LOGGER.info("Exception occurred while writing employee data to file {} : ", file.getAbsolutePath(), e);
            return false;
        }
    }

    private static GenericData.Record getEmployee(Schema employeeSchema) {
        GenericRecordBuilder employeeBuilder = new GenericRecordBuilder(employeeSchema);
        employeeBuilder.set("employee_id", "X12879AZ");
        employeeBuilder.set("fist_name", "Ketul");
        employeeBuilder.set("last_name", "Patel");
        employeeBuilder.set("age", 25);
        employeeBuilder.set("salary", 12000.50f);
        return employeeBuilder.build();
    }

    private static Schema getSchema() {
        Schema.Parser parser = new Schema.Parser();

        return parser.parse("{\n" +
                "    \"type\" : \"record\",\n" +
                "    \"namespace\" : \"com.ketul.avro.schema\",\n" +
                "    \"name\" : \"Employee\",\n" +
                "    \"doc\" : \"This is Avro schema for Employee\",\n" +
                "    \"fields\" : [\n" +
                "        {\n" +
                "            \"name\": \"employee_id\",\n" +
                "            \"type\": \"string\",\n" +
                "            \"doc\": \"Employee Id\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"fist_name\",\n" +
                "            \"type\": \"string\",\n" +
                "            \"doc\": \"First name of the employee\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"last_name\",\n" +
                "            \"type\": \"string\",\n" +
                "            \"doc\": \"Last name of the employee\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"age\",\n" +
                "            \"type\": \"int\",\n" +
                "            \"doc\": \"Age of the employee\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"salary\",\n" +
                "            \"type\": \"float\",\n" +
                "            \"doc\": \"Salary\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"is_permanent\",\n" +
                "            \"type\": \"boolean\",\n" +
                "            \"default\": true,\n" +
                "            \"doc\": \"Is employee permanent ?\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
    }
}
