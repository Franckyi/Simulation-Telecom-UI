package fr.rtgrenoble.velascof.simtelui.controller;

import fr.rtgrenoble.velascof.simtelui.Main;
import fr.rtgrenoble.velascof.simtelui.task.LoadTask;
import fr.rtgrenoble.velascof.simtelui.task.SaveTask;
import fr.rtgrenoble.velascof.simtelui.task.SimulationTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    public MenuItem menuEnregistrer;

    @FXML
    public CheckBox coderCheckBox;

    @FXML
    public CheckBox modulerCheckBox;

    @FXML
    private Button codageSourceButton;

    @FXML
    private Button affichageCodageSourceButton;

    @FXML
    private Button codageCanalButton;

    @FXML
    private Button affichageCodageCanalButton;

    @FXML
    private Button affichageCanalTransmissionButton;

    @FXML
    private Button decodageSourceButton;

    @FXML
    private Button affichageDecodageCanalButton;

    @FXML
    private Button decodageCanalButton;

    @FXML
    private ScrollPane paramPane;

    @FXML
    void actionMenuNouveau(ActionEvent event) throws IOException {
        Main.currentFile = null;
        Main.start();

    }

    @FXML
    void actionMenuEnregistrer(ActionEvent event) {
        if (Main.currentFile != null) {
            Main.EXECUTOR_SERVICE.submit(new SaveTask(Main.currentFile));
        } else {
            menuEnregistrer.setDisable(true);
        }
    }

    @FXML
    void actionMenuEnregistrerSous(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialFileName("simulation.json");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier de simulation JSON", "*.json"));
        chooser.setTitle("Enregistrer sous...");
        File file = chooser.showSaveDialog(Main.stage);
        if (file != null) {
            Main.EXECUTOR_SERVICE.submit(new SaveTask(file));
            Main.currentFile = file;
            Main.updateTitle();
            menuEnregistrer.setDisable(false);
        }
    }

    @FXML
    void actionMenuOuvrir(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier de simulation JSON", "*.json"));
        chooser.setTitle("Ouvrir...");
        File file = chooser.showOpenDialog(Main.stage);
        if (file != null) {
            Main.EXECUTOR_SERVICE.submit(new LoadTask(file));
            Main.currentFile = file;
            Main.updateTitle();
            menuEnregistrer.setDisable(false);
        }
    }

    @FXML
    void actionMenuSimuler(ActionEvent event) {
        simuler();
    }

    @FXML
    void actionAffichageCodageCanal(ActionEvent event) {
        paramPane.setContent(Main.paramAffichageCodageCanal.getView());
    }

    @FXML
    void actionAffichageDonnees(ActionEvent event) {
        paramPane.setContent(Main.paramAffichageDonnees.getView());
    }

    @FXML
    void actionAffichageCodageSource(ActionEvent event) {
        paramPane.setContent(Main.paramAffichageCodageSource.getView());
    }

    @FXML
    void actionAffichageCanalTransmission(ActionEvent event) {
        paramPane.setContent(Main.paramAffichageCanalTransmission.getView());
    }

    public void actionAffichageDecodageCanal(ActionEvent event) {
        paramPane.setContent(Main.paramAffichageDecodageCanal.getView());
    }

    @FXML
    void actionCodageCanal(ActionEvent event) {
        paramPane.setContent(Main.paramCodageCanal.getView());
    }

    @FXML
    void actionDonnees(ActionEvent event) {
        paramPane.setContent(Main.paramDonnees.getView());
    }

    @FXML
    void actionCodageSource(ActionEvent event) {
        paramPane.setContent(Main.paramCodageSource.getView());
    }

    @FXML
    void actionCanalTransmission(ActionEvent event) {
        paramPane.setContent(Main.paramCanalTransmission.getView());
    }

    @FXML
    void actionSimulation(ActionEvent event) {
        simuler();
    }

    @FXML
    void actionMenuAide(ActionEvent event) {
        WizardPane pane0 = new WizardPane();
        pane0.setHeaderText("Aide");
        pane0.setContentText("Bienvenue dans la fenêtre d'aide. Pour l'instant, elle est un peu vide, mais elle devrait se remplir !");
        pane0.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        WizardPane pane1 = new WizardPane();
        pane1.setHeaderText("Affichage");
        pane1.setContentText("Dans les onglets 'affichage' (les boutons flèchés), vous pouvez choisir ce que vous voulez afficher.\n" +
                "Les paramètres supplémentaires sont optionels, et sont à cocher seulement si vous voulez remplacer la valeur par défaut !\n" +
                "A noter que pour les valeurs numériques (Vmax, Tmin, Fmax,...), ces valeurs par défaut sont calculées lors de la simulation. Elles ne valent pas 0.0.");
        pane1.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        Wizard wizard = new Wizard();
        wizard.setTitle("Aide");
        wizard.setFlow(new Wizard.LinearFlow(pane0, pane1));
        wizard.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paramPane.setContent(Main.paramDonnees.getView());
        codageSourceButton.disableProperty().bind(coderCheckBox.selectedProperty().not());
        affichageCodageSourceButton.disableProperty().bind(coderCheckBox.selectedProperty().not());
        decodageSourceButton.disableProperty().bind(coderCheckBox.selectedProperty().not());
        affichageDecodageCanalButton.disableProperty().bind(coderCheckBox.selectedProperty().not());
        codageCanalButton.disableProperty().bind(modulerCheckBox.selectedProperty().not());
        affichageCodageCanalButton.disableProperty().bind(modulerCheckBox.selectedProperty().not());
        decodageCanalButton.disableProperty().bind(modulerCheckBox.selectedProperty().not());
        affichageCanalTransmissionButton.disableProperty().bind(modulerCheckBox.selectedProperty().not());
    }

    private void simuler() {
        Main.EXECUTOR_SERVICE.execute(new SimulationTask());
    }

}
