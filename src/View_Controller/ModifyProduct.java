package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import com.sun.org.apache.xml.internal.security.Init;
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
import java.util.ResourceBundle;

import static Model.Inventory.getAllParts;

public class ModifyProduct implements Initializable {

    public ModifyProduct(Product product, Inventory inv) {
        this.product = product;
        this.inv = inv;
    }

    Product product;
    Inventory inv;
    Product updatedProduct = null;
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Part> AssociatedParts;


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

    @FXML
    void cancelButtonAction(ActionEvent event) {
        backToMain(event);

    }

    @FXML
    void deletePart(ActionEvent event) {
        if(associatedParts.getSelectionModel().isEmpty()){
            return;
        }else{
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

    private void addAssociatedParts(Part part){
        partIDColumn1.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn1.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInvLevel1.setCellValueFactory(new PropertyValueFactory<>("partInvLevel"));
        partPrice1.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        associatedParts.getItems().add(part);

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
                Product addedProduct = new Product(id, name, price, inventory, max, min);
                Inventory.deleteProduct(product);
                Inventory.addProduct(addedProduct);
                backToMain(event);

            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        //AssociatedParts = FXCollections.observableArrayList();
        //associatedParts.setItems(AssociatedParts);
        AssociatedParts = product.getAssociatedParts();
        associatedParts.setItems(AssociatedParts);

        //refreshAssociatedView();

        this.idField.setText(String.valueOf(product.getProductID()));
        this.nameField.setText(String.valueOf(product.getProductName()));
        this.priceField.setText(String.valueOf(product.getProductPrice()));
        this.invField.setText(String.valueOf(product.getProductInvLevel()));
        this.maxField.setText(String.valueOf(product.getProductMax()));
        this.minField.setText(String.valueOf(product.getProductMin()));


    }

    //Update & Refresh Table Methods
    private void partsUpdate(){ partsTable.setItems(getAllParts()); }
    private void refreshTableView() { partsTable.refresh(); }
    //private void refreshAssociatedView() { associatedParts.refresh(); }
}
