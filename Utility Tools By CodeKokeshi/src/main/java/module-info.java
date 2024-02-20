module org.example.shutdownwithtimergui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires java.prefs;
    opens utility.tools.bycodekokeshi to javafx.fxml;
    exports utility.tools.bycodekokeshi;
}