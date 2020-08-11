package View;

import Controller.StopButtonController;
import javafx.scene.control.Button;

/**
 * Stop button to stop count down timer
 */
public class StopButton extends Button {

    private StopButtonController stopButtonController;

    public StopButton() {
        super("Stop");
        this.setDisable(true);
        this.prefWidth(70);
        this.getStyleClass().add("StopButton");
    }

    public void setStartButton(StartButton startButton) {
        stopButtonController = new StopButtonController(startButton, this);
        stopButtonController.setEventProperty();
    }
}
