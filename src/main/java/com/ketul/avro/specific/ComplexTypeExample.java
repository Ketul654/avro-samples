package com.ketul.avro.specific;

import com.ketul.avro.schema.Department;
import com.ketul.avro.schema.EmployeeV5;
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

public class ComplexTypeExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComplexTypeExample.class);

    public static void main(String[] args) {

        // Create employee record
        EmployeeV5 employee = getEmployee();

        LOGGER.info(employee.toString());

        // Write to file
        File file = new File("target//complex-employee.avro");
        boolean isWritten = writeToFile(file, employee);

        if(isWritten) {
            // Read from file
            List<EmployeeV5> employeeRecords = readFromFile(file);
            employeeRecords.forEach((employeeRecord -> LOGGER.info(employeeRecord.toString())));
        }
    }

    private static List<EmployeeV5> readFromFile(File file) {
        LOGGER.info("Reading from file {}", file.getAbsolutePath());
        DatumReader<EmployeeV5> datumReader = new SpecificDatumReader<>();
        List<EmployeeV5> employees = new ArrayList<>();
        try(DataFileReader<EmployeeV5> employeeReader = new DataFileReader<>(file, datumReader)) {
            while (employeeReader.hasNext()) {
                EmployeeV5 employeeRecord = employeeReader.next();
                employees.add(employeeRecord);
            }
        } catch (IOException e) {
            LOGGER.info("Exception occurred while reading employee data from file {} : ", file.getAbsolutePath(), e);
        }
        return employees;
    }

    private static boolean writeToFile(File file, EmployeeV5 employee) {
        LOGGER.info("Writing from file {}", file.getAbsolutePath());
        DatumWriter<EmployeeV5> datumWriter = new SpecificDatumWriter<>();
        try(DataFileWriter<EmployeeV5> employeeDataWriter = new DataFileWriter<>(datumWriter)) {
            employeeDataWriter.create(employee.getSchema(), file);
            employeeDataWriter.append(employee);
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
        employeeBuilder.setDepartment(Department.COMPUTER);

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
