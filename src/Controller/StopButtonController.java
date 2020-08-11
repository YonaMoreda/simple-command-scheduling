package Controller;

import View.StartButton;
import View.StopButton;

/**
 * Controller for Stop Button, stops countdown timer and toggles the StartButton to be available
 */
public class StopButtonController {

    private final StartButton startButton;
    private final StopButton stopButton;

    public StopButtonController(StartButton startButton, StopButton stopButton) {
        this.startButton = startButton;
        this.stopButton = stopButton;
    }

    public void setEventProperty() {
        stopButton.setOnAction(actionEvent -> {
            startButton.getStartButtonController().getTimer().cancel();
            stopButton.setDisable(true);
            startButton.setDisable(false);
        });
    }
}
