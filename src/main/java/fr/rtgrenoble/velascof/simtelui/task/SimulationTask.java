package fr.rtgrenoble.velascof.simtelui.task;

import fr.rtgrenoble.velascof.simtelui.Main;
import fr.rtgrenoble.velascof.simtelui.controller.param.IParamController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class SimulationTask extends TaskBase {

    private static final String PATH = "/home/user/python/Simulation-Telecom/src";

    @Override
    protected void call0() throws IOException {
        if (Stream.of(Main.parametres).allMatch(IParamController::validate)) {
            File file = File.createTempFile(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE), null);
            new SaveTask(file).run();
            ProcessBuilder pb = new ProcessBuilder(String.format("%s/fichier.py", PATH), file.getAbsolutePath());
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process p = pb.start();
        } else {
            Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Vérifiez vos informations", ButtonType.OK).show());
        }
    }
}
