package gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import CSV.CsvWithOpenCsv;
import domain.Invoice;
import invoiceChecker.InvoiceChecker;

public class MainView {

    public void show(Stage stage) {
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: white;");

        Label label = new Label("Invoice Verification URL");
        label.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #1f497d; -fx-padding: 30 10 10 50;");
        root.getChildren().add(label);

        TextArea textArea = new TextArea();
        textArea.setStyle("-fx-fill: black; -fx-font-size: 12px;");
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
        deleteIcon.setFitHeight(20); deleteIcon.setFitWidth(20);
        Button deleteButton = new Button("Delete", deleteIcon);
        deleteButton.setOnAction(e -> textArea.clear());

        ImageView copyIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/copy.png")));
        copyIcon.setFitHeight(20); copyIcon.setFitWidth(20);
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
                showAlert(Alert.AlertType.ERROR, "ERROR", "URL is not valid.",
                        "Please enter a valid URL starting with http:// or https://", "/images/logo.png", null);
                return;
            }

            Invoice invoice = InvoiceChecker.getInvoice(inputUrl);
            boolean exported = CsvWithOpenCsv.writeToCSV(invoice);

            if (exported) {
                showAlert(Alert.AlertType.INFORMATION, "Export to CSV", null,
                        "Invoice data has been exported to CSV!", "/images/logo.png", "/icons/checked.png");
            } else {
                showAlert(Alert.AlertType.ERROR, "Export to CSV", null,
                        "Invoice data has NOT been exported to CSV!", "/images/logo.png", "/icons/cancel.png");
            }
        });

        HBox buttonBox = new HBox(10, deleteButton, copyButton, exportButton);
        AnchorPane.setTopAnchor(buttonBox, 240.0);
        AnchorPane.setLeftAnchor(buttonBox, 50.0);
        root.getChildren().add(buttonBox);

        Image branding = new Image(getClass().getResourceAsStream("/images/company branding.png"));
        ImageView brandingView = new ImageView(branding);
        brandingView.setFitWidth(branding.getWidth() * 0.8);
        brandingView.setFitHeight(branding.getHeight() * 0.8);
        brandingView.setPreserveRatio(true);
        AnchorPane.setBottomAnchor(brandingView, 20.0);
        AnchorPane.setRightAnchor(brandingView, 10.0);
        root.getChildren().add(brandingView);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("MML Radenković");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
        stage.setMaximized(true);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content, String iconPath, String graphicPath) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        if (graphicPath != null) {
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(graphicPath)));
            icon.setFitWidth(48); icon.setFitHeight(48);
            alert.setGraphic(icon);
        }
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
        alert.showAndWait();
    }
}
