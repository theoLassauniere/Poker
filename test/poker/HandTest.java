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
        assertEquals(new HandComparison(0, Patterns.EQUALITY, null), hand1.getResult(hand2));
        setUp();
        assertEquals(new HandComparison(1, Patterns.HIGHER, List.of(Value.ACE)), hand3.getResult(hand7));
        assertEquals(new HandComparison(1, Patterns.PAIR, List.of(Value.EIGHT)), hand1.getResult(hand3));
        assertEquals(new HandComparison(1, Patterns.PAIR, List.of(Value.EIGHT)), hand1.getResult(hand4));
        setUp();
        hand1.sortHand();
        hand8.sortHand();
        assertEquals(new HandComparison(-1, Patterns.HIGHER, List.of(Value.ACE)), hand1.getResult(hand8));
    }

    /**
     * Test for the function comparePatterns and at the same time the function deleteCardInPattern
     * when we have an equality of patterns like in the comparison between Hand1 and Hand2
     */
    @Test
    void testComparePattern() {
        assertEquals(new HandComparison(1, Patterns.PAIR, List.of(Value.EIGHT)), hand2.comparePatterns(hand3));
        assertEquals(new HandComparison(-1, Patterns.PAIR, List.of(Value.EIGHT)), hand3.comparePatterns(hand1));
        assertEquals(new HandComparison(1, Patterns.PAIR, List.of(Value.EIGHT)), hand2.comparePatterns(hand4));
        assertEquals(new HandComparison(1, Patterns.THREE_OF_A_KIND, List.of(Value.TWO)), hand6.comparePatterns(hand4));
        assertEquals(new HandComparison(0, Patterns.EQUALITY, null), hand1.comparePatterns(hand2));
        assertEquals(new HandComparison(1, Patterns.DOUBLE_PAIR, List.of(Value.EIGHT, Value.TWO)), hand5.comparePatterns(hand7)); //TODO : modify the return value of comparePatterns
    }

    @Test
    void occurrencesTest() {
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
        assertEquals(Map.of(Patterns.HIGHER, new ArrayList<>(List.of(Value.ACE, Value.KING, Value.EIGHT, Value.FIVE, Value.TWO))), hand3.getPatterns());
        assertEquals(Map.of(Patterns.PAIR, new ArrayList<>(List.of(Value.EIGHT)), Patterns.HIGHER, new ArrayList<>(List.of(Value.KING, Value.FIVE, Value.TWO))), hand2.getPatterns());
        assertEquals(Map.of(Patterns.DOUBLE_PAIR, new ArrayList<>(List.of(Value.EIGHT, Value.TWO)), Patterns.HIGHER, new ArrayList<>(List.of(Value.FIVE))), hand5.getPatterns());
        assertEquals(Map.of(Patterns.THREE_OF_A_KIND, new ArrayList<>(List.of(Value.TWO)), Patterns.HIGHER, new ArrayList<>(List.of(Value.EIGHT, Value.FIVE))), hand6.getPatterns());
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
