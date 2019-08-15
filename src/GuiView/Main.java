package GuiView;


import Controller.Controller;
import MarketActorsModel.Market;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Market defaultMarket = new Market();
        Controller controller = new Controller(defaultMarket);
        Parent root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));
        primaryStage.setTitle("Cloud capacity Market");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
