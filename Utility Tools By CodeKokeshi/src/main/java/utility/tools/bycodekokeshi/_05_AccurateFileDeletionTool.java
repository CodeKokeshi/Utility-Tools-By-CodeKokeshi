package utility.tools.bycodekokeshi;

import javafx.application.Platform;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class _05_AccurateFileDeletionTool {

    // GUI components
    @FXML TextField SelectFolderField;
    @FXML Button SelectFolderButton;
    @FXML ListView<String> FileLists;
    @FXML Button SearchButton;
    @FXML Label TitleLabel;
    @FXML CheckBox ReverseMode;
    @FXML CheckBox Subfolders;
    @FXML TextField SearchField;

    ObservableList<String> DetectedFileList = FXCollections.observableArrayList();

    public void initialize() {
        Tooltip SearchButtonTooltip = new Tooltip("Search for files containing the search term.");
        SearchButton.setTooltip(SearchButtonTooltip);

        Tooltip SelectFolderButtonTooltip = new Tooltip("Select the folder where you want to delete files.");
        SelectFolderButton.setTooltip(SelectFolderButtonTooltip);

        Tooltip ReverseModeTooltip = new Tooltip("Invert the search results. If ticked, files that do not contain the search term will be shown.");
        ReverseMode.setTooltip(ReverseModeTooltip);
        Platform.runLater(() -> {
            TitleLabel.requestFocus();
        });

        // Set action for SelectFolderButton, basically because I forgot to set it in SceneBuilder.
        SelectFolderButton.setOnAction(event -> {
            // This one is the same as file chooser, but it's for directory chooser.
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(SelectFolderButton.getScene().getWindow());

            // If a directory is selected, set the selected directory to the SelectFolderField (the text field in the GUI).
            if (selectedDirectory != null) {
                SelectFolderField.setText(selectedDirectory.getAbsolutePath());
                DisplayDetectedFiles();
            }
        });

        // Set selection mode for FileLists (multiple selection mode)
        FileLists.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    // Method for displaying detected files in the selected folder (used as a method and as a button.)
    @FXML void DisplayDetectedFiles() {
        if (!SelectFolderField.getText().isEmpty()) {
            DetectFiles();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No folder selected. Please select a folder.");
            alert.showAndWait();
        }}

    // Method for detecting files in the selected folder (used as a method and as a button.)
    void DetectFiles() {
        // Clear the detected file list first to refresh it.
        DetectedFileList.clear();
        try {
            Path parentPath = Paths.get(SelectFolderField.getText());
            if (Subfolders.isSelected()) {
                // This is where we detect files in the selected folder and its subfolders.
                Files.walk(parentPath)
                        .filter(Files::isRegularFile)
                        .map(parentPath::relativize) // Get the relative path
                        .map(Path::toString)
                        .forEach(DetectedFileList::add);
            } else {
                // This is where we detect files in the selected folder only.
                Files.list(parentPath)
                        .filter(Files::isRegularFile)
                        .map(Path::getFileName) // Get only the file name
                        .map(Path::toString)
                        .forEach(DetectedFileList::add);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // And then we set the detected files to the File List (the list view in the GUI).
        FileLists.setItems(DetectedFileList);
    }



    // Method for searching files in the detected files list (used as a method and as a button.)
    @FXML void SearchFiles() {
        // If no folder is selected, show an error message.
        if (SelectFolderField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No folder selected. Please select a folder before searching.");
            alert.showAndWait();
            return;
        }
        try {
            // If the search field is empty, show an error message.
            String searchText = SearchField.getText().toLowerCase();
            if (searchText.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Search field is empty. Please enter a search term.");
                alert.showAndWait();
                return;
            }
            ObservableList<String> filteredList;

            // If reverse mode is ticked (checkbox is selected), the files that do not contain the search term will be shown.
            if (ReverseMode.isSelected()) {
                filteredList = DetectedFileList.stream()
                        .filter(fileName -> !fileName.toLowerCase().contains(searchText))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            } else {
                // If reverse mode is not ticked (checkbox is not selected), the files that contain the search term will be shown.
                filteredList = DetectedFileList.stream()
                        .filter(fileName -> fileName.toLowerCase().contains(searchText))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            }

            // Set the filtered list to the File List (the list view in the GUI).
            FileLists.setItems(filteredList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Method for deleting selected files in the File List (the list view in the GUI).
    @FXML void DeleteSelectedFiles() {
        // This observable list is used to get the selected files in the File List using getSelectedItems() method.
        ObservableList<String> selectedFiles = FileLists.getSelectionModel().getSelectedItems();

        // If no file is selected, and we try to delete, show an error message.
        if (selectedFiles.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No file selected for deletion. Please select a file.");
            alert.showAndWait();
            return;
        }

        // If the selected files are not empty, delete the selected files.
        for (String selectedFile : new ArrayList<>(selectedFiles)) {
            try {
                Files.delete(Paths.get(selectedFile));
                DetectedFileList.remove(selectedFile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Refresh the File List (the list view in the GUI) after deletion.
        DisplayDetectedFiles();

        // If the search field is not empty, search the files again after deletion (to give an illusion that the files are deleted from the search results).
        if (!SearchField.getText().isEmpty()) {
            SearchFiles();
        }
    }

}