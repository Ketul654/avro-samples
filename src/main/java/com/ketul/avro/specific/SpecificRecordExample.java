package com.ketul.avro.specific;

import com.ketul.avro.schema.Employee;
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

public class SpecificRecordExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecificRecordExample.class);

    public static void main(String[] args) {

        // Create employee record
        Employee employee = getEmployee();

        LOGGER.info(employee.toString());

        // Write to file
        File file = new File("target//specific-employee.avro");
        boolean isWritten = writeToFile(file, employee);

        if(isWritten) {
            // Read from file
            List<Employee> employeeRecords = readFromFile(file);
            employeeRecords.forEach((employeeRecord -> LOGGER.info(employeeRecord.toString())));
        }
    }

    private static List<Employee> readFromFile(File file) {
        LOGGER.info("Reading from file {}", file.getAbsolutePath());
        DatumReader<Employee> datumReader = new SpecificDatumReader<>();
        List<Employee> employees = new ArrayList<>();
        try(DataFileReader<Employee> employeeReader = new DataFileReader<>(file, datumReader)) {
            while (employeeReader.hasNext()) {
                Employee employeeRecord = employeeReader.next();
                employees.add(employeeRecord);
            }
        } catch (IOException e) {
            LOGGER.info("Exception occurred while reading employee data from file {} : ", file.getAbsolutePath(), e);
        }
        return employees;
    }

    private static boolean writeToFile(File file, Employee employee) {
        LOGGER.info("Writing from file {}", file.getAbsolutePath());
        DatumWriter<Employee> datumWriter = new SpecificDatumWriter<>();
        try(DataFileWriter<Employee> employeeDataWriter = new DataFileWriter<>(datumWriter)) {
            employeeDataWriter.create(employee.getSchema(), file);
            employeeDataWriter.append(employee);
            LOGGER.info("Written to {}", file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            LOGGER.info("Exception occurred while writing employee data to file {} : ", file.getAbsolutePath(), e);
            return false;
        }
    }

    private static Employee getEmployee(){
        Employee.Builder employeeBuilder = Employee.newBuilder();
        employeeBuilder.setEmployeeId("BK128746XT");
        employeeBuilder.setFistName("Shiva");
        employeeBuilder.setLastName("Kailasha");
        employeeBuilder.setAge(20);
        employeeBuilder.setSalary(100000.34f);
        employeeBuilder.setIsPermanent(false);
        return employeeBuilder.build();
    }
}
