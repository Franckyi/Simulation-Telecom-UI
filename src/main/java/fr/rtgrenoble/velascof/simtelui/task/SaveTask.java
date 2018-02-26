package fr.rtgrenoble.velascof.simtelui.task;

import fr.rtgrenoble.velascof.simtelui.Main;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class SaveTask extends TaskBase {

    private File file;

    public SaveTask(File file) {
        this.file = file;
    }

    @Override
    protected void call0() throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        JSONObject jsonObject = new JSONObject();
        Stream.of(Main.parametres).sequential().forEach(controller -> controller.toJson(jsonObject));
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonObject.toString());
        }
    }
}
