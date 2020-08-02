package View;

import javafx.scene.control.Button;

public class StopButton extends Button {

    private StartButton startButton;

    public StopButton() {
        super("Stop");
        this.setDisable(true);
        this.prefWidth(70);
        this.getStyleClass().add("StopButton");
        setEventProperty();
    }

    public void setStartButton(StartButton startButton) {
        this.startButton = startButton;
    }

    private void setEventProperty() {
        this.setOnAction(actionEvent -> {
            startButton.getTimer().cancel();
            this.setDisable(true);
            startButton.setDisable(false);
        });
    }
}
