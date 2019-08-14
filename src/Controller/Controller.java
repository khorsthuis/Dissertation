package Controller;

import GuiView.AddBuyerScreen;
import GuiView.AddSellerScreen;
import GuiView.MainPageController;
import MarketActorsModel.Market;

/**
 * Controller class that couples the view (gui) with the logic (market Model)
 */
public class Controller {
    private MainPageController mainScreenController;
    private AddBuyerScreen buyerScreenController;
    private AddSellerScreen sellerScreenController;
    private Market market;

    public Controller(MainPageController mainPageController, AddBuyerScreen buyerScreenController, AddSellerScreen sellerScreenController, Market market){
        this.mainScreenController = mainPageController;
        this.buyerScreenController = buyerScreenController;
        this.sellerScreenController = sellerScreenController;
        this.market = market;
    }




}
