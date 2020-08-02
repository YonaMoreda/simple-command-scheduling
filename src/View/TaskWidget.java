package View;

import Model.TimeFieldType;
import javafx.scene.layout.HBox;


public class TaskWidget extends HBox {

    public TaskWidget() {
        super();
        this.setSpacing(5);
        CommandWidget commandWidget = new CommandWidget();
        TimeWidget hourWidget = new TimeWidget(TimeFieldType.HOUR);
        TimeWidget minuteWidget = new TimeWidget(TimeFieldType.MINUTE);
        TimeWidget secondWidget = new TimeWidget(TimeFieldType.SECOND);
        StartButton startButton = new StartButton(commandWidget, hourWidget, minuteWidget, secondWidget);
        StopButton stopButton = new StopButton();
        startButton.setStopButton(stopButton);
        stopButton.setStartButton(startButton);
        DeleteButton deleteButton = new DeleteButton();
        this.getChildren().addAll(commandWidget, hourWidget, minuteWidget, secondWidget, startButton, stopButton, deleteButton);
    }
}
