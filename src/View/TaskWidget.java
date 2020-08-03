package View;

import Model.TimeFieldType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class TaskWidget extends HBox {

    private StartButton startButton;
    private StopButton stopButton;
    private DeleteButton deleteButton;

    public TaskWidget() {
        super();
        this.setSpacing(5);
        CommandWidget commandWidget = new CommandWidget();
        TimeWidget hourWidget = new TimeWidget(TimeFieldType.HOUR);
        TimeWidget minuteWidget = new TimeWidget(TimeFieldType.MINUTE);
        TimeWidget secondWidget = new TimeWidget(TimeFieldType.SECOND);
        startButton = new StartButton(commandWidget, hourWidget, minuteWidget, secondWidget);
        stopButton = new StopButton();
        startButton.setStopButton(stopButton);
        stopButton.setStartButton(startButton);
        deleteButton = new DeleteButton();
        this.getChildren().addAll(commandWidget, hourWidget, minuteWidget, secondWidget, startButton, stopButton, deleteButton);
    }

    public StartButton getStartButton() {
        return startButton;
    }

    public StopButton getStopButton() {
        return stopButton;
    }

    public DeleteButton getDeleteButton() {
        return deleteButton;
    }
}
