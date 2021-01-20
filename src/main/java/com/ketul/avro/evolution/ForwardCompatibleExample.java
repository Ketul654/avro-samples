package com.ketul.avro.evolution;

import com.ketul.avro.schema.EmployeeV3;
import com.ketul.avro.schema.EmployeeV4;
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
import java.util.List;

public class ForwardCompatibleExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackwardCompatibleExample.class);

    public static void main(String[] args) {

        // Create employee record
        EmployeeV4 employeeV4 = getEmployee();

        LOGGER.info(employeeV4.toString());

        // Write to file
        File file = new File("target//employee-v4.avro");
        boolean isWritten = writeToFile(file, employeeV4);

        if(isWritten) {
            // Read from file
            List<EmployeeV3> employeeRecords = readFromFile(file);
            employeeRecords.forEach((employeeRecord -> LOGGER.info(employeeRecord.toString())));
        }
    }

    private static List<EmployeeV3> readFromFile(File file) {
        LOGGER.info("Reading from file {} using schema V3", file.getAbsolutePath());
        /*
         * Explicitly mention which schema you are going to use to read
         * EmployeeV3.class indicates its employeeV3.avsc
         */
        DatumReader<EmployeeV3> datumReader = new SpecificDatumReader<>(EmployeeV3.class);
        List<EmployeeV3> employees = new ArrayList<>();
        try(DataFileReader<EmployeeV3> employeeReader = new DataFileReader<>(file, datumReader)) {
            while (employeeReader.hasNext()) {
                EmployeeV3 employeeRecord = employeeReader.next();
                employees.add(employeeRecord);
            }
        } catch (IOException e) {
            LOGGER.info("Exception occurred while reading employee data from file {} : ", file.getAbsolutePath(), e);
        }
        return employees;
    }

    private static boolean writeToFile(File file, EmployeeV4 employeeV4) {
        LOGGER.info("Writing from file {} using schema V4", file.getAbsolutePath());
        DatumWriter<EmployeeV4> datumWriter = new SpecificDatumWriter<>();
        try(DataFileWriter<EmployeeV4> employeeDataWriter = new DataFileWriter<>(datumWriter)) {
            employeeDataWriter.create(employeeV4.getSchema(), file);
            employeeDataWriter.append(employeeV4);
            LOGGER.info("Written to {}", file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            LOGGER.info("Exception occurred while writing employee data to file {} : ", file.getAbsolutePath(), e);
            return false;
        }
    }

    private static EmployeeV4 getEmployee(){
        EmployeeV4.Builder employeeBuilder = EmployeeV4.newBuilder();
        employeeBuilder.setEmployeeId("BK128746XT");
        employeeBuilder.setFistName("Shiva");
        employeeBuilder.setLastName("Kailasha");
        employeeBuilder.setAge(20);
        employeeBuilder.setSalary(100000.34f);
        employeeBuilder.setPhoneNumber("444-5555-66666");
        employeeBuilder.setEmailId("abc@xyz.com");
        return employeeBuilder.build();
    }
}
