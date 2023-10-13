package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Team B
 */
public class HandTest {
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

}
