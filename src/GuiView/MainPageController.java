package GuiView;

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




    /**
     * Method that initialises the tables that show the bids and jobs that are posted to the market in a tabular
     * overview.
     *
     * This method also initialises the contents of the choiceboxes
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // setting columns for Job table
        jobNameColumn.setCellValueFactory(new PropertyValueFactory<>("seller"));
        jobCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        jobPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        jobTableView.setItems(getJobs());

        // setting collumns for Bid table
        bidNameColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        bidCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("minCapacity"));
        bidPriceColumn.setCellValueFactory(new PropertyValueFactory<>("maxPrice"));

        bidTableView.setItems(getBids());

        // setting sellers and buyers for choiceBoxes
        sellerChoiceBox.setItems(getSellers());
        buyerChoiceBox.setItems(getBuyers());

    }



    /**
     * Method that returns all the bid objects that exist in a market as an observable List
     *
     * @TODO: create method that returns the values for the contents of the market
     *
     * @return All the bids that have been posted to the marketplace as an ObservableList
     */
    public ObservableList<Bid> getBids(){
        ObservableList<Bid> bids = FXCollections.observableArrayList();

        bids.add(new Bid(new Buyer(12,"Pipo"), 45,100));
        bids.add(new Bid(new Buyer(1,"barry"), 30,200));

        return bids;
    }

    /**
     * Method that returns all the Job objects that exist in a market as an observable List
     *
     * @TODO: create method that returns the values for the contents of the market
     *
     * @return All the Jobs that have been posted to the marketplace as an ObservableList
     */
    public ObservableList<Job> getJobs(){
        ObservableList<Job> jobs = FXCollections.observableArrayList();

        jobs.add(new Job(new Seller(1,"bob"),123,234));
        jobs.add(new Job(new Seller(2,"Henk"),123,2434));

        return jobs;
    }

    /**
     * Method getting all the sellers that exist in the marketplace
     * @return an observable arraylist for the choicebox Dropdown
     */
    public ObservableList<Seller> getSellers(){
        ObservableList<Seller> sellers = FXCollections.observableArrayList();

        sellers.add(new Seller(1,"Barry"));
        sellers.add(new Seller(2,"Henk"));
        sellers.add(new Seller(1,"Piet"));

        return sellers;
    }

    /**
     * Method getting all the Buyers that exist in the marketplace
     * @return an observable arraylist for the choicebox Dropdown
     */
    public ObservableList<Buyer> getBuyers(){
        ObservableList<Buyer> buyers = FXCollections.observableArrayList();

        buyers.add(new Buyer(1,"Sarah"));
        buyers.add(new Buyer(2,"Anna"));
        buyers.add(new Buyer(3, "Jake"));

        return buyers;
    }

    /**
     * Method that saves the user input and adds the information contained in there as a job to the marketplace.
     */
    @FXML
    public void handleAddJobButton(){
        try {
            Seller seller = sellerChoiceBox.getValue();
            int price = Integer.valueOf(jobPrice.getText());
            int capacity = Integer.valueOf(jobCapacity.getText());

            AlertWindow alertWindowSuccesfull = new AlertWindow();
            alertWindowSuccesfull.show("Job added succesfully", "The job was added to the market succesfully");

            //System.out.println(String.format("Seller: %s price: %x capacity: %x", seller.getName(), price,capacity));

            Job newJob = new Job(seller, price, capacity);
        }catch (Exception e){
            AlertWindow alertWindowUnsuccesfull = new AlertWindow();
            alertWindowUnsuccesfull.show("Something went wrong","Something went wrong in adding the job to the market. \n" +
                    "please use only integer values for capacity and price. Also, make sure you select a seller");
        }
    }

    /**
     * Method that saves the user input and adds the information contained in there as a bid to the marketplace.
     * It confirms the button was clicked by showing a pop-up window.
     * If fields were left empty the method should initiate a pop-up window that tells the user to correct their input
     */
    @FXML
    public void handleAddBidButton(){
        try {
            Buyer buyer = buyerChoiceBox.getValue();
            int price = Integer.valueOf(bidPrice.getText());
            int capacity = Integer.valueOf(bidCapacity.getText());

            System.out.println(String.format("Buyer: %s price: %x capacity %x", buyer.getName(), price, capacity));
            Bid newBid = new Bid(buyer, price, capacity);
        }catch(Exception e){
            AlertWindow alertWindowUnsuccesfull = new AlertWindow();
            alertWindowUnsuccesfull.show("Something went wrong","Something went wrong in adding the Bid to the market. \n" +
                    "please use only integer values for capacity and price. Also, make sure you select a Buyer");
        }
    }

    @FXML
    public void handleAddSellerButton(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addSellerScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Seller");
            stage.setScene(new Scene(root1,530,280));
            stage.show();
        }catch (IOException e) {
            System.out.println("something went wrong in opening the addSeller Screen");
        }
    }

    @FXML
    public void handleAddBuyerButton(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addBuyerScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Seller");
            stage.setScene(new Scene(root1,530,280));
            stage.show();
        }catch (IOException e) {
            System.out.println("something went wrong in opening the addBuyerScreen");
        }
    }






}
