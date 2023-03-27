package com.sat.demo.exportweb1.utill;

import com.sat.demo.exportweb1.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.List;

@Slf4j
@Component
public class CSVGenerator {
    public void writeToCSV(List<Customer> customers, Writer writer){

        try{
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord("Id","firstName","lastName","email","gender","contactNo","country","dob");
            for(Customer customer:customers){
                printer.printRecord(customer.getId(),customer.getFirstName(),customer.getLastName(),customer.getEmail()
                ,customer.getGender(),customer.getContactNo(),customer.getCountry(),customer.getDob());
            }
        }catch (Exception e){
            log.error("Something wrong while exporting",e);
        }

    }
}
