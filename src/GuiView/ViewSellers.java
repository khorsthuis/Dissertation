package GuiView;

import Controller.Controller;
import MarketActorsModel.Score;
import MarketActorsModel.Seller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewSellers implements Initializable{
    private Controller controller;

    @FXML private Button cancelButton;
    @FXML private TableView<Seller> sellerTableView;
    @FXML private TableColumn<Seller, Integer> sellerIdColumn;
    @FXML private TableColumn<Seller, String> sellerNameColumn;
    @FXML private TableColumn<Seller, Score> reliabilityScoreColumn;
    @FXML private TableColumn<Seller, Score> responseTimeScoreColumn;
    @FXML private TableColumn<Seller, Score> performanceScoreColumn;
    @FXML private TableColumn<Seller, Score> assuranceScoreColumn;

    /**
     * Constructor method
     @param controller main controller that holds the market object
     */
    public ViewSellers(Controller controller){
        this.controller = controller;
    }

    /**
     * Method that initialises the tables that show the bids and jobs that are posted to the market in a tabular
     * overview.
     * This method also initialises the contents of the choiceboxes
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        sellerIdColumn.setCellValueFactory(new PropertyValueFactory<>("sellerId"));
        sellerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        reliabilityScoreColumn.setCellValueFactory(new PropertyValueFactory<>("reliabilityScore"));
        responseTimeScoreColumn.setCellValueFactory(new PropertyValueFactory<>("responseTimeScore"));
        performanceScoreColumn.setCellValueFactory(new PropertyValueFactory<>("performanceScore"));
        assuranceScoreColumn.setCellValueFactory(new PropertyValueFactory<>("assuranceScore"));

        sellerTableView.setItems(controller.getSellers());
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
