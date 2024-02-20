package utility.tools.bycodekokeshi;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class _06_01_SavedAccount implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Path iniFilePath = Paths.get("key.cbr");
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(iniFilePath.toString())) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Error loading .ini file: " + e.getMessage());
        }

        // _06_03_EncryptionService.decrypt is a method that decrypts the data from the key.cbr file.
        String[] rooms = _06_03_EncryptionService.decrypt(properties.getProperty("rooms", "")).split(",");
        String[] sharks = _06_03_EncryptionService.decrypt(properties.getProperty("sharks", "")).split(",");
        String[] doors = _06_03_EncryptionService.decrypt(properties.getProperty("doors", "")).split(",");

        ObservableList<_06_02_Information> data = FXCollections.observableArrayList();
        for (int i = 0; i < rooms.length ; i++){
            data.add(new _06_02_Information(rooms[i],sharks[i],doors[i]));
        }
        AccountTable.setItems(data);

        PlatformCol.setCellValueFactory(cellData -> cellData.getValue().platformProperty());
        UsernameCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        PasswordCol.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

    }

    @FXML void Delete(){
        _06_02_Information selectedItem = AccountTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            AccountTable.getItems().remove(selectedItem);

            saveDataToFile();
        }
    }

    private void saveDataToFile() {
        Properties properties = new Properties();
        ObservableList<_06_02_Information> data = AccountTable.getItems();

        StringBuilder rooms = new StringBuilder();
        StringBuilder sharks = new StringBuilder();
        StringBuilder doors = new StringBuilder();

        for (_06_02_Information info : data) {
            rooms.append(info.getPlatform()).append(",");
            sharks.append(info.getUsername()).append(",");
            doors.append(info.getPassword()).append(",");
        }

        if (!rooms.isEmpty()) {
            rooms.deleteCharAt(rooms.length() - 1);
        }
        if (!sharks.isEmpty()) {
            sharks.deleteCharAt(sharks.length() - 1);
        }
        if (!doors.isEmpty()) {
            doors.deleteCharAt(doors.length() - 1);
        }

        // _06_03_EncryptionService.encrypt is a method that encrypts the data and saves it to the key.cbr file.
        properties.setProperty("rooms", _06_03_EncryptionService.encrypt(rooms.toString()));
        properties.setProperty("sharks", _06_03_EncryptionService.encrypt(sharks.toString()));
        properties.setProperty("doors", _06_03_EncryptionService.encrypt(doors.toString()));

        try {
            properties.store(new FileOutputStream("key.cbr"), null);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }


    // GUI Components
    @FXML TableView <_06_02_Information> AccountTable;
    @FXML TableColumn <_06_02_Information,String> PlatformCol;
    @FXML TableColumn <_06_02_Information,String> UsernameCol;
    @FXML TableColumn <_06_02_Information,String> PasswordCol;
    // "<>" Contains the name of the class we made that serves as setter and getter for the data.
    // The second part of it is the type of data that the setter and getter will return.
}
