package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class GameTest {
    Game oneCardGame;

    /**
     * Initialize Game for testing
     **/
    @BeforeEach
    void setUp() {
        oneCardGame = new Game(1);
    }

    /**
     * Test hand parsing
     */
    @Test
    void testParsing() throws ParseException {
        assertThrowsExactly(ParseException.class, () -> oneCardGame.parse(""));
        assertThrowsExactly(ParseException.class, () -> oneCardGame.parse("1"));
        assertThrowsExactly(ParseException.class, () -> oneCardGame.parse("26"));
        assertThrowsExactly(ParseException.class, () -> oneCardGame.parse("W"));
        assertThrowsExactly(IllegalArgumentException.class, () -> oneCardGame.parse("3 5"));
        assertArrayEquals(new Card[]{new Card(Value.THREE)}, oneCardGame.parse("3"));
        assertArrayEquals(new Card[]{new Card(Value.TEN)}, oneCardGame.parse("10"));
        assertArrayEquals(new Card[]{new Card(Value.KING)}, oneCardGame.parse("K"));
    }
}