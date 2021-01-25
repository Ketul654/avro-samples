package com.ketul.avro.evolution;

import com.ketul.avro.schema.EmployeeV7;
import com.ketul.avro.schema.EmployeeV8;
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

public class FullCompatibleExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackwardCompatibleExample.class);

    public static void main(String[] args) {

        LOGGER.info("Testing backward compatibility");
        // Create employee record
        EmployeeV7 employeeV7 = getEmployeeBackwardCompatibility();

        LOGGER.info(employeeV7.toString());

        // Write to file for backward compatibility
        File file = new File("target//employee-v7.avro");
        boolean isWritten = writeToFileForBackwardCompatibility(file, employeeV7);

        if(isWritten) {
            // Read from file
            List<EmployeeV8> employeeRecords = readFromFileBackwardCompatibility(file);
            employeeRecords.forEach((employeeRecord -> LOGGER.info(employeeRecord.toString())));
        }


        LOGGER.info("Testing forward compatibility");
        EmployeeV8 employeeV8 = getEmployeeForwardCompatibility();

        LOGGER.info(employeeV8.toString());

        // Write to file for forward compatibility
        file = new File("target//employee-v8.avro");
        isWritten = writeToFileForForwardCompatibility(file, employeeV8);

        if(isWritten) {
            // Read from file
            List<EmployeeV7> employeeRecords = readFromFileForwardCompatibility(file);
            employeeRecords.forEach((employeeRecord -> LOGGER.info(employeeRecord.toString())));
        }
    }

    private static List<EmployeeV8> readFromFileBackwardCompatibility(File file) {
        LOGGER.info("Reading from file {} using schema V8", file.getAbsolutePath());
        /*
         * Explicitly mention which schema you are going to use to read
         * EmployeeV8.class indicates its employeeV8.avsc
         *
         * Note : Adding or removing default fields is full compatible
         */
        DatumReader<EmployeeV8> datumReader = new SpecificDatumReader<>(EmployeeV8.class);
        List<EmployeeV8> employees = new ArrayList<>();
        try(DataFileReader<EmployeeV8> employeeReader = new DataFileReader<>(file, datumReader)) {
            while (employeeReader.hasNext()) {
                EmployeeV8 employeeRecord = employeeReader.next();
                employees.add(employeeRecord);
            }
        } catch (IOException e) {
            LOGGER.info("Exception occurred while reading employee data from file {} : ", file.getAbsolutePath(), e);
        }
        return employees;
    }

    private static List<EmployeeV7> readFromFileForwardCompatibility(File file) {
        LOGGER.info("Reading from file {} using schema V7", file.getAbsolutePath());
        /*
         * Explicitly mention which schema you are going to use to read
         * EmployeeV8.class indicates its employeeV8.avsc
         *
         * Note : Adding or removing default fields is full compatible
         */
        DatumReader<EmployeeV7> datumReader = new SpecificDatumReader<>(EmployeeV7.class);
        List<EmployeeV7> employees = new ArrayList<>();
        try(DataFileReader<EmployeeV7> employeeReader = new DataFileReader<>(file, datumReader)) {
            while (employeeReader.hasNext()) {
                EmployeeV7 employeeRecord = employeeReader.next();
                employees.add(employeeRecord);
            }
        } catch (IOException e) {
            LOGGER.info("Exception occurred while reading employee data from file {} : ", file.getAbsolutePath(), e);
        }
        return employees;
    }

    private static boolean writeToFileForBackwardCompatibility(File file, EmployeeV7 employeeV7) {
        LOGGER.info("Writing from file {} using schema V7", file.getAbsolutePath());
        DatumWriter<EmployeeV7> datumWriter = new SpecificDatumWriter<>();
        try(DataFileWriter<EmployeeV7> employeeDataWriter = new DataFileWriter<>(datumWriter)) {
            employeeDataWriter.create(employeeV7.getSchema(), file);
            employeeDataWriter.append(employeeV7);
            LOGGER.info("Written to {}", file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            LOGGER.info("Exception occurred while writing employee data to file {} : ", file.getAbsolutePath(), e);
            return false;
        }
    }

    private static boolean writeToFileForForwardCompatibility(File file, EmployeeV8 employeeV8) {
        LOGGER.info("Writing from file {} using schema V8", file.getAbsolutePath());
        DatumWriter<EmployeeV8> datumWriter = new SpecificDatumWriter<>();
        try(DataFileWriter<EmployeeV8> employeeDataWriter = new DataFileWriter<>(datumWriter)) {
            employeeDataWriter.create(employeeV8.getSchema(), file);
            employeeDataWriter.append(employeeV8);
            LOGGER.info("Written to {}", file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            LOGGER.info("Exception occurred while writing employee data to file {} : ", file.getAbsolutePath(), e);
            return false;
        }
    }

    private static EmployeeV7 getEmployeeBackwardCompatibility(){
        EmployeeV7.Builder employeeBuilder = EmployeeV7.newBuilder();
        employeeBuilder.setEmployeeId("BK128746XT");
        employeeBuilder.setFistName("Shiva");
        employeeBuilder.setLastName("Kailasha");
        employeeBuilder.setAge(20);
        employeeBuilder.setSalary(100000.34f);
        employeeBuilder.setIsPermanent(true);
        return employeeBuilder.build();
    }

    private static EmployeeV8 getEmployeeForwardCompatibility(){
        EmployeeV8.Builder employeeBuilder = EmployeeV8.newBuilder();
        employeeBuilder.setEmployeeId("KR128746XT");
        employeeBuilder.setFistName("Ketul");
        employeeBuilder.setLastName("Patel");
        employeeBuilder.setAge(20);
        employeeBuilder.setSalary(13400.34f);
        employeeBuilder.setEmailId("abc@xyz.com");
        return employeeBuilder.build();
    }
}
