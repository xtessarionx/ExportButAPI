package com.sat.demo.exportweb1.utill;

import com.sat.demo.exportweb1.model.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.List;

@Component
public class CSVGenerator {
    public void writeToCSV(List<Customer> customers, Writer writer){
        try{
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for(Customer customer:customers){
                System.out.println(customer.toString());
                printer.printRecord(customer.getId(),customer.getFirstName(),customer.getLastName(),customer.getEmail()
                ,customer.getGender(),customer.getContactNo(),customer.getCountry(),customer.getDob());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
