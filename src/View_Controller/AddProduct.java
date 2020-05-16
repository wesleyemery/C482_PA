package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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

public class AddProduct implements Initializable {

    Product product;
    Inventory inv;
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Part> AssociatedParts = FXCollections.observableArrayList();;


    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField priceField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

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
    private TableView<Part> associatedParts;

    @FXML
    private TableColumn<Part, Integer> partIDColumn1;

    @FXML
    private TableColumn<Part, String> partNameColumn1;

    @FXML
    private TableColumn<Part, Integer> partInvLevel1;

    @FXML
    private TableColumn<Part, Double> partPrice1;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    public AddProduct(Inventory inv, Product product) {
        this.inv = inv;
        this.product = product;

    }


    @FXML
    void addPart(ActionEvent event) {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Part associatedPart = partsTable.getSelectionModel().getSelectedItem();
            addAssociatedParts(associatedPart);
            associatedParts.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: Add");
            alert.setContentText("A part must be selected in order to add.");
            alert.show();
        }

    }

    private void backToMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            MainScreen controller = new MainScreen(inv);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        backToMain(event);
    }

    @FXML
    void deletePart(ActionEvent event) {
        if(associatedParts.getSelectionModel().isEmpty()){
            return;
        }else {
            Part p = associatedParts.getSelectionModel().getSelectedItem();
            AssociatedParts.remove(p);
        }

    }

    @FXML
    void lookupPart(ActionEvent event) {
        String search = searchField.getText();
        if(!search.isEmpty()){
            for (Part part: inv.getAllParts()){
                if(part.getPartName().contains(searchField.getText())){
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
    void saveButtonAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int inventory = Integer.parseInt(invField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText());
            if (Integer.parseInt(minField.getText()) > Integer.parseInt(maxField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error! Minimum");
                alert.setContentText("Min amount cannot be greater than the max allowed.");
                alert.show();
                return;
            } else if (Integer.parseInt(invField.getText().trim()) < Integer.parseInt(minField.getText().trim()) || Integer.parseInt(invField.getText().trim()) > Integer.parseInt(maxField.getText().trim())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error! Inventory");
                alert.setContentText("Inventory must be in between min and max allowed.");
                alert.show();
                return;
            } else {
                Product product = new Product(id, name, price, inventory, max, min);
                Inventory.addProduct(product);
                backToMain(event);

            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Sets up Parts table
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInvLevel.setCellValueFactory(new PropertyValueFactory<>("partInvLevel"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        partsUpdate();
        refreshTableView();

        partIDColumn1.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn1.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInvLevel1.setCellValueFactory(new PropertyValueFactory<>("partInvLevel"));
        partPrice1.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

    }

    private void addAssociatedParts(Part part){
        //Sets up Associated Table
        partIDColumn1.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn1.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInvLevel1.setCellValueFactory(new PropertyValueFactory<>("partInvLevel"));
        partPrice1.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        associatedParts.getItems().add(part);

    }

    //Update & Refresh Table Methods
    private void partsUpdate(){
        partsTable.setItems(getAllParts());
    }
    //private void associatedUpdate(){ associatedTable.setItems(getAllParts()); }
    private void refreshTableView() {
        partsTable.refresh();
    }

}
