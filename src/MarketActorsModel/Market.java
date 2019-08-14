package MarketActorsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Market {

    private ArrayList<Job> allJobs;
    private ArrayList<Bid> allBids;

    public Market(){
        allJobs = new ArrayList<>();
        allBids = new ArrayList<>();
    }

    /**
     * method allowing for jobs to be added to market
     * @param newJob
     */
    public void addJob(Job newJob){
        allJobs.add(newJob);
    }


    /**
     * method allowing for bids to be added to market
     * @param newBid
     */
    public void addBid(Bid newBid){
        allBids.add(newBid);
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
     * Getters for allBids and allJobs for market object
     *
     * @return
     */
    public ArrayList<Job> getAllJobs() {
        return allJobs;
    }

    public ArrayList<Bid> getAllBids() {
        return allBids;
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
     * @return
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
