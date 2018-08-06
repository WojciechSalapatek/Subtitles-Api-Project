package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import view.CreatorWindow;

import java.io.File;

public class Controller {

    @FXML
    private Button loadFileButton;
    @FXML
    private Button convertButton;
    @FXML
    private Button shiftButton;
    @FXML
    private Button statisticsButton;

    @FXML
    Label pathLabel;

    public void setPathLabelText(String text) {
        pathLabel.setText(text);
    }

    private String format;
    private File inputFile;
    private File outputFile;

    public void handleLoadButtonClick(){
        format = CreatorWindow.show("Load file","Which format is file in?","MicroDVD","SubRip");
        if(format != null && !format.equals("null")){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load file in " + format + " format");
            inputFile = fileChooser.showOpenDialog(loadFileButton.getScene().getWindow());
            if(inputFile != null) {
                unlockButtons();
                setPathLabelText(inputFile.getAbsolutePath());
            }

        }

    }

    public void handleConvertButtonClick(){
        String outputFormat = CreatorWindow.show("Convert subtitles", "Choose destination format", "MicroDVD", "SubRip");
        if(outputFormat != null && !outputFormat.equals("null")){
            FileChooser saver = new FileChooser();
            saver.setTitle("Save your output file");
            File outputFile = saver.showSaveDialog(loadFileButton.getScene().getWindow());
            if(outputFile != null && inputFile != null) {
                ConvertController controller = new ConvertController();
                controller.convert(inputFile, outputFile, format, outputFormat, 24);
            }
        }
    }

    private void unlockButtons(){
        loadFileButton.setDisable(false);
        convertButton.setDisable(false);
        shiftButton.setDisable(false);
        statisticsButton.setDisable(false);
    }
}
