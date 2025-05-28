package MMLapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import CSV.CsvWithOpenCsv;
import domain.Invoice;
import invoiceChecker.InvoiceChecker;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: white;");
        
        Label label = new Label("Invoice Verification URL");
        label.setStyle("-fx-font-size: 22px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #1f497d; " +
                "-fx-background-color: white; " +
                "-fx-padding: 30 10 10 50; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 5px;");
        root.getChildren().add(label);
        
        TextArea textArea = new TextArea();
        textArea.setStyle("-fx-fill: black; " +
        		"-fx-font-size: 12px");
        textArea.setWrapText(true);
        textArea.setPrefSize(1440, 150);
        AnchorPane.setTopAnchor(textArea, 80.0);
        AnchorPane.setLeftAnchor(textArea, 50.0);

        Label placeholder = new Label("Enter Invoice Verification URL");
        placeholder.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
        placeholder.setMouseTransparent(true);
        placeholder.setPickOnBounds(false);
        AnchorPane.setTopAnchor(placeholder, 85.0);
        AnchorPane.setLeftAnchor(placeholder, 60.0);
        textArea.textProperty().addListener((obs, oldText, newText) -> {
            placeholder.setVisible(newText.isEmpty());
        });
        
        root.getChildren().addAll(textArea, placeholder);
        
        ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/delete.png")));
        deleteIcon.setFitHeight(20);
        deleteIcon.setFitWidth(20);
        Button deleteButton = new Button("Delete", deleteIcon);
        deleteButton.setOnAction(e -> textArea.clear());

        ImageView copyIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/copy.png")));
        copyIcon.setFitHeight(20);
        copyIcon.setFitWidth(20);
        Button copyButton = new Button("Copy", copyIcon);
        copyButton.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(textArea.getText());
            clipboard.setContent(content);
        });

        Button exportButton = new Button("Export to CSV");
        exportButton.setMinHeight(28);
        exportButton.setStyle("-fx-font-size: 12px");
        exportButton.setOnAction(e -> {
        	String inputUrl = textArea.getText().trim();
        	if (!inputUrl.startsWith("http://") && !inputUrl.startsWith("https://")) {
        	    Alert alert = new Alert(Alert.AlertType.ERROR);
        	    alert.setTitle("ERROR");
        	    alert.setHeaderText("URL is not valid.");
        	    alert.setContentText("Please enter a valid URL starting with http:// or https://");
        	    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        		alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
        	    alert.showAndWait();
        	    return;
        	}
        	Invoice invoice = InvoiceChecker.getInvoice(inputUrl);
        	Boolean exported = CsvWithOpenCsv.writeToCSV(invoice);
        	if (exported) {
        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        		alert.setTitle("Export to CSV");
        		alert.setHeaderText(null);
        		alert.setContentText("Invoice data has been exported to CSV!");
				ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/icons/checked.png")));
				icon.setFitWidth(48);
				icon.setFitHeight(48);
				alert.setGraphic(icon);
        		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        		alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
        		alert.showAndWait(); 
        	} else {
        		Alert alert = new Alert(Alert.AlertType.ERROR);
        		alert.setTitle("Export to CSV");
        		alert.setHeaderText(null);
        		alert.setContentText("Invoice data has NOT been exported to CSV!");
        		ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/icons/cancel.png")));
				icon.setFitWidth(48);
				icon.setFitHeight(48);
				alert.setGraphic(icon);
        		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        		alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
        		alert.showAndWait(); 
        	}
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(deleteButton, copyButton, exportButton);
        AnchorPane.setTopAnchor(buttonBox, 240.0);
        AnchorPane.setLeftAnchor(buttonBox, 50.0);
        root.getChildren().add(buttonBox);
        
        Image image = new Image(getClass().getResourceAsStream("/images/company branding.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth() * 0.8);
        imageView.setFitHeight(image.getHeight() * 0.8);
        imageView.setPreserveRatio(true);
        AnchorPane.setBottomAnchor(imageView, 20.0); 
        AnchorPane.setRightAnchor(imageView, 10.0);  
        root.getChildren().add(imageView);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("MML Radenković");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
