package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.InputWindow;

public class InputController {

    @FXML
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField inputField;



    public void handleOkButtonClick(){
        Stage st = (Stage) okButton.getScene().getWindow();
        InputWindow.setOutput(inputField.getText());
        st.close();
    }

    public void handleCancelButtonClick(){
        Stage st = (Stage) okButton.getScene().getWindow();
        st.close();
    }
}
