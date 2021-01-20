package com.ketul.avro.reflection;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ReflectionExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExample.class);

    public static void main(String[] args) {
        Schema schema = ReflectData.get().getSchema(ReflectedEmployee.class);
        LOGGER.info("Schema created using reflection : {}", schema.toString(true));

        File file = new File("target//reflected-employee.avro");

        // Write to file
        ReflectedEmployee employee = new ReflectedEmployee("Ketul", "Patel", 20, 100.34f, false);
        boolean isWritten = writeToFile(schema, file, employee);

        if(isWritten) {
            // Read from file
            readFromFile(file);
        }
    }

    private static void readFromFile(File file) {
        LOGGER.info("Reading from file {}", file.getAbsolutePath());
        DatumReader<ReflectedEmployee> datumReader = new ReflectDatumReader<>(ReflectedEmployee.class);
        try(DataFileReader<ReflectedEmployee> fileReader = new DataFileReader<ReflectedEmployee>(file, datumReader)) {
            for(ReflectedEmployee employee : fileReader) {
                LOGGER.info(employee.toString());
            }
        } catch (IOException e) {
            LOGGER.error("Exception occurred while reading reflected employee from file {} : ", file.getAbsolutePath(), e);
        }
    }

    private static boolean writeToFile(Schema schema, File file, ReflectedEmployee employee) {
        LOGGER.info("Writing to file {}", file.getAbsolutePath());
        DatumWriter<ReflectedEmployee> datumWriter = new ReflectDatumWriter<>();
        try(DataFileWriter<ReflectedEmployee> fileWriter = new DataFileWriter<>(datumWriter).create(schema, file)) {
            fileWriter.append(employee);
            LOGGER.info("Written successfully to {}", file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            LOGGER.error("Exception occurred while writing reflected employee to file {} : ", file.getAbsolutePath(), e);
            return false;
        }
    }
}
