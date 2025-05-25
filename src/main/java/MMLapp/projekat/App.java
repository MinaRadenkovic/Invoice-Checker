package MMLapp.projekat;

import CSV.CsvWithOpenCsv;
import domain.Invoice;
import invoiceChecker.InvoiceChecker;

public class App 
{
    public static void main( String[] args )
    {
    	String url = "https://suf.purs.gov.rs/v/?vl=A0tYRFdHSjVLR0VTRTZITzCrAAAAqwAAAEDilQEAAAAAAAABlWbw1K8AAAwxMDoxMTQ4Nzc5NDVGoYCI5r0cXlZr1p0jdNKjO2AgrMk%2BUdmUDiaVE5Uu1AT5eSotKNKI8tonNinJ3YgiYFkWX%2BPCk8iHypO77gRN%2FgfeHOpdYBjG8YQ83NuRWKmYgUnT0xGa66FN20gtpCC1LIW6WvotdbBnejFu09WTsJfzLN%2F1yAq%2Fm8K0lhgjfD7QqobDUOetSBS%2BOk5AkCmPSLh54vo9ZMxLFQ2mDKdPJA1ikNB%2BAnern7HV4BibeVj%2FHEabGEAw6ZxQV5RN24TMui5OS6kO8Bn0zKsIBVrIYtVpt5db8FJjy%2FvFPXVdizZaJdR4igCePC1Ec38Nx4n1tHGMVCOZS8dvsmSNHMo1nba6uzaBrduP0DxW6s8gq8oAmo4MyB4jkgRLBhDGJsQTJzXQqG6F8oCZuyDBez%2FBwZYuN7H9uayojuKPhiFucZUZVJBsCjIr7NMK7okLJpkQq87J7zHR51lYXBakxvxpF%2FwwTivBHHlhn9376mxiFpmofuFALdgFwWqL2RHMv8YJIpgPpH4UTeJbmMZMSULTFEu52F3kxkhzj923Ykgrh3X6nLSHwQYnf%2FeV6BcDhMHVTqHzqeE%2B%2FiutDIo8D8CXYHYl0BiS7ADQbmbmFc5FShZ1N0tcwvbX%2BSVuDEtRUIfQQkj24IJUveqSLD7%2BVxcSvi1e2gctdF44RKg%2Fa80cJc2VDQkDajPBWiWvUTma7rM%3D";
    	
    	Invoice invoice = InvoiceChecker.getInvoice(url);
    	CsvWithOpenCsv.writeToCSV(invoice);
    	

 
    	
    	
    }
}
