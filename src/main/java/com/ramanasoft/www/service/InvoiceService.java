//package com.ramanasoft.www.service;
//
//import java.awt.Color;
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.lowagie.text.Chunk;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.FontFactory;
//import com.lowagie.text.Image;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//import com.lowagie.text.pdf.draw.VerticalPositionMark;
//import com.ramanasoft.www.mode.Customer;
//import com.ramanasoft.www.mode.Payment;
//import com.ramanasoft.www.repository.CustomerRepository;
//import com.ramanasoft.www.repository.PaymentRepository;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Service
//public class InvoiceService 
//{
//	private final CustomerRepository crepo;
//	private final PaymentRepository prepo;
//	
//	private List<Customer> customerDetails;
//	private List<Payment> paymentDetails;
//	
//	@Autowired
//    public InvoiceService(CustomerRepository crepo,PaymentRepository prepo) {
//        this.crepo = crepo;
//        this.prepo= prepo;
//    }
//	
////	@PostConstruct
////    public void init() {
////		this.paymentDetails =  prepo.findByPaymentid(paymentid);
////        this.customerDetails =  crepo.findByCustomerid(customerid);
////    }
//
//    public List<Payment> getPaymentDetails() {
//        return paymentDetails;
//    }
//
//    public List<Customer> getCustomerDetails() {
//        return customerDetails;
//    }
//
//	private void writeTableHeader(PdfPTable table) 
//	{
//	    PdfPCell cell = new PdfPCell();
//	    cell.setBackgroundColor(new Color(253, 240, 213));
//	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//	    cell.setPadding(5);
//	   
//	    Font font = FontFactory.getFont(FontFactory.HELVETICA);
//	    font.setColor(new Color(31, 53, 65));
//	    
//		cell.setPhrase(new Phrase("Vehicle Number", font));
//		table.addCell(cell);
//	
//		cell.setPhrase(new Phrase("Registration Year", font));
//		table.addCell(cell);
//	
//	    cell.setPhrase(new Phrase("Market Value(IDV)", font));
//	    table.addCell(cell);
//	
//	    cell.setPhrase(new Phrase("Premium Amount", font));
//	    table.addCell(cell);
//	
//	    cell.setPhrase(new Phrase("Address", font));
//	    table.addCell(cell);
//	
//	    cell.setPhrase(new Phrase("Start Date", font));
//	    table.addCell(cell);
//	
//	    cell.setPhrase(new Phrase("Due Date", font));
//	    table.addCell(cell);
//	}
//
//	private void writeTableData(PdfPTable table) 
//	{	
//		table.addCell(paymentDetails.get(0).getVnumber());
//		table.addCell(paymentDetails.get(0).getVyear());
//    	table.addCell(paymentDetails.get(0).getIdv());
//		table.addCell(paymentDetails.get(0).getPremiumAmount());
//        table.addCell(customerDetails.get(0).getAddress());	
//        table.addCell(paymentDetails.get(0).getStartdate().toString());
//        table.addCell(paymentDetails.get(0).getEnddate().toString());
//	}
//
//
//	public void export(HttpServletResponse response,String paymentid,long customerid) throws DocumentException, IOException 
//	{
//
//		this.paymentDetails =  prepo.findByPaymentid(paymentid);
//        this.customerDetails =  crepo.findByCustomerid(customerid);
//		
//	    Document document = new Document(PageSize.A4);
//	    PdfWriter.getInstance(document, response.getOutputStream());
//	
//	    document.open();
//	
//	    try 
//	    {
//	        // Load image from resources folder
//	    	
//	
//	        Image image = Image.getInstance("C:\\Users\\Public\\Pictures ramanasoftlogo.jpg");
//	        image.scalePercent(8.0f, 8.0f);
//	        document.add(image);
//	
//	        // Add header
//	        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        headerFont.setSize(25);
//	        headerFont.setColor(new Color(120, 0, 0));
//	        Paragraph pHeader = new Paragraph("RS Insurance Pvt ltd. \n", headerFont);
//	        pHeader.setAlignment(Paragraph.ALIGN_CENTER);
//	        document.add(pHeader);
//	
//	        // Add bank details
//	        Font fontP = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        fontP.setSize(18);
//	        fontP.setColor(new Color(120, 0, 0));
//	        Chunk glue = new Chunk(new VerticalPositionMark());
//	        Paragraph pp = new Paragraph("\nCompany Details", fontP);
//	        pp.add(new Chunk(glue));
//	        pp.add("Custumer Details:");
//	        document.add(pp);
//	
//	        Font fontN = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        fontN.setSize(12);
//	        fontN.setColor(Color.BLACK);
//	        Paragraph pN = new Paragraph("Company Name: RS Insurance pvt ltd.");
//	        pN.add(new Chunk(glue));
//	
//	        pN.add("CustumerID: "+customerDetails.get(0).getCustomerid());
//	       
//	        document.add(pN);
//	
//	        Font fontA = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        fontA.setSize(12);
//	        fontA.setColor(Color.BLACK);
//	        Paragraph pA = new Paragraph("Agency No: 10012");
//	        pA.add(new Chunk(glue));
//	        pA.add("Email: " + customerDetails.get(0).getEmail());
//	        document.add(pA);
//	
//	        Font fontC = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        fontC.setSize(12);
//	        fontC.setColor(Color.BLACK);
//	        Paragraph pC = new Paragraph("Email : support@rsinsurance.com", fontC);
//	        pC.add(new Chunk(glue));
//	        pC.add("Mobile Number: "+customerDetails.get(0).getMobile());
//	        document.add(pC);
//	
//	        // Add transaction section header
//	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        font.setSize(22);
//	        font.setColor(new Color(120, 0, 0));
//	        Paragraph p = new Paragraph("\n Invoice\n", font);
//	        p.setAlignment(Paragraph.ALIGN_CENTER);
//	        document.add(p);
//	
//	        // Add statement range
//	        // Font fontStatementRange = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        // fontStatementRange.setSize(14);
//	        // fontStatementRange.setColor(new Color(120, 0, 0));
//	        // Paragraph pStatementRange = new Paragraph("\n ", fontStatementRange);
//	        // pStatementRange.setAlignment(Paragraph.ALIGN_LEFT);
//	        // document.add(pStatementRange);
//	
//	        // Add table
//	        PdfPTable table = new PdfPTable(6);
//	        table.setWidthPercentage(100f);
//	        table.setWidths(new float[]{2.0f, 2.0f, 1.5f, 2.0f, 3.1f, 3.2f});
//	        table.setSpacingBefore(10);
//	
//	        writeTableHeader(table);
//	        writeTableData(table);
//	
//	        document.add(table);
//	        
//	        Font fontP1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        fontP1.setSize(18);
//	        fontP1.setColor(new Color(120, 0, 0));
//	        Chunk glue1 = new Chunk(new VerticalPositionMark());
//	        Paragraph pp1 = new Paragraph("\n", fontP1);
//	        pp1.add(new Chunk(glue1));
//	        pp1.add("Best Regards");
//	        document.add(pp1);
//	
//	        Font fontN1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        fontN1.setSize(12);
//	        fontN1.setColor(Color.BLACK);
//	        Paragraph pN1 = new Paragraph("");
//	        pN1.add(new Chunk(glue1));
//	        pN1.add("Rs Insurance pvt ltd");
//	        document.add(pN1);
//	
//	        Font fontA1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        fontA1.setSize(12);
//	        fontA1.setColor(Color.BLACK);
//	        Paragraph pA1 = new Paragraph("");
//	        pA1.add(new Chunk(glue1));
//	        pA1.add("Madhapur, Hyderbad");
//	        document.add(pA1);
//	
//	        Font fontC1= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        fontC1.setSize(12);
//	        fontC1.setColor(Color.BLACK);
//	        Paragraph pC1 = new Paragraph("");
//	        pC1.add(new Chunk(glue1));
//	        pC1.add("India, 500081\n");
//	        document.add(pC1);
//	        
//	        Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//	        footerFont.setSize(12);
//	        footerFont.setColor(new Color(120, 0, 0));
//	        Paragraph fHeader = new Paragraph("\n Thank you choosing Rs Insurance.If any querys feel free to Contact us at support@ramanasoft.com or +91 9951489432 \n");
//	        fHeader.setAlignment(Paragraph.ALIGN_CENTER);
//	        document.add(fHeader);
//	    } 
//	    catch (Exception e) 
//	    {
//	        e.printStackTrace();
//	    } 
//	    finally 
//	    {
//	    	 if (document.isOpen()) {
//	             document.close();
//	         } 
//	    }
//	}
//}
//


