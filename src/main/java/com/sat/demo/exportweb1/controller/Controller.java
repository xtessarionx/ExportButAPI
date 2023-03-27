package com.sat.demo.exportweb1.controller;

import com.sat.demo.exportweb1.model.Customer;
import com.sat.demo.exportweb1.service.CustomerService;
import com.sat.demo.exportweb1.utill.CSVGenerator;
import com.sat.demo.exportweb1.utill.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CSVGenerator csvGenerator;

    @GetMapping("/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException{
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"customers.csv\"");
        csvGenerator.writeToCSV(customerService.getCustomersList(),response.getWriter());
    }
    @GetMapping("/export/txt")
    public void exportToTXT(HttpServletResponse response) throws IOException{
        response.setContentType("text/plain");
        response.addHeader("Content-Disposition", "attachment; filename=\"customers.txt\"");
        response.setCharacterEncoding("UTF-8");
        csvGenerator.writeToCSV(customerService.getCustomersList(),response.getWriter());
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + dateFormat.format(new Date()) + ".pdf";
        response.setHeader(headerkey,headervalue);


        PDFGenerator generator = new PDFGenerator();
        generator.exportToPDF(customerService.getCustomersList(),response);
    }
}
