package Controller;

import View.TaskWidget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static javafx.geometry.Pos.CENTER_RIGHT;

/**
 * controller class for view (layout.fxml)
 */
public class MainController {

    @FXML
    private VBox main_VBox;

    /**
     * setting actions for events
     */
    @FXML
    public void initialize() {
        Button appendTaskButton = new Button("Append a new task");
        Button startAllButton = createStartAllButton();
        Button stopAllButton = createStopAllButton();
        Button deleteAllButton = createDeleteAllButton();

        HBox hBox = new HBox(startAllButton, stopAllButton, deleteAllButton);
        hBox.setSpacing(5);
        hBox.setAlignment(CENTER_RIGHT);
        main_VBox.getChildren().add(0, hBox);
        main_VBox.getChildren().add(appendTaskButton);

        appendTaskButton.setOnAction(actionEvent -> {
            TaskWidget taskWidget = new TaskWidget();
            main_VBox.getChildren().remove(appendTaskButton);
            main_VBox.getChildren().add(taskWidget);
            main_VBox.getChildren().add(appendTaskButton);
        });
    }

    /**
     * triggers delete button for each task widget to delete all tasks
     * @return: initialized delete all button with its event activity
     */
    private Button createDeleteAllButton() {
        Button deleteAllButton = new Button("Delete All");
        deleteAllButton.setOnAction(actionEvent -> {
            int size = main_VBox.getChildren().size();
            for(int i = 0; i < size; i++) {
                Node node = main_VBox.getChildren().get(i);
                if (node.getClass().getSimpleName().equals("TaskWidget")) {
                    ((TaskWidget) node).getDeleteButton().fire();
                    size--;
                    i--;
                }
            }
        });
        return deleteAllButton;
    }

    /**
     * triggers stop button for each task widget to stop all tasks
     * @return: initialized stop all button with its event activity
     */
    private Button createStopAllButton() {
        Button stopAllButton = new Button("Stop All");
        stopAllButton.setOnAction(actionEvent -> {
            for (Node node : main_VBox.getChildren()) {
                if (node.getClass().getSimpleName().equals("TaskWidget")) {
                    ((TaskWidget) node).getStopButton().fire();
                }
            }
        });
        return stopAllButton;
    }

    /**
     * triggers start button for each task widget to start all tasks
     * @return: initialized start all button with its event activity
     */
    private Button createStartAllButton() {
        Button startAllButton = new Button("Start All");
        startAllButton.setOnAction(actionEvent -> {
            for (Node node : main_VBox.getChildren()) {
                if (node.getClass().getSimpleName().equals("TaskWidget")) {
                    ((TaskWidget) node).getStartButton().fire();
                }
            }
        });
        return startAllButton;
    }
}
