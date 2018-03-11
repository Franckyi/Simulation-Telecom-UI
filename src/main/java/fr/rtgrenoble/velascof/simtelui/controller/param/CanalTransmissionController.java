package fr.rtgrenoble.velascof.simtelui.controller.param;

import fr.rtgrenoble.velascof.simtelui.controller.param.base.ParamControllerBase;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.rtgrenoble.velascof.simtelui.Util.toDouble;

public class CanalTransmissionController extends ParamControllerBase {

    @FXML
    private CheckBox bruitButton;

    @FXML
    private ToggleGroup bruit;

    @FXML
    private VBox bruitPane;

    @FXML
    private RadioButton bruitGaussienButton;

    @FXML
    private RadioButton bruitAleatoireButton;

    @FXML
    private TextField intensiteField;

    @FXML
    private CheckBox filtreButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bruitPane.disableProperty().bind(bruitButton.selectedProperty().not());
        registerPositiveDoubleValidator(intensiteField, bruitButton);
        getValidationSupport().initInitialDecoration();
    }

    @Override
    public void toJson(JSONObject json) {
        JSONObject canal = new JSONObject();
        if (bruitButton.isSelected()) {
            JSONObject bruit = new JSONObject();
            bruit.put("type", bruitGaussienButton.isSelected() ? "gaussien" : "aleatoire");
            bruit.put("intensite", toDouble(intensiteField.getText()));
            canal.put("bruit", bruit);
        }
        json.put("canal", canal);
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        JSONObject canal = json.getJSONObject("canal");
        if (canal.has("bruit")) {
            bruitButton.setSelected(true);
            JSONObject bruit = canal.getJSONObject("bruit");
            switch (bruit.getString("type")) {
                case "gaussien":
                    bruitGaussienButton.setSelected(true);
                    break;
                case "aleatoire":
                    bruitAleatoireButton.setSelected(true);
                    break;
            }
            intensiteField.setText(Double.toString(bruit.getDouble("intensite")));
        }
    }
}
