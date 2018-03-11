package fr.rtgrenoble.velascof.simtelui.controller.param.base;

import fr.rtgrenoble.velascof.simtelui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AffichageModulationControllerBase extends AffichageControllerBase {

    @FXML
    private CheckBox constellationButton;

    @FXML
    private HBox constellationPane;

    @FXML
    private CheckBox titreConstellationButton;

    @FXML
    private TextField titreConstellationField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        constellationPane.disableProperty().bind(constellationButton.selectedProperty().not());
        titreConstellationField.disableProperty().bind(titreConstellationButton.selectedProperty().not());
        registerNotEmptyValidator(titreConstellationField, constellationButton, titreConstellationButton);
        getValidationSupport().initInitialDecoration();
    }

    @Override
    public boolean validate() {
        return !Main.root.getController().modulerCheckBox.isSelected() || super.validate();
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        if (!json.has(getName())) return;
        super.fromJson(json);
        JSONObject aff = json.getJSONObject(getName());
        if (aff.has("constellation")) {
            constellationButton.setSelected(true);
            fromText(aff.getJSONObject("constellation"), titreConstellationButton, titreConstellationField, "titre");
        }
    }

    @Override
    public void toJson(JSONObject json) {
        super.toJson(json);
        JSONObject aff = json.getJSONObject(getName());
        if (constellationButton.isSelected()) {
            JSONObject constellation = new JSONObject();
            if (titreConstellationButton.isSelected()) constellation.put("titre", titreConstellationField.getText());
            aff.put("constellation", constellation);
        }
    }
}