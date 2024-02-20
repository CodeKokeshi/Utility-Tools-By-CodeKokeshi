package utility.tools.bycodekokeshi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;



public class _07_JavaArrayGenerator implements Initializable {

    // GUI Components
    @FXML Label Status;
    @FXML CheckBox ElementsOnly;
    @FXML CheckBox IntegerC;
    @FXML CheckBox DoubleC;
    @FXML CheckBox Null;
    @FXML CheckBox StringC;
    @FXML CheckBox Mixed;
    @FXML CheckBox Duplicates;
    @FXML TextArea Input;
    @FXML TextArea Output;

    @FXML
    public void initialize(URL url, ResourceBundle bundle) {
        Tooltip InputAreaTT = new Tooltip("Enter the values to be converted into an array.\n\nSeparate each value with a new line.");
        Input.setTooltip(InputAreaTT);

        Tooltip OutputAreaTT = new Tooltip("The generated array will be displayed here.");
        Output.setTooltip(OutputAreaTT);

        Tooltip IntegerCheckBoxTT = new Tooltip("If the input values are integers.");
        IntegerC.setTooltip(IntegerCheckBoxTT);

        Tooltip DoubleCheckBoxTT = new Tooltip("If the input values are doubles.");
        DoubleC.setTooltip(DoubleCheckBoxTT);

        Tooltip StringCheckBoxTT = new Tooltip("If the input values are strings.");
        StringC.setTooltip(StringCheckBoxTT);

        Tooltip MixedCheckBoxTT = new Tooltip("If the input values are mixed types (Object).");
        Mixed.setTooltip(MixedCheckBoxTT);

        Tooltip NullCheckBoxTT = new Tooltip("Include blank lines in the input.");
        Null.setTooltip(NullCheckBoxTT);

        Tooltip DuplicatesCheckBoxTT = new Tooltip("Include duplicate values in the input. (e.g 1,1,2,3 or \"a\",\"b\",\"c\", \"a\" )");
        Duplicates.setTooltip(DuplicatesCheckBoxTT);

        Tooltip ElementsOnlyCheckBoxTT = new Tooltip("Copy only the elements to the clipboard.");
        ElementsOnly.setTooltip(ElementsOnlyCheckBoxTT);

        // When the checkbox is checked, the other checkboxes will be unchecked.
        IntegerC.setOnAction(event -> handleCheckboxChecked(IntegerC));
        DoubleC.setOnAction(event -> handleCheckboxChecked(DoubleC));
        StringC.setOnAction(event -> handleCheckboxChecked(StringC));
        Mixed.setOnAction(event -> handleCheckboxChecked(Mixed));
    }

    // Unchecking the other checkboxes when one is checked.
    private void handleCheckboxChecked(CheckBox checkedCheckbox) {
        if (checkedCheckbox.isSelected()) {
            // A different way to handle conditional statements.
            // This happened because we used action event.
            if (checkedCheckbox != IntegerC) IntegerC.setSelected(false);
            if (checkedCheckbox != DoubleC) DoubleC.setSelected(false);
            if (checkedCheckbox != StringC) StringC.setSelected(false);
            if (checkedCheckbox != Mixed) Mixed.setSelected(false);
        }
    }


