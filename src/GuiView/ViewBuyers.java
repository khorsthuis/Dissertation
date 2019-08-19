package GuiView;

import Controller.Controller;
import MarketActorsModel.Buyer;
import MarketActorsModel.Score;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewBuyers implements Initializable {
    private Controller controller;

    @FXML private Button cancelButton;
    @FXML private TableView<Buyer> buyerTableView;
    @FXML private TableColumn<Buyer, Integer> buyerIdColumn;
    @FXML private TableColumn<Buyer, String> buyerNameColumn;
    @FXML private TableColumn<Buyer, Score> buyerNumBought;

    /**
     * Constructor method
     @param controller main controller that holds the market object
     */
    public ViewBuyers(Controller controller){
        this.controller = controller;
    }

    /**
     * Method that initialises the tables that show the bids and jobs that are posted to the market in a tabular
     * overview.
     * This method also initialises the contents of the choiceboxes
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        buyerIdColumn.setCellValueFactory(new PropertyValueFactory<>("buyerId"));
        buyerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        buyerNumBought.setCellValueFactory(new PropertyValueFactory<>("numBought"));

        buyerTableView.setItems(controller.getBuyers());
    }

    /**
     * Method that closes the screen once cancel button is pressed
     */
    @FXML
    public void handleCancelButtonPressed() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
