package fr.rtgrenoble.velascof.simtelui.view;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ConsoleView extends Pane {

    private final TextArea textArea = new TextArea("Fermez les graphiques pour actualiser la console...");

    public ConsoleView() {
        textArea.setEditable(false);
        textArea.setPrefSize(800, 400);
        textArea.setFont(Font.font("monospaced"));
        this.getChildren().add(textArea);
    }

    public void appendLine(String s) {
        textArea.setText(textArea.getText() + System.lineSeparator() + s);
    }

}
