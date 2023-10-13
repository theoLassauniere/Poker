package poker;

public class Game {
    public static final int HAND_SIZE = 1;

    public Game() {
        // TODO
    }

    public static Card[] parse(String text) {
        var cards = new Card[HAND_SIZE];
        var cardsUnparsed = text.split(" ");

        if (cardsUnparsed.length != HAND_SIZE)
            throw new IllegalArgumentException("Hand must contain exactly " + HAND_SIZE + " cards");

        for (int i = 0; i < HAND_SIZE; i++) {
            cards[i] = Card.tryParse(cardsUnparsed[i]);
            if (cards[i] == null) throw new IllegalArgumentException("Couldn't parse card (" + cardsUnparsed[i] + ")");
        }
        return cards;
    }
}
