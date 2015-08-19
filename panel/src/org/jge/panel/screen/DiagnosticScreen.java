package org.jge.panel.screen;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class DiagnosticScreen extends Screen {

    private final long upTime;
    private final long freeMemory;
    private final long usedMemory;
    private final long totalMemory;
    private final String exceptions;
    private static final long MEGABYTE = 1024 * 1024;

    public DiagnosticScreen(long upTime, long freeMemory, long usedMemory, long totalMemory, String exceptions) {


        this.upTime = upTime;
        this.exceptions = exceptions;

        this.freeMemory = freeMemory / MEGABYTE;

        this.usedMemory = usedMemory / MEGABYTE;
        this.totalMemory = totalMemory / MEGABYTE;
    }

    @Override
    protected Scene buildScene() {
        Label header = new Label("Diagnostic Window");
        header.setLayoutX(136);
        header.setLayoutY(14);

        Label exceptionsHeader = new Label("Exceptions");
        exceptionsHeader.setLayoutX(14);
        exceptionsHeader.setLayoutY(41);


        Label uptime = new Label("Uptime: " + parseUptime());
        uptime.setLayoutX(14);
        uptime.setLayoutY(250);

        Label memory = new Label(usedMemory + "mb/" + totalMemory + "mb (" + freeMemory + "mb avail)");
        if (freeMemory < 50) {
            memory.setTextFill(Color.RED);
        } else {
            memory.setTextFill(Color.GREEN);
        }
        memory.setLayoutX(14);
        memory.setLayoutY(270);
        TextArea exceptions = new TextArea();
        exceptions.setLayoutX(14);
        exceptions.setLayoutY(60);
        exceptions.setPrefWidth(350);
        exceptions.setFocusTraversable(false);
        exceptions.setText(this.exceptions);

        Pane pane = new Pane(header, exceptionsHeader, exceptions, uptime, memory);
        exceptions.focusedProperty().addListener((listener) -> {
                    pane.requestFocus();
                }
        );
        Scene scene = new Scene(pane, 375, 400);
        return scene;

    }

    private Label buildMemoryLabel() {
        Label label = new Label(usedMemory + "mb/" + totalMemory + "mb (" + freeMemory + "mb avail)");
        if (freeMemory < 50) {
            label.setTextFill(Color.RED);
        } else {
            label.setTextFill(Color.GREEN);
        }
        return label;
    }


    private String parseUptime() {
        final long second = TimeUnit.MILLISECONDS.toSeconds(upTime) % 60;
        final long minute = TimeUnit.MILLISECONDS.toMinutes(upTime) % 60;
        final long hour = TimeUnit.MILLISECONDS.toHours(upTime) % 24;
        final long days = TimeUnit.MILLISECONDS.toDays(upTime) % 365;
        String msg = second + " seconds.";
        if (minute > 0) {
            msg = minute + " minutes, " + msg;
        }
        if (hour > 0) {
            msg = hour + " hours, " + msg;
        }
        if (days > 0) {
            msg = days + " days, " + msg;
        }
        return msg;
    }
}
