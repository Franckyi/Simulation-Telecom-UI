package fr.rtgrenoble.velascof.simtelui.controller.param.base;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

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
