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
    Hand hand1,hand2,hand3,hand4,hand5,hand6;

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

        hand1 = new Hand(main1);
        hand2 = new Hand(main2);
        hand3 = new Hand(main3);
        hand4 = new Hand(main4);
        hand5 = new Hand(main5);
        hand6 = new Hand(main6);

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
        setUp();
        assertEquals(1,hand2.compareTo(hand3));
        setUp();
        assertEquals(-1,hand3.compareTo(hand1));
        setUp();
        assertEquals(0,hand1.compareTo(hand2));
        setUp();
        assertEquals(1,hand2.compareTo(hand4));
    }

    /**
     * Test for the function comparePatterns and at the same time the function deleteCardInPattern with the equality of the comparaison between Hand1 and Hand2
     */
    @Test
    void testComparePattern(){
        assertEquals(1,hand2.compareTo(hand3));
        assertEquals(-1,hand3.compareTo(hand1));
        assertEquals(1,hand2.compareTo(hand4));
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
        assertEquals(1,hand6.compareTo(hand4));
        assertEquals(0,hand1.compareTo(hand2));
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
        assertEquals(0, hand1.compareCards(hand2));
        assertEquals(1, hand3.compareCards(hand1));
        assertEquals(-1, hand1.compareCards(hand3));
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
        testArray.add(0,Value.TWO.ordinal());
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
