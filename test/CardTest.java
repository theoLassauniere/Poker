import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card c1;
    Card c2;
    Card c3;

    @BeforeEach
    void setUp() {
        c1 = new Card(Value.EIGHT);
        c2 = new Card(Value.KING);
        c3 = new Card(Value.EIGHT);
    }

    @Test
    void testCompareTo() {
        assertEquals(0,c1.compareTo(c3));
        assertEquals(1,c2.compareTo(c1));
        assertEquals(-1,c1.compareTo(c2));
    }

    @Test
    void testEquals() {
        assertFalse(c1.equals(c2));
        assertTrue(c1.equals(c3));
    }
}