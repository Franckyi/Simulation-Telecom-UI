package fr.rtgrenoble.velascof.simtelui.controller.param.base;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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
    }

}