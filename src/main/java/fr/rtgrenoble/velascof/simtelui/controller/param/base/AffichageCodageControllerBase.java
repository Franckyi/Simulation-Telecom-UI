package fr.rtgrenoble.velascof.simtelui.controller.param.base;

import fr.rtgrenoble.velascof.simtelui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.rtgrenoble.velascof.simtelui.Util.toInteger;

public abstract class AffichageCodageControllerBase extends AffichageControllerBase {

    @FXML
    protected CheckBox diagrammeOeilButton;

    @FXML
    protected VBox diagrammeOeilPane;

    @FXML
    protected TextField nbYeuxField;

    @FXML
    protected CheckBox titreDiagrammeOeilButton;

    @FXML
    protected TextField titreDiagrammeOeilField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        diagrammeOeilPane.disableProperty().bind(diagrammeOeilButton.selectedProperty().not());
        titreDiagrammeOeilField.disableProperty().bind(titreDiagrammeOeilButton.selectedProperty().not());
        registerIntegerValidator(nbYeuxField, diagrammeOeilButton);
        registerNotEmptyValidator(titreDiagrammeOeilField, diagrammeOeilButton, titreDiagrammeOeilButton);
        getValidationSupport().initInitialDecoration();
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        if (!json.has(getName())) return;
        super.fromJson(json);
        JSONObject aff = json.getJSONObject(getName());
        if (aff.has("diagramme_oeil")) {
            diagrammeOeilButton.setSelected(true);
            JSONObject diag = aff.getJSONObject("diagramme_oeil");
            nbYeuxField.setText(Integer.toString(diag.getInt("nb_yeux")));
            fromText(diag, titreDiagrammeOeilButton, titreDiagrammeOeilField, "titre");
        }
    }

    @Override
    public boolean validate() {
        return !Main.root.getController().getCoderCheckBox().isSelected() || super.validate();
    }

    @Override
    public void toJson(JSONObject json) {
        super.toJson(json);
        JSONObject aff = json.getJSONObject(getName());
        if (diagrammeOeilButton.isSelected()) {
            JSONObject diag = new JSONObject();
            diag.put("nb_yeux", toInteger(nbYeuxField));
            if (titreDiagrammeOeilButton.isSelected()) diag.put("titre", titreDiagrammeOeilField.getText());
            aff.put("diagramme_oeil", diag);
        }
    }

}
