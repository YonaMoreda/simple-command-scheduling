package View;

import Model.TimeUnit;
import javafx.scene.layout.HBox;

/**
 * TaskWidget is a single row that contains CommandWidget and TimeWidget and their control buttons (start, stop, delete)
 */
public class TaskWidget extends HBox {

    private StartButton startButton;
    private StopButton stopButton;
    private DeleteButton deleteButton;

    public TaskWidget() {
        super();
        this.setSpacing(5);
        CommandWidget commandWidget = new CommandWidget();
        TimeWidget hourWidget = new TimeWidget(TimeUnit.HOUR);
        TimeWidget minuteWidget = new TimeWidget(TimeUnit.MINUTE);
        TimeWidget secondWidget = new TimeWidget(TimeUnit.SECOND);

        initializeControlButtons(commandWidget, hourWidget, minuteWidget, secondWidget);
        this.getChildren().addAll(commandWidget, hourWidget, minuteWidget, secondWidget, startButton, stopButton, deleteButton);
    }

    /**
     * creates control buttons for the widgets
     * @param commandWidget: custom text field widget for handling commands
     * @param hourWidget: hour text field with control buttons
     * @param minuteWidget: minute text field with control buttons
     * @param secondWidget: second text field with control buttons
     */
    private void initializeControlButtons(CommandWidget commandWidget, TimeWidget hourWidget, TimeWidget minuteWidget, TimeWidget secondWidget) {
        startButton = new StartButton(commandWidget, hourWidget, minuteWidget, secondWidget);
        stopButton = new StopButton();
        startButton.setStopButton(stopButton);
        stopButton.setStartButton(startButton);
        deleteButton = new DeleteButton();
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
