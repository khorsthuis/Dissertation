package MarketActorsModel;

import java.util.LinkedList;


public class Bid {

    private Buyer buyer;
    private int maxPrice;
    private int minCapacity;
    int minReliabilityScore , minResponseTimeScore, minPerformanceScore, minAssuranceScore;
    private LinkedList<Job> jobs;
    private boolean matched;


    /**
     * Public constructor for the Bid object
     * @param buyer the potential buyer making the bid
     * @param maxPrice the max price the buyer is willing to pay for the job
     * @param minCapacity the minimum capacity required by the buyer
     */
    public Bid(Buyer buyer, int maxPrice, int minCapacity) {
        this.buyer = buyer;
        this.maxPrice = maxPrice;
        this.minCapacity = minCapacity;
        this.jobs = new LinkedList<>();
        this.matched = false;
    }



    /**
     * Getters and setters for the minimumScores a buyer would like to see a seller meet
     * @return
     */
    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public int getMinReliabilityScore() {
        return minReliabilityScore;
    }

    public void setMinReliabilityScore(int minReliabilityScore) {
        this.minReliabilityScore = minReliabilityScore;
    }

    public int getMinResponseTimeScore() {
        return minResponseTimeScore;
    }

    public void setMinResponseTimeScore(int minResponseTimeScore) {
        this.minResponseTimeScore = minResponseTimeScore;
    }

    public int getMinPerformanceScore() {
        return minPerformanceScore;
    }

    public void setMinPerformanceScore(int minPerformanceScore) {
        this.minPerformanceScore = minPerformanceScore;
    }

    public int getMinAssuranceScore() {
        return minAssuranceScore;
    }

    public void setMinAssuranceScore(int minAssuranceScore) {
        this.minAssuranceScore = minAssuranceScore;
    }

    public boolean isMatched() {
        return matched;
    }

    public LinkedList<Job> getJobs() {
        return jobs;
    }



    public Buyer getBuyer(){
        return buyer;
    }

    /**
     * method for finding whether or not the bid has anyone left to propose to
     * @return
     */
    public boolean hasProposalsLeft(){
        if(jobs.size() ==0){
            return true;
        }else
            return false;
    }

    /**
     * Method that sets boolean to matched --> bid has found a job and are now married
     */
    public void match(){
        this.matched = true;
    }

    /**
     * method that sets boolean matched to false --> bid needs to find another job
     */
    public void unMatch(){
        this.matched = false;
    }

    /**
     * Setter for all the scores as one
     */
    public void setAllMinScores(int minReliabilityScore, int minResponseTimeScore, int minPerformanceScore, int minAssuranceScore){
        this.minReliabilityScore = minReliabilityScore;
        this.minResponseTimeScore = minResponseTimeScore;
        this.minPerformanceScore = minPerformanceScore;
        this.minAssuranceScore = minAssuranceScore;
    }

    public boolean addJob(Job job){
        if(job.getSeller().getReliabilityScore() >= minReliabilityScore &&
        job.getSeller().getResponseTimeScore() >= minResponseTimeScore &&
        job.getSeller().getPerformanceScore() >= minPerformanceScore &&
        job.getSeller().getAssuranceScore() >= minAssuranceScore &&
        job.getCapacity() >= minCapacity &&
        job.getPrice() <= maxPrice){
            jobs.add(job);
            return true;
        }else{
            return false;
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (maxPrice != bid.maxPrice) return false;
        if (minCapacity != bid.minCapacity) return false;
        if (!buyer.equals(bid.buyer)) return false;
        return jobs != null ? jobs.equals(bid.jobs) : bid.jobs == null;
    }


    @Override
    public String toString() {
        return buyer.getName();
//        return "Bid{" +
//                "buyer=" + buyer.getName() +
//                ", maxPrice=" + maxPrice +
//                ", minCapacity=" + minCapacity +
//                "is matched = " + matched +"}" + "\n" +
//                "potential jobs: " + "\n" + jobs + "\n";
    }
}
