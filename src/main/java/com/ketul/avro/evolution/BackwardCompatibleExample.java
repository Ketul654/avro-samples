package com.ketul.avro.evolution;

import com.ketul.avro.schema.EmployeeV1;
import com.ketul.avro.schema.EmployeeV2;
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

public class BackwardCompatibleExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackwardCompatibleExample.class);

    public static void main(String[] args) {

        // Create employee record
        EmployeeV1 employeeV1 = getEmployee();

        LOGGER.info(employeeV1.toString());

        // Write to file
        File file = new File("target//employee-v1.avro");
        boolean isWritten = writeToFile(file, employeeV1);

        if(isWritten) {
            // Read from file
            List<EmployeeV2> employeeRecords = readFromFile(file);
            employeeRecords.forEach((employeeRecord -> LOGGER.info(employeeRecord.toString())));
        }
    }

    private static List<EmployeeV2> readFromFile(File file) {
        LOGGER.info("Reading from file {} using schema V2", file.getAbsolutePath());
        /*
         * Explicitly mention which schema you are going to use to read
         * EmployeeV2.class indicates its employeeV2.avsc
         */
        DatumReader<EmployeeV2> datumReader = new SpecificDatumReader<>(EmployeeV2.class);
        List<EmployeeV2> employees = new ArrayList<>();
        try(DataFileReader<EmployeeV2> employeeReader = new DataFileReader<>(file, datumReader)) {
            while (employeeReader.hasNext()) {
                EmployeeV2 employeeRecord = employeeReader.next();
                employees.add(employeeRecord);
            }
        } catch (IOException e) {
            LOGGER.info("Exception occurred while reading employee data from file {} : ", file.getAbsolutePath(), e);
        }
        return employees;
    }

    private static boolean writeToFile(File file, EmployeeV1 employeeV1) {
        LOGGER.info("Writing from file {} using schema V1", file.getAbsolutePath());
        DatumWriter<EmployeeV1> datumWriter = new SpecificDatumWriter<>();
        try(DataFileWriter<EmployeeV1> employeeDataWriter = new DataFileWriter<>(datumWriter)) {
            employeeDataWriter.create(employeeV1.getSchema(), file);
            employeeDataWriter.append(employeeV1);
            LOGGER.info("Written to {}", file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            LOGGER.info("Exception occurred while writing employee data to file {} : ", file.getAbsolutePath(), e);
            return false;
        }
    }

    private static EmployeeV1 getEmployee(){
        EmployeeV1.Builder employeeBuilder = EmployeeV1.newBuilder();
        employeeBuilder.setEmployeeId("BK128746XT");
        employeeBuilder.setFistName("Shiva");
        employeeBuilder.setLastName("Kailasha");
        employeeBuilder.setAge(20);
        employeeBuilder.setSalary(100000.34f);
        employeeBuilder.setIsPermanent(false);
        employeeBuilder.setPhoneNumber("444-5555-66666");
        return employeeBuilder.build();
    }
}
