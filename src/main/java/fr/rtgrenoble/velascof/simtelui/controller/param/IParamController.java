package fr.rtgrenoble.velascof.simtelui.controller.param;

import javafx.scene.control.TextInputControl;
import org.json.JSONException;
import org.json.JSONObject;

public interface IParamController {

    boolean validate();

    void toJson(JSONObject json);

    void fromJson(JSONObject json) throws JSONException;

    default boolean isInteger(TextInputControl c) {
        try {
            Integer.parseInt(c.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    default boolean isDouble(TextInputControl c) {
        try {
            Double.parseDouble(c.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    default int toInteger(TextInputControl c) {
        try {
            return Integer.parseInt(c.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    default double toDouble(TextInputControl c) {
        try {
            return Double.parseDouble(c.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
