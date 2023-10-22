package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Team B
 */
class HandTest {
    Hand hand1, hand2, hand3, hand4, hand5, hand6, hand7, hand8;

    @BeforeEach
    void setUp() {

        hand1 = new Hand(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        hand2 = new Hand(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        hand3 = new Hand(new Card[]{
                new Card(Value.ACE),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        hand4 = new Hand(new Card[]{
                new Card(Value.ACE),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        hand5 = new Hand(new Card[]{
                new Card(Value.TWO),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.EIGHT),
                new Card(Value.FIVE)
        });

        hand6 = new Hand(new Card[]{
                new Card(Value.TWO),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        hand7 = new Hand(new Card[]{
                new Card(Value.KING),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.SEVEN),
                new Card(Value.FIVE)
        });

        hand8 = new Hand(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.SEVEN),
                new Card(Value.ACE)
        });
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
        assertEquals(1, hand2.compareTo(hand3));
        assertEquals(-1, hand3.compareTo(hand1));
        assertEquals(0, hand1.compareTo(hand2));
        setUp();
        assertEquals(1, hand2.compareTo(hand4));
    }

    /**
     * Test for the getResult method with all cases
     */
    @Test
    void getResultTest() {
        assertArrayEquals(new int[]{0, -2, 0}, hand1.getResult(hand2));
        setUp();
        assertArrayEquals(new int[]{1, -1, 14}, hand3.getResult(hand7));
        assertArrayEquals(new int[]{1, 2, 8}, hand1.getResult(hand3));
        assertArrayEquals(new int[]{1, 2, 8}, hand1.getResult(hand4));
        setUp();
        hand1.sortHand();
        hand8.sortHand();
        assertArrayEquals(new int[]{-1, -1, 14}, hand1.getResult(hand8));
    }

    /**
     * Test for the function comparePatterns and at the same time the function deleteCardInPattern
     * when we have an equality of patterns like in the comparaison between Hand1 and Hand2
     */
    @Test
    void testComparePattern() {
        assertEquals(1, hand2.comparePatterns(hand3)[0]);
        assertEquals(-1, hand3.comparePatterns(hand1)[0]);
        assertEquals(1, hand2.comparePatterns(hand4)[0]);
        assertArrayEquals(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        }, hand2.getCards());
        assertArrayEquals(new Card[]{
                new Card(Value.ACE),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        }, hand4.getCards());
        assertEquals(1, hand6.comparePatterns(hand4)[0]);
        assertEquals(0, hand1.comparePatterns(hand2)[0]);
        assertArrayEquals(new Card[]{
                null,
                new Card(Value.KING),
                null,
                new Card(Value.TWO),
                new Card(Value.FIVE)
        }, hand2.getCards());
        assertArrayEquals(new Card[]{
                null,
                new Card(Value.KING),
                null,
                new Card(Value.TWO),
                new Card(Value.FIVE)
        }, hand1.getCards());
    }

    /**
     * Test the compareCards method with all the possibilities (equality,superiority,inferiority)
     **/
    @Test
    void compareCards() {
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
        assertEquals(Map.of(
                Value.TWO, 1,
                Value.FIVE, 1,
                Value.EIGHT, 2,
                Value.KING, 1
        ), hand1.occurrences());
    }

    /**
     * Test of the function getPatterns in Hand class with all the case possible of pattern in a Hand
     */
    @Test
    void testGetPattern() {
        assertEquals(Map.of(), hand3.getPatterns());
        assertEquals(Map.of(Patterns.PAIR, new ArrayList<>(List.of(Value.EIGHT))), hand2.getPatterns());
        assertEquals(Map.of(Patterns.PAIR, new ArrayList<>(List.of(Value.EIGHT, Value.TWO))), hand5.getPatterns());
        assertEquals(Map.of(Patterns.BRELAN, new ArrayList<>(List.of(Value.TWO))), hand6.getPatterns());
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
