package GuiView;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *  Class that allows for quick insertion of a pop up alert window
 */
public class AlertWindow {

    public static void show(String windowTitle, String message){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(windowTitle);
        window.setMinWidth(200.0);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button();
        closeButton.setText("Close window");
        closeButton.setOnAction(e -> window.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(label,closeButton);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }
}
