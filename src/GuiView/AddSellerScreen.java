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

    /**
     * Method that handles when the submit button is pressed in the add seller screen. the relevant
     * information is taken from the text-fields, a new object of type Seller is created and added to the market through
     * use of the controller class.
     *
     * furthermore, all tables in the main screen are refreshed
     */
    @FXML
    public void handleSubmitButtonPressed(){
        try {
            int newSellerId = Integer.valueOf(Id.getText().trim());
            String newSellerName = Name.getText().trim();

            // construct and add seller to market
            Seller newSeller = null;
            if(newSellerName.length()>0 && Id.getText()!=null) {
                newSeller = new Seller(newSellerId, newSellerName);
                controller.controlAddSeller(newSeller);
                // show text indicating seller was added successful
                showSellerAdded();
                // clear the text after successful completion
                Name.clear();
                Id.clear();
                // refresh all tables
                controller.controlRefreshTables();
            }else{
                AlertWindow alertWindow = new AlertWindow();
                alertWindow.show("Invalid information", "The information you have provided was incorrect" + "\n" +
                        "Please try again");
            }

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
