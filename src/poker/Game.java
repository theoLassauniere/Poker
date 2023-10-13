package poker;

import java.text.ParseException;

/**
 * A game
 *
 * @author Team B
 */
public class Game {
    public final int handSize;

    public Game(int handSize) {
        this.handSize = handSize;
    }

    /**
     * Parses hand
     * @param text Hand input
     * @return The cards of the hand
     * @throws IllegalArgumentException The input doesn't contain the exacts number of card that is expected for that game
     * @throws ParseException The input contains an invalid card
     */
    public Card[] parse(String text) throws IllegalArgumentException, ParseException {
        var cards = new Card[handSize];
        var cardsUnparsed = text.split(" ");

        if (cardsUnparsed.length != handSize)
            throw new IllegalArgumentException("Hand must contain exactly " + handSize + " cards");

        for (int i = 0; i < handSize; i++) {
            cards[i] = Card.tryParse(cardsUnparsed[i]);
            if (cards[i] == null)
                throw new ParseException("Couldn't parse card (" + cardsUnparsed[i] + ")", i);
        }
        return cards;
    }
}
