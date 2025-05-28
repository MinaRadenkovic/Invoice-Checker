package CSV;

import com.opencsv.CSVWriter;

import domain.Invoice;
import domain.InvoiceSpecification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWithOpenCsv {
	
    public static boolean writeToCSV(Invoice invoice) {
    	if (invoice == null) {
    		return false;
    	}
    	String folderPath = System.getProperty("user.home") + "/Downloads/invoices";
    	File folder = new File(folderPath);
    	if (!folder.exists()) {
    	    folder.mkdirs();  
    	}
    	String filePath = folderPath + "/" + invoice.getInvoiceNumber() + ".csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
        	String[] header = {"InvoiceNumber", "sdcTime", "Buyer", "TaxId", "Amount", "PaymentType"};
        	writer.writeNext(header);
        	String[] row1 = {invoice.getInvoiceNumber(), invoice.getSdcTime(), invoice.getBuyer(), invoice.getTaxId(), invoice.getAmount(), invoice.getPaymentType()};
        	writer.writeNext(row1);        	
            String[] specification = { "Name", "GrossUnitPrice", "Quantity", "TotalPrice", "Rate" };
            writer.writeNext(specification);
            if (invoice.getInvoiceSpecification() == null) {
            	return false;
            }
            for (int i = 0; i < invoice.getInvoiceSpecification().size(); i++) {
            	InvoiceSpecification invoiceSpecification = invoice.getInvoiceSpecification().get(i);
            	String[] row2 = {invoiceSpecification.getName(), invoiceSpecification.getGrossUnitPrice(), invoiceSpecification.getQuantity(), invoiceSpecification.getTotalPrice(), invoiceSpecification.getRate()};
            	writer.writeNext(row2);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
