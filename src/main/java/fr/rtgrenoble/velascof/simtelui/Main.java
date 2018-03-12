package fr.rtgrenoble.velascof.simtelui;

import fr.rtgrenoble.velascof.simtelui.controller.ConfigurationController;
import fr.rtgrenoble.velascof.simtelui.controller.MainWindowController;
import fr.rtgrenoble.velascof.simtelui.controller.param.*;
import fr.rtgrenoble.velascof.simtelui.controller.param.base.ParamControllerBase;
import fr.rtgrenoble.velascof.simtelui.view.ConsoleView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    public static final Configuration configuration = new Configuration();

    public static Stage primaryStage, configStage, consoleStage;
    public static ControllerView<MainWindowController> root;
    public static ControllerView<ConfigurationController> config;
    public static ConsoleView console;
    public static ControllerView<DonneesController> paramDonnees;
    public static ControllerView<AffichageDonneesController> paramAffichageDonnees;
    public static ControllerView<CodageSourceController> paramCodageSource;
    public static ControllerView<AffichageCodageSourceController> paramAffichageCodageSource;
    public static ControllerView<CodageCanalController> paramCodageCanal;
    public static ControllerView<AffichageCodageCanalController> paramAffichageCodageCanal;
    public static ControllerView<CanalTransmissionController> paramCanalTransmission;
    public static ControllerView<AffichageCanalTransmissionController> paramAffichageCanalTransmission;
    public static ControllerView<AffichageDecodageCanalController> paramAffichageDecodageCanal;
    public static ParamControllerBase[] parametres;

    public static File currentFile;

    public static void main(String[] args) {
        if (Configuration.CFG_FILE.exists()) {
            configuration.load();
        } else {
            configuration.loadDefaults();
        }
        launch(args);
    }

    public static void updateTitle() {
        primaryStage.setTitle("Simulation Télécom UI" + (currentFile != null ? " - " + currentFile.getName() : ""));
    }

    public static void start() throws IOException {
        load();
        primaryStage.setScene(new Scene(root.getView()));
        primaryStage.setResizable(false);
        configStage.setScene(new Scene(config.getView()));
        configStage.setResizable(false);
        configStage.setAlwaysOnTop(true);
        configStage.setTitle("Simulation Télécom UI - Configuration");
        consoleStage.setResizable(false);
        consoleStage.setTitle("Simulation Télécom UI - Console");
        updateTitle();
        primaryStage.show();
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
        parametres = new ParamControllerBase[]{paramDonnees.getController(), paramAffichageDonnees.getController(), paramCodageSource.getController(),
                paramAffichageCodageSource.getController(), paramCodageCanal.getController(), paramAffichageCodageCanal.getController(),
                paramCanalTransmission.getController(), paramAffichageCanalTransmission.getController(), paramAffichageDecodageCanal.getController()};
        root = ControllerView.of("MainWindow.fxml");
        config = ControllerView.of("Config.fxml");
    }

    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setOnCloseRequest(event -> {
            configStage.close();
            consoleStage.close();
        });
        configStage = new Stage();
        consoleStage = new Stage();
        start();
    }

    @Override
    public void stop() {
        EXECUTOR_SERVICE.shutdown();
    }

    public static void initConsole() {
        console = new ConsoleView();
        consoleStage.setScene(new Scene(console, 800, 400));
        consoleStage.show();
    }
}
