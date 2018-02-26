package fr.rtgrenoble.velascof.simtelui.controller.param;

import fr.rtgrenoble.velascof.simtelui.Main;
import fr.rtgrenoble.velascof.simtelui.controller.param.base.AffichageModulationControllerBase;
import org.json.JSONException;
import org.json.JSONObject;

public class AffichageCanalTransmissionController extends AffichageModulationControllerBase {

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void toJson(JSONObject json) {
        if (!Main.root.getController().modulerCheckBox.isSelected()) return;

    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        if (json.has("transmission")) {

        }
    }
}
