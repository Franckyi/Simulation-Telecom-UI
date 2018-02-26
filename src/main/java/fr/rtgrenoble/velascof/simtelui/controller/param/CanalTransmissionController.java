package fr.rtgrenoble.velascof.simtelui.controller.param;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class CanalTransmissionController implements IParamController, Initializable {

    @FXML
    private CheckBox bruitButton;

    @FXML
    private ToggleGroup bruit;

    @FXML
    private VBox bruitPane;

    @FXML
    private RadioButton bruitGaussienButton;

    @FXML
    private RadioButton bruitBlancButton;

    @FXML
    private TextField intensiteField;

    @FXML
    private CheckBox filtreButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bruitPane.disableProperty().bind(bruitButton.selectedProperty().not());
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void toJson(JSONObject json) {

    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {

    }
}
