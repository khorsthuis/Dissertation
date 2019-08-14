package Tests;

import MarketActorsModel.Seller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SellerTest {

    private Seller mark, steve;

    @Before
    public void setup(){
        mark = new Seller(1, "Mark");
        steve = new Seller(2, "Steve");
    }

    @After
    public void after(){
        mark = new Seller(1, "Mark");
        steve = new Seller(2, "Steve");
    }

    // testing if all setters and getters work correctly
    @Test
    public void test1(){
        mark.setAllScores(40,50,60,70);

        int expectedReliability = 40;
        int expectedResponse = 50;
        int expectedPerformance = 60;
        int expectedAssurance = 70;

        int actualReliability = mark.getReliabilityScore();
        int actualResponse = mark.getResponseTimeScore();
        int actualPerformance = mark.getPerformanceScore();
        int actualAssurance = mark.getAssuranceScore();

        assertEquals(expectedReliability,actualReliability);
        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedPerformance,actualPerformance);
        assertEquals(expectedAssurance,actualAssurance);
    }

}