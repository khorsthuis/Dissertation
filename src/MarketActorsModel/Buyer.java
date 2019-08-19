package MarketActorsModel;

public class Buyer {

    private int buyerId;
    private String name;
    int numBought;

    /**
     * First constructor that sets up a new account
     */
    public Buyer(int buyerId, String name) {
        this.buyerId = buyerId;
        this.name = name;
        this.numBought = 0;
    }

    /**
     * overloading of constructor to allow for setting of numBought in consturctor for testing purposes
     * @param buyerId
     * @param name
     * @param numBought
     */
    public Buyer(int buyerId, String name, int numBought) {
        this.buyerId = buyerId;
        this.name = name;
        this.numBought = numBought;
    }

    /**
     * basic getters
     * @return
     */
    public int getNumBought() {
        return numBought;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public String getName() {
        return name;
    }

    public void setNumBought(int numBought){
        this.numBought = numBought;
    }




    /**
     *  Equals method
     * @param o object with wich to be compared
     * @return true if objects are considered the same, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buyer buyer = (Buyer) o;

        return buyerId == buyer.buyerId;
    }


    @Override
    public String toString() {
        return name;
    }
}

