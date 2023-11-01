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
    Hand highestAce, highestKing,
            pairOfEights, secondPairOfEights, pairOfTwos, thirdPairOfEights,
            threeTwos,
            doublePairOfTwosAndEights,
            fourAces,
            bigStraight, littleStraight, almostStraight;


    @BeforeEach
    void setUp() {
        highestAce = new Hand(new Card[]{
                new Card(Value.ACE),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        highestKing = new Hand(new Card[]{
                new Card(Value.KING),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.SEVEN),
                new Card(Value.FIVE)
        });

        pairOfEights = new Hand(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        secondPairOfEights = new Hand(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        pairOfTwos = new Hand(new Card[]{
                new Card(Value.ACE),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        thirdPairOfEights = new Hand(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.SEVEN),
                new Card(Value.ACE)
        });

        threeTwos = new Hand(new Card[]{
                new Card(Value.TWO),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        });

        doublePairOfTwosAndEights = new Hand(new Card[]{
                new Card(Value.TWO),
                new Card(Value.TWO),
                new Card(Value.EIGHT),
                new Card(Value.EIGHT),
                new Card(Value.FIVE)
        });

        fourAces = new Hand(new Card[]{
                new Card(Value.ACE),
                new Card(Value.ACE),
                new Card(Value.ACE),
                new Card(Value.ACE),
                new Card(Value.KING)
        });

        bigStraight = new Hand(new Card[]{
                new Card(Value.ACE),
                new Card(Value.KING),
                new Card(Value.QUEEN),
                new Card(Value.JACK),
                new Card(Value.TEN)
        });

        littleStraight = new Hand(new Card[]{
                new Card(Value.SIX),
                new Card(Value.FIVE),
                new Card(Value.FOUR),
                new Card(Value.THREE),
                new Card(Value.TWO)
        });

        almostStraight = new Hand(new Card[]{
                new Card(Value.ACE),
                new Card(Value.KING),
                new Card(Value.QUEEN),
                new Card(Value.JACK),
                new Card(Value.TWO)
        });

    }

    /**
     * Test of the sorting of the cards in the hand
     */
    @Test
    void sortTest() {
        assertArrayEquals(new Card[]{
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.EIGHT),
                new Card(Value.FIVE),
                new Card(Value.TWO)
        }, pairOfEights.getCards());
    }

    @Test
    void isStraightTest() {
        assertFalse(pairOfTwos.isStraight());
        assertTrue(bigStraight.isStraight());
        assertTrue(littleStraight.isStraight());
        assertFalse(almostStraight.isStraight());
    }

    /**
     * Test the compareTo method with all the possibilities (equality,superiority,inferiority)
     **/
    @Test
    void compareTo() {
        assertEquals(1, secondPairOfEights.compareTo(highestAce));
        assertEquals(-1, highestAce.compareTo(pairOfEights));
        assertEquals(0, pairOfEights.compareTo(secondPairOfEights));
        assertEquals(1, secondPairOfEights.compareTo(pairOfTwos));
    }

    /**
     * Test for the getResult method with all cases
     */
    @Test
    void getResultTest() {
        assertEquals(new HandComparison(0, Patterns.EQUALITY, null), pairOfEights.getResult(secondPairOfEights));
        assertEquals(new HandComparison(1, Patterns.HIGHER, List.of(Value.ACE)), highestAce.getResult(highestKing));
        assertEquals(new HandComparison(1, Patterns.PAIR, List.of(Value.EIGHT)), pairOfEights.getResult(highestAce));
        assertEquals(new HandComparison(1, Patterns.PAIR, List.of(Value.EIGHT)), pairOfEights.getResult(pairOfTwos));
        assertEquals(new HandComparison(-1, Patterns.HIGHER, List.of(Value.ACE)), pairOfEights.getResult(thirdPairOfEights));
    }

    /**
     * Test for the function comparePatterns and at the same time the function deleteCardInPattern
     * when we have an equality of patterns like in the comparison between Hand1 and Hand2
     */
    @Test
    void testComparePattern() {
        assertEquals(new HandComparison(1, Patterns.PAIR, List.of(Value.EIGHT)), secondPairOfEights.comparePatterns(highestAce));
        assertEquals(new HandComparison(-1, Patterns.PAIR, List.of(Value.EIGHT)), highestAce.comparePatterns(pairOfEights));
        assertEquals(new HandComparison(1, Patterns.PAIR, List.of(Value.EIGHT)), secondPairOfEights.comparePatterns(pairOfTwos));
        assertEquals(new HandComparison(1, Patterns.THREE_OF_A_KIND, List.of(Value.TWO)), threeTwos.comparePatterns(pairOfTwos));
        assertEquals(new HandComparison(0, Patterns.EQUALITY, null), pairOfEights.comparePatterns(secondPairOfEights));
        assertEquals(new HandComparison(1, Patterns.DOUBLE_PAIR, List.of(Value.EIGHT, Value.TWO)), doublePairOfTwosAndEights.comparePatterns(highestKing));
        assertEquals(new HandComparison(1, Patterns.FOUR_OF_A_KIND, List.of(Value.ACE)), fourAces.comparePatterns(pairOfEights));
        assertEquals(new HandComparison(1, Patterns.STRAIGHT, List.of(Value.ACE)), bigStraight.comparePatterns(littleStraight));
        assertEquals(new HandComparison(1, Patterns.STRAIGHT, List.of(Value.ACE)), bigStraight.comparePatterns(pairOfEights));
    }

    /**
     * Test of the number of occurrences of each values in the hand
     */
    @Test
    void occurrencesTest() {
        assertEquals(Map.of(
                Value.TWO, 1,
                Value.FIVE, 1,
                Value.EIGHT, 2,
                Value.KING, 1
        ), pairOfEights.occurrences());
    }

    /**
     * Test of the function getPatterns in Hand class with all the case possible of pattern in a Hand
     */
    @Test
    void testGetPattern() {
        assertEquals(Map.of(Patterns.HIGHER, new ArrayList<>(List.of(Value.ACE, Value.KING, Value.EIGHT, Value.FIVE, Value.TWO))), highestAce.getPatterns());
        assertEquals(Map.of(Patterns.PAIR, new ArrayList<>(List.of(Value.EIGHT)), Patterns.HIGHER, new ArrayList<>(List.of(Value.KING, Value.FIVE, Value.TWO))), secondPairOfEights.getPatterns());
        assertEquals(Map.of(Patterns.DOUBLE_PAIR, new ArrayList<>(List.of(Value.EIGHT, Value.TWO)), Patterns.HIGHER, new ArrayList<>(List.of(Value.FIVE))), doublePairOfTwosAndEights.getPatterns());
        assertEquals(Map.of(Patterns.THREE_OF_A_KIND, new ArrayList<>(List.of(Value.TWO)), Patterns.HIGHER, new ArrayList<>(List.of(Value.EIGHT, Value.FIVE))), threeTwos.getPatterns());
        assertEquals(Map.of(Patterns.FOUR_OF_A_KIND, new ArrayList<>(List.of(Value.ACE)), Patterns.HIGHER, new ArrayList<>(List.of(Value.KING))), fourAces.getPatterns());
        assertEquals(Map.of(Patterns.STRAIGHT, new ArrayList<>(List.of(Value.ACE))), bigStraight.getPatterns());
        assertEquals(Map.of(Patterns.STRAIGHT, new ArrayList<>(List.of(Value.SIX))), littleStraight.getPatterns());
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
