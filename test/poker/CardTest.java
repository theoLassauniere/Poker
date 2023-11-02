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

    /**
     * Initialize Cards for testing
     **/
    @BeforeEach
    void setUp() {
        c1 = new Card(Value.EIGHT);
        c2 = new Card(Value.KING);
        c3 = new Card(Value.EIGHT);
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
        assertEquals(c1, c3);
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
        assertEquals(new Card(Value.THREE), Card.tryParse("3").orElseThrow());
        assertEquals(new Card(Value.TEN), Card.tryParse("10").orElseThrow());
        assertEquals(new Card(Value.KING), Card.tryParse("K").orElseThrow());
    }
}