package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
            bigStraightFlush, littleStraightFlush, almostStraightFlush,
    // 7 cards hands :
    sevenCardHandStraightEnd, sevenCardHandStraightBeginning, sevenCardHandStraightDuplicate,
            sevenCardHandStraightTriplicate, sevenCardHandAlmostStraight;


    @BeforeEach
    void setUp() {
        highestAce1 = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.FIVE, Color.SPADES)
        )));

        highestAce2 = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.FIVE, Color.SPADES)
        )));

        highestKing = new Hand(new ArrayList<>(List.of(
                new Card(Value.KING, Color.DIAMONDS),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.EIGHT, Color.SPADES),
                new Card(Value.SEVEN, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS)
        )));

        pairOfEights = new Hand(new ArrayList<>(List.of(
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.FIVE, Color.SPADES)
        )));

        secondPairOfEights = new Hand(new ArrayList<>(List.of(
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.KING, Color.SPADES),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS),
                new Card(Value.FIVE, Color.SPADES)
        )));

        pairOfTwos = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.DIAMONDS),
                new Card(Value.FIVE, Color.HEARTS)
        )));

        thirdPairOfEights = new Hand(new ArrayList<>(List.of(
                new Card(Value.EIGHT, Color.SPADES),
                new Card(Value.KING, Color.DIAMONDS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.SEVEN, Color.SPADES),
                new Card(Value.ACE, Color.SPADES)
        )));

        doublePairOfTwosAndEights = new Hand(new ArrayList<>(List.of(
                new Card(Value.TWO, Color.SPADES),
                new Card(Value.TWO, Color.CLUBS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.FIVE, Color.SPADES)
        )));

        threeTwos = new Hand(new ArrayList<>(List.of(
                new Card(Value.TWO, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.TWO, Color.SPADES),
                new Card(Value.FIVE, Color.SPADES)
        )));

        bigStraight = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TEN, Color.HEARTS)
        )));

        littleStraight = new Hand(new ArrayList<>(List.of(
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.HEARTS)
        )));

        almostStraight = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS)
        )));

        aceDiamonds = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.KING, Color.DIAMONDS),
                new Card(Value.QUEEN, Color.DIAMONDS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TWO, Color.DIAMONDS)
        )));

        nineDiamonds = new Hand(new ArrayList<>(List.of(
                new Card(Value.NINE, Color.DIAMONDS),
                new Card(Value.FIVE, Color.DIAMONDS),
                new Card(Value.THREE, Color.DIAMONDS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.TWO, Color.DIAMONDS)
        )));

        almostDiamonds = new Hand(new ArrayList<>(List.of(
                new Card(Value.NINE, Color.DIAMONDS),
                new Card(Value.FIVE, Color.DIAMONDS),
                new Card(Value.THREE, Color.DIAMONDS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS)
        )));

        fullThreeEight = new Hand(new ArrayList<>(List.of(
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.EIGHT, Color.SPADES),
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.TWO, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS)
        )));

        fullThreeAce = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.ACE, Color.SPADES),
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.TWO, Color.DIAMONDS),
                new Card(Value.TWO, Color.HEARTS)
        )));

        fourAces = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.ACE, Color.CLUBS),
                new Card(Value.ACE, Color.SPADES),
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.SPADES)
        )));

        bigStraightFlush = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.HEARTS),
                new Card(Value.TEN, Color.HEARTS)
        )));

        littleStraightFlush = new Hand(new ArrayList<>(List.of(
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.HEARTS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.HEARTS)
        )));

        almostStraightFlush = new Hand(new ArrayList<>(List.of(
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.HEARTS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.SPADES)
        )));

        sevenCardHandStraightEnd = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.SIX, Color.SPADES),
                new Card(Value.FIVE, Color.DIAMONDS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.SPADES)
        )));

        sevenCardHandStraightBeginning = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.SPADES),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TEN, Color.DIAMONDS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.SPADES)
        )));

        sevenCardHandStraightDuplicate = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.SPADES),
                new Card(Value.QUEEN, Color.DIAMONDS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TEN, Color.HEARTS),
                new Card(Value.TWO, Color.SPADES)
        )));

        sevenCardHandStraightTriplicate = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.SPADES),
                new Card(Value.QUEEN, Color.DIAMONDS),
                new Card(Value.QUEEN, Color.CLUBS),
                new Card(Value.JACK, Color.HEARTS),
                new Card(Value.TEN, Color.SPADES)
        )));

        sevenCardHandAlmostStraight = new Hand(new ArrayList<>(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.SPADES),
                new Card(Value.QUEEN, Color.DIAMONDS),
                new Card(Value.QUEEN, Color.CLUBS),
                new Card(Value.JACK, Color.HEARTS),
                new Card(Value.TWO, Color.SPADES)
        )));
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
        assertEquals(Stream.of(
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.EIGHT, Color.HEARTS),
                new Card(Value.EIGHT, Color.DIAMONDS),
                new Card(Value.FIVE, Color.SPADES),
                new Card(Value.TWO, Color.CLUBS)
        ).toList(), pairOfEights.getCards());
    }

    /**
     * Tests if the hands are correctly detected as valid/invalid (no duplicated cards)
     */
    @Test
    void testHandValidity() {
        assertDoesNotThrow(() -> new Hand(new ArrayList<>(List.of(new Card(Value.THREE, Color.HEARTS), new Card(Value.THREE, Color.DIAMONDS), new Card(Value.THREE, Color.SPADES), new Card(Value.THREE, Color.CLUBS)))));
        assertDoesNotThrow(() -> new Hand(new ArrayList<>(List.of(new Card(Value.THREE, Color.HEARTS), new Card(Value.FOUR, Color.HEARTS), new Card(Value.FIVE, Color.HEARTS), new Card(Value.SIX, Color.HEARTS)))));
        assertDoesNotThrow(() -> new Hand(new ArrayList<>(List.of(new Card(Value.KING, Color.DIAMONDS), new Card(Value.KING, Color.SPADES), new Card(Value.QUEEN, Color.SPADES)))));
        assertThrowsExactly(IllegalArgumentException.class, () -> new Hand(new ArrayList<>(List.of(new Card(Value.THREE, Color.HEARTS), new Card(Value.THREE, Color.HEARTS)))));
        assertThrowsExactly(IllegalArgumentException.class, () -> new Hand(new ArrayList<>(List.of(new Card(Value.KING, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS)))));
    }

    /**
     * Tests the straight hand detection
     */
    @Test
    void isStraightTest() {
        Hand littleHand = new Hand(new ArrayList<>(List.of(new Card(Value.ACE, Color.HEARTS))));
        assertEquals(Collections.emptyList(), littleHand.findStraight());
        assertEquals(Collections.emptyList(), pairOfTwos.findStraight());
        assertEquals(Collections.emptyList(), almostStraight.findStraight());
        assertEquals(Collections.emptyList(), sevenCardHandAlmostStraight.findStraight());
        assertEquals(List.of(
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.HEARTS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.SPADES)), almostStraightFlush.findStraight()); // almost Straight Flush is a straight
        assertEquals(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TEN, Color.HEARTS)), bigStraight.findStraight());
        assertEquals(List.of(
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.HEARTS)), littleStraight.findStraight());
        assertEquals(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.HEARTS),
                new Card(Value.TEN, Color.HEARTS)), bigStraightFlush.findStraight());
        assertEquals(List.of(
                new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.HEARTS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.HEARTS)), littleStraightFlush.findStraight());
        assertEquals(List.of(
                new Card(Value.SIX, Color.SPADES),
                new Card(Value.FIVE, Color.DIAMONDS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.SPADES)), sevenCardHandStraightEnd.findStraight());
        assertEquals(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.SPADES),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TEN, Color.DIAMONDS)), sevenCardHandStraightBeginning.findStraight());
        assertEquals(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.SPADES),
                new Card(Value.QUEEN, Color.DIAMONDS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TEN, Color.HEARTS)), sevenCardHandStraightDuplicate.findStraight());
        assertEquals(List.of(
                new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.SPADES),
                new Card(Value.QUEEN, Color.DIAMONDS),
                new Card(Value.QUEEN, Color.CLUBS),
                new Card(Value.JACK, Color.HEARTS),
                new Card(Value.TEN, Color.SPADES)), sevenCardHandStraightTriplicate.findStraight());

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
        assertEquals(new Winner(secondPairOfEights, Patterns.PAIR, new Card(Value.EIGHT, Color.HEARTS)), secondPairOfEights.comparePatterns(highestAce1));
        assertEquals(new Winner(pairOfEights, Patterns.PAIR, new Card(Value.EIGHT, Color.HEARTS)), highestAce1.comparePatterns(pairOfEights));
        assertEquals(new Winner(secondPairOfEights, Patterns.PAIR, new Card(Value.EIGHT, Color.HEARTS)), secondPairOfEights.comparePatterns(pairOfTwos));
        assertEquals(new Winner(threeTwos, Patterns.THREE_OF_A_KIND, new Card(Value.TWO, Color.DIAMONDS)), threeTwos.comparePatterns(pairOfTwos));
        assertEquals(new Winner(null, Patterns.EQUALITY, null), pairOfEights.comparePatterns(secondPairOfEights));
        assertEquals(new Winner(highestAce1, Patterns.HIGHER, new Card(Value.ACE, Color.DIAMONDS)), highestAce1.comparePatterns(highestKing));
        assertEquals(new Winner(pairOfEights, Patterns.PAIR, new Card(Value.EIGHT, Color.HEARTS)), pairOfEights.comparePatterns(highestAce1));
        assertEquals(new Winner(pairOfEights, Patterns.PAIR, new Card(Value.EIGHT, Color.HEARTS)), pairOfEights.comparePatterns(pairOfTwos));
        assertEquals(new Winner(thirdPairOfEights, Patterns.HIGHER, new Card(Value.ACE, Color.SPADES)), pairOfEights.comparePatterns(thirdPairOfEights));
        assertEquals(new Winner(doublePairOfTwosAndEights, Patterns.DOUBLE_PAIR, new Card(Value.EIGHT, Color.DIAMONDS)), doublePairOfTwosAndEights.comparePatterns(highestKing));
        assertEquals(new Winner(fourAces, Patterns.FOUR_OF_A_KIND, new Card(Value.ACE, Color.DIAMONDS)), fourAces.comparePatterns(pairOfEights));
        assertEquals(new Winner(bigStraight, Patterns.STRAIGHT, new Card(Value.ACE, Color.HEARTS)), bigStraight.comparePatterns(littleStraight));
        assertEquals(new Winner(bigStraight, Patterns.STRAIGHT, new Card(Value.ACE, Color.HEARTS)), bigStraight.comparePatterns(pairOfEights));
        assertEquals(new Winner(aceDiamonds, Patterns.FLUSH, new Card(Value.ACE, Color.DIAMONDS)), aceDiamonds.comparePatterns(nineDiamonds));
        assertEquals(new Winner(fourAces, Patterns.FOUR_OF_A_KIND, new Card(Value.ACE, Color.DIAMONDS)), aceDiamonds.comparePatterns(fourAces));
        assertEquals(new Winner(fullThreeEight, Patterns.FULL, new Card(Value.EIGHT, Color.DIAMONDS)), fullThreeEight.comparePatterns(aceDiamonds));
        assertEquals(new Winner(fullThreeAce, Patterns.FULL, new Card(Value.ACE, Color.DIAMONDS)), fullThreeEight.comparePatterns(fullThreeAce));
        assertEquals(new Winner(fullThreeAce, Patterns.FULL, new Card(Value.ACE, Color.DIAMONDS)), fullThreeAce.comparePatterns(thirdPairOfEights));
    }

    /**
     * Tests grouping cards by their values
     */
    @Test
    void testGroupCardsByValue() {
        assertEquals(Map.of(
                Value.TWO, List.of(new Card(Value.TWO, Color.CLUBS)),
                Value.FIVE, List.of(new Card(Value.FIVE, Color.SPADES)),
                Value.EIGHT, List.of(new Card(Value.EIGHT, Color.HEARTS), new Card(Value.EIGHT, Color.DIAMONDS)),
                Value.KING, List.of(new Card(Value.KING, Color.HEARTS))
        ), pairOfEights.groupCardsByValue());
    }

    /**
     * Tests grouping cards by their colors
     */
    @Test
    void testGroupCardsByColor() {
        assertEquals(Map.of(
                Color.CLUBS, List.of(new Card(Value.TWO, Color.CLUBS)),
                Color.SPADES, List.of(new Card(Value.FIVE, Color.SPADES)),
                Color.HEARTS, List.of(new Card(Value.KING, Color.HEARTS), new Card(Value.EIGHT, Color.HEARTS)),
                Color.DIAMONDS, List.of(new Card(Value.EIGHT, Color.DIAMONDS))
        ), pairOfEights.groupCardsByColor());
    }

    /**
     * Test of the function findPatterns in Hand class
     */
    @Test
    void testFindPatterns() {
        assertEquals(Map.of(
                Patterns.HIGHER, List.of(
                        List.of(new Card(Value.ACE, Color.DIAMONDS)),
                        List.of(new Card(Value.KING, Color.HEARTS)),
                        List.of(new Card(Value.EIGHT, Color.DIAMONDS)),
                        List.of(new Card(Value.FIVE, Color.SPADES)),
                        List.of(new Card(Value.TWO, Color.CLUBS))
                )), highestAce1.findAllPatterns());
        assertEquals(Map.of(
                Patterns.PAIR, List.of(List.of(
                        new Card(Value.EIGHT, Color.HEARTS),
                        new Card(Value.EIGHT, Color.DIAMONDS)
                )), Patterns.HIGHER, List.of(
                        List.of(new Card(Value.KING, Color.SPADES)),
                        List.of(new Card(Value.FIVE, Color.SPADES)),
                        List.of(new Card(Value.TWO, Color.HEARTS))
                )), secondPairOfEights.findAllPatterns());
        assertEquals(Map.of(
                Patterns.DOUBLE_PAIR, List.of(List.of(
                        new Card(Value.EIGHT, Color.DIAMONDS), new Card(Value.EIGHT, Color.HEARTS),
                        new Card(Value.TWO, Color.CLUBS), new Card(Value.TWO, Color.SPADES))),
                Patterns.PAIR, List.of(
                        List.of(new Card(Value.EIGHT, Color.DIAMONDS), new Card(Value.EIGHT, Color.HEARTS)),
                        List.of(new Card(Value.TWO, Color.CLUBS), new Card(Value.TWO, Color.SPADES))),
                Patterns.HIGHER, List.of(List.of(new Card(Value.FIVE, Color.SPADES))
                )), doublePairOfTwosAndEights.findAllPatterns());
        assertEquals(Map.of(
                Patterns.THREE_OF_A_KIND, List.of(List.of(new Card(Value.TWO, Color.DIAMONDS), new Card(Value.TWO, Color.SPADES), new Card(Value.TWO, Color.HEARTS))),
                Patterns.HIGHER, List.of(List.of(new Card(Value.EIGHT, Color.DIAMONDS)), List.of(new Card(Value.FIVE, Color.SPADES))
                )), threeTwos.findAllPatterns());
        assertEquals(Map.of(
                Patterns.STRAIGHT, List.of(List.of(
                        new Card(Value.ACE, Color.HEARTS), new Card(Value.KING, Color.HEARTS),
                        new Card(Value.QUEEN, Color.HEARTS), new Card(Value.JACK, Color.DIAMONDS),
                        new Card(Value.TEN, Color.HEARTS)
                )), Patterns.HIGHER, List.of(
                        List.of(new Card(Value.ACE, Color.HEARTS)), List.of(new Card(Value.KING, Color.HEARTS)),
                        List.of(new Card(Value.QUEEN, Color.HEARTS)), List.of(new Card(Value.JACK, Color.DIAMONDS)),
                        List.of(new Card(Value.TEN, Color.HEARTS))
                )), bigStraight.findAllPatterns());
        assertEquals(Map.of(
                Patterns.STRAIGHT, List.of(List.of(new Card(Value.SIX, Color.HEARTS),
                        new Card(Value.FIVE, Color.HEARTS), new Card(Value.FOUR, Color.DIAMONDS),
                        new Card(Value.THREE, Color.HEARTS), new Card(Value.TWO, Color.HEARTS)
                )), Patterns.HIGHER, List.of(List.of(new Card(Value.SIX, Color.HEARTS)),
                        List.of(new Card(Value.FIVE, Color.HEARTS)), List.of(new Card(Value.FOUR, Color.DIAMONDS)),
                        List.of(new Card(Value.THREE, Color.HEARTS)), List.of(new Card(Value.TWO, Color.HEARTS))
                )), littleStraight.findAllPatterns());
        assertEquals(Map.of(
                Patterns.STRAIGHT, List.of(List.of(new Card(Value.SIX, Color.HEARTS),
                        new Card(Value.FIVE, Color.HEARTS), new Card(Value.FOUR, Color.HEARTS),
                        new Card(Value.THREE, Color.HEARTS), new Card(Value.TWO, Color.SPADES)
                )), Patterns.HIGHER, List.of(List.of(new Card(Value.SIX, Color.HEARTS)),
                        List.of(new Card(Value.FIVE, Color.HEARTS)), List.of(new Card(Value.FOUR, Color.HEARTS)),
                        List.of(new Card(Value.THREE, Color.HEARTS)), List.of(new Card(Value.TWO, Color.SPADES))
                )), almostStraightFlush.findAllPatterns());
        assertEquals(Map.of(
                Patterns.FLUSH, List.of(List.of(new Card(Value.ACE, Color.DIAMONDS),
                        new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.DIAMONDS),
                        new Card(Value.JACK, Color.DIAMONDS), new Card(Value.TWO, Color.DIAMONDS)
                )), Patterns.HIGHER, List.of(List.of(new Card(Value.ACE, Color.DIAMONDS)),
                        List.of(new Card(Value.KING, Color.DIAMONDS)), List.of(new Card(Value.QUEEN, Color.DIAMONDS)),
                        List.of(new Card(Value.JACK, Color.DIAMONDS)), List.of(new Card(Value.TWO, Color.DIAMONDS))
                )), aceDiamonds.findAllPatterns());
        assertEquals(Map.of(
                Patterns.FLUSH, List.of(List.of(new Card(Value.NINE, Color.DIAMONDS),
                        new Card(Value.FIVE, Color.DIAMONDS), new Card(Value.FOUR, Color.DIAMONDS),
                        new Card(Value.THREE, Color.DIAMONDS), new Card(Value.TWO, Color.DIAMONDS)
                )), Patterns.HIGHER, List.of(List.of(new Card(Value.NINE, Color.DIAMONDS)),
                        List.of(new Card(Value.FIVE, Color.DIAMONDS)), List.of(new Card(Value.FOUR, Color.DIAMONDS)),
                        List.of(new Card(Value.THREE, Color.DIAMONDS)), List.of(new Card(Value.TWO, Color.DIAMONDS))
                )), nineDiamonds.findAllPatterns());
        assertEquals(Map.of(
                Patterns.FULL, List.of(List.of(new Card(Value.EIGHT, Color.DIAMONDS),
                        new Card(Value.EIGHT, Color.SPADES), new Card(Value.EIGHT, Color.HEARTS),
                        new Card(Value.TWO, Color.DIAMONDS), new Card(Value.TWO, Color.HEARTS)
                )), Patterns.THREE_OF_A_KIND, List.of(List.of(new Card(Value.EIGHT, Color.DIAMONDS),
                        new Card(Value.EIGHT, Color.SPADES), new Card(Value.EIGHT, Color.HEARTS)
                )), Patterns.PAIR, List.of(List.of(new Card(Value.TWO, Color.DIAMONDS), new Card(Value.TWO, Color.HEARTS))
                )), fullThreeEight.findAllPatterns());
        assertEquals(Map.of(
                Patterns.FULL, List.of(List.of(new Card(Value.ACE, Color.DIAMONDS),
                        new Card(Value.ACE, Color.SPADES), new Card(Value.ACE, Color.HEARTS),
                        new Card(Value.TWO, Color.DIAMONDS), new Card(Value.TWO, Color.HEARTS)
                )), Patterns.THREE_OF_A_KIND, List.of(List.of(new Card(Value.ACE, Color.DIAMONDS),
                        new Card(Value.ACE, Color.SPADES), new Card(Value.ACE, Color.HEARTS)
                )), Patterns.PAIR, List.of(List.of(new Card(Value.TWO, Color.DIAMONDS), new Card(Value.TWO, Color.HEARTS))
                )), fullThreeAce.findAllPatterns());
        assertEquals(Map.of(
                Patterns.FOUR_OF_A_KIND, List.of(List.of(
                        new Card(Value.ACE, Color.DIAMONDS), new Card(Value.ACE, Color.CLUBS),
                        new Card(Value.ACE, Color.SPADES), new Card(Value.ACE, Color.HEARTS)
                )), Patterns.HIGHER, List.of(List.of(new Card(Value.KING, Color.SPADES))
                )), fourAces.findAllPatterns());
        assertEquals(Map.of(
                Patterns.STRAIGHT_FLUSH, List.of(List.of(new Card(Value.ACE, Color.HEARTS),
                        new Card(Value.KING, Color.HEARTS), new Card(Value.QUEEN, Color.HEARTS),
                        new Card(Value.JACK, Color.HEARTS), new Card(Value.TEN, Color.HEARTS)
                )), Patterns.STRAIGHT, List.of(List.of(new Card(Value.ACE, Color.HEARTS),
                        new Card(Value.KING, Color.HEARTS), new Card(Value.QUEEN, Color.HEARTS),
                        new Card(Value.JACK, Color.HEARTS), new Card(Value.TEN, Color.HEARTS)
                )), Patterns.FLUSH, List.of(List.of(new Card(Value.ACE, Color.HEARTS),
                        new Card(Value.KING, Color.HEARTS), new Card(Value.QUEEN, Color.HEARTS),
                        new Card(Value.JACK, Color.HEARTS), new Card(Value.TEN, Color.HEARTS)
                )), Patterns.HIGHER, List.of(List.of(new Card(Value.ACE, Color.HEARTS)),
                        List.of(new Card(Value.KING, Color.HEARTS)), List.of(new Card(Value.QUEEN, Color.HEARTS)),
                        List.of(new Card(Value.JACK, Color.HEARTS)), List.of(new Card(Value.TEN, Color.HEARTS))
                )), bigStraightFlush.findAllPatterns());
        assertEquals(Map.of(
                Patterns.STRAIGHT_FLUSH, List.of(List.of(new Card(Value.SIX, Color.HEARTS),
                        new Card(Value.FIVE, Color.HEARTS), new Card(Value.FOUR, Color.HEARTS),
                        new Card(Value.THREE, Color.HEARTS), new Card(Value.TWO, Color.HEARTS)
                )), Patterns.STRAIGHT, List.of(List.of(new Card(Value.SIX, Color.HEARTS),
                        new Card(Value.FIVE, Color.HEARTS), new Card(Value.FOUR, Color.HEARTS),
                        new Card(Value.THREE, Color.HEARTS), new Card(Value.TWO, Color.HEARTS)
                )), Patterns.FLUSH, List.of(List.of(new Card(Value.SIX, Color.HEARTS),
                        new Card(Value.FIVE, Color.HEARTS), new Card(Value.FOUR, Color.HEARTS),
                        new Card(Value.THREE, Color.HEARTS), new Card(Value.TWO, Color.HEARTS)
                )), Patterns.HIGHER, List.of(List.of(new Card(Value.SIX, Color.HEARTS)),
                        List.of(new Card(Value.FIVE, Color.HEARTS)), List.of(new Card(Value.FOUR, Color.HEARTS)),
                        List.of(new Card(Value.THREE, Color.HEARTS)), List.of(new Card(Value.TWO, Color.HEARTS))
                )), littleStraightFlush.findAllPatterns());
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
        assertEquals(List.of(new Card(Value.THREE, Color.DIAMONDS)), Hand.parse("3Ca", 1).getCards());
        assertEquals(List.of(new Card(Value.TEN, Color.SPADES)), Hand.parse("10Pi", 1).getCards());
        assertEquals(List.of(new Card(Value.KING, Color.CLUBS)), Hand.parse("KTr", 1).getCards());
        assertEquals(List.of(new Card(Value.KING, Color.HEARTS)), Hand.parse("KCo", 1).getCards());
        assertEquals(List.of(new Card(Value.KING, Color.CLUBS), new Card(Value.KING, Color.HEARTS)), Hand.parse("KTr KCo", 2).getCards());
    }

    /**
     * Test detection of the flush pattern
     */
    @Test
    void testFlush() {
        assertEquals(List.of(new Card(Value.ACE, Color.DIAMONDS),
                new Card(Value.KING, Color.DIAMONDS),
                new Card(Value.QUEEN, Color.DIAMONDS),
                new Card(Value.JACK, Color.DIAMONDS),
                new Card(Value.TWO, Color.DIAMONDS)), aceDiamonds.findFlush());
        assertEquals(List.of(new Card(Value.NINE, Color.DIAMONDS),
                new Card(Value.FIVE, Color.DIAMONDS),
                new Card(Value.FOUR, Color.DIAMONDS),
                new Card(Value.THREE, Color.DIAMONDS),
                new Card(Value.TWO, Color.DIAMONDS)), nineDiamonds.findFlush());
        assertEquals(List.of(new Card(Value.ACE, Color.HEARTS),
                new Card(Value.KING, Color.HEARTS),
                new Card(Value.QUEEN, Color.HEARTS),
                new Card(Value.JACK, Color.HEARTS),
                new Card(Value.TEN, Color.HEARTS)), bigStraightFlush.findFlush());
        assertEquals(List.of(new Card(Value.SIX, Color.HEARTS),
                new Card(Value.FIVE, Color.HEARTS),
                new Card(Value.FOUR, Color.HEARTS),
                new Card(Value.THREE, Color.HEARTS),
                new Card(Value.TWO, Color.HEARTS)), littleStraightFlush.findFlush());

        assertEquals(Collections.emptyList(), almostDiamonds.findFlush());
        assertEquals(Collections.emptyList(), bigStraight.findFlush());
        assertEquals(Collections.emptyList(), almostStraight.findFlush());
        assertEquals(Collections.emptyList(), almostStraightFlush.findFlush());
        assertEquals(Collections.emptyList(), littleStraight.findFlush());
        assertEquals(Collections.emptyList(), pairOfEights.findFlush());
        assertEquals(Collections.emptyList(), secondPairOfEights.findFlush());
        assertEquals(Collections.emptyList(), thirdPairOfEights.findFlush());
        assertEquals(Collections.emptyList(), pairOfTwos.findFlush());
        assertEquals(Collections.emptyList(), threeTwos.findFlush());
        assertEquals(Collections.emptyList(), doublePairOfTwosAndEights.findFlush());
        assertEquals(Collections.emptyList(), fourAces.findFlush());
    }
}
