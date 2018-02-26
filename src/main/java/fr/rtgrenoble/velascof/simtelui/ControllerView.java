package fr.rtgrenoble.velascof.simtelui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ControllerView<T> {

    private final T controller;
    private final Parent view;

    private ControllerView(T controller, Parent view) {
        this.controller = controller;
        this.view = view;
    }

    public static <T> ControllerView<T> of(String res) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("view/" + res));
        Parent parent = loader.load();
        T controller = loader.getController();
        return new ControllerView<>(controller, parent);
    }

    public T getController() {
        return controller;
    }

    public Parent getView() {
        return view;
    }

}
