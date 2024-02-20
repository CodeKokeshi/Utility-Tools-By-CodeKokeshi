package utility.tools.bycodekokeshi;

import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

@SuppressWarnings("ALL")
public class _04_FileConcealer implements Initializable {

    // GUI Components
    @FXML Button BrowseZip;
    @FXML Button BrowseImage;
    @FXML Button BrowseOutput1;
    @FXML Button ConcealB;
    @FXML Button BrowseConceal;
    @FXML Button BrowseOutput2;
    @FXML Button DecryptB;
    @FXML Label ConcealLabel;
    @FXML TextField SelectZipFileTF;
    @FXML TextField SelectImageFileTF;
    @FXML TextField SelectOutputDirectoryTF;
    @FXML TextField SelectConcealedFileTF;
    @FXML TextField OutputDirectoryTF;

    @Override
    public void initialize(URL url, ResourceBundle bundle){
        Tooltip ZipFileBrowsing = new Tooltip("Select the ZIP file to be concealed.");
        BrowseZip.setTooltip(ZipFileBrowsing);
        Tooltip ImageFileBrowsing = new Tooltip("Select the image file to conceal the ZIP file behind.");
        BrowseImage.setTooltip(ImageFileBrowsing);
        Tooltip OutputDirectoryBrowsing = new Tooltip("Select the directory where the concealed file will be saved.");
        BrowseOutput1.setTooltip(OutputDirectoryBrowsing);
        Tooltip BrowseConcealedFile = new Tooltip("Select the concealed file to be decrypted.");
        BrowseConceal.setTooltip(BrowseConcealedFile);
        Tooltip OutputDirectoryBrowsing2 = new Tooltip("Select the directory where the decrypted file will be saved.");
        BrowseOutput2.setTooltip(OutputDirectoryBrowsing2);
        Tooltip ConcealButton = new Tooltip("Finalize and initiate the concealment process.");
        ConcealB.setTooltip(ConcealButton);
        Tooltip DecryptButton = new Tooltip("Finalize and initiate the decryption process.");
        DecryptB.setTooltip(DecryptButton);
        Platform.runLater(() ->{ConcealLabel.requestFocus();});
    }

    // Method we use to disable or enable all buttons
    void disableAllButtons(boolean bool) {
        BrowseZip.setDisable(bool);
        BrowseImage.setDisable(bool);
        BrowseOutput1.setDisable(bool);
        ConcealB.setDisable(bool);
        BrowseConceal.setDisable(bool);
        BrowseOutput2.setDisable(bool);
        DecryptB.setDisable(bool);
        SelectZipFileTF.setDisable(bool);
        SelectImageFileTF.setDisable(bool);
        SelectOutputDirectoryTF.setDisable(bool);
        SelectConcealedFileTF.setDisable(bool);
        OutputDirectoryTF.setDisable(bool);
    }

    // We use this for optimization but I don't think it's necessary, but because I don't know how to use it, I'll leave it here
    private static final int BUFFER_SIZE = 8192;

    // We use this to create a thread pool with a fixed number of threads, we basically use this to run decryption and concealment in the background.
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    // Browse Zip File Button Method
    @FXML void BrowseZipFile() {
        // We instantiate a new FileChooser object
        FileChooser fileChooser = new FileChooser();

        // We set the title of the file chooser
        fileChooser.setTitle("Select Zip File");

        // We add an extension filter to the file chooser.
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ZIP Files", "*.zip")
        );

