package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class HandTest {
    Hand hand1;

    @BeforeEach
    void setUp() {
        Card[] main1 = new Card[]{
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
        System.out.println(hand1);
        hand1.sortHand();
        System.out.println(hand1);
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
        assertArrayEquals(new Card[]{new Card(Value.THREE)}, Hand.parse("3", 1));
        assertArrayEquals(new Card[]{new Card(Value.TEN)}, Hand.parse("10", 1));
        assertArrayEquals(new Card[]{new Card(Value.KING)}, Hand.parse("K", 1));
    }

}
