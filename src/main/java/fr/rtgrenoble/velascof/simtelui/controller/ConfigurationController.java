package fr.rtgrenoble.velascof.simtelui.controller;

import fr.rtgrenoble.velascof.simtelui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {

    private File file;

    @FXML
    private CheckBox afficherSortieButton;

    @FXML
    private TextField cheminProgrammeField;

    @FXML
    private Button appliquerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficherSortieButton.setSelected(Main.configuration.isShowScriptOutput());
        cheminProgrammeField.setText(Main.configuration.getScriptFile());
        afficherSortieButton.selectedProperty().addListener((o, oldVal, newVal) -> appliquerButton.setDisable(false));
        cheminProgrammeField.textProperty().addListener((o, oldVal, newVal) -> appliquerButton.setDisable(false));
    }

    @FXML
    void actionParcourir(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        if (file != null) chooser.setInitialDirectory(file.getParentFile());
        chooser.setInitialFileName("fichier.py");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Script Python", "*.py"));
        chooser.setTitle("Sélection du fichier fichier.py");
        file = chooser.showOpenDialog(Main.primaryStage);
        if (file != null) {
            cheminProgrammeField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    void actionOk(ActionEvent event) {
        save();
        Main.configStage.close();
    }

    @FXML
    void actionAnnuler(ActionEvent event) {
        Main.configStage.close();
    }

    @FXML
    void actionAppliquer(ActionEvent event) {
        save();
        appliquerButton.setDisable(true);
    }

    private void save() {
        Main.configuration.setShowScriptOutput(afficherSortieButton.isSelected());
        Main.configuration.setScriptFile(cheminProgrammeField.getText());
        Main.configuration.save();
    }
}
