package fr.rtgrenoble.velascof.simtelui;

import fr.rtgrenoble.velascof.simtelui.controller.MainWindowController;
import fr.rtgrenoble.velascof.simtelui.controller.param.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public static Stage stage;
    public static ControllerView<MainWindowController> root;
    public static ControllerView<DonneesController> paramDonnees;
    public static ControllerView<AffichageDonneesController> paramAffichageDonnees;
    public static ControllerView<CodageSourceController> paramCodageSource;
    public static ControllerView<AffichageCodageSourceController> paramAffichageCodageSource;
    public static ControllerView<CodageCanalController> paramCodageCanal;
    public static ControllerView<AffichageCodageCanalController> paramAffichageCodageCanal;
    public static ControllerView<CanalTransmissionController> paramCanalTransmission;
    public static ControllerView<AffichageCanalTransmissionController> paramAffichageCanalTransmission;
    public static ControllerView<AffichageDecodageCanalController> paramAffichageDecodageCanal;
    public static IParamController[] parametres;

    public static File currentFile;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        start();
    }

    public static void updateTitle() {
        stage.setTitle("Simulation Télécom UI" + (currentFile != null ? " - " + currentFile.getName() : ""));
    }

    public static void start() throws IOException {
        load();
        stage.setScene(new Scene(root.getView()));
        stage.setResizable(false);
        updateTitle();
        stage.show();
    }

    private static void load() throws IOException {
        paramDonnees = ControllerView.of("param/Donnees.fxml");
        paramAffichageDonnees = ControllerView.of("param/AffichageDonnees.fxml");
        paramCodageSource = ControllerView.of("param/CodageSource.fxml");
        paramAffichageCodageSource = ControllerView.of("param/AffichageCodageSource.fxml");
        paramCodageCanal = ControllerView.of("param/CodageCanal.fxml");
        paramAffichageCodageCanal = ControllerView.of("param/AffichageCodageCanal.fxml");
        paramCanalTransmission = ControllerView.of("param/CanalTransmission.fxml");
        paramAffichageCanalTransmission = ControllerView.of("param/AffichageCanalTransmission.fxml");
        paramAffichageDecodageCanal = ControllerView.of("param/AffichageDecodageCanal.fxml");
        parametres = new IParamController[]{paramDonnees.getController(), paramAffichageDonnees.getController(), paramCodageSource.getController(),
                paramAffichageCodageSource.getController(), paramCodageCanal.getController(), paramAffichageCodageCanal.getController(),
                paramCanalTransmission.getController(), paramAffichageCanalTransmission.getController(), paramAffichageDecodageCanal.getController()};
        root = ControllerView.of("MainWindow.fxml");
    }

}
