package View_Controller;

import Model.Inventory;
import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {

    Inventory inv;

    public ModifyPart(Inventory inv, Part part) {
        this.inv = inv;
    }


    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private ToggleGroup partTG;

    @FXML
    private RadioButton outSourcedRadio;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField companyField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
