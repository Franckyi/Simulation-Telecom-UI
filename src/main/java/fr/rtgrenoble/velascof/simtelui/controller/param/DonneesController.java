package fr.rtgrenoble.velascof.simtelui.controller.param;

import fr.rtgrenoble.velascof.simtelui.controller.param.base.ParamControllerBase;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.rtgrenoble.velascof.simtelui.Util.toDouble;
import static fr.rtgrenoble.velascof.simtelui.Util.toInteger;

public class DonneesController extends ParamControllerBase {

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton donneeButton;

    @FXML
    private HBox donneePane;

    @FXML
    private TextField donneeField;

    @FXML
    private VBox aleatoirePane;

    @FXML
    private RadioButton aleatoireButton;

    @FXML
    private TextField aleatoireTailleField;

    @FXML
    private VBox pseudoAleatoirePane;

    @FXML
    private RadioButton pseudoAleatoireButton;

    @FXML
    private TextField pseudoAleatoireTailleField;

    @FXML
    private TextField repetitionsField;

    @FXML
    private TextField debitBinaireField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        donneePane.disableProperty().bind(donneeButton.selectedProperty().not());
        aleatoirePane.disableProperty().bind(aleatoireButton.selectedProperty().not());
        pseudoAleatoirePane.disableProperty().bind(pseudoAleatoireButton.selectedProperty().not());
        registerBinaryStringValidator(donneeField, donneeButton);
        registerPositiveIntegerValidator(aleatoireTailleField, aleatoireButton);
        registerPositiveIntegerValidator(pseudoAleatoireTailleField, pseudoAleatoireButton);
        registerPositiveIntegerValidator(repetitionsField, pseudoAleatoireButton);
        registerPositiveDoubleValidator(debitBinaireField);
        getValidationSupport().initInitialDecoration();
    }

    @Override
    public void toJson(JSONObject json) {
        JSONObject sequence = new JSONObject();
        if (group.getSelectedToggle() == donneeButton) {
            sequence.put("seq", donneeField.getText());
        } else if (group.getSelectedToggle() == aleatoireButton) {
            sequence.put("taille", toInteger(aleatoireTailleField));
        } else if (group.getSelectedToggle() == pseudoAleatoireButton) {
            sequence.put("taille", toInteger(pseudoAleatoireTailleField));
            sequence.put("repetitions", toInteger(repetitionsField));
        }
        sequence.put("db", toDouble(debitBinaireField));
        json.put("sequence", sequence);
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        JSONObject sequence = json.getJSONObject("sequence");
        if (sequence.has("seq")) {
            group.selectToggle(donneeButton);
            donneeField.setText(sequence.getString("seq"));
        } else if (!sequence.has("repetitions")) {
            group.selectToggle(aleatoireButton);
            aleatoireTailleField.setText(Integer.toString(sequence.getInt("taille")));
        } else {
            group.selectToggle(pseudoAleatoireButton);
            pseudoAleatoireTailleField.setText(Integer.toString(sequence.getInt("taille")));
            repetitionsField.setText(Integer.toString(sequence.getInt("repetitions")));
        }
        debitBinaireField.setText(Double.toString(sequence.getDouble("db")));
    }
}
