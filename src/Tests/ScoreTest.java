package Tests;

import static org.junit.Assert.*;
import MarketActorsModel.Score;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit testing for the Score class to ensure score is calculated correctly
 */
public class ScoreTest {

    private Score score1;

    // seting up a new score variable
    @Before
    public void setUp() {
        score1 = new Score();
    }

    // making sure that after runing test score is initialised back to initial position again
    @After
    public void afterTest(){
        score1 = new Score();
    }

    // testing simple calculation for score
    @Test
    public void test1() {
        score1.updateScore(100);
        score1.updateScore(0);

        int expectedScore = 50;
        int actualScore = score1.getScore();
        assertEquals(expectedScore,actualScore);
    }

    // testing if average calculation works when entering all values 0-100 --> avg should be 50
    @Test
    public void test2(){
        for(int i =0; i<= 100; i++){
            score1.updateScore(i);
        }
        int expectedScore = 50;
        int actualScore = score1.getScore();

        assertEquals(expectedScore,actualScore);
    }

    // testing scoring higher than 100 throws excpetion
    @Test (expected = IllegalArgumentException.class)
    public void test3(){
        score1.updateScore(101);
    }

    // testing scoring lower than 0  throws excpetion
    @Test (expected = IllegalArgumentException.class)
    public void test4(){
        score1.updateScore(-1);
    }

    // testing toString method
    @Test
    public void test5(){
        score1.updateScore(23);
        score1.updateScore(88);
        String expectedString = "Score = 56\n" + "numReviews = 2";
        String actualString = score1.toString();

        assertEquals(expectedString,actualString);
    }
}