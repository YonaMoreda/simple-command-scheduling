package View;

import Model.TimeClass;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class StartButton extends Button {

    private final CommandWidget commandWidget;
    private final TimeWidget hourWidget;
    private final TimeWidget minuteWidget;
    private final TimeWidget secondWidget;
    private Timer timer;
    private StopButton stopButton;

    public StartButton(CommandWidget commandWidget, TimeWidget hourWidget, TimeWidget minuteWidget, TimeWidget secondWidget) {
        super("Go");
        this.setPrefWidth(33);
        this.commandWidget = commandWidget;
        this.hourWidget = hourWidget;
        this.minuteWidget = minuteWidget;
        this.secondWidget = secondWidget;
        setEventProperty();
    }

    private void setEventProperty() {
        this.setOnAction(actionEvent -> {
            commandWidget.setStyle("-fx-border-color: transparent");
            createAndStartTimer();
            this.setDisable(true);
            stopButton.setDisable(false);
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

                    if (totalTimeInSec == 0) {
                        timer.cancel();
                        stopButton.setDisable(true);
                        getStartButton().setDisable(false);
                        commandWidget.setStyle("-fx-border-color: greenyellow");

                        String[] cmdAndArgs = commandWidget.getText().split("\\s+"); // TODO:: SUPPORT FOR && and |
                        ProcessBuilder processBuilder = new ProcessBuilder(cmdAndArgs);

                        processBuilder.redirectErrorStream(true);
                        try {
                            Process process = processBuilder.start();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                            File outputFile = new File("shell_output.log");
                            outputFile.createNewFile();
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, true));

                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime currentTime = LocalDateTime.now();

                            bufferedWriter.write(">> Command: \"" + commandWidget.getText() +  "\"\n" +
                                    ">> Time: " + dtf.format(currentTime) + "\n" +
                                    ">> Output: \n");


                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                bufferedWriter.write(line.concat("\n"));
                            }
                            bufferedWriter.write(">> END\n\n");
                            try {
                                process.waitFor();
                            } catch (InterruptedException e) {
                                displayErrorAlert(e);
                            }
                            try {
                                bufferedReader.close();
                                bufferedWriter.close();
                            } catch (IOException e) {
                                displayErrorAlert(e);
                            }
                        } catch (IOException e) {
                            displayErrorAlert(e);
                        }
                    }
                });
            }
        }, delay, period);
    }

    private void displayErrorAlert(Exception e) {
        commandWidget.setStyle("-fx-border-color: red");
        timer.cancel();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - " + e.getMessage());
        alert.setHeaderText("Failed to execute given command.");
        alert.setContentText(e.getLocalizedMessage());
        Scene scene = alert.getDialogPane().getScene();
        scene.getStylesheets().add("Styles/DarkMode.css");
        Stage stage = (Stage) scene.getWindow();
        stage.getIcons().add(new Image("icon.png"));
        alert.showAndWait();
    }

    public StartButton getStartButton() {
        return this;
    }


    public Timer getTimer() {
        return timer;
    }

    public void setStopButton(StopButton stopButton) {
        this.stopButton = stopButton;
    }
}
