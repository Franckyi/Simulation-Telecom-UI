package fr.rtgrenoble.velascof.simtelui.task;

import fr.rtgrenoble.velascof.simtelui.Main;
import javafx.application.Platform;
import org.controlsfx.dialog.ExceptionDialog;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class LoadTask extends TaskBase {

    private File file;

    public LoadTask(File file) {
        this.file = file;
    }

    @Override
    protected void call0() throws IOException {
        StringBuilder s = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                s.append(line);
            }
        }
        JSONObject json = new JSONObject(s.toString());
        Platform.runLater(() -> Stream.of(Main.parametres).forEach(controller -> {
            try {
                controller.fromJson(json);
            } catch (JSONException e) {
                new ExceptionDialog(e).show();
            }
        }));
    }

}
