package fr.rtgrenoble.velascof.simtelui.controller.param.base;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.rtgrenoble.velascof.simtelui.Util.toDouble;

public abstract class AffichageControllerBase extends ParamControllerBase {

    @FXML
    protected CheckBox chronogrammeButton;

    @FXML
    protected VBox chronogrammePane;

    @FXML
    protected CheckBox tMinButton;

    @FXML
    protected TextField tMinField;

    @FXML
    protected CheckBox tMaxButton;

    @FXML
    protected TextField tMaxField;

    @FXML
    protected CheckBox vMinChronogrammeButton;

    @FXML
    protected TextField vMinChronogrammeField;

    @FXML
    protected CheckBox vMaxChronogrammeButton;

    @FXML
    protected TextField vMaxChronogrammeField;

    @FXML
    protected CheckBox xLegendChronogrammeButton;

    @FXML
    protected TextField xLegendChronogrammeField;

    @FXML
    protected CheckBox yLegendChronogrammeButton;

    @FXML
    protected TextField yLegendChronogrammeField;

    @FXML
    protected CheckBox titreChronogrammeButton;

    @FXML
    protected TextField titreChronogrammeField;

    @FXML
    protected CheckBox spectreButton;

    @FXML
    protected VBox spectrePane;

    @FXML
    protected CheckBox fMinButton;

    @FXML
    protected TextField fMinField;

    @FXML
    protected CheckBox fMaxButton;

    @FXML
    protected TextField fMaxField;

    @FXML
    protected CheckBox vMinSpectreButton;

    @FXML
    protected TextField vMinSpectreField;

    @FXML
    protected CheckBox vMaxSpectreButton;

    @FXML
    protected TextField vMaxSpectreField;

    @FXML
    protected CheckBox xLegendSpectreButton;

    @FXML
    protected TextField xLegendSpectreField;

    @FXML
    protected CheckBox yLegendSpectreButton;

    @FXML
    protected TextField yLegendSpectreField;

    @FXML
    protected CheckBox titreSpectreButton;

