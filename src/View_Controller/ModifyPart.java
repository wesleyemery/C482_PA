package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
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
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {

    Inventory inv;
    Part part;

    public ModifyPart(Inventory inv, Part part) {
        this.inv = inv;
        this.part = part;
    }

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private ToggleGroup partType;

    @FXML
    private RadioButton outsourcedRadio;

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
    private HBox companyBox;

    @FXML
    private TextField companyField;

    @FXML
    private Label companyLabel;

    @FXML
    private Label machineLabel;

    @FXML
    private HBox machineBox;

    @FXML
    private TextField machineField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancelButtonAction(ActionEvent event) {
        backToMain(event);

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
            stage.setTitle("Main Screen");
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
        int id = Integer.parseInt(idField.getText()); // Part that'll be updated
        String name = nameField.getText(); // No need to parse since input already passed in as String
        double price = Double.parseDouble(priceField.getText());
        int inventory= Integer.parseInt(invField.getText());
        int max = Integer.parseInt(maxField.getText());
        int min = Integer.parseInt(minField.getText());
        if (Integer.parseInt(minField.getText()) > Integer.parseInt(maxField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error! Minimum");
            alert.setContentText("Min amount cannot be greater than the max allowed.");
            alert.show();
            return;
        } else if (Integer.parseInt(invField.getText().trim()) < Integer.parseInt(minField.getText().trim()) || Integer.parseInt(invField.getText().trim()) > Integer.parseInt(maxField.getText().trim())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error! Inventory");
            alert.setContentText("Inventory must be in between min and max allowed.");
            alert.show();
            return;

        } else if (outsourcedRadio.isSelected() && companyField.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: Company");
            alert.setContentText("Company must be listed.");
            alert.show();
            return;
        } if (inHouseRadio.isSelected()) {
            int machineID = Integer.parseInt(machineField.getText());
            InHouse inHouseAddedPart = new InHouse(id, name,  inventory, price, min, max, machineID);
            Inventory.deletePart(part);
            Inventory.addPart(inHouseAddedPart);
        } else if (outsourcedRadio.isSelected()) {
            String compName = companyField.getText();
            Outsourced outsourcedAddedPart = new Outsourced(id, name, price, inventory, max, min, compName);
            Inventory.deletePart(part);
            Inventory.addPart(outsourcedAddedPart);
        }
        backToMain(event);
    }

    private void setData () {

        if (part instanceof InHouse) {
            InHouse part1 = (InHouse) part;
            inHouseRadio.setSelected(true);
            this.idField.setText(String.valueOf(part1.getPartID()));
            this.nameField.setText(String.valueOf(part1.getPartName()));
            this.priceField.setText(String.valueOf(part1.getPartPrice()));
            this.invField.setText(String.valueOf(part1.getPartInvLevel()));
            this.maxField.setText(String.valueOf(part1.getPartMax()));
            this.minField.setText(String.valueOf(part1.getPartMin()));
            this.machineField.setText(String.valueOf(((InHouse) part1).getMachineId()));
            inHouseRadio.setSelected(true);
            machineBox.setVisible(true);
            companyBox.setVisible(false);

        }
        if (part instanceof Outsourced) {
            Outsourced part2 = (Outsourced) part;
            outsourcedRadio.setSelected(true);
            this.idField.setText(String.valueOf(part2.getPartID()));
            this.nameField.setText(String.valueOf(part2.getPartName()));
            this.priceField.setText(String.valueOf(part2.getPartPrice()));
            this.invField.setText(String.valueOf(part2.getPartInvLevel()));
            this.maxField.setText(String.valueOf(part2.getPartMax()));
            this.minField.setText(String.valueOf(part2.getPartMin()));
            this.companyField.setText(String.valueOf(((Outsourced) part2).getCompanyName()));
            outsourcedRadio.setSelected(true);
            companyField.setVisible(true);
            machineBox.setVisible(false);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
    }
}
