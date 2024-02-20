package utility.tools.bycodekokeshi;

import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class _01_MainWindow implements Initializable {
    // GUI Components
    @FXML Label TitleLabel; // Title Label (Not really necessary, but we can use it to set focus on so that the user can see the whole window without the initial focus on a field or somewhere.)
    @FXML Button ShutdownTimerButton; // Shutdown Timer Button
    @FXML Button OpenWebID; // Open Chris Titus Tech
    @FXML Button PWGen; // Password Generator
    @FXML Button FileConcealer; // File Concealer
    @FXML Button AccurateFileRemoverButton; // Accurate File Remover
    @FXML Button AccountManager; // Account Manager
    @FXML Button ArrayGenerator; // Java Array Generator

    // Initialize method is a type of method that executes at the start of the class.
    @Override
    public void initialize(URL url, ResourceBundle bundle){
        // We set the focus of the GUI to the Title Label, so the GUI will not start within or above any button or text field.
        Platform.runLater(() -> {
            TitleLabel.requestFocus();
        });

        // We create a tooltip here.
        Tooltip ShutdownTimerToolTip = new Tooltip("Shutdown your PC base on your specified duration.");

        // And then we set the tooltip to the duration text field. So when the user hovers over the text field, the tooltip will be displayed.
        ShutdownTimerButton.setTooltip(ShutdownTimerToolTip);

        Tooltip ChrisTitusToolTip = new Tooltip("Requires Internet.\n\nThis opens the best catalog of\nWindow applications and tweaks.");
        OpenWebID.setTooltip(ChrisTitusToolTip);

        Tooltip PassGeneratorToolTip = new Tooltip("Generate a password using real-life words.");
        PWGen.setTooltip(PassGeneratorToolTip);

        Tooltip FileConcealerTT = new Tooltip("Conceal your files behind images.");
        FileConcealer.setTooltip(FileConcealerTT);

        Tooltip DuplicateRemoverTT = new Tooltip("An extremely accurate file deletion tool.");
        AccurateFileRemoverButton.setTooltip(DuplicateRemoverTT);

        Tooltip AccountManagerTT = new Tooltip("Manage your accounts by saving your passwords and usernames here.");
        AccountManager.setTooltip(AccountManagerTT);

        Tooltip ArrayGenTT = new Tooltip( "Create Java arrays from user input with ease.\nSupports generation of arrays for Double, Integer, String, and Mixed types.\nFeatures include duplicate filtering and copying the full syntax or only the elements to the clipboard.");
        ArrayGenerator.setTooltip(ArrayGenTT);
    }


    // This method is called when the shutdown button is clicked or when the user presses the enter key while the button is focused.
    @FXML
    void ShutdownTimer() throws IOException {
        // We create a new stage.
        Stage stage = new Stage();

        // We load an image to be used as the application icon.
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Timer.png")));

        // We set the icon to the stage.
        stage.getIcons().add(icon);

        // We load the Shutdown Timer GUI from an FXML file.
        FXMLLoader fxmlLoader = new FXMLLoader(_00_Special.class.getResource("AutoShutdownV1.fxml"));

        // We set the scene of the stage to the Shutdown Timer GUI.
        Scene scene = new Scene(fxmlLoader.load());

        // We get the primary stage of the scene.
        Stage primaryStage = (Stage) scene.getWindow();

        // We set the stage to be non-resizable.
        stage.setResizable(false);

        // We set the scene of the stage to the Shutdown Timer GUI.
        stage.setScene(scene);

        // We set the modality of the stage to APPLICATION_MODAL, so the user cannot interact with the main window until the stage is closed.
        stage.initModality(Modality.APPLICATION_MODAL);

        // We set the owner of the stage to the primary stage.
        stage.initOwner(primaryStage);

        // We show the stage and wait for it to be closed.
        stage.showAndWait();
    }

    // This method is called when the Chris Titus Tech button is clicked.
    @FXML void OpenWeb() throws IOException {
        // We store our command in a string.
        String runAsAdminCommand = "powershell.exe Start-Process powershell -Verb RunAs -ArgumentList '-NoProfile -ExecutionPolicy Bypass -Command \"iwr -useb https://christitus.com/win | iex\"'";

        // We execute the command.
        Runtime.getRuntime().exec(runAsAdminCommand);

        // This button basically opens Chris Titus' catalog of Windows applications, utilities, and tweaks.
    }

    // This method is called when the Password Generator button is clicked or when the user presses the enter key while the button is focused.
    @FXML void PWGenAction() throws IOException {
        Stage stage = new Stage();
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Password Icon.png")));
        stage.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(_00_Special.class.getResource("Password Generator.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = (Stage) scene.getWindow();
        stage.setResizable(false);
        stage.setScene(scene);


        stage.initModality(Modality.APPLICATION_MODAL);

        stage.initOwner(primaryStage);

        stage.showAndWait();
    }

    // This method is called when the File Concealer button is clicked or when the user presses the enter key while the button is focused.
    @FXML void FileConcealerM() throws IOException {
        Stage stage = new Stage();
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Password Icon.png")));
        stage.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(_00_Special.class.getResource("FileConcealer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = (Stage) scene.getWindow();
        stage.setResizable(false);
        stage.setScene(scene);


        stage.initModality(Modality.APPLICATION_MODAL);

        stage.initOwner(primaryStage);

        stage.showAndWait();    }


    // This method is called when the Accurate File Deletion Tool button is clicked or when the user presses the enter key while the button is focused.
    @FXML void AccurateFileRemover() throws IOException {
        Stage stage = new Stage();
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Timer.png")));
        stage.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(_00_Special.class.getResource("AccurateFileDeletionTool.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = (Stage) scene.getWindow();
        stage.setResizable(false);
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.initOwner(primaryStage);

        stage.showAndWait();
    }


    // This method is called when the Account Manager button is clicked or when the user presses the enter key while the button is focused.
    @FXML void AccountManagerM() throws IOException {
        Stage stage = new Stage();
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Timer.png")));
        stage.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(_00_Special.class.getResource("AccountManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = (Stage) scene.getWindow();
        stage.setResizable(false);
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.initOwner(primaryStage);

        stage.showAndWait();
    }

    // This method is called when the Java Array Generator button is clicked or when the user presses the enter key while the button is focused.
    @FXML void JavaArrayGen() throws IOException {
        Stage stage = new Stage();
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("WIPIcon.png")));
        stage.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(_00_Special.class.getResource("Java Array Generator.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage primaryStage = (Stage) scene.getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Java Array Generator");


        stage.initModality(Modality.APPLICATION_MODAL);

        stage.initOwner(primaryStage);

        stage.showAndWait();
    }
}