    @FXML
    protected TextField titreSpectreField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chronogrammePane.disableProperty().bind(chronogrammeButton.selectedProperty().not());
        tMinField.disableProperty().bind(tMinButton.selectedProperty().not());
        tMaxField.disableProperty().bind(tMaxButton.selectedProperty().not());
        vMinChronogrammeField.disableProperty().bind(vMinChronogrammeButton.selectedProperty().not());
        vMaxChronogrammeField.disableProperty().bind(vMaxChronogrammeButton.selectedProperty().not());
        xLegendChronogrammeField.disableProperty().bind(xLegendChronogrammeButton.selectedProperty().not());
        yLegendChronogrammeField.disableProperty().bind(yLegendChronogrammeButton.selectedProperty().not());
        titreChronogrammeField.disableProperty().bind(titreChronogrammeButton.selectedProperty().not());
        spectrePane.disableProperty().bind(spectreButton.selectedProperty().not());
        fMinField.disableProperty().bind(fMinButton.selectedProperty().not());
        fMaxField.disableProperty().bind(fMaxButton.selectedProperty().not());
        vMinSpectreField.disableProperty().bind(vMinSpectreButton.selectedProperty().not());
        vMaxSpectreField.disableProperty().bind(vMaxSpectreButton.selectedProperty().not());
        xLegendSpectreField.disableProperty().bind(xLegendSpectreButton.selectedProperty().not());
        yLegendSpectreField.disableProperty().bind(yLegendSpectreButton.selectedProperty().not());
        titreSpectreField.disableProperty().bind(titreSpectreButton.selectedProperty().not());
        registerPositiveDoubleValidator(tMinField, chronogrammeButton, tMinButton);
        registerPositiveDoubleValidator(tMaxField, chronogrammeButton, tMaxButton);
        registerDoubleValidator(vMinChronogrammeField, chronogrammeButton, vMinChronogrammeButton);
        registerDoubleValidator(vMaxChronogrammeField, chronogrammeButton, vMaxChronogrammeButton);
        registerNotEmptyValidator(xLegendChronogrammeField, chronogrammeButton, xLegendChronogrammeButton);
        registerNotEmptyValidator(yLegendChronogrammeField, chronogrammeButton, yLegendChronogrammeButton);
        registerNotEmptyValidator(titreChronogrammeField, chronogrammeButton, titreChronogrammeButton);
        registerPositiveDoubleValidator(fMinField, spectreButton, fMinButton);
        registerPositiveDoubleValidator(fMaxField, spectreButton, fMaxButton);
        registerDoubleValidator(vMinSpectreField, spectreButton, vMinSpectreButton);
        registerDoubleValidator(vMaxSpectreField, spectreButton, vMaxSpectreButton);
        registerNotEmptyValidator(xLegendSpectreField, spectreButton, xLegendSpectreButton);
        registerNotEmptyValidator(yLegendSpectreField, spectreButton, yLegendSpectreButton);
        registerNotEmptyValidator(titreSpectreField, spectreButton, titreSpectreButton);
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        JSONObject aff = json.getJSONObject(getName());
        if (aff.has("chronogramme")) {
            chronogrammeButton.setSelected(true);
            JSONObject chronogramme = aff.getJSONObject("chronogramme");
            fromDouble(chronogramme, tMinButton, tMinField, "tmin");
            fromDouble(chronogramme, tMaxButton, tMaxField, "tmax");
            fromDouble(chronogramme, vMinChronogrammeButton, vMinChronogrammeField, "vmin");
            fromDouble(chronogramme, vMaxChronogrammeButton, vMaxChronogrammeField, "vmax");
            fromText(chronogramme, xLegendChronogrammeButton, xLegendChronogrammeField, "xlegend");
            fromText(chronogramme, yLegendChronogrammeButton, yLegendChronogrammeField, "ylegend");
            fromText(chronogramme, titreChronogrammeButton, titreChronogrammeField, "titre");
        }
        if (aff.has("spectre")) {
            spectreButton.setSelected(true);
            JSONObject spectre = aff.getJSONObject("spectre");
            fromDouble(spectre, fMinButton, fMinField, "tmin");
            fromDouble(spectre, fMaxButton, fMaxField, "tmax");
            fromDouble(spectre, vMinSpectreButton, vMinSpectreField, "vmin");
            fromDouble(spectre, vMaxSpectreButton, vMaxSpectreField, "vmax");
            fromText(spectre, xLegendSpectreButton, xLegendSpectreField, "xlegend");
            fromText(spectre, yLegendSpectreButton, yLegendSpectreField, "ylegend");
            fromText(spectre, titreSpectreButton, titreSpectreField, "titre");
        }
    }

    protected void fromText(JSONObject json, CheckBox b, TextInputControl c, String name) {
        if (json.has(name)) {
            b.setSelected(true);
            c.setText(json.getString(name));
        }
    }

    protected void fromDouble(JSONObject json, CheckBox b, TextInputControl c, String name) {
        if (json.has(name)) {
            b.setSelected(true);
            c.setText(Double.toString(json.getDouble(name)));
        }
    }

    @Override
    public void toJson(JSONObject json) {
        JSONObject aff = new JSONObject();
        if (chronogrammeButton.isSelected()) {
            JSONObject chronogramme = new JSONObject();
            if (tMinButton.isSelected()) chronogramme.put("tmin", toDouble(tMinField));
            if (tMaxButton.isSelected()) chronogramme.put("tmax", toDouble(tMaxField));
            if (vMinChronogrammeButton.isSelected()) chronogramme.put("vmin", toDouble(vMinChronogrammeField));
            if (vMaxChronogrammeButton.isSelected()) chronogramme.put("vmax", toDouble(vMaxChronogrammeField));
            if (xLegendChronogrammeButton.isSelected()) chronogramme.put("xlegend", xLegendChronogrammeField.getText());
            if (yLegendChronogrammeButton.isSelected()) chronogramme.put("ylegend", yLegendChronogrammeField.getText());
            if (titreChronogrammeButton.isSelected()) chronogramme.put("titre", titreChronogrammeField.getText());
            aff.put("chronogramme", chronogramme);
        }
        if (spectreButton.isSelected()) {
            JSONObject spectre = new JSONObject();
            if (fMinButton.isSelected()) spectre.put("fmin", toDouble(fMinField));
            if (fMaxButton.isSelected()) spectre.put("fmax", toDouble(fMaxField));
            if (vMinSpectreButton.isSelected()) spectre.put("vmin", toDouble(vMinSpectreField));
            if (vMaxSpectreButton.isSelected()) spectre.put("vmax", toDouble(vMaxSpectreField));
            if (xLegendSpectreButton.isSelected()) spectre.put("xlegend", xLegendSpectreField.getText());
            if (yLegendSpectreButton.isSelected()) spectre.put("ylegend", yLegendSpectreField.getText());
            if (titreSpectreButton.isSelected()) spectre.put("titre", titreSpectreField.getText());
            aff.put("spectre", spectre);
        }
        json.put(getName(), aff);
    }

    protected abstract String getName();

}
