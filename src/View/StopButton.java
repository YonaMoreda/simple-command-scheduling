package View;

import javafx.scene.control.Button;

public class StopButton extends Button {

    private StartButton startButton;

    public StopButton(StartButton startButton) {
        super("Stop");
        this.startButton = startButton;
        this.getStyleClass().add("StopButton");
        setEventProperty();
    }

    private void setEventProperty() {
        this.setOnAction(actionEvent -> {
            startButton.getTimer().cancel();
        });
    }
}
