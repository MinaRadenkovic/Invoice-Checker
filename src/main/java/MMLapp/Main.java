package MMLapp;

import javafx.scene.control.Label;
import CSV.CsvWithOpenCsv;
import domain.Invoice;
import invoiceChecker.InvoiceChecker;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label labela = new Label("Zdravo iz JavaFX-a!");
        StackPane koren = new StackPane();
        koren.getChildren().add(labela); // Pravilno dodavanje čvora
        Scene scena = new Scene(koren, 300, 200);
        stage.setScene(scena);
        stage.setTitle("JavaFX Maven primer");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        
//      String url = "https://suf.purs.gov.rs:443/v/?vl=A1M4QVVBRkFZUzhBVUFGQVnTnQAApZ0AABhl1gEAAAAAAAABlsp%2FC5cAAAwxMDoxMDA4MzQ3NjaXIIASpHe8lwuYmG1l6p2AKT9KkRztgCLQqjelIPUZZyk%2FbNN%2Fug1tuoBMYtQY4n6U%2BPZrx%2B74ntKwUEf%2FQJBOjmZeA1aHtV%2BNB%2B%2FWcYGm4S4T2vQXbmYHqLnXSauyMNfztxQMGpbPo5Skn9FQa9Uv9DkOl7qxc7y%2FzLmQKeWpUk9eP%2BPUvZUVmDZmdgZ7xWjmTDo8sNBWK7RIYI33fqHzB8bwaw7tMHrkX8jg0gxjBa0aB%2B%2BTHW1tpzOVkomIWEzmUWBeBoZp4IEs9HC%2F%2F0hpiPof3xSDaoDA0YRK1CPZP2IEPpBUr926Q7E6FZKyTYoUgHVg6mJmf5w%2Bmlmj2lrCEBUcuokYv7fv9hQ76dlHgjrtDiWjA3jQnXiAyppi0%2FTCHmBf%2Bbi6RtDBgVQRUw67cs0Qs42MCShovEd7bjq%2B%2BsTbRbahUKAdLHX2CWR7jQmyya%2Bg71GE5BUbWMqv2n%2BwHRNdCEEBLfkKPzZkoiue67IAJ%2B3tJPWAhAkB%2FJrHwvXZwm3Bqb7Kt9bQJtnYxj3pvPR1%2FqUGDRdyIlsWiy8HIL4m7K8aJPUVndPID%2F5oaasHfSKHHp0RdQvqmPJkRCuGs65xWQk9qqSKOoPXSNNwWL%2BlTngGYVb3Kb2SuuBKqMPBIS%2FuvVsd%2BgfJOlt6n6634liew%2F7AoTumil6Xi7GXhkzXB680RPR9SyPXSGFg3gw%3D";
//    	
//    	Invoice invoice = InvoiceChecker.getInvoice(url);
//    	CsvWithOpenCsv.writeToCSV(invoice);
    }
}
