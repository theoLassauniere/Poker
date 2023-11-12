package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Team B
 */
class CardTest {
    Card c1;
    Card c2;
    Card c3;
    Card c4;

    /**
     * Initialize Cards for testing
     **/
    @BeforeEach
    void setUp() {
        c1 = new Card(Value.EIGHT, Color.HEARTS);
        c2 = new Card(Value.KING, Color.DIAMONDS);
        c3 = new Card(Value.EIGHT, Color.CLUBS);
        c4 = new Card(Value.TWO, Color.HEARTS);
    }

    /**
     * Test the compareTo method with all the possibilities (equality,superiority,inferiority)
     **/
    @Test
    void testCompareTo() {
        assertEquals(0, c1.compareTo(c3));
        assertEquals(1, c2.compareTo(c1));
        assertEquals(-1, c1.compareTo(c2));
    }

    /**
     * Test the equals method with an equality and an inequality
     **/
    @Test
    void testEquals() {
        assertNotEquals(c1, c2);
        assertNotEquals(c1, c3);
        assertEquals(new Card(Value.EIGHT, Color.CLUBS), c3);
    }

    /**
     * Test card parsing
     **/
    @Test
    void testParsing() {
        assertTrue(Card.tryParse("").isEmpty());
        assertTrue(Card.tryParse("1").isEmpty());
        assertTrue(Card.tryParse("26").isEmpty());
        assertTrue(Card.tryParse("W").isEmpty());
        assertTrue(Card.tryParse("3").isEmpty());
        assertTrue(Card.tryParse("10").isEmpty());
        assertTrue(Card.tryParse("K").isEmpty());
        assertTrue(Card.tryParse("Ca").isEmpty());
        assertTrue(Card.tryParse("Car").isEmpty());
        assertTrue(Card.tryParse("3Va").isEmpty());
        assertTrue(Card.tryParse("1Ca").isEmpty());
        assertTrue(Card.tryParse("WTr").isEmpty());
        assertEquals(new Card(Value.THREE, Color.DIAMONDS), Card.tryParse("3Ca").orElseThrow());
        assertEquals(new Card(Value.TEN, Color.SPADES), Card.tryParse("10Pi").orElseThrow());
        assertEquals(new Card(Value.KING, Color.CLUBS), Card.tryParse("KTr").orElseThrow());
        assertEquals(new Card(Value.KING, Color.HEARTS), Card.tryParse("KCo").orElseThrow());
    }

    /**
     * Test the compareOrdinal
     */
    @Test
    void testCompareOrdinal() {
        assertEquals(-5, c1.compareOrdinal(c2));
        assertEquals(11, c2.compareOrdinal(c4));
    }
}