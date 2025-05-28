package invoiceChecker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Invoice;
import domain.InvoiceSpecification;

public class InvoiceChecker {

	public static JsonNode Checker(String url) {
		if (url == null) {
			return null;
		}
	    try {
	        URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("Accept", "application/json");
	        con.setRequestProperty("Content-Type", "application/json");
	        int responseCode = con.getResponseCode();
	        if (responseCode == 200) { // OK
	            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
	            String inputLine;
	            StringBuilder response = new StringBuilder();
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	            String jsonRaw = response.toString();
	            String jsonDecoded = StringEscapeUtils.unescapeJava(jsonRaw);
	            ObjectMapper mapper = new ObjectMapper();
	            JsonNode rootNode = mapper.readTree(jsonRaw);
	            return rootNode;
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
    private static JsonNode getInvoiceRequestNode(JsonNode json) {
        return json != null ? json.path("invoiceRequest") : null;
    }

    private static JsonNode getInvoiceResultNode(JsonNode json) {
        return json != null ? json.path("invoiceResult") : null;
    }
    
	public static String getTaxId(String url) {
		JsonNode json = Checker(url);
		if (json == null) {
			return null;
		}
		JsonNode invoiceRequest = getInvoiceRequestNode(json);
		String taxId = invoiceRequest.get("taxId").asText();
		return taxId;
	}
	
	public static String getBuyer(String url) {
		JsonNode json = Checker(url);
		if (json == null) {
			return null;
		}
		JsonNode invoiceRequest = getInvoiceRequestNode(json);
		String buyer = invoiceRequest.get("buyer").asText();
		return buyer;
	}
	
	public static String getPaymentType(String url) {
		JsonNode json = Checker(url);
		if (json == null) {
			return null;
		}
		JsonNode invoiceRequest = getInvoiceRequestNode(json);
		JsonNode payments = invoiceRequest.get("payments");
		String paymentType = null;
		if (payments != null && payments.isArray() && payments.size() > 0) {
			paymentType = payments.get(0).path("paymentType").asText();
		}
		return paymentType;
	}
	
	public static String getAmount(String url) {
		JsonNode json = Checker(url);
		if (json == null) {
			return null;
		}
		JsonNode invoiceRequest = getInvoiceRequestNode(json);
		JsonNode payments = invoiceRequest.get("payments");
		String amount = null;
		if (payments != null && payments.isArray() && payments.size() > 0) {
		    amount = payments.get(0).path("amount").asText();
		}
		return amount;
	}
	
	public static String getInvoiceNumber(String url) {
		JsonNode json = Checker(url);
		if (json == null) {
			return null;
		}
		JsonNode invoiceRequest = getInvoiceResultNode(json);
		String invoiceNumber = invoiceRequest.get("invoiceNumber").asText();
		return invoiceNumber;
	}
	
	public static String getSdcTime(String url) {
		JsonNode json = Checker(url);
		if (json == null) {
			return null;
		}
		JsonNode invoiceRequest = getInvoiceResultNode(json);
		String sdcTime = invoiceRequest.get("sdcTime").asText();
		return sdcTime;
	}
	
	public static ArrayList<InvoiceSpecification> getInvoiceSpecification(String url) {
		JsonNode json = Checker(url);
        if (json == null || !json.has("journal")) return null;
		String journalNode = json.path("journal").asText();
		String startMarker = "Назив   Цена         Кол.         Укупно";
	    String endMarker = "----------------------------------------";

	    int startIndex = journalNode.indexOf(startMarker);
	    if (startIndex == -1) return null;

	    int endIndex = journalNode.indexOf(endMarker, startIndex);
	    if (endIndex == -1) return null;

	    String articles = journalNode.substring(startIndex + startMarker.length(), endIndex).trim();
		ArrayList<InvoiceSpecification> specification = new ArrayList<InvoiceSpecification>();
		String[] LineItem = articles.split("\\r\\n");
		for(int i = 0; i < LineItem.length; i = i + 2) {
		    String name = LineItem[i].substring(0, LineItem[i].indexOf('('));
		    String rate = LineItem[i].substring(LineItem[i].indexOf('(') + 1, LineItem[i].indexOf(")"));
		    String[] pricing = LineItem[i + 1].trim().split("\\s+");
		    if (pricing.length != 3) {
		    	return null;
		    }
		    String grossUnitPrice = pricing[0];
		    String quantity = pricing[1];
		    String totalPrice = pricing[2];
		    specification.add(new InvoiceSpecification(name, grossUnitPrice, quantity, totalPrice, rate));
		}
	    return specification;
	}
	
	public static Invoice getInvoice(String url) {
		String amount = InvoiceChecker.getAmount(url);
    	String buyer = InvoiceChecker.getBuyer(url);
    	String invoiceNumber = InvoiceChecker.getInvoiceNumber(url);
    	String paymentType = InvoiceChecker.getPaymentType(url);
    	String sdcTime = InvoiceChecker.getSdcTime(url);
    	String taxId = InvoiceChecker.getTaxId(url);
    	ArrayList<InvoiceSpecification> specifiction = InvoiceChecker.getInvoiceSpecification(url);
    	return new Invoice(taxId, buyer, paymentType, amount, invoiceNumber, sdcTime, specifiction);
	}
}
