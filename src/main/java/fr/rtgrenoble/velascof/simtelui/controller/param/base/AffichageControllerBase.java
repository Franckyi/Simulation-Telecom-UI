package fr.rtgrenoble.velascof.simtelui.controller.param.base;

import fr.rtgrenoble.velascof.simtelui.controller.param.IParamController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AffichageControllerBase implements Initializable, IParamController {

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
    }

}
