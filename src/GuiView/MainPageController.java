package GuiView;

import Controller.Controller;
import MarketActorsModel.Bid;
import MarketActorsModel.Buyer;
import MarketActorsModel.Job;
import MarketActorsModel.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    // contents of jobs table
    @FXML private TableView<Job> jobTableView;
    @FXML private TableColumn<Job, Seller> jobNameColumn;
    @FXML private TableColumn<Job, Integer> jobCapacityColumn;
    @FXML private TableColumn<Job, Integer> jobPriceColumn;
    // contents of bids table
    @FXML private TableView<Bid> bidTableView;
    @FXML private TableColumn<Bid, Buyer> bidNameColumn;
    @FXML private TableColumn<Bid, Integer> bidCapacityColumn;
    @FXML private TableColumn<Bid, Integer> bidPriceColumn;
    // text fields & choicebox for jobs
    @FXML private ChoiceBox<Seller> sellerChoiceBox;
    @FXML private TextField jobCapacity;
    @FXML private TextField jobPrice;
    // text fields and choicebox for Bids
    @FXML private ChoiceBox<Buyer> buyerChoiceBox;
    @FXML private TextField bidCapacity;
    @FXML private TextField bidPrice;

    // buttons in menu
    @FXML private Button addSeller;
    @FXML private Button addBuyer;
    @FXML private Button refresh;

    private Controller controller;

    public MainPageController(Controller controller){
        this.controller = controller;
    }


    /**
     * Method that initialises the tables that show the bids and jobs that are posted to the market in a tabular
     * overview.
     * This method also initialises the contents of the choiceboxes
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // setting columns for Job table
        jobNameColumn.setCellValueFactory(new PropertyValueFactory<>("seller"));
        jobCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        jobPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        jobTableView.setItems(controller.getJobs());

        // setting collumns for Bid table
        bidNameColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        bidCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("minCapacity"));
        bidPriceColumn.setCellValueFactory(new PropertyValueFactory<>("maxPrice"));

        bidTableView.setItems(controller.getBids());

        // setting sellers and buyers for choiceBoxes
        sellerChoiceBox.setItems(controller.getSellers());
        buyerChoiceBox.setItems(controller.getBuyers());

    }

    /**
     * Method takes the input from the textfields and returns a Job object that can be handled by the controller
     *
     * If fields were left empty the method should initiate a pop-up window that tells the user to correct their input
     */
    @FXML
    public void viewAddJobButton(){
        try {
            Seller seller = sellerChoiceBox.getValue();
            int price = Integer.valueOf(jobPrice.getText());
            int capacity = Integer.valueOf(jobCapacity.getText());

            AlertWindow alertWindowSuccesfull = new AlertWindow();
            alertWindowSuccesfull.show("Job added succesfully", "The job was added to the market succesfully");

            //System.out.println(String.format("Seller: %s price: %x capacity: %x", seller.getName(), price,capacity));

            Job newJob = new Job(seller, price, capacity);
            this.controller.controlAddJob(newJob);

        } catch (Exception e) {
            AlertWindow alertWindowUnsuccesfull = new AlertWindow();
            alertWindowUnsuccesfull.show("Something went wrong", "Something went wrong in adding the job to the market. \n" +
                    "please use only integer values for capacity and price. Also, make sure you select a seller");
        }
    }

    /**
     * Method takes the input from the textfields and returns a Bid object that can be handeled by the controller
     *
     * If fields were left empty the method should initiate a pop-up window that tells the user to correct their input
     */
    @FXML
    public Bid viewAddBidButton(){
        Bid newBid = null;
        try {
            Buyer buyer = buyerChoiceBox.getValue();
            int price = Integer.valueOf(bidPrice.getText());
            int capacity = Integer.valueOf(bidCapacity.getText());

            System.out.println(String.format("Buyer: %s price: %x capacity %x", buyer.getName(), price, capacity));
            newBid = new Bid(buyer, price, capacity);
            this.controller.controlAddBid(newBid);

        }catch(Exception e){
            AlertWindow alertWindowUnsuccesfull = new AlertWindow();
            alertWindowUnsuccesfull.show("Something went wrong","Something went wrong in adding the Bid to the market. \n" +
                    "please use only integer values for capacity and price. Also, make sure you select a Buyer");
        }
        return newBid;
    }

    /**
     * Method that handles the gui view change (opening of new window) when the add seller button is pressed.
     */
    @FXML
    public void viewAddSellerButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addSellerScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Seller");
            stage.setScene(new Scene(root1, 530, 280));
            stage.show();
        } catch (IOException e) {
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.show("something went wrong", "Something went wrong in opening the add seller screen");
        }
    }

    /**
     * Method that handles the gui view change (opening of new window) when the add buyer button is pressed
     */
    @FXML
    public void viewAddBuyerButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addBuyerScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Seller");
            stage.setScene(new Scene(root1, 530, 280));
            stage.show();
        } catch (IOException e) {
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.show("something went wrong", "Something went wrong in opening the add buyer screen");
        }
    }


}
