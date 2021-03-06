package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreatorWindow {

    static String output;

    public static String show(String title, String text, String option1, String option2) {
        output = "null";
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);

        BorderPane root = new BorderPane();
        HBox buttons = new HBox();

        Button button1 = new Button(option1);
        button1.setOnAction(e -> {
            output = option1;
            window.close();
        });

        Button button2 = new Button(option2);
        button2.setOnAction(e -> {
            output = option2;
            window.close();
        });

        Button button3 = new Button("Cancel");
        button3.setOnAction(e -> {
            output = "null";
            window.close();
        });
        Region region = new Region();
        buttons.getChildren().addAll(button1,button2, button3);
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(0,10,0,60));
        root.setCenter(new Label(text));
        root.setBottom(buttons);

        window.setScene(new Scene(root, 300, 100));
        window.showAndWait();

        return output;
    }

}
