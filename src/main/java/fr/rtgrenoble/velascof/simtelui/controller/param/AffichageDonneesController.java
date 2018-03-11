package fr.rtgrenoble.velascof.simtelui.controller.param;

import fr.rtgrenoble.velascof.simtelui.controller.param.base.ParamControllerBase;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.rtgrenoble.velascof.simtelui.Util.toInteger;

public class AffichageDonneesController extends ParamControllerBase {

    @FXML
    private CheckBox afficherSequenceBox;

    @FXML
    private CheckBox afficherRepartitionBox;

    @FXML
    private HBox afficherRepartitionPane;

    @FXML
    private TextField bpsField;

    @Override
    public void toJson(JSONObject json) {
        JSONObject aff = new JSONObject();
        aff.put("sequence", afficherSequenceBox.isSelected());
        if (afficherRepartitionBox.isSelected()) {
            JSONObject repartition = new JSONObject();
            repartition.put("bps", toInteger(bpsField));
            aff.put("repartition", repartition);
        }
        json.put("aff_sequence", aff);
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        JSONObject aff = json.getJSONObject("aff_sequence");
        afficherSequenceBox.setSelected(aff.getBoolean("sequence"));
        if (aff.has("repartition")) {
            afficherRepartitionBox.setSelected(true);
            bpsField.setText(Integer.toString(aff.getJSONObject("repartition").getInt("bps")));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficherRepartitionPane.disableProperty().bind(afficherRepartitionBox.selectedProperty().not());
        registerPositiveIntegerValidator(bpsField, afficherRepartitionBox);
        getValidationSupport().initInitialDecoration();
    }
}
