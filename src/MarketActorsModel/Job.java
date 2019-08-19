package MarketActorsModel;

public class Job implements Comparable<Job> {

    private Seller seller;
    private int price;
    private int capacity;
    private boolean matched;
    private Bid partner;
    private int finalPrice;

    /**
     * Public constructor for the job object
     * @param seller Seller that posts the job
     * @param price Minimum price for which the job may be sold (set by seller)
     * @param capacity Capacity available in the job (set by seller)
     */
    public Job(Seller seller, int price, int capacity){
        this.seller = seller;
        this.price = price;
        this.capacity = capacity;
        this.matched = false;
    }

    /**
     * Getter methods for the field variables
     * @return
     */
    public Seller getSeller() {
        return seller;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isMatched() {
        return matched;
    }

    public Bid getPartner(){
        return partner;
    }

    public int getFinalPrice(){
        return finalPrice;
    }

    /**
     * Method that sets boolean to matched --> job has found a job and are now married
     * @param bid is the bid that is now set to be the new partner of the job.
     */
    public void match(Bid bid){
        this.matched = true;
        this.partner = bid;
        this.finalPrice = bid.getMaxPrice();
    }

    /**
     * method that sets boolean matched to false --> job has found a better match
     */
    public void unMatch(){
        this.matched = false;
    }

    /**
     * Method that evaluates whether the parameter bid is better than the current partner for a job
     * @param bid the bid that is to be evaluated with the current partner
     * @return boolean: true if current partner is worse, false if current partner is better
     */
    public boolean isBetterMatch(Bid bid){
        if(bid.getMaxPrice()==this.partner.getMaxPrice()){ // if price is the same we want person with more jobs bought
            return this.partner.getBuyer().getNumBought() < bid.getBuyer().getNumBought();
        } else if(this.partner.getMaxPrice() < bid.getMaxPrice()){ // if price is higher take that option
            return true;
        }else{ // bid is not better than current partner
            return false;
        }
    }

    /**
     * Tostring method for job object
     * @return
     */
    @Override
    public String toString() {
        if(partner!=null){
            return "Job{" +
                    "seller=" + seller.getName() +
                    " price=" + price +
                    " capacity=" + capacity +
                    " Matched = " + matched +
                    " Partner = " + partner.getBuyer().getName() +
                    '}' + "\n";
        }else{
            return "Job{" +
                    "seller=" + seller.getName() +
                    " price=" + price +
                    " capacity=" + capacity +
                    " Matched = " + matched +
                    " Partner =  NULL" +
                    '}' + "\n";
        }

    }

    /**
     * compare to method sorts job by price.
     *  if price is the same > higher capacity for same price takes priority
     *  if price and capacity is the same seller with better overall score takes precedence
     * @param o other job with which job is to be compared
     * @return integer allowing for sorting of objects of type Job
     */
    @Override
    public int compareTo(Job o) {
        if(this.price == o.price && this.capacity == o.capacity){
            return this.getSeller().compareTo(o.getSeller());
        }else if(this.price == o.price){
            return o.capacity - this.capacity;
        }else {
            return this.price - o.price;
        }
    }

    /**
     * equals method that compares two objects and evaluates if they are the same or not
     * this is based on the price, capacity, and seller being the same.
     * @param o other object that is to be compared with object of type Job
     * @return boolean: true if objects are the same. False if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (price != job.price) return false;
        if (capacity != job.capacity) return false;
        return seller != null ? seller.equals(job.seller) : job.seller == null;
    }


}