    // Generate the array based on the input values.
    @FXML
    private void GenerateB() {

        // If the status is "Copied to Clipboard.", clear the status.
        if (Status.getText().equals("Copied to Clipboard.")){
            Status.setText("");
        }

        // Get the input values and split them into an array. We know it is split when it is separated by a new line.
        String inputText = Input.getText();
        String[] words = inputText.split("\n");

        // If Null checkbox is not selected, filter out blank lines
        if (!Null.isSelected()) {
            words = Arrays.stream(words)
                    .filter(line -> !line.trim().isEmpty())
                    .toArray(String[]::new);
        }

        // Create a StringBuilder to store the output.
        StringBuilder outputBuilder = new StringBuilder();

        // Create a counter to keep track of the number of elements in the array.
        int counter = 0;

        // If Duplicates checkbox is not selected, remove duplicate values.
        if (!Duplicates.isSelected()) {
            Set<String> set = new LinkedHashSet<>(Arrays.asList(words));
            words = set.toArray(new String[0]);
        }

        // If integer checkbox is selected, generate an integer array.
        if (IntegerC.isSelected()) {
            outputBuilder.append("Integer[] numbers = {\n");
            for (int i = 0; i < words.length; i++) {
                try {
                    Integer.parseInt(words[i]);
                    outputBuilder.append(words[i]);
                    counter++;
                    if (i < words.length - 1) {
                        outputBuilder.append(", ");
                        if (counter == 10) {
                            outputBuilder.append("\n");
                            counter = 0;
                        }
                    }
                } catch (NumberFormatException e) {
                    showAlert("An integer is expected but a non-integer value is found.");
                    return;
                }
            }
            outputBuilder.append("\n};");
        }
        // If double checkbox is selected, generate a double array.
        else if (DoubleC.isSelected()) {
            outputBuilder.append("Double[] numbers = {\n");
            for (int i = 0; i < words.length; i++) {
                try {
                    Double.parseDouble(words[i]);
                    outputBuilder.append(words[i]);
                    counter++;
                    if (i < words.length - 1) {
                        outputBuilder.append(", ");
                        if (counter == 10) {
                            outputBuilder.append("\n");
                            counter = 0;
                        }
                    }
                } catch (NumberFormatException e) {
                    showAlert("A double is expected but a non-double value is found.");
                    return;
                }
            }
            outputBuilder.append("\n};");
        }
        // If the String checkbox is checked generate a string array.
        else if (StringC.isSelected()) {
            outputBuilder.append("String[] words = {\n");
            for (int i = 0; i < words.length; i++) {
                outputBuilder.append("\"").append(words[i]).append("\"");
                counter++;
                if (i < words.length - 1) {
                    outputBuilder.append(", ");
                    if (counter == 10) {
                        outputBuilder.append("\n");
                        counter = 0;
                    }
                }
            }
            outputBuilder.append("\n};");
        }
        // If the Mixed checkbox is checked, generate an object array.
        else if (Mixed.isSelected()) {
            outputBuilder.append("Object[] mixed = {\n");
            for (int i = 0; i < words.length; i++) {
                try {
                    int intValue = Integer.parseInt(words[i]);
                    outputBuilder.append(intValue);
                } catch (NumberFormatException e1) {
                    try {
                        double doubleValue = Double.parseDouble(words[i]);
                        outputBuilder.append(doubleValue);
                    } catch (NumberFormatException e2) {
                        outputBuilder.append("\"").append(words[i]).append("\"");
                    }
                }
                counter++;
                if (i < words.length - 1) {
                    outputBuilder.append(", ");
                    if (counter == 10) {
                        outputBuilder.append("\n");
                        counter = 0;
                    }
                }
            }
            outputBuilder.append("\n};");
        }

        // After going all through conditions, set the generated array to the output area.
        Output.setText(outputBuilder.toString());
    }


    // Show an alert message. (Reusable method)
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Copy the generated array to the clipboard.
    @FXML
    void CopyToClipboard() {
        String outputText = Output.getText();

        // If ElementsOnly checkbox is selected, copy only the elements to the clipboard by removing the array declaration.
        if (ElementsOnly.isSelected()) {
            outputText = outputText.substring(outputText.indexOf('\n') + 1, outputText.lastIndexOf('\n')).trim();
            if (outputText.endsWith(",")) {
                outputText = outputText.substring(0, outputText.length() - 1);
            }
        }
        ClipboardContent content = new ClipboardContent();
        content.putString(outputText);
        Clipboard.getSystemClipboard().setContent(content);
        Status.setText("Copied to Clipboard.");
    }
}
