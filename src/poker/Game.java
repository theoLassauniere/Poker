package poker;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Set;

/**
 * A game
 *
 * @author Team B
 */
public class Game {
    public static final int DEFAULT_HAND_SIZE = 5;
    public static final int DEFAULT_NUMBER_OF_PLAYERS = 2;

    @java.lang.SuppressWarnings("java:S106")
    protected static PrintStream outputStream = System.out;
    private final Set<Card> deck;
    private final Hand[] hands;
    private final int handSize;

    public Game() {
        this(DEFAULT_HAND_SIZE);
    }

    public Game(int handSize) {
        this(handSize, DEFAULT_NUMBER_OF_PLAYERS);
    }

    public Game(int handSize, int numberOfPlayers) {
        if (numberOfPlayers < 2)
            throw new IllegalArgumentException("There must be at least two players");
        if (numberOfPlayers != 2) throw new UnsupportedOperationException("Only two players are supported");
        this.handSize = handSize;
        deck = Card.getDeck();
        hands = new Hand[numberOfPlayers];
    }

    /**
     * Start the game, create the hands with the entries
     */
    public void start() throws IllegalArgumentException, ParseException {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < hands.length; i++) {
            outputStream.print("Main " + (i + 1) + ": ");
            hands[i] = Hand.parse(scanner.nextLine(), handSize);

            for (int j = 0; j < handSize; j++) {
                var card = hands[i].getCard(j);
                if (card.isEmpty()) continue;
                if (deck.contains(card.get())) deck.remove(card.get());
                else throw new IllegalArgumentException("The same card can't be in two different hands");
            }
        }
        outputStream.println(hands[0].comparePatterns(hands[1]));
    }

    public static void main(String[] args) {
        while (true) {
            try {
                new Game().start();
                break;
            } catch (IllegalArgumentException | ParseException e) {
                outputStream.println("ERROR: " + e.getMessage() + "\n");
            }
        }
    }
}