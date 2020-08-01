package View;


import Model.TimeClass;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class StartButton extends Button {

    private CommandWidget commandWidget;
    private TimeWidget hourWidget;
    private TimeWidget minuteWidget;
    private TimeWidget secondWidget;
    private Timer timer;

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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        timer.cancel();
                    }
                });
            }
        }, delay, period);
    }

    public Timer getTimer() {
        return timer;
    }
}
