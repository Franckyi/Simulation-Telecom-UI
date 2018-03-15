package fr.rtgrenoble.velascof.simtelui.task;

import fr.rtgrenoble.velascof.simtelui.Main;
import fr.rtgrenoble.velascof.simtelui.controller.param.base.ParamControllerBase;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class SimulationTask extends TaskBase {

    public static Process p;

    @Override
    protected void call0() throws IOException {
        if (Stream.of(Main.parametres).allMatch(ParamControllerBase::validate)) {
            File file = File.createTempFile(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE), null);
            new SaveTask(file).run();
            ProcessBuilder pb = new ProcessBuilder(Main.configuration.getScriptFile(), file.getAbsolutePath());
            if (!Main.configuration.isShowScriptOutput()) {
                pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            }
            p = pb.start();
            if (Main.configuration.isShowScriptOutput()) {
                Platform.runLater(Main::initConsole);
                try (BufferedReader input = new BufferedReader(new InputStreamReader(new SequenceInputStream(p.getInputStream(), p.getErrorStream())))) {
                    String line;
                    while ((line = input.readLine()) != null) {
                        String finalLine = line;
                        Platform.runLater(() -> Main.console.appendLine(finalLine));
                    }
                    input.close();
                } catch (IOException e) {
                    Platform.runLater(() -> {
                        Main.root.getController().changerBoutonsSimulation(false);
                        Main.consoleStage.close();
                    });

                }
            }
            while (p.isAlive()) {
            }
            Platform.runLater(() -> Main.root.getController().changerBoutonsSimulation(false));
        } else {
            Platform.runLater(() -> {
                Main.root.getController().changerBoutonsSimulation(false);
                Main.consoleStage.close();
                new Alert(Alert.AlertType.ERROR, "Certaines informations sont erronnées.", ButtonType.OK).show();
            });
        }
    }

}
