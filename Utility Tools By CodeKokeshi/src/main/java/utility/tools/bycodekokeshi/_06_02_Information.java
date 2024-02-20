package utility.tools.bycodekokeshi;

import javafx.beans.property.SimpleStringProperty;

public class _06_02_Information {
    // This is a simple setter and getter class for the TableView in _06_01_SavedAccount.java
    // Basically JavaFX uses this a lot to set and get data from the TableView.

    private final SimpleStringProperty platform;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    public _06_02_Information(String platform, String username, String password){
        this.platform = new SimpleStringProperty(platform);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getPlatform() {
        return platform.get();
    }

    public SimpleStringProperty platformProperty() {
        return platform;
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }
}
