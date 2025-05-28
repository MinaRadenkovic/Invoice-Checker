package domain;

import java.util.ArrayList;

public class Invoice {
	
	private String taxId;
	private String buyer;
	private String paymentType;
	private String amount;
	private String invoiceNumber;
	private String sdcTime;
	private ArrayList<InvoiceSpecification> invoiceSpecification;
	
	public Invoice() {
		
	}

	public Invoice(String taxId, String buyer, String paymentType, String amount, String invoiceNumber, String sdcTime,
			ArrayList<InvoiceSpecification> invoiceSpecification) {
		this.taxId = taxId;
		this.buyer = buyer;
		this.paymentType = paymentType;
		this.amount = amount;
		this.invoiceNumber = invoiceNumber;
		this.sdcTime = sdcTime;
		this.invoiceSpecification = invoiceSpecification;
	}
	
	public Invoice (Invoice invoice) {
		this.taxId = invoice.getTaxId();
		this.buyer = invoice.getBuyer();
		this.paymentType = invoice.getPaymentType();
		this.amount = invoice.getAmount();
		this.invoiceNumber = invoice.getInvoiceNumber();
		this.sdcTime = invoice.getSdcTime();
		this.invoiceSpecification = invoice.getInvoiceSpecification();
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getSdcTime() {
		return sdcTime;
	}

	public void setSdcTime(String sdcTime) {
		this.sdcTime = sdcTime;
	}

	public ArrayList<InvoiceSpecification> getInvoiceSpecification() {
		return invoiceSpecification;
	}

	public void setInvoiceSpecification(ArrayList<InvoiceSpecification> invoiceSpecification) {
		this.invoiceSpecification = invoiceSpecification;
	}
}
