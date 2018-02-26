package fr.rtgrenoble.velascof.simtelui.controller.param;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class DonneesController implements Initializable, IParamController {

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
    }

    @Override
    public boolean validate() {
        if (!isDouble(debitBinaireField)) return false;
        if (group.getSelectedToggle() == donneeButton) {
            return donneeField.getText().matches("[0-1]*");
        } else if (group.getSelectedToggle() == aleatoireButton) {
            return isInteger(aleatoireTailleField);
        } else if (group.getSelectedToggle() == pseudoAleatoireButton) {
            return isInteger(pseudoAleatoireTailleField) && isInteger(repetitionsField);
        }
        return false;
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
            aleatoireTailleField.setText(String.valueOf(sequence.getInt("taille")));
        } else {
            group.selectToggle(pseudoAleatoireButton);
            pseudoAleatoireTailleField.setText(String.valueOf(sequence.getInt("taille")));
            repetitionsField.setText(String.valueOf(sequence.getInt("repetitions")));
        }
        debitBinaireField.setText(String.valueOf(sequence.getDouble("db")));
    }
}
