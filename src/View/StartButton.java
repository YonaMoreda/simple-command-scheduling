package View;

import Controller.StartButtonController;
import javafx.scene.control.Button;

/**
 * StartButton to start timer countdown
 */
public class StartButton extends Button {

    private final CommandWidget commandWidget;
    private final TimeWidget hourWidget;
    private final TimeWidget minuteWidget;
    private final TimeWidget secondWidget;
    private StartButtonController startButtonController;

    public StartButton(CommandWidget commandWidget, TimeWidget hourWidget, TimeWidget minuteWidget, TimeWidget secondWidget) {
        super("Go");
        this.setPrefWidth(33);
        this.commandWidget = commandWidget;
        this.hourWidget = hourWidget;
        this.minuteWidget = minuteWidget;
        this.secondWidget = secondWidget;
    }

    public void setStopButton(StopButton stopButton) {
        startButtonController = new StartButtonController(this, stopButton, commandWidget, hourWidget, minuteWidget, secondWidget);
        startButtonController.setEventProperty();
    }

    public StartButtonController getStartButtonController() {
        return startButtonController;
    }
}
