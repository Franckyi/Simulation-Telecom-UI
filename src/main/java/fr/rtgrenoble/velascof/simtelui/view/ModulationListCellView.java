package fr.rtgrenoble.velascof.simtelui.view;

import fr.rtgrenoble.velascof.simtelui.model.ModulationListItem;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ModulationListCellView extends ListCell<ModulationListItem> {

    private ModulationListItem model;

    private HBox hBox = new HBox(10);
    private Label label = new Label();
    private TextField textField = new TextField();

    public ModulationListCellView() {
        this.setPrefHeight(30);
        label.setLabelFor(textField);
        textField.setPrefWidth(50);
        textField.textProperty().addListener((v, oldVal, newVal) -> {
            try {
                model.setValue(Double.parseDouble(textField.getText()));
                model.setValid(true);
            } catch (NumberFormatException e) {
                model.setValid(false);
            }
        });
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPrefHeight(30);
        hBox.getChildren().addAll(label, textField);
    }

    @Override
    protected void updateItem(ModulationListItem modulationListItem, boolean b) {
        super.updateItem(modulationListItem, b);
        this.model = modulationListItem;
        if (b) {
            this.setGraphic(null);
        } else {
            label.setText(modulationListItem.getName());
            textField.setText(String.valueOf(modulationListItem.getValue()));
            this.setGraphic(hBox);
        }
    }

}
