package com.ketul.avro.evolution;

import com.ketul.avro.schema.EmployeeV5;
import com.ketul.avro.schema.EmployeeV6;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NonCompatibleExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackwardCompatibleExample.class);

    public static void main(String[] args) {

        // Create employee record
        EmployeeV5 employeeV5 = getEmployee();

        LOGGER.info(employeeV5.toString());

        // Write to file
        File file = new File("target//employee-v5.avro");
        boolean isWritten = writeToFile(file, employeeV5);

        if(isWritten) {
            // Read from file
            List<EmployeeV6> employeeRecords = readFromFile(file);
            employeeRecords.forEach((employeeRecord -> LOGGER.info(employeeRecord.toString())));
        }
    }

    private static List<EmployeeV6> readFromFile(File file) {
        LOGGER.info("Reading from file {} using schema V6", file.getAbsolutePath());
        /*
         * Explicitly mention which schema you are going to use to read
         * EmployeeV6.class indicates its employeeV6.avsc
         *
         * Note : Changing enum is non compatible.
         * Here we have removed two departments from Department enum so it is non compatible.
         */
        DatumReader<EmployeeV6> datumReader = new SpecificDatumReader<>(EmployeeV6.class);
        List<EmployeeV6> employees = new ArrayList<>();
        try(DataFileReader<EmployeeV6> employeeReader = new DataFileReader<>(file, datumReader)) {
            while (employeeReader.hasNext()) {
                EmployeeV6 employeeRecord = employeeReader.next();
                employees.add(employeeRecord);
            }
        } catch (IOException e) {
            LOGGER.info("Exception occurred while reading employee data from file {} : ", file.getAbsolutePath(), e);
        }
        return employees;
    }

    private static boolean writeToFile(File file, EmployeeV5 employeeV4) {
        LOGGER.info("Writing from file {} using schema V4", file.getAbsolutePath());
        DatumWriter<EmployeeV5> datumWriter = new SpecificDatumWriter<>();
        try(DataFileWriter<EmployeeV5> employeeDataWriter = new DataFileWriter<>(datumWriter)) {
            employeeDataWriter.create(employeeV4.getSchema(), file);
            employeeDataWriter.append(employeeV4);
            LOGGER.info("Written to {}", file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            LOGGER.info("Exception occurred while writing employee data to file {} : ", file.getAbsolutePath(), e);
            return false;
        }
    }

    private static EmployeeV5 getEmployee(){
        EmployeeV5.Builder employeeBuilder = EmployeeV5.newBuilder();
        employeeBuilder.setEmployeeId("BK128746XT");
        employeeBuilder.setFistName("Shiva");
        employeeBuilder.setLastName("Kailasha");
        employeeBuilder.setAge(20);
        employeeBuilder.setSalary(100000.34f);
        List<String> hobbies = new ArrayList<>();
        hobbies.add("reading");
        hobbies.add("hiking");
        hobbies.add("travelling");
        employeeBuilder.setHobbies(hobbies);

        // This is for demonstration purpose only. It is not ideal to store sensitive information in avro.
        Map<String, String> secretQuestionsMap = new HashMap<>();
        secretQuestionsMap.put("What is your favourite colour ?", "black");
        secretQuestionsMap.put("What is your favourite city ?", "kashi");
        employeeBuilder.setSecretQuestions(secretQuestionsMap);
        return employeeBuilder.build();
    }
}
