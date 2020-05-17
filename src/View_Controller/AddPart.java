package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static Model.Inventory.getAllParts;

public class AddPart implements Initializable {

    Inventory inv;

    public AddPart(Inventory inv) {
        this.inv = inv;
    }

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private HBox machineBox;

    @FXML
    private Label machineLabel;

    @FXML
    private TextField machineField;

    @FXML
    private HBox companyBox;

    @FXML
    private TextField companyField;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private ToggleGroup partType;

    @FXML
    private RadioButton outsourcedRadio;


    @FXML
    void cancelButtonAction(ActionEvent event) {
        String message = "Are you sure you want to cancel?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT: Cancel");
        alert.setHeaderText("Confirm");
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            backToMain(event);
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
    void partTypeRotate(ActionEvent event) {
        if (inHouseRadio.isSelected()) {
            machineBox.setVisible(true);
            companyBox.setVisible(false);
        }
        if (outsourcedRadio.isSelected()) {
            companyBox.setVisible(true);
            machineBox.setVisible(false);

        }

    }

    @FXML
    void saveButtonAction(ActionEvent event) {
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

        } else if (outsourcedRadio.isSelected() && companyField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: Company");
            alert.setContentText("Company must be listed.");
            alert.show();
            return;
        } else if (inHouseRadio.isSelected()) {
            int machineID = Integer.parseInt(machineField.getText());
            InHouse inHouseAddedPart = new InHouse(id, name, price, inventory, min, max, machineID);
            Inventory.addPart(inHouseAddedPart);
            backToMain(event);
        } else if (outsourcedRadio.isSelected()) {
            String compName = companyField.getText();
            Outsourced outsourcedAddedPart = new Outsourced(id, name, price, inventory, min, max, compName);
            Inventory.deletePart(outsourcedAddedPart);
            Inventory.addPart(outsourcedAddedPart);
            backToMain(event);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inHouseRadio.setSelected(true);
        machineBox.setVisible(true);
        companyBox.setVisible(false);
        idField.setEditable(false);
        idField.setDisable(true);
        //Auto generate id based on number of products
        idField.setText(String.valueOf(getAllParts().size() + 1));
    }
}
