package Controller;

import GuiView.*;
import MarketActorsModel.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.security.SecureRandom;


/**
 * Controller class that couples the view (gui) with the logic (market Model)
 */
public class Controller {
    private MainPageController mainPageController;
    private AddBuyerScreen buyerScreenController;
    private AddSellerScreen sellerScreenController;
    private ViewSellers viewSellersController;
    private ViewBuyers viewBuyersController;
    private Market market;

    public Controller(Market market){
        this.mainPageController = new MainPageController(this);
        this.buyerScreenController = new AddBuyerScreen(this);
        this.sellerScreenController = new AddSellerScreen(this);
        this.viewSellersController = new ViewSellers(this);
        this.viewBuyersController = new ViewBuyers(this);
        this.market = market;
    }

    /**
     * Method that stores job Object in the marketplace.
     */
    public void controlAddJob(Job newJob){
        this.market.addJob(newJob);
    }

    /**
     Method that stores Bid Object in the marketplace.
     */
    public void controlAddBid(Bid newBid){
        this.market.addBid(newBid);
    }

    /**
     Method that stores Seller Object in the marketplace.
     */
    public void controlAddSeller(Seller newSeller){
        this.market.addSeller(newSeller);
    }
    /**
     Method that stores Buyer Object in the marketplace.
     */
    public void controlAddBuyer(Buyer newBuyer){
        this.market.addBuyer(newBuyer);
    }

    /**
     * Getter method for the MainScreenController used for controlling the corresponding fxml file
     */
    public MainPageController getMainScreenController(){ return mainPageController; }

    /**
     * Getter method for the BuyerScreenController used for controlling the corresponding fxml file
     */
    public AddBuyerScreen getBuyerScreenController(){ return buyerScreenController; }
    /**
     * Getter method for the SellerScreenController used for controlling the corresponding fxml file
     */
    public AddSellerScreen getSellerScreenController(){ return sellerScreenController;}

    /**
     * Getter method for the viewSellerScreenController used for controlling the corresponding fxml file
     */
    public ViewSellers getViewSellersController(){
        return viewSellersController;
    }
    /**
     * Getter method for the viewBuyerScreenController used for controlling the corresponding fxml file
     */
    public ViewBuyers getViewBuyersController(){
        return viewBuyersController;
    }

    /**
     * Method that updates all tables on the main screen with information from the market when called
     */
    public void controlRefreshTables(){
        mainPageController.viewRefreshButton();
    }
    /**
     * Method that takes all bids existing in the market and returns them as an observableList
     * @return ObservableList of type bid to be read by the gui view controller
     */
    public ObservableList<Bid> getBids(){
        ObservableList<Bid> bids = FXCollections.observableArrayList();
        for(Bid bid : market.getAllBids()){
            bids.add(bid);
        }
        return bids;
    }

    /**
     * Method that takes all jobs existing in the market and returns them as an observableList
     * @return ObservableList of type job to be read by the gui view controller
     */
    public ObservableList<Job> getJobs(){
        ObservableList<Job> jobs = FXCollections.observableArrayList();
        for(Job job : market.getAllJobs()){
            jobs.add(job);
        }
        return jobs;
    }

    /**
     * Method that takes all Sellers existing in the market and returns them as an observableList
     * @return ObservableList of type Seller to be read by the gui view controller
     */
    public ObservableList<Seller> getSellers(){
        ObservableList<Seller> sellers = FXCollections.observableArrayList();
        for(Seller seller : market.getAllSellers()){
            sellers.add(seller);
        }
        return sellers;
    }

    /**
     * Method that takes all buyers existing in the market and returns them as an observableList
     * @return ObservableList of type Buyer to be read by the gui view controller
     */
    public ObservableList<Buyer> getBuyers(){
        ObservableList<Buyer> buyers = FXCollections.observableArrayList();
        for(Buyer buyer : market.getAllBuyers()) {
            buyers.add(buyer);
        }
        return buyers;
    }


    /**
     * Method that allows the algorithm to run and create matches between the different buyers and sellers.
     * After completion of this algorithm, the tables are again updated to show the partners with wich the jobs were matched.
     */
    public void controlRunAlgorithm(){
        market.matchBidsJobs();
        market.arangeMarriages();
        controlRefreshTables();
    }

    /**
     * Helper method that takes a textField as a parameter and returns the integer value of this text field if it is filled.
     * If the textfield is empty this method will return 0;
     */
    public int textFieldIntValue(TextField textField){
        int out = 0;
        if(! textField.getText().trim().isEmpty()){
            out = Integer.valueOf(textField.getText().trim());
        }
        return out;
    }

    /**
     * Method that shall add 10 random jobs to the marketplace using the first seller added to the market.
     * If no seller has been added to the market yet a new seller will be created
     *
     * for simplicity sake the range of both price and capacity for created jobs will be between 0 and 100
     */
    public void generateAddRandomJobs(){
        Seller seller;
        if(market.getAllSellers().size()<= 0){
            seller = new Seller(1,"randomSeller");
            controlAddSeller(seller);
        }else{
            seller = market.getAllSellers().get(0);
        }
        for(int i = 0; i <= 10; i++){
            int price = (int) Math.round(Math.random()*100);
            int capacity =(int) Math.round(Math.random()*100);
            Job newJob = new Job(seller,price,capacity);
            // add job to market
            controlAddJob(newJob);
        }
        // refresh tables
        mainPageController.viewRefreshButton();
    }

    /**
     * Method that shall add 10 random bids tot the marketplace using the first buyer added to the market.
     * If no buyer is available a new buyer will be created.
     *
     * for simplicity sake the range of both price and capacity for created jobs will be between 0 and 100
     */
    public void generateAddRandomBids(){
        Buyer buyer;
        if(market.getAllBuyers().size() <=0){
            buyer = new Buyer(1,"randomBuyer");
            controlAddBuyer(buyer);
        }else{
            buyer = market.getAllBuyers().get(0);
        }
        for(int i = 0; i <= 10; i++){
            int price = (int) Math.round(Math.random()*100);
            int capacity =(int) Math.round(Math.random()*100);
            Bid newBid = new Bid(buyer,price,capacity);
            // add bid to market
            controlAddBid(newBid);
        }
        // refresh tables
        mainPageController.viewRefreshButton();
    }


}
