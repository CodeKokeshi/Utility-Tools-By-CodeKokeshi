package utility.tools.bycodekokeshi;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;

import java.util.*;

public class _00_Special extends Application {
    @Override
    public void start(Stage stage){
        try {
            // Load an image to be used as the application icon.
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("MainWindowICON.png")));
            stage.getIcons().add(icon);

            // Load the main window layout from an FXML file.
            FXMLLoader fxmlLoader = new FXMLLoader(_00_Special.class.getResource("MainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            // If an exception occurs, print the error message to the console. (For Developers Only)
            System.out.println(e.getMessage());
        }


    }

    public static void main(String[] args) {
        launch();
    }
}