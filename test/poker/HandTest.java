package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        System.out.println(hand1);
        hand1.sortHand();
        System.out.println(hand1);
    }

}
