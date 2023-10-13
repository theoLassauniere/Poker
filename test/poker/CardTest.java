import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poker.*;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card c1;
    Card c2;
    Card c3;

    /**
     * Initialize Cards for testing
     * **/
    @BeforeEach
    void setUp() {
        c1 = new Card(Value.EIGHT);
        c2 = new Card(Value.KING);
        c3 = new Card(Value.EIGHT);
    }

    /**
     * Test the compareTo method with all the possibilities (equality,superiority,inferiority)
     * **/
    @Test
    void testCompareTo() {
        assertEquals(0,c1.compareTo(c3));
        assertEquals(1,c2.compareTo(c1));
        assertEquals(-1,c1.compareTo(c2));
    }

    /**
     * Test the equals method with an equality and an inequality
     * **/
    @Test
    void testEquals() {
        assertFalse(c1.equals(c2));
        assertTrue(c1.equals(c3));
    }

    /**
     * Test card parsing
     * **/
    @Test
    void testParsing() {
        assertNull(Card.tryParse(""));
        assertNull(Card.tryParse("1"));
        assertNull(Card.tryParse("26"));
        assertNull(Card.tryParse("W"));
        assertEquals(new Card(Value.THREE), Card.tryParse("3"));
        assertEquals(new Card(Value.TEN), Card.tryParse("10"));
        assertEquals(new Card(Value.KING), Card.tryParse("K"));
    }
}