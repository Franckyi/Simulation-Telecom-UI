package fr.rtgrenoble.velascof.simtelui.task;

import javafx.application.Platform;
import javafx.concurrent.Task;
import org.controlsfx.dialog.ExceptionDialog;

public abstract class TaskBase extends Task<Void> {

    @Override
    protected Void call() {
        try {
            call0();
        } catch (Throwable t) {
            t.printStackTrace();
            Platform.runLater(() -> new ExceptionDialog(t).show());
        }
        return null;
    }

    protected abstract void call0() throws Throwable;

}
