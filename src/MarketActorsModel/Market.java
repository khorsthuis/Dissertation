package MarketActorsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * This class holds the central logid of the market
 */
public class Market {

    private ArrayList<Job> allJobs;
    private ArrayList<Bid> allBids;
    private ArrayList<Buyer> allBuyers;
    private ArrayList<Seller> allSellers;

    public Market(){
        allJobs = new ArrayList<>();
        allBids = new ArrayList<>();
        allBuyers = new ArrayList<>();
        allSellers = new ArrayList<>();
    }

    /**
     * method allowing for jobs to be added to market
     */
    public void addJob(Job newJob){
        allJobs.add(newJob);
    }

    /**
     * method allowing for bids to be added to market
     */
    public void addBid(Bid newBid){
        allBids.add(newBid);
    }

    /**
     * method allowing for Sellers to be added to market
     */
    public void addSeller(Seller newSeller){
        allSellers.add(newSeller);
    }

    /**
     * method allowing for Buyers to be added to market
     */
    public void addBuyer(Buyer newBuyer){
        allBuyers.add(newBuyer);
    }

    /**
     * Method that matches bids with jobs that exist in the market if the requirements of the bid's
     * meet up with the capacity price of the job
     */
    public void matchBidsJobs(){
        for(Job job : allJobs){
            for(Bid bid : allBids){
                bid.addJob(job);
            }
        }
        for(Bid bid : allBids) {
            Collections.sort(bid.getJobs());
        }
    }

    /**
     * Method that uses the stable marriage algorithm to match the sellers with buyers. The buyers (bid) propose to the sellers(job)
     */
    public void arangeMarriages(){
        int countEmpty = 0;
        int i = 0;
        int endOfLoop = allBids.size() -1;
        while(countEmpty<allBids.size()) {
            if (!allBids.get(i).isMatched() && !allBids.get(i).getJobs().isEmpty()) { //bid not matched and has jobs left in list
                countEmpty = 0;

                // stable marriage algorithm implementation:

                // job w(wife) is first choice for bid(suitor)
                Job w = allBids.get(i).getJobs().pop();

                if (!w.isMatched()) { // Job(wife) that is proposed to does not yet have a partner
                    w.match(allBids.get(i)); // Job(wife) accepts proposal
                    allBids.get(i).match(); // update status for bid(suitor)
                } else { // preferred job already has a partner
                    if (w.isBetterMatch(allBids.get(i))) { // current proposal is better than previous
                        w.getPartner().unMatch(); // Job(wife) breaks from current partner
                        w.match(allBids.get(i)); // Job(wife) accepts better proposal
                        allBids.get(i).match(); // update status for bid(suitor)
                    }
                }
                if (i == endOfLoop) { // if end of loop is reached start again with i = 0
                    i = 0;
                }else // increment i
                i++;

            } else {
                if (i == endOfLoop) { // if end of loop is reached start again with i = 0
                    i = 0;
                    countEmpty++;
                }else{ // increment both i and countEmpty
                    countEmpty++;
                    i++;
                }
            }
        }
    }

    /**
     * Method that clears all the contents of the market
     */
    public void clearMarket(){
        allBids = new ArrayList<>();
        allJobs = new ArrayList<>();
        allSellers = new ArrayList<>();
        allBuyers = new ArrayList<>();
    }

    /**
     * Method that removes all bids and jobs from the market but keeps sellers and buyers
     */
    public void clearJobsBids(){
        allBids = new ArrayList<>();
        allJobs = new ArrayList<>();
    }
    /**
     * Method that clears all jobs from market
     */
    public void clearJobs(){
        allJobs = new ArrayList<>();
    }
    /**
     * Method that clears all bids from market
     */
    public void clearBids(){
        allBids = new ArrayList<>();
    }

    /**
     * Getters for allBids and allJobs for market object
     */
    public ArrayList<Job> getAllJobs() {
        return allJobs;
    }

    public ArrayList<Bid> getAllBids() {
        return allBids;
    }

    public ArrayList<Buyer> getAllBuyers(){
        return allBuyers;
    }

    public ArrayList<Seller> getAllSellers(){
        return allSellers;
    }

    /**
     * getter method that returns all jobs for specific bid
     * @return LinkedList of jobs found for the bid
     */
    public LinkedList<Job> getJobsBid(Bid bid) {
        LinkedList<Job> out = new LinkedList<>();
        for (Bid otherBid : allBids) {
            if (otherBid.equals(bid)) {
                out = bid.getJobs();
                break;
            }
        }
        return out;
    }

    /**
     * Tostring method returning the contents of the complete market as a string
     */
    @Override
    public String toString(){
        String out = "";
        for(Bid bid : allBids){
            out += bid.toString();
        }
        return out;
    }

}
