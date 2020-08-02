package View;


import Model.TimeClass;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class StartButton extends Button {

    private final CommandWidget commandWidget;
    private final TimeWidget hourWidget;
    private final TimeWidget minuteWidget;
    private final TimeWidget secondWidget;
    private Timer timer;
    private StopButton stopButton;

    public StartButton(CommandWidget commandWidget, TimeWidget hourWidget, TimeWidget minuteWidget, TimeWidget secondWidget) {
        super("Go");
        this.commandWidget = commandWidget;
        this.hourWidget = hourWidget;
        this.minuteWidget = minuteWidget;
        this.secondWidget = secondWidget;
        setEventProperty();
    }

    private void setEventProperty() {
        this.setOnAction(actionEvent -> {
            createAndStartTimer();
            this.setDisable(true);
            stopButton.setDisable(false);
        });
    }

    private void createAndStartTimer() {
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    int totalTimeInSec = hourWidget.getTextFieldTime() * 3600 + minuteWidget.getTextFieldTime() * 60 + secondWidget.getTextFieldTime();
                    TimeClass timeClass = new TimeClass(totalTimeInSec - 1);
                    hourWidget.setTextFieldTime(timeClass.getHours());
                    minuteWidget.setTextFieldTime(timeClass.getMinutes());
                    secondWidget.setTextFieldTime(timeClass.getSeconds());

                    if(totalTimeInSec == 0) {
                        Runtime runtime = Runtime.getRuntime();
                        try {
                            runtime.exec(commandWidget.getText());
                        } catch (IOException | IllegalArgumentException e) {
                            displayErrorAlert(e);
                        }
                        timer.cancel();
                    }
                });
            }
        }, delay, period);
    }

    private void displayErrorAlert(Exception e) {
        timer.cancel();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - " + e.getMessage());
        alert.setHeaderText("Failed to execute given command.");
        alert.setContentText(e.getLocalizedMessage());
        Scene scene = alert.getDialogPane().getScene();
        scene.getStylesheets().add("Styles/DarkMode.css");
        Stage stage = (Stage) scene.getWindow();
        stage.getIcons().add(new Image("icon.png"));
        alert.showAndWait();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setStopButton(StopButton stopButton) {
        this.stopButton = stopButton;
    }
}
