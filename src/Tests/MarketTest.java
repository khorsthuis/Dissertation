package Tests;

import MarketActorsModel.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarketTest {

    // 2 Sellers
    Seller hans = new Seller(12,"Hans");
    Seller barry = new Seller(11,"Barry");

    // 3 Buyers
    Buyer mark = new Buyer(1,"mark");
    Buyer sarah = new Buyer(2,"Sarah");
    Buyer mike = new Buyer(3,"Mike",10);

    // 1 market
    Market market = new Market();


    @Before
    public void setup(){
        // providing both hans and barry with review scores
        hans.setAllScores(60,70,80,90);
        barry.setAllScores(50,60,70,80);
        // creating some jobs posted by hans
        Job hansJob1 = new Job(hans,25,100);
        Job hansJob2 = new Job(hans,10,101);
        Job hansJob3 = new Job(hans,15,100);
        // creating some jobs posted by barry
        Job barryJob1 = new Job(barry, 10,101);
        Job barryJob2 = new Job(barry,35,250);
        Job barryJob3 = new Job(barry, 33,230);

        // posting the jobs on the market
        market.addJob(hansJob1);
        market.addJob(hansJob2);
        market.addJob(hansJob3);
        market.addJob(barryJob1);
        market.addJob(barryJob2);
        market.addJob(barryJob3);

    }

    // test basic sorting function for the bid --> must select best job for bid
    @Test
    public void test1(){
        //creating new bid for buyer mar
        Bid marksBid = new Bid(mark,20,80);
        //posting bid to market
        market.addBid(marksBid);

        // running script to find jobs that match bid requirements for complete market
        market.matchBidsJobs();

        // best job should be Hans's job because he offers same thing but his review score is higher
        Job expectedFirstJob = new Job(hans,10,101);
        Job actualFirstJob = market.getJobsBid(marksBid).pop();

        assertEquals(expectedFirstJob,actualFirstJob);
    }

    // testing that bids meet minimum review score as set by buyer
    @Test
    public void test2(){
        //creating new bid for buyer mark
        Bid marksBid = new Bid(mark,20,80);
        // setting minimum requirements for mark's bid
        marksBid.setAllMinScores(60,70,80,90);

        market.addBid(marksBid);

        // running method to find jobs that match bid requirements for complete market
        market.matchBidsJobs();

        // second offer should no longer be from barry because his review score is too low for mark
        Job expectedSeccondJob = new Job(hans,15,100);
        Job actualSeccondJob = market.getJobsBid(marksBid).get(1);

        assertEquals(expectedSeccondJob,actualSeccondJob);
    }

    // Test that checks the working of the complete system logic with 3 buyers all making one bid.
    @Test
    public void test3(){
        //creating new bid for buyer mark
        Bid marksBid = new Bid(mark,20,80);
        // setting minimum requirements for mark's bid
        marksBid.setAllMinScores(60,70,80,90);

        // creating new bid for buyer sarah (first time buyer --> ths is less preferred by sellers)
        Bid sarahsBid = new Bid(sarah, 35, 230);
        // creating new bid for buyer mike (same bid but mike has more jobs bought --> should get the better deal than sarah)
        Bid mikeBid = new Bid(mike, 35, 230);

        // adding bids to market
        market.addBid(marksBid);
        market.addBid(sarahsBid);
        market.addBid(mikeBid);

        // running method to find jobs that match bid requirements for complete market
        market.matchBidsJobs();

        // running method that arranges the marriages
        market.arangeMarriages();

        System.out.println(market.getAllJobs());

        Bid expectedPartnerMike = mikeBid;
        Bid actualPartnerMike = market.getAllJobs().get(5).getPartner();

        Bid expectedPartnerSarah = sarahsBid;
        Bid actualPartnerSarah = market.getAllJobs().get(4).getPartner();

        assertEquals(expectedPartnerMike,actualPartnerMike);
        assertEquals(expectedPartnerSarah,actualPartnerSarah);
    }


}