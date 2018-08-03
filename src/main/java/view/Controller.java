package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

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
        if(format != "null"){
            FileChooser fileChooser = new FileChooser();
            inputFile = fileChooser.showOpenDialog(loadFileButton.getScene().getWindow());
            setPathLabelText(inputFile.getAbsolutePath());
            unlockButtons();
        }

    }

    public void handleConvertButtonClick(){
        String outputFormat = CreatorWindow.show("Convert subtitles", "Choose destination format", "MicroDVD", "SubRip");
        if(outputFormat != "null"){

        }
    }

    private void unlockButtons(){
        loadFileButton.setDisable(false);
        convertButton.setDisable(false);
        shiftButton.setDisable(false);
        statisticsButton.setDisable(false);
    }
}