package com.ramanasoft.www.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import com.ramanasoft.www.mode.Customer;
import com.ramanasoft.www.mode.Payment;
import com.ramanasoft.www.repository.CustomerRepository;
import com.ramanasoft.www.repository.PaymentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class InvoiceService {
    private final CustomerRepository crepo;
    private final PaymentRepository prepo;
    
    private List<Customer> customerDetails;
    private List<Payment> paymentDetails;
    
    @Autowired
    public InvoiceService(CustomerRepository crepo, PaymentRepository prepo) {
        this.crepo = crepo;
        this.prepo = prepo;
    }
    
    public List<Payment> getPaymentDetails() {
        return paymentDetails;
    }

    public List<Customer> getCustomerDetails() {
        return customerDetails;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(253, 240, 213));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
       
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(new Color(31, 53, 65));
        
        cell.setPhrase(new Phrase("Vehicle Number", font));
        table.addCell(cell);
    
        cell.setPhrase(new Phrase("Registration Year", font));
        table.addCell(cell);
    
        cell.setPhrase(new Phrase("Market Value(IDV)", font));
        table.addCell(cell);
    
        cell.setPhrase(new Phrase("Premium Amount", font));
        table.addCell(cell);
    
        cell.setPhrase(new Phrase("Start Date", font));
        table.addCell(cell);
    
        cell.setPhrase(new Phrase("Due Date", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
    	PdfPCell cell = new PdfPCell();
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	cell.setPadding(7);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);
        
        for (Payment payment : paymentDetails) {
        	
        	cell.setPhrase(new Phrase(payment.getVnumber(), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(payment.getVyear(), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(payment.getIdv(), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(payment.getPremiumAmount(), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(payment.getStartdate().toString(), font));
            table.addCell(cell);
            
            cell.setPhrase(new Phrase(payment.getEnddate().toString(), font));
            table.addCell(cell);
        }
    }

    public void export(HttpServletResponse response, String paymentid, long customerid) throws DocumentException, IOException {
        this.paymentDetails = prepo.findByPaymentid(paymentid);
        this.customerDetails = crepo.findByCustomerid(customerid);
        
        if (customerDetails.isEmpty() || paymentDetails.isEmpty()) {
            throw new IOException("Customer details or payment details are missing.");
        }

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
    
        document.open();
    
        try {
        	
        	//Load image from resources folder
            Image image = Image.getInstance("C:\\Users\\Public\\Pictures\\ramanasoftlogo.jpg");
            image.scalePercent(8.0f, 8.0f);
            document.add(image);
            
            //Add header
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            headerFont.setSize(25);
            headerFont.setColor(new Color(120, 0, 0));
            Paragraph pHeader = new Paragraph("RS Insurance Pvt ltd. \n", headerFont);
            pHeader.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(pHeader);
            
            //Add details
            Font fontP = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontP.setSize(18);
            fontP.setColor(new Color(120, 0, 0));
            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph pp = new Paragraph("\nCompany Details", fontP);
            pp.add(new Chunk(glue));
            pp.add("Customer Details");
            document.add(pp);

            Font fontN = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontN.setSize(12);
            fontN.setColor(Color.BLACK);
            Paragraph pN = new Paragraph("Company Name: RS Insurance pvt ltd.");
            pN.add(new Chunk(glue));
            pN.add("CustomerID: " + customerDetails.get(0).getCustomerid());
            document.add(pN);

            Font fontA = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontA.setSize(12);
            fontA.setColor(Color.BLACK);
            Paragraph pA = new Paragraph("Agency No: 10012");
            pA.add(new Chunk(glue));
            pA.add("Mobile Number: " + customerDetails.get(0).getMobile());
            document.add(pA);

            Font fontC = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontC.setSize(12);
            fontC.setColor(Color.BLACK);
            Paragraph pC = new Paragraph("Email : support@rsinsurance.com", fontC);
            pC.add(new Chunk(glue));
            pC.add("Email: " + customerDetails.get(0).getEmail());
            document.add(pC);

            //Add transaction section header
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(22);
            font.setColor(new Color(120, 0, 0));
            Paragraph p = new Paragraph("\n Invoice\n\n", font);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(p);
            
            
            //Retreve the name
            String name = customerDetails.get(0).getFirstName()+" "+customerDetails.get(0).getLastName();
            
            //Define fonts
            Font normalFont = FontFactory.getFont(FontFactory.TIMES,15,Font.NORMAL);
            Font boldFont = FontFactory.getFont(FontFactory.TIMES,15,Font.BOLD);
            
            //creating chunks for parts of paragraph
            Chunk chunk1 = new Chunk("Dear ",normalFont);
            Chunk chunk2 = new Chunk(name,boldFont);
            Chunk chunk3 = new Chunk("PolicyID: RSVI"+paymentDetails.get(0).getId(),normalFont);
            
            //Combine chunks into paragaraph
            Paragraph p1 = new Paragraph();
            p1.add(chunk1);
            p1.add(chunk2);
            p1.add(new Chunk(glue));
            p1.add(chunk3);
            
            //Add paragraph to document
            document.add(p1);
            Paragraph ps = new Paragraph("\n");
            document.add(ps);
            

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{2.85f, 2.15f, 2.0f, 2.0f, 2.25f, 2.25f});
            table.setSpacingBefore(10);

            writeTableHeader(table);
            writeTableData(table);

            document.add(table);

            Font fontP1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontP1.setSize(18);
            fontP1.setColor(new Color(120, 0, 0));
            Chunk glue1 = new Chunk(new VerticalPositionMark());
            Paragraph pp1 = new Paragraph("\n", fontP1);
            pp1.add(new Chunk(glue1));
            pp1.add("Best Regards");
            document.add(pp1);

            Font fontN1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontN1.setSize(12);
            fontN1.setColor(Color.BLACK);
            Paragraph pN1 = new Paragraph("");
            pN1.add(new Chunk(glue1));
            pN1.add("RS Insurance pvt ltd");
            document.add(pN1);

            Font fontA1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontA1.setSize(12);
            fontA1.setColor(Color.BLACK);
            Paragraph pA1 = new Paragraph("");
            pA1.add(new Chunk(glue1));
            pA1.add("Madhapur, Hyderabad");
            document.add(pA1);

            Font fontC1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontC1.setSize(12);
            fontC1.setColor(Color.BLACK);
            Paragraph pC1 = new Paragraph("");
            pC1.add(new Chunk(glue1));
            pC1.add("India, 500081\n");
            document.add(pC1);

            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            footerFont.setSize(12);
            footerFont.setColor(new Color(120, 0, 0));
            Paragraph fHeader = new Paragraph("\nThank you for choosing RS Insurance. If you have any queries, feel free to contact us at support@ramanasoft.com or 1800-258-2465.\n");
            fHeader.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(fHeader);
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}