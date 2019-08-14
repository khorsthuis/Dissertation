package MarketActorsModel;

public class Seller implements Comparable<Seller> {

    private int sellerId;
    private String name;
    private Score reliabilityScore, responseTimeScore, performanceScore, assuranceScore;

    /**
     * public constructor taking in an int sellerID and a sellerName.
     * the associated scores are initialized to 0
     * @param sellerId
     * @param name
     */
    public Seller(int sellerId, String name){
        this.sellerId = sellerId;
        this.name = name;
        this.reliabilityScore = new Score();
        this.responseTimeScore = new Score();
        this.performanceScore = new Score();
        this.assuranceScore = new Score();
    }

    /**
     * Getters for the different scores
     * @return the integer value with the score for that variable rather than the score object.
     */
    public int getReliabilityScore(){
        return this.reliabilityScore.getScore();
    }

    public int getResponseTimeScore(){
        return this.responseTimeScore.getScore();
    }

    public int getPerformanceScore(){
        return this.performanceScore.getScore();
    }

    public int getAssuranceScore(){
        return this.assuranceScore.getScore();
    }

    public String getName(){ return this.name;}

    /**
     * Setters for the different scores taking a integer value with the new score rather than a score object.
     * @param newScore
     */
    public void setReliabilityScore(int newScore){
        this.reliabilityScore.updateScore(newScore);
    }

    public void setResponseTimeScore(int newScore){
        this.responseTimeScore.updateScore(newScore);
    }

    public void setPerformanceScore(int newScore) {
        this.performanceScore.updateScore(newScore);
    }

    public void setAssuranceScore(int newScore){
        this.assuranceScore.updateScore(newScore);
    }

    public void setAllScores(int newReliabilityScore, int newResponseTimeScore, int newPerformanceScore, int newAssuranceScore){
        setReliabilityScore(newReliabilityScore);
        setResponseTimeScore(newResponseTimeScore);
        setPerformanceScore(newPerformanceScore);
        setAssuranceScore(newAssuranceScore);
    }

    @Override
    public String toString() {
        return name;
//        return "Seller:" +
//                "sellerId = " + sellerId + "\n" +
//                "name = '" + name + '\'' + "\n" +
//                "reliabilityScore = " + getReliabilityScore() + "\n" +
//                "responseTimeScore = " + getResponseTimeScore() + "\n" +
//                "performanceScore = " + getPerformanceScore() + "\n" +
//                "assuranceScore = " + getAssuranceScore();
    }

    public static void main(String[] args) {
        Seller mark = new Seller(123,"Mark");
        System.out.println(mark);

        mark.setAllScores(140,50,60,70);
        System.out.println(mark);

    }

    /**
     * Equals method working on the basis that sellerId is unique for each seller
     * @param o object with which the seller is to be checked
     * @return boolean to indicate whether two objects are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Seller seller = (Seller) o;

        return this.sellerId == seller.sellerId;

    }

    /**
     * Compare to method for sellers ordering based on total score --> higher overall score takes precedence
     * @return integer with values allowing for sorting
     */
    @Override
    public int compareTo(Seller o) {
        if(this.equals(o)){
            return 0;
        } else{
            int thisOverallScore = this.getAssuranceScore() + this.getPerformanceScore() + this.getResponseTimeScore() + this.getReliabilityScore();
            int otherOverallScore = o.getAssuranceScore() + o.getPerformanceScore() + o.getResponseTimeScore() + o.getReliabilityScore();
            return otherOverallScore - thisOverallScore;
        }
    }
}
