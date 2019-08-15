package Controller;

import GuiView.AddBuyerScreen;
import GuiView.AddSellerScreen;
import GuiView.AlertWindow;
import GuiView.MainPageController;
import MarketActorsModel.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Controller class that couples the view (gui) with the logic (market Model)
 */
public class Controller {
    private MainPageController mainScreenController;
    private AddBuyerScreen buyerScreenController;
    private AddSellerScreen sellerScreenController;
    private Market market;

    public Controller(Market market){
        this.mainScreenController = new MainPageController(this);
        this.buyerScreenController = new AddBuyerScreen(this);
        this.sellerScreenController = new AddSellerScreen(this);
        this.market = market;
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



}
