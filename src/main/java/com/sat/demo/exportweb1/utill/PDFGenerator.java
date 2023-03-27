package com.sat.demo.exportweb1.utill;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sat.demo.exportweb1.model.Customer;
import com.sat.demo.exportweb1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PDFGenerator {

    public void exportToPDF(List<Customer> customers,HttpServletResponse response)throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph Title = new Paragraph("List of Customers",fontTitle);
        Title.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(Title);
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 1, 2,2,3,2,2,2,2  });
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.black);

        //add table column name
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Contact", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Country", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date-of-Birth", font));
        table.addCell(cell);

        //insert customer data to table
        for(Customer customer:customers){
            table.addCell(String.valueOf(customer.getId()));
            table.addCell(String.valueOf(customer.getFirstName()));
            table.addCell(String.valueOf(customer.getLastName()));
            table.addCell(String.valueOf(customer.getEmail()));
            table.addCell(String.valueOf(customer.getGender()));
            table.addCell(String.valueOf(customer.getContactNo()));
            table.addCell(String.valueOf(customer.getCountry()));
            table.addCell(String.valueOf(customer.getDob()));
        }

        document.add(table);

        document.close();
    }
}
