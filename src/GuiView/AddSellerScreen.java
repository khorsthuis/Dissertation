package GuiView;

import Controller.Controller;
import MarketActorsModel.Seller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * This class handles the creation of a new seller through a popup menu
 */

public class AddSellerScreen {

    private Controller controller;

    @FXML private Button submit;
    @FXML private Button cancelButton;
    @FXML private TextField Id;
    @FXML private TextField Name;
    @FXML private Label successfulMessage;

    /**
     * Constructor for the AddSellerScreen
     * @param controller main controller that holds the market object
     */
    public AddSellerScreen(Controller controller){
        this.controller = controller;
    }
    @FXML
    public void handleSubmitButtonPressed(){
        try {
            int newSellerId = Integer.valueOf(Id.getText().trim());
            String newSellerName = Name.getText().trim();

            // construct and add seller to market
            Seller newSeller = new Seller(newSellerId, newSellerName);
            controller.controlAddSeller(newSeller);
            // show text indicating seller was added successful
            showSellerAdded();
            // clear the text after successful completion
            Name.clear();
            Id.clear();
            // refresh all tables
            controller.controlRefreshTables();
        } catch(Exception e){
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.show("Invalid information", "The information you have provided was incorrect" + "\n" +
                    "Please try again");
        }

    }

    public void showSellerAdded(){
        successfulMessage.setText("Seller was added successfully");
    }

    @FXML
    public void handleCancelButtonPressed() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
