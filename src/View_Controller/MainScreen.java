package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;

public class MainScreen implements Initializable {

    Inventory inv;
    Product product;
    private static Part modifyPart = null;
    private static Product modifyProduct = null;
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();

    public MainScreen(Inventory inv){ this.inv = inv;}


    @FXML
    private Button addPartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInvLevel;

    @FXML
    private TableColumn<Part, Double> partPrice;

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInvLevel;

    @FXML
    private TableColumn<Product, Double> productPrice;

    @FXML
    private Button partSearchButton;

    @FXML
    private TextField partsSearch;

    @FXML
    private Button addProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button productSearchButton;

    @FXML
    private TextField productsSearch;

    @FXML
    private Button exitButton;

    //Inventory Add Methods
    @FXML public void addPart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        AddPart controller = new AddPart(inv);

        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Add Part");
        stage.setResizable(false);
        stage.show();
    }

    @FXML public void addProduct(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        AddProduct controller = new AddProduct(inv, product);

        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Add Product");
        stage.setResizable(false);
        stage.show();/*
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); */
    }

    //Inventory Delete Methods
    @FXML public void deletePart(ActionEvent event) {
        Part partDelete = partsTable.getSelectionModel().getSelectedItem();
        if(partDelete != null){
            String message = "Are you sure you want to delete " + partsTable.getSelectionModel().getSelectedItem().getPartName() + "?";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT: Delete Part Selected");
            alert.setHeaderText("Confirm");
            alert.setContentText(message);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                inv.deletePart(partDelete);
                partsUpdate();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Please select Part");
            alert.showAndWait();
        }
    }

    @FXML void deleteProduct(ActionEvent event) {
        Product productDelete = productsTable.getSelectionModel().getSelectedItem();
        if(productDelete != null) {
            String message = "Are you sure you want to delete " + productsTable.getSelectionModel().getSelectedItem().getProductName() + "?";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT: Delete Part Selected");
            alert.setHeaderText("Confirm");
            alert.setContentText(message);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                inv.deleteProduct(productDelete);
                productsUpdate();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText(" Please select Part");
            alert.showAndWait();
        }
    }


    //Inventory Lookup Methods
    @FXML
    void lookupPart(ActionEvent event) {
        String search = partsSearch.getText();
        if(!search.isEmpty()){
            for (Part part: inv.getAllParts()){
                if(part.getPartName().contains(partsSearch.getText())){
                    partInventorySearch.add(part);
                }
            }
             partsTable.setItems(partInventorySearch);
             partsTable.refresh();

        } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Part not found");
                alert.setContentText("Does not exist.");
                alert.showAndWait();
                }
    }

    @FXML
    void lookupProduct(ActionEvent event) {
        String search = productsSearch.getText();
        if(!search.isEmpty()){
            for(Product product: inv.getAllProducts()){
                if(product.getProductName().contains(productsSearch.getText())){
                    productInventorySearch.add(product);
                }
            }
            productsTable.setItems(productInventorySearch);
            productsTable.refresh();

        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Product not found");
            alert.setContentText("Does not exist.");
            alert.showAndWait();
        }
    }

    //Inventory Update Methods
    @FXML private void updatePart(ActionEvent event) throws IOException {
        modifyPart = partsTable.getSelectionModel().getSelectedItem();
        if (modifyPart != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
            ModifyPart controller = new ModifyPart(inv, modifyPart);

            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Modify Part");
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText(" Please select Part");
            alert.showAndWait();
        }
    }

    @FXML private void updateProduct(ActionEvent event) throws IOException{
        modifyProduct = productsTable.getSelectionModel().getSelectedItem();
        if (modifyProduct != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
            ModifyProduct controller = new ModifyProduct(modifyProduct, inv);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Modify Product");

            stage.show();
            /*
            Parent modif = loader.load();
            Scene modifyScene = new Scene(modifyParent);
            Stage modifyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            modifyStage.setResizable(false);
            modifyStage.setTitle("Modify Product Inventory");
            modifyStage.setScene(modifyScene);
            modifyStage.show();*/

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText(" Please select Product");
            alert.showAndWait();
        }

    }

    //Terminate Program Method
    @FXML void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Alert: Exiting Inventory Management Program");
        alert.setContentText("Would you like to continue?");

        Optional<ButtonType> button = alert.showAndWait();
        if (button.get() == ButtonType.OK){
            Platform.exit();
        }

    }
    //MainScreen Initialization
    @Override public void initialize(URL url, ResourceBundle rb) {
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInvLevel.setCellValueFactory(new PropertyValueFactory<>("partInvLevel"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productInvLevel.setCellValueFactory(new PropertyValueFactory<>("productInvLevel"));
        partsUpdate();
        productsUpdate();
        refreshTableView();
    }
    //Update & Refresh Table Methods
    private void partsUpdate(){
        partsTable.setItems(getAllParts());
    }
    private void productsUpdate(){
        productsTable.setItems(getAllProducts());
    }

    private void refreshTableView() {
        partsTable.refresh();
        productsTable.refresh();
    }

}
