package utility.tools.bycodekokeshi;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;


public class _02_ShutdownController implements Initializable {

    // Maximum characters allowed in the duration text field
    private static final int MaximumCharacters = 3;

    // GUI Components
    @FXML Button Shutdown;
    @FXML Label Status;
    @FXML TextField Duration;

    // Hard coded GUI Component For Countdown Animation
    Stage countdownStage;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        Tooltip DurationToolTip = new Tooltip("Enter the duration in minutes before the computer shuts down.");
        Duration.setTooltip(DurationToolTip);
        Tooltip ShutdownToolTip = new Tooltip("Finalize input and initiate shutdown.");
        Shutdown.setTooltip(ShutdownToolTip);
        Platform.runLater(() -> Status.requestFocus());

        // We make use of the character limitation by adding a listener to the duration text field.
        Duration.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > MaximumCharacters) {
                Duration.setText(oldValue);
            }
        });
    }

    // This method is called when the shutdown button is clicked.
    @FXML
    private void ShutdownMethod() {
        // We check if the duration text field is empty.
        if (Duration.getText().isEmpty()) {
            Status.setText("Please enter a duration.");
        } else {
            // If the duration text field is not empty, we create a confirmation dialog.
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);

            // We set the modality of the confirmation dialog to APPLICATION_MODAL, so the user cannot interact with the main window until the dialog is closed.
            confirmationDialog.initModality(Modality.APPLICATION_MODAL);

            // We set the title of the confirmation dialog.
            confirmationDialog.setTitle("Confirm Shutdown");

            // We set the header text of the confirmation dialog.
            confirmationDialog.setHeaderText("Are you sure you want to shutdown?");

            // We set the content text of the confirmation dialog.
            confirmationDialog.setContentText("This action is irreversible and not stackable.");

            // We show the confirmation dialog and wait for the user to click a button.
            confirmationDialog.showAndWait().ifPresent(response -> {

                // This will check if the user clicked the OK button.
                if (response == javafx.scene.control.ButtonType.OK) {
                    try {
                        // We first set our input (duration) to an integer. And name it durationInMinutes.
                        int durationInMinutes = Integer.parseInt(Duration.getText());

                        // We then convert the duration in minutes to seconds. We need to use seconds for the shutdown command. Shutdown -s -t <time in seconds>
                        int durationInSeconds = durationInMinutes * 60;

                        // We then initiate the shutdown.
                        initiateShutdown();

                        // Then we initiate the countdown animation.
                        initiateCountdown(durationInMinutes, durationInSeconds);
                    } catch (Exception e) {
                        // If anything goes wrong, this stuff will be executed.
                        Status.setText("Error: Unable to initiate shutdown.");
                        System.out.println(e.getMessage());
                    }
                }

                // If the user didn't click the OK button, we set the status label to "Shutdown canceled."
                else {
                    Status.setText("Shutdown canceled.");
                }
            });
        }
    }

    // This method is responsible for the countdown initiation.
    private void initiateCountdown(int durationInMinutes, int durationInSeconds) {
        // We first hide the shutdown window.
        Scene sceneMain = Status.getScene();
        Window windowMain = sceneMain.getWindow();
        Stage stageMain = (Stage) windowMain;
        stageMain.hide();

        // Then we create a new stage for the countdown animation.
        countdownStage = new Stage();
        countdownStage.initModality(Modality.APPLICATION_MODAL);

        // Make the window undecorated so the user cannot close it unless forcefully terminated.
        countdownStage.initStyle(StageStyle.UNDECORATED);
        countdownStage.setResizable(false);

        // When the countdown window is closed (forcefully), the application will be terminated.
        countdownStage.setOnCloseRequest(event -> System.exit(0));

        Label countdownLabel = new Label();

        // Set the countdown timer text color to white and later on, we will set our background to black to make the text even more readable.
        countdownLabel.setTextFill(Color.WHITE);

        // This one is the message that the user needs to see.
        Label irreversibleText = new Label("This process is irreversible and not stackable,\nonce executed, it is not cancellable.\n\nEven if the application was forcefully terminated.");

        // We also set the text color to white and center align the text.
        irreversibleText.setTextFill(Color.WHITE);
        irreversibleText.setTextAlignment(TextAlignment.CENTER);


        // We create a VBox layout with a spacing of 10 and center align the elements in the VBox.
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // We add the countdown label and the irreversible text to the layout.
        layout.getChildren().addAll(countdownLabel, irreversibleText);

        // We set the background color of the layout to black.
        layout.setStyle("-fx-background-color: black;");

        // We then set the scene with the VBox layout
        countdownStage.setScene(new Scene(layout, 400, 200));
        countdownStage.show();
        Timeline timeline = getTimeline(durationInMinutes, durationInSeconds, countdownLabel);

        // We play the animation.
        timeline.play();

    }


    // This method is responsible for the countdown animation.
    private Timeline getTimeline(int durationInMinutes, int durationInSeconds, Label countdownLabel) {
        AtomicInteger atomicDurationInSeconds = new AtomicInteger(durationInSeconds);

        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            int remainingSeconds = atomicDurationInSeconds.getAndDecrement();
            if (remainingSeconds <= 0) {
                countdownStage.close();
            } else {
                int minutes = remainingSeconds / 60;
                int seconds = remainingSeconds % 60;
                countdownLabel.setText(String.format("Countdown: "+"%02d:%02d", minutes, seconds));
            }
        }));
        timeline.setCycleCount(durationInMinutes * 60);
        return timeline;
    }

    // This one shutdown the computer based on the duration.
    private void initiateShutdown() {
        try {
            int durationInMinutes = Integer.parseInt(Duration.getText());
            int durationInSeconds = durationInMinutes * 60;
            // We initiate the shutdown command Runtime Execution (it's like typing in CMD, Terminal or Run)
            Runtime.getRuntime().exec("shutdown -s -t " + durationInSeconds);
            Status.setText("Shutdown initiated. Computer will shutdown in " + durationInMinutes + " minutes.");
        } catch (Exception e) {
            Status.setText("Error: Unable to initiate shutdown.");
            System.out.println(e.getMessage());
        }
    }
}
