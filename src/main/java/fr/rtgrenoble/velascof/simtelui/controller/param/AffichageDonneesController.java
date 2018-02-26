package fr.rtgrenoble.velascof.simtelui.controller.param;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import org.json.JSONException;
import org.json.JSONObject;

public class AffichageDonneesController implements IParamController {

    @FXML
    private CheckBox afficherSequenceBox;

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void toJson(JSONObject json) {
        json.getJSONObject("sequence").put("aff", afficherSequenceBox.isSelected());
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        afficherSequenceBox.setSelected(json.getJSONObject("sequence").getBoolean("aff"));
    }
}