        // We show the open dialog and store the selected file in a File object
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        // If the selected file is not null, we set the text of the SelectZipFileTF to the path of the selected file.
        if (selectedFile != null) {
            SelectZipFileTF.setText(selectedFile.getAbsolutePath());
        }
    }

    // Browse Image File Button Method
    @FXML void BrowseImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            SelectImageFileTF.setText(selectedFile.getAbsolutePath());
        }
    }

    // Browse Output Directory Button Method
    @FXML
    private void BrowseOutputDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Output Directory");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            SelectOutputDirectoryTF.setText(selectedDirectory.getAbsolutePath());
        }
    }

    // Browse Concealed File Button Method
    @FXML
    private void BrowseConcealedFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Concealed File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            SelectConcealedFileTF.setText(selectedFile.getAbsolutePath());
        }
    }

    // Browse Output Directory (Decryption) Button Method
    @FXML
    private void BrowseOutputDirectory2() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Output Directory");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            OutputDirectoryTF.setText(selectedDirectory.getAbsolutePath());
        }
    }

    // Conceal Button Method
    @FXML void Conceal() {
        // We create a new thread for the concealment process.
        executorService.submit(() -> {

            // We create a File variable for the zip file, image file and output directory.
            File zipFile = new File(SelectZipFileTF.getText());
            File imageFile = new File(SelectImageFileTF.getText());
            File outputDirectory = new File(SelectOutputDirectoryTF.getText());

            // We check if these files exist, if they don't we display an alert and return.
            if (!zipFile.exists() || !imageFile.exists() || !outputDirectory.exists()) {
                Platform.runLater(() -> displayAlert("Please select all files and directory."));
                return; // "return" basically exits the method to prevent further execution of the current method.
            }

            // We check if the file size is larger than 220 MB
            long fileSizeInMB = zipFile.length() / (1024 * 1024);

            // If the file size is larger than 220 MB, we display an alert and return.
            if (fileSizeInMB > 220) {
                Platform.runLater(() -> displayAlert("The file size is larger than 220 MB. Please select a smaller file."));
                return;
            }

            // We disable all buttons to prevent the user from clicking them while the concealment process is running.
            disableAllButtons(true);
            String outputFilePath = outputDirectory.getAbsolutePath() + File.separator + "output.png";

            try {
                /*
                * This line copies the content of the imageFile to a new file at outputFilePath.
                * If a file already exists at outputFilePath, it is replaced due to the StandardCopyOption.REPLACE_EXISTING option.
                * */
                Files.copy(imageFile.toPath(), new File(outputFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

                // This is necessary to append the zip file to the image file.
                try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(Paths.get(outputFilePath), StandardOpenOption.APPEND))) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;
                    try (InputStream in = new BufferedInputStream(Files.newInputStream(zipFile.toPath()))) {
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                }

                // We create a secret file inside the output file to identify it as a concealed file next time we try to decrypt it.
                String concealedFileSignature = "concealed.file";
                Files.write(new File(outputFilePath).toPath(), concealedFileSignature.getBytes(), java.nio.file.StandardOpenOption.APPEND);

                // After everything is done, we display an alert to the user. And enable all buttons.
                Platform.runLater(() -> {displayAlert("File concealed successfully at: " + outputFilePath);
                    disableAllButtons(false);

                });
            } catch (IOException e) {
                // If something goes wrong, we display an alert to the user.
                displayAlert("Error while concealing the file: " + e.getMessage());
            }
        });
    }


    // Decrypt Button Method
    @FXML void Decrypt() {
        executorService.submit(() -> {
            // We check if the concealed file and output directory are empty, if they are we display an alert and return.
            if (SelectConcealedFileTF.getText().isEmpty() || OutputDirectoryTF.getText().isEmpty()) {
                Platform.runLater(() -> displayAlert("Please select the concealed file and output directory."));
                return;
            }

            disableAllButtons(true);

            File concealedFile = new File(SelectConcealedFileTF.getText());
            File outputDirectory = new File(OutputDirectoryTF.getText());

            try {
                if (!outputDirectory.exists()) {
                    outputDirectory.mkdirs();
                }

                byte[] concealedBytes = Files.readAllBytes(concealedFile.toPath());

                String concealedFileSignature = "concealed.file";
                String concealedBytesString = new String(concealedBytes);

                // We check if the selected image is a concealer, if it's not we display an alert and return. We know it's a concealer if it contains the concealedFileSignature.
                if (!concealedBytesString.contains(concealedFileSignature)) {
                    Platform.runLater(() -> displayAlert("The selected image is not a concealer."));
                    return;
                }

                // Logic to extract the zip file from the image file.
                int start = 0;
                int end = concealedBytes.length;
                for (int i = 0; i < concealedBytes.length - 4; i++) {
                    if (concealedBytes[i] == 'P' && concealedBytes[i + 1] == 'K' &&
                            concealedBytes[i + 2] == 3 && concealedBytes[i + 3] == 4) {
                        start = i;
                        break;
                    }
                }

                // We write the extracted zip file to the output directory.
                try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(Paths.get(outputDirectory.getAbsolutePath(), "output.zip")))) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;
                    try (InputStream in = new BufferedInputStream(Files.newInputStream(concealedFile.toPath()))) {
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                }

                Platform.runLater(() -> {displayAlert("Decryption completed successfully.");
                disableAllButtons(false);
                });
            } catch (IOException e) {
                displayAlert("Error while decrypting the file: " + e.getMessage());
            }
        });
    }

    // Basically, we use this method to display alerts to the user.
    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
