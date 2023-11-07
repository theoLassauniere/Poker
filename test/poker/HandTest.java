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
    Hand highestAce1, highestAce2, highestKing,
            pairOfEights, secondPairOfEights, pairOfTwos, thirdPairOfEights,
            threeTwos,
            doublePairOfTwosAndEights,
            fourAces,
            fullThreeEight, fullThreeAce,
            bigStraight, littleStraight, almostStraight,
            aceDiamonds, nineDiamonds, almostDiamonds,
            bigStraightFlush, littleStraightFlush, almostStraightFlush;


    @BeforeEach
    void setUp() {
        highestAce1 = new Hand(new Card[]{
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.FIVE, Color.SPADES)
        });

        highestAce2 = new Hand(new Card[]{
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.FIVE, Color.SPADES)
        });

        highestKing = new Hand(new Card[]{
                new Card(Value.KING, Color.DIAMONDS),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.EIGHT, Color.SPADES),
                new Card(Value.SEVEN, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS)
        });

        pairOfEights = new Hand(new Card[]{
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.FIVE, Color.SPADES)
        });

        secondPairOfEights = new Hand(new Card[]{
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.KING, Color.SPADES),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS),
                new Card(Value.FIVE, Color.SPADES)
        });

        pairOfTwos = new Hand(new Card[]{
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.DIAMONDS),
                new Card(Value.FIVE, Color.HEARTS)
        });

        thirdPairOfEights = new Hand(new Card[]{
                new Card(Value.EIGHT, Color.SPADES),
                new Card(Value.KING, Color.DIAMONDS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.SEVEN, Color.SPADES),
                new Card(Value.ACE, Color.SPADES)
        });

        doublePairOfTwosAndEights = new Hand(new Card[]{
                new Card(Value.TWO, Color.SPADES),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.FIVE, Color.SPADES)
        });

        threeTwos = new Hand(new Card[]{
                new Card(Value.TWO, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.SPADES),
                new Card(Value.FIVE, Color.SPADES)
        });

        bigStraight = new Hand(new Card[]{
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TEN, Color.HEARTS)
        });

        littleStraight = new Hand(new Card[]{
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.HEARTS)
        });

        almostStraight = new Hand(new Card[]{
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS)
        });

        aceDiamonds = new Hand(new Card[]{
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.KING, Color.DIAMONDS),
                new Card(Value.QUEEN, Color.DIAMONDS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TWO, Color.DIAMONDS)
        });

        nineDiamonds = new Hand(new Card[]{
                new Card(Value.NINE, Color.DIAMONDS),
                new Card(Value.FIVE, Color.DIAMONDS),
                new Card(Value.THREE, Color.DIAMONDS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.TWO, Color.DIAMONDS)
        });

        almostDiamonds = new Hand(new Card[]{
                new Card(Value.NINE, Color.DIAMONDS),
                new Card(Value.FIVE, Color.DIAMONDS),
                new Card(Value.THREE, Color.DIAMONDS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS)
        });

        fullThreeEight = new Hand(new Card[]{
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.EIGHT, Color.SPADES),
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.TWO, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS)
        });

        fullThreeAce = new Hand(new Card[]{
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.ACE, Color.SPADES),
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.TWO, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS)
        });

        fourAces = new Hand(new Card[]{
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.ACE, Color.CLUBS),
                new Card(Value.ACE, Color.SPADES),
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.SPADES)
        });

        bigStraightFlush = new Hand(new Card[]{
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.HEARTS),
                new Card(Value.TEN, Color.HEARTS)
        });

        littleStraightFlush = new Hand(new Card[]{
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.HEARTS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.HEARTS)
        });

        almostStraightFlush = new Hand(new Card[]{
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.HEARTS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.SPADES)
        });
    }

    /**
     * Test the hand equals method
     */
    @Test
    void equalsTest() {
        assertEquals(highestAce1, highestAce2);
        assertNotEquals(highestAce1, highestKing);
    }

    /**
     * Test the hand hashCode method
     */
    @Test
    void hashCodeTest() {
        assertEquals(highestAce1.hashCode(), highestAce2.hashCode());
        assertNotEquals(highestAce1.hashCode(), highestKing.hashCode());
    }

    /**
     * Test the hand toStringMethod
     */
    @Test
    void toStringTest() {
        assertEquals("Main (ACa APi ACo 2Ca 2Co)", fullThreeAce.toString());
        assertEquals("Main (8Ca 8Pi 8Co 2Ca 2Co)", fullThreeEight.toString());
        fullThreeEight.setName("1st Player");
        assertEquals("1st Player (8Ca 8Pi 8Co 2Ca 2Co)", fullThreeEight.toString());
    }

    /**
     * Test of the sorting of the cards in the hand
     */
    @Test
    void sortTest() {
        assertArrayEquals(new Card[]{
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.FIVE, Color.SPADES),
                new Card(Value.TWO, Color.CLUBS)
        }, pairOfEights.getCards());
    }

    /**
     * Tests if the hands are correctly detected as valid/invalid (no duplicated cards)
     */
    @Test
    void testHandValidity() {
        assertDoesNotThrow(() -> new Hand(new Card[]{new Card(Value.THREE, Color.HEARTS), new Card(Value.THREE, Color.DIAMONDS), new Card(Value.THREE, Color.SPADES), new Card(Value.THREE, Color.CLUBS)}));
        assertDoesNotThrow(() -> new Hand(new Card[]{new Card(Value.THREE, Color.HEARTS), new Card(Value.FOUR, Color.HEARTS), new Card(Value.FIVE, Color.HEARTS), new Card(Value.SIX, Color.HEARTS)}));
        assertDoesNotThrow(() -> new Hand(new Card[]{new Card(Value.KING, Color.DIAMONDS), new Card(Value.KING, Color.SPADES), new Card(Value.QUEEN, Color.SPADES)}));
        assertThrowsExactly(IllegalArgumentException.class, () -> new Hand(new Card[]{new Card(Value.THREE, Color.HEARTS), new Card(Value.THREE, Color.HEARTS)}));
        assertThrowsExactly(IllegalArgumentException.class, () -> new Hand(new Card[]{new Card(Value.KING, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS)}));
    }

    /**
     * Tests the straight hand detection
     */
    @Test
    void isStraightTest() {
        Hand littleHand = new Hand(new Card[]{new Card(Value.ACE, Color.HEARTS)});
        assertFalse(littleHand.isStraight());
        assertFalse(pairOfTwos.isStraight());
        assertTrue(bigStraight.isStraight());
        assertTrue(littleStraight.isStraight());
        assertFalse(almostStraight.isStraight());
        assertTrue(bigStraightFlush.isStraight());
        assertTrue(littleStraightFlush.isStraight());
        assertTrue(almostStraightFlush.isStraight());
    }

    /**
     * Test the compareTo method with all the possibilities (equality,superiority,inferiority)
     **/
    @Test
    void compareTo() {
        assertEquals(1, secondPairOfEights.compareTo(highestAce1));
        assertEquals(-1, highestAce1.compareTo(pairOfEights));
        assertEquals(0, pairOfEights.compareTo(secondPairOfEights));
        assertEquals(1, secondPairOfEights.compareTo(pairOfTwos));
    }

    /**
     * Test for the function comparePatterns
     * when we have an equality of patterns like in the comparison between Hand1 and Hand2
     */
    @Test
    void testComparePattern() {
        assertEquals(new Winner(secondPairOfEights, Patterns.PAIR, Value.EIGHT), secondPairOfEights.comparePatterns(highestAce1));
        assertEquals(new Winner(pairOfEights, Patterns.PAIR, Value.EIGHT), highestAce1.comparePatterns(pairOfEights));
        assertEquals(new Winner(secondPairOfEights, Patterns.PAIR, Value.EIGHT), secondPairOfEights.comparePatterns(pairOfTwos));
        assertEquals(new Winner(threeTwos, Patterns.THREE_OF_A_KIND, Value.TWO), threeTwos.comparePatterns(pairOfTwos));
        assertEquals(new Winner(null, Patterns.EQUALITY, null), pairOfEights.comparePatterns(secondPairOfEights));
        assertEquals(new Winner(highestAce1, Patterns.HIGHER, Value.ACE), highestAce1.comparePatterns(highestKing));
        assertEquals(new Winner(pairOfEights, Patterns.PAIR, Value.EIGHT), pairOfEights.comparePatterns(highestAce1));
        assertEquals(new Winner(pairOfEights, Patterns.PAIR, Value.EIGHT), pairOfEights.comparePatterns(pairOfTwos));
        assertEquals(new Winner(thirdPairOfEights, Patterns.HIGHER, Value.ACE), pairOfEights.comparePatterns(thirdPairOfEights));
        assertEquals(new Winner(doublePairOfTwosAndEights, Patterns.DOUBLE_PAIR, Value.EIGHT), doublePairOfTwosAndEights.comparePatterns(highestKing));
        assertEquals(new Winner(fourAces, Patterns.FOUR_OF_A_KIND, Value.ACE), fourAces.comparePatterns(pairOfEights));
        assertEquals(new Winner(bigStraight, Patterns.STRAIGHT, Value.ACE), bigStraight.comparePatterns(littleStraight));
        assertEquals(new Winner(bigStraight, Patterns.STRAIGHT, Value.ACE), bigStraight.comparePatterns(pairOfEights));
        assertEquals(new Winner(aceDiamonds, Patterns.FLUSH, Value.ACE), aceDiamonds.comparePatterns(nineDiamonds));
        assertEquals(new Winner(fourAces, Patterns.FOUR_OF_A_KIND, Value.ACE), aceDiamonds.comparePatterns(fourAces));
        assertEquals(new Winner(fullThreeEight, Patterns.FULL, Value.EIGHT), fullThreeEight.comparePatterns(aceDiamonds));
        assertEquals(new Winner(fullThreeAce, Patterns.FULL, Value.ACE), fullThreeEight.comparePatterns(fullThreeAce));
        assertEquals(new Winner(fullThreeAce, Patterns.FULL, Value.ACE), fullThreeAce.comparePatterns(thirdPairOfEights));
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
        assertEquals(Map.of(Patterns.HIGHER, new ArrayList<>(List.of(Value.ACE, Value.KING, Value.EIGHT, Value.FIVE, Value.TWO))), highestAce1.getPatterns());
        assertEquals(Map.of(Patterns.PAIR, new ArrayList<>(List.of(Value.EIGHT)), Patterns.HIGHER, new ArrayList<>(List.of(Value.KING, Value.FIVE, Value.TWO))), secondPairOfEights.getPatterns());
        assertEquals(Map.of(Patterns.DOUBLE_PAIR, new ArrayList<>(List.of(Value.EIGHT, Value.TWO)), Patterns.HIGHER, new ArrayList<>(List.of(Value.FIVE))), doublePairOfTwosAndEights.getPatterns());
        assertEquals(Map.of(Patterns.THREE_OF_A_KIND, new ArrayList<>(List.of(Value.TWO)), Patterns.HIGHER, new ArrayList<>(List.of(Value.EIGHT, Value.FIVE))), threeTwos.getPatterns());
        assertEquals(Map.of(Patterns.STRAIGHT, new ArrayList<>(List.of(Value.ACE))), bigStraight.getPatterns());
        assertEquals(Map.of(Patterns.STRAIGHT, new ArrayList<>(List.of(Value.SIX))), littleStraight.getPatterns());
        assertEquals(Map.of(Patterns.STRAIGHT, new ArrayList<>(List.of(Value.SIX))), almostStraightFlush.getPatterns());
        assertEquals(Map.of(Patterns.FLUSH, new ArrayList<>(List.of(Value.ACE)), Patterns.HIGHER, new ArrayList<>(List.of(Value.ACE, Value.KING, Value.QUEEN, Value.JACK, Value.TWO))), aceDiamonds.getPatterns());
        assertEquals(Map.of(Patterns.FLUSH, new ArrayList<>(List.of(Value.NINE)), Patterns.HIGHER, new ArrayList<>(List.of(Value.NINE, Value.FIVE, Value.FOUR, Value.THREE, Value.TWO))), nineDiamonds.getPatterns());
        assertEquals(Map.of(Patterns.FULL, new ArrayList<>(List.of(Value.EIGHT))), fullThreeEight.getPatterns());
        assertEquals(Map.of(Patterns.FULL, new ArrayList<>(List.of(Value.ACE))), fullThreeAce.getPatterns());
        assertEquals(Map.of(Patterns.FOUR_OF_A_KIND, new ArrayList<>(List.of(Value.ACE)), Patterns.HIGHER, new ArrayList<>(List.of(Value.KING))), fourAces.getPatterns());
        assertEquals(Map.of(Patterns.STRAIGHT_FLUSH, new ArrayList<>(List.of(Value.ACE))), bigStraightFlush.getPatterns());
        assertEquals(Map.of(Patterns.STRAIGHT_FLUSH, new ArrayList<>(List.of(Value.SIX))), littleStraightFlush.getPatterns());
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
        assertThrowsExactly(ParseException.class, () -> Hand.parse("Ca", 1));
        assertThrowsExactly(ParseException.class, () -> Hand.parse("Car", 1));
        assertThrowsExactly(ParseException.class, () -> Hand.parse("3Va", 1));
        assertThrowsExactly(ParseException.class, () -> Hand.parse("1Ca", 1));
        assertThrowsExactly(ParseException.class, () -> Hand.parse("WTr", 1));
        assertThrowsExactly(IllegalArgumentException.class, () -> Hand.parse("3 5", 1));
        assertThrowsExactly(IllegalArgumentException.class, () -> Hand.parse("3Ca 5Ca", 1));
        assertArrayEquals(new Card[]{new Card(Value.THREE, Color.DIAMONDS)}, Hand.parse("3Ca", 1).getCards());
        assertArrayEquals(new Card[]{new Card(Value.TEN, Color.SPADES)}, Hand.parse("10Pi", 1).getCards());
        assertArrayEquals(new Card[]{new Card(Value.KING, Color.CLUBS)}, Hand.parse("KTr", 1).getCards());
        assertArrayEquals(new Card[]{new Card(Value.KING, Color.HEARTS)}, Hand.parse("KCo", 1).getCards());
        assertArrayEquals(new Card[]{new Card(Value.KING, Color.CLUBS), new Card(Value.KING, Color.HEARTS)}, Hand.parse("KTr KCo", 2).getCards());
    }

    /**
     * Test of isSameColor method
     */
    @Test
    void testSameColor() {
        assertTrue(aceDiamonds.isSameColor());
        assertTrue(nineDiamonds.isSameColor());
        assertFalse(almostDiamonds.isSameColor());
        assertFalse(bigStraight.isSameColor());
        assertTrue(bigStraightFlush.isSameColor());
        assertFalse(almostStraight.isSameColor());
        assertFalse(almostStraightFlush.isSameColor());
        assertFalse(littleStraight.isSameColor());
        assertTrue(littleStraightFlush.isSameColor());
        assertFalse(pairOfEights.isSameColor());
        assertFalse(secondPairOfEights.isSameColor());
        assertFalse(thirdPairOfEights.isSameColor());
        assertFalse(pairOfTwos.isSameColor());
        assertFalse(threeTwos.isSameColor());
        assertFalse(doublePairOfTwosAndEights.isSameColor());
        assertFalse(fourAces.isSameColor());
    }
}
