package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Team B
 */
class HandTest {
    Hand hand1,hand2,hand3,hand4,hand5,hand6,hand7,hand8;

    @BeforeEach
    void setUp() {

        Card[] main1 = new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main2 = new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main3 = new Card[]{
                new Card(Value.ACE),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main4 = new Card[]{
                new Card(Value.ACE),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main5 = new Card[]{
                new Card(Value.TWO),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.EIGHT),
                new Card(Value.FIVE)
        };

        Card[] main6 = new Card[]{
                new Card(Value.TWO),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main7 =  new Card[]{
                new Card(Value.KING),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.SEVEN),
                new Card(Value.FIVE)
        };

        Card[] main8 = new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.SEVEN),
                new Card(Value.ACE)
        };


        hand1 = new Hand(main1);
        hand2 = new Hand(main2);
        hand3 = new Hand(main3);
        hand4 = new Hand(main4);
        hand5 = new Hand(main5);
        hand6 = new Hand(main6);
        hand7 = new Hand(main7);
        hand8 = new Hand(main8);

    }

    /**
     * Some test needs sorted hand because method work only if the hands are sort like getresult
     */
    void setUpSort() {

        Card[] main1 = new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main2 = new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main3 = new Card[]{
                new Card(Value.ACE),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main4 = new Card[]{
                new Card(Value.ACE),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main5 = new Card[]{
                new Card(Value.TWO),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.EIGHT),
                new Card(Value.FIVE)
        };

        Card[] main6 = new Card[]{
                new Card(Value.TWO),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };

        Card[] main7 =  new Card[]{
                new Card(Value.KING),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.SEVEN),
                new Card(Value.FIVE)
        };

        Card[] main8 = new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.SEVEN),
                new Card(Value.ACE)
        };


        hand1 = new Hand(main1);
        hand1.sortHand();
        hand2 = new Hand(main2);
        hand2.sortHand();
        hand3 = new Hand(main3);
        hand3.sortHand();
        hand4 = new Hand(main4);
        hand4.sortHand();
        hand5 = new Hand(main5);
        hand5.sortHand();
        hand6 = new Hand(main6);
        hand6.sortHand();
        hand7 = new Hand(main7);
        hand7.sortHand();
        hand8 = new Hand(main8);
        hand8.sortHand();

    }

    @Test
    void sortTest() {
        assertArrayEquals(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        }, hand1.getCards());
        hand1.sortHand();
        assertArrayEquals(new Card[]{
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.EIGHT),
                new Card(Value.FIVE),
                new Card(Value.TWO)

        }, hand1.getCards());
    }

    /**
     * Test the compareTo method with all the possibilities (equality,superiority,inferiority)
     **/
    @Test
    void compareTo() {
        assertEquals(1,hand2.compareTo(hand3));
        assertEquals(-1,hand3.compareTo(hand1));
        assertEquals(0,hand1.compareTo(hand2));
        setUp();
        assertEquals(1,hand2.compareTo(hand4));
    }

    /**
     * Test for the getResult method with all cases
     */
    @Test
    void getResultTest(){
        setUpSort();
        assertArrayEquals(new int[]{0,-2,0},hand1.getResult(hand2));
        setUpSort();
        assertArrayEquals(new int[]{1,-1,14},hand3.getResult(hand7));
        assertArrayEquals(new int[]{1,2,8},hand1.getResult(hand3));
        assertArrayEquals(new int[]{1,2,8},hand1.getResult(hand4));
        setUpSort();
        assertArrayEquals(new int[]{-1,-1,14},hand1.getResult(hand8));
    }

    /**
     * Test for the function comparePatterns and at the same time the function deleteCardInPattern when we have an equality of patterns like in the comparaison between Hand1 and Hand2
     */
    @Test
    void testComparePattern(){
        assertEquals(1,hand2.comparePatterns(hand3)[0]);
        assertEquals(-1,hand3.comparePatterns(hand1)[0]);
        assertEquals(1,hand2.comparePatterns(hand4)[0]);
        assertArrayEquals(new Card[] {
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        },hand2.getCards());
        assertArrayEquals(new Card[]{
                new Card(Value.ACE),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        },hand4.getCards());
        assertEquals(1,hand6.comparePatterns(hand4)[0]);
        assertEquals(0,hand1.comparePatterns(hand2)[0]);
        assertArrayEquals(new Card[] {
                null,
                new Card(Value.KING),
                null,
                new Card(Value.TWO),
                new Card(Value.FIVE)
        },hand2.getCards());
        assertArrayEquals(new Card[] {
                null,
                new Card(Value.KING),
                null,
                new Card(Value.TWO),
                new Card(Value.FIVE)
        },hand1.getCards());
    }

    /**
     * Test the compareCards method with all the possibilities (equality,superiority,inferiority)
     **/
    @Test
    void compareCards(){
        assertEquals(0, hand1.compareCards(hand2)[0]);
        assertEquals(1, hand3.compareCards(hand1)[0]);
        assertEquals(-1, hand1.compareCards(hand3)[0]);
    }

    @Test
    void occurrencesTest() {
        assertArrayEquals(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        }, hand1.getCards());
        assertArrayEquals(new int[]{1, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 1, 0}, hand1.occurrences());
    }

    /**
     * Test of the function getPatterns in Hand class with all the case possible of pattern in a Hand
     */
    @Test
    void testGetPattern(){
        HashMap<Patterns, ArrayList<Integer>> test = new HashMap<>();
        assertEquals(test,hand3.getPatterns());
        ArrayList<Integer> testArray = new ArrayList<>();
        testArray.add(Value.EIGHT.ordinal());
        test.put(Patterns.Pair,testArray);
        assertEquals(test,hand2.getPatterns());
        testArray.add(Value.TWO.ordinal());
        test.put(Patterns.Pair,testArray);
        assertEquals(test,hand5.getPatterns());
        test = new HashMap<>();
        testArray = new ArrayList<>();
        testArray.add(Value.TWO.ordinal());
        test.put(Patterns.Brelan,testArray);
        assertEquals(test,hand6.getPatterns());
    }

    /**
     * Test hand parsing
     */
    @Test
    void testParsing() throws ParseException {
        assertThrowsExactly(ParseException.class, () -> Hand.parse("", 1));
        assertThrowsExactly(ParseException.class, () -> Hand.parse("1", 1));
        assertThrowsExactly(ParseException.class, () -> Hand.parse("26", 1));
        assertThrowsExactly(ParseException.class, () -> Hand.parse("W", 1));
        assertThrowsExactly(IllegalArgumentException.class, () -> Hand.parse("3 5", 1));
        assertArrayEquals(new Card[]{new Card(Value.THREE)}, Hand.parse("3", 1).getCards());
        assertArrayEquals(new Card[]{new Card(Value.TEN)}, Hand.parse("10", 1).getCards());
        assertArrayEquals(new Card[]{new Card(Value.KING)}, Hand.parse("K", 1).getCards());
    }

}
