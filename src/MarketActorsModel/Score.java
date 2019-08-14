package MarketActorsModel;

/**
 * This class exist so to create an object in which the different scores for the sellers are stored.
 */

public class Score {

    private int score;
    private int numReviews;


    /**
     * Public constructor initialising the score to 0 with 0 reviews
     */
    public Score(){
        this.score = 0;
        this.numReviews = 0;
    }

//    /**
//     * Private Constructor for testing purposes
//     * @param score
//     * @param numReviews
//     */
//    private Score(int score, int numReviews){
//        this.score = score;
//        this.numReviews = numReviews;
//    }

    /**
     * Getter method for the score
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * method taking a new customer review and recalculating accordingly
     * @param newScore
     */
    public void updateScore(int newScore){
        if(newScore < 0 || newScore > 100){
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        int newTotalScore = Math.round(this.score * this.numReviews + newScore);
        this.numReviews ++;
        this.score = (int) Math.round(newTotalScore / (this.numReviews * 1.0));
    }

    /**
     * toString method for testing/clarification purposes
     * @return
     */
    @Override
    public String toString(){
        return "Score = " + this.score +"\n" + "numReviews = " + numReviews;
    }


}
