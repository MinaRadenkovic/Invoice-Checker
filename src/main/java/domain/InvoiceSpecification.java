package domain;

public class InvoiceSpecification {
	private String name;
	private String grossUnitPrice;
	private String quantity;
	private String totalPrice;
	private String rate;
	
	public InvoiceSpecification() {
		
	}
	
	public InvoiceSpecification(String name, String grossUnitPrice, String quantity, String totalPrice, String rate) {
		super();
		this.name = name;
		this.grossUnitPrice = grossUnitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.rate = rate;
	}
	
	public InvoiceSpecification(InvoiceSpecification specification) {
		this.name = specification.getName();
		this.grossUnitPrice = specification.getGrossUnitPrice();
		this.quantity = specification.getQuantity();
		this.totalPrice = specification.getTotalPrice();
		this.rate = specification.getRate();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrossUnitPrice() {
		return grossUnitPrice;
	}

	public void setGrossUnitPrice(String grossUnitPrice) {
		this.grossUnitPrice = grossUnitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return String.format("name: %s grossUnitPrice: %s quantity: %s totalPrice: %s rate: %s ", 
				name, grossUnitPrice, quantity, totalPrice, rate);
	}
}
