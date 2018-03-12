package fr.rtgrenoble.velascof.simtelui.controller.param;

import fr.rtgrenoble.velascof.simtelui.Main;
import fr.rtgrenoble.velascof.simtelui.controller.param.base.ParamControllerBase;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.rtgrenoble.velascof.simtelui.Util.toDouble;

public class CodageSourceController extends ParamControllerBase {

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton nrzButton;

    @FXML
    private HBox nrzPane;

    @FXML
    private TextField nrzV0Field;

    @FXML
    private TextField nrzV1Field;

    @FXML
    private RadioButton rzButton;

    @FXML
    private HBox rzPane;

    @FXML
    private TextField rzV0Field;

    @FXML
    private TextField rzV1Field;

    @FXML
    private RadioButton manchesterButton;

    @FXML
    private HBox manchesterPane;

    @FXML
    private TextField manchesterVpField;

    @FXML
    private TextField manchesterVmField;

    @FXML
    private RadioButton _2b1qButton;

    @FXML
    private HBox _2b1qPane;

    @FXML
    private TextField _2b1qV00Field;

    @FXML
    private TextField _2b1qV01Field;

    @FXML
    private TextField _2b1qV10Field;

    @FXML
    private TextField _2b1qV11Field;

    @FXML
    private TextField fEchField;

    @Override
    public boolean validate() {
        return !Main.root.getController().getCoderCheckBox().isSelected() || super.validate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nrzPane.disableProperty().bind(nrzButton.selectedProperty().not());
        rzPane.disableProperty().bind(rzButton.selectedProperty().not());
        manchesterPane.disableProperty().bind(manchesterButton.selectedProperty().not());
        _2b1qPane.disableProperty().bind(_2b1qButton.selectedProperty().not());
        registerDoubleValidator(nrzV0Field, nrzButton);
        registerDoubleValidator(nrzV1Field, nrzButton);
        registerDoubleValidator(rzV0Field, rzButton);
        registerDoubleValidator(rzV1Field, rzButton);
        registerDoubleValidator(manchesterVpField, manchesterButton);
        registerDoubleValidator(manchesterVmField, manchesterButton);
        registerDoubleValidator(_2b1qV00Field, _2b1qButton);
        registerDoubleValidator(_2b1qV01Field, _2b1qButton);
        registerDoubleValidator(_2b1qV10Field, _2b1qButton);
        registerDoubleValidator(_2b1qV11Field, _2b1qButton);
        registerPositiveDoubleValidator(fEchField);
        getValidationSupport().initInitialDecoration();
    }

    @Override
    public void toJson(JSONObject json) {
        if (!Main.root.getController().getCoderCheckBox().isSelected()) return;
        JSONObject codage = new JSONObject();
        if (group.getSelectedToggle() == nrzButton) {
            codage.put("type", "nrz");
            codage.put("v0", toDouble(nrzV0Field));
            codage.put("v1", toDouble(nrzV1Field));
        } else if (group.getSelectedToggle() == rzButton) {
            codage.put("type", "rz");
            codage.put("v0", toDouble(rzV0Field));
            codage.put("v1", toDouble(rzV1Field));
        } else if (group.getSelectedToggle() == manchesterButton) {
            codage.put("type", "manchester");
            codage.put("vp", toDouble(manchesterVpField));
            codage.put("vm", toDouble(manchesterVmField));
        } else if (group.getSelectedToggle() == _2b1qButton) {
            codage.put("type", "2b1q");
            codage.put("v00", toDouble(_2b1qV00Field));
            codage.put("v01", toDouble(_2b1qV01Field));
            codage.put("v10", toDouble(_2b1qV10Field));
            codage.put("v11", toDouble(_2b1qV11Field));
        }
        codage.put("fech", toDouble(fEchField));
        json.put("codage", codage);
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        if (!json.has("codage")) {
            Main.root.getController().getCoderCheckBox().setSelected(false);
            return;
        }
        JSONObject codage = json.getJSONObject("codage");
        switch (codage.getString("type")) {
            case "nrz":
                group.selectToggle(nrzButton);
                nrzV0Field.setText(Double.toString(codage.getDouble("v0")));
                nrzV1Field.setText(Double.toString(codage.getDouble("v1")));
                break;
            case "rz":
                group.selectToggle(rzButton);
                rzV0Field.setText(Double.toString(codage.getDouble("v0")));
                rzV1Field.setText(Double.toString(codage.getDouble("v1")));
                break;
            case "manchester":
                group.selectToggle(manchesterButton);
                manchesterVpField.setText(Double.toString(codage.getDouble("vp")));
                manchesterVmField.setText(Double.toString(codage.getDouble("vm")));
                break;
            case "2b1q":
                group.selectToggle(_2b1qButton);
                _2b1qV00Field.setText(Double.toString(codage.getDouble("v00")));
                _2b1qV01Field.setText(Double.toString(codage.getDouble("v01")));
                _2b1qV10Field.setText(Double.toString(codage.getDouble("v10")));
                _2b1qV11Field.setText(Double.toString(codage.getDouble("v11")));
                break;
        }
        fEchField.setText(Double.toString(codage.getDouble("fech")));
    }
}
