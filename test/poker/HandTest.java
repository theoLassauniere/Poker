package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * @author Team B
 */
class HandTest {
    Hand hand1;

    @BeforeEach
    void setUp() {
        Card[] main1 = new Card[] {
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        };
        hand1 = new Hand(main1);
    }

    @Test
    void sortTest() {
        assertArrayEquals(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        },hand1.getCards());
        hand1.sortHand();
        assertArrayEquals(new Card[]{
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.EIGHT),
                new Card(Value.FIVE),
                new Card(Value.TWO)

        },hand1.getCards());
    }

    @Test
    void occurrencesTest() {
        assertArrayEquals(new Card[]{
                new Card(Value.EIGHT),
                new Card(Value.KING),
                new Card(Value.EIGHT),
                new Card(Value.TWO),
                new Card(Value.FIVE)
        },hand1.getCards());
        assertArrayEquals(new int[]{1,0,0,1,0,0,2,0,0,0,0,1,0},hand1.occurrences());
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
