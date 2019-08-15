package GuiView;

import Controller.Controller;
import MarketActorsModel.Buyer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBuyerScreen {

    private Controller controller;
    // fxml variables
    @FXML private Button submit;
    @FXML private Button cancelButton;
    @FXML private TextField Id;
    @FXML private TextField Name;
    @FXML private TextField numJobs;
    @FXML private Label successfulMessage;

    /**
     * Constructor method
     @param controller main controller that holds the market object
     */
    public AddBuyerScreen(Controller controller){
        this.controller = controller;
    }

    @FXML
    public void handleSubmitButtonPressed(){
        try {
            Buyer newBuyer;
            int newBuyerId = Integer.valueOf(Id.getText().trim());
            String newBuyerName = Name.getText().trim();
            // conditional to check which constructor to use
            if(numJobs.getText().trim().isEmpty()) {
                newBuyer = new Buyer(newBuyerId, newBuyerName);
            }else{
                int newNumJobs = Integer.valueOf(numJobs.getText().trim());
                newBuyer = new Buyer(newBuyerId,newBuyerName,newNumJobs);
            }
            controller.controlAddBuyer(newBuyer);
            // show text indicating buyer was added successful
            showBuyerAdded();
            // clear the text after successful completion
            Name.clear();
            Id.clear();
            numJobs.clear();
            // refresh tables after adding
            controller.controlRefreshTables();
        } catch(Exception e){
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.show("Invalid information", "The information you have provided was incorrect" + "\n" +
                    "Please try again");
        }
    }

    /**
     * Method that displays message indicating successful adding of a new buyer
     */
    public void showBuyerAdded(){
        successfulMessage.setText("Buyer was added successfully");
    }

    @FXML
    public void handleCancelButtonPressed() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}

