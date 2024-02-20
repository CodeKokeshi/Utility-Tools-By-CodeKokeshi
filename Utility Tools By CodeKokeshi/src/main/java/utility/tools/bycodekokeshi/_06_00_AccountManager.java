package utility.tools.bycodekokeshi;

import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class _06_00_AccountManager implements Initializable {
    // GUI Components
    @FXML TextField PlatformF;
    @FXML TextField Username;
    @FXML PasswordField Password;
    @FXML Label Label;

    // When the save button is clicked, this method is called.
    @FXML void SaveMethod(){
        // Check if the fields are empty or not.
        if (!PlatformF.getText().isEmpty() || !PlatformF.getText().isBlank() && !Username.getText().isEmpty()  || !Username.getText().isBlank() && !Password.getText().isEmpty() || !Password.getText().isBlank()) {
            // Check if the platform name is at least 2 characters long
            if (PlatformF.getText().length() < 2) {
                Label.setText("Platform must be at least 2 characters.");
                return;
            }
            if (Username.getText().length() < 4) {
                Label.setText("Username must be at least 4 characters.");
                return;
            }
            if (Password.getText().length() < 4) {
                Label.setText("Password must be at least 4 characters.");
                return;
            }

            // Save the account details by storing them inside a variable first.
            String platformToSave = PlatformF.getText();
            String usernameToSave = Username.getText();
            String passwordToSave = Password.getText();

            // Load key.cbr file where the account details are stored.
            Properties properties = new Properties();
            try (FileInputStream input = new FileInputStream("key.cbr")) {
                properties.load(input);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            // Add the new account details to the file.
            properties.setProperty("rooms", _06_03_EncryptionService.encrypt(properties.getProperty("rooms", "") + "," + platformToSave));
            properties.setProperty("sharks", _06_03_EncryptionService.encrypt(properties.getProperty("sharks", "") + "," + usernameToSave));
            properties.setProperty("doors", _06_03_EncryptionService.encrypt(properties.getProperty("doors", "") + "," + passwordToSave));

            // Save the new account details to the file.
            try (FileOutputStream output = new FileOutputStream("key.cbr")) {
                properties.store(output, null);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            // Clear the fields and display a success message.
            Label.setText("Successful.");
            PlatformF.setText("");
            Username.setText("");
            Password.setText("");
        } else {
            // If one or more fields are empty, display an error message.
            Label.setText("One or more field is empty.");
        }
    }


    // When the view button is clicked, this method is called.
    @FXML void ViewMethod(){
        try {
            Stage stage = new Stage();
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Timer.png")));
            stage.getIcons().add(icon);
            FXMLLoader fxmlLoader = new FXMLLoader(_00_Special.class.getResource("SavedAccount.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage primaryStage = (Stage) scene.getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);
            stage.showAndWait();
        } catch (Exception ignored) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the path to the key.cbr file.
        Path iniFilePath = Paths.get("key.cbr");

        // If the file does not exist, create it.
        if (!Files.exists(iniFilePath)) {
            try {
                Files.createFile(iniFilePath);
            } catch (IOException e) {
                System.out.println("Error creating .ini file: " + e.getMessage());
            }
        }

        // Instantiate a new Properties object and load the key.cbr file.
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(iniFilePath.toString())) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Error loading .ini file: " + e.getMessage());
        }

        // If the file is empty, add some initial data to it.
        if (properties.getProperty("rooms", "").isEmpty() &&
                properties.getProperty("sharks", "").isEmpty() &&
                properties.getProperty("doors", "").isEmpty()) {

            // These are the initial data to be added to the file. (rooms, sharks, doors)
            // Rooms means Platform, Sharks means Username, and Doors means Password.
            properties.setProperty("rooms", _06_03_EncryptionService.encrypt("ExamplePlatform"));
            properties.setProperty("sharks", _06_03_EncryptionService.encrypt("ex@example.com"));
            properties.setProperty("doors", _06_03_EncryptionService.encrypt("Example_1234"));

            // Save the initial data to the file.
            try (FileOutputStream output = new FileOutputStream(iniFilePath.toString())) {
                properties.store(output, null);
            } catch (IOException e) {
                System.out.println("Error saving initial data to .ini file: " + e.getMessage());
            }
        }
        Tooltip platformTip = new Tooltip("Enter platform (e.g Google, Facebook, Twitter,...)");
        Tooltip.install(PlatformF, platformTip);
        Tooltip usernameTip = new Tooltip("Enter your username or email.");
        Tooltip.install(Username, usernameTip);
        Tooltip passwordTip = new Tooltip("Enter your password.");
        Tooltip.install(Password, passwordTip);
        Platform.runLater(() -> Label.requestFocus());
    }
}
