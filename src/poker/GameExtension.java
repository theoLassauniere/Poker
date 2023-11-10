package poker;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A game
 *
 * @author Team B
 */
public class GameExtension {
    public static final int DEFAULT_HAND_SIZE = 5;
    public static final int DEFAULT_NUMBER_OF_PLAYERS = 2;

    @java.lang.SuppressWarnings("java:S106")
    protected static PrintStream outputStream = System.out;
    private final List<Card> deck;
    private final Hand[] hands;
    private final int handSize;
    private final Random random;

    public GameExtension() {
        this(DEFAULT_HAND_SIZE);
    }

    public GameExtension(int handSize) {
        this(handSize, DEFAULT_NUMBER_OF_PLAYERS);
    }

    public GameExtension(int handSize, int numberOfPlayers) {
        if (numberOfPlayers < 2)
            throw new IllegalArgumentException("There must be at least two players");
        if (numberOfPlayers != 2) throw new UnsupportedOperationException("Only two players are supported");
        this.handSize = handSize;
        deck = Card.getDeck();
        random = new Random();
        hands = new Hand[numberOfPlayers];
    }

    /**
     * Start the game, create the hands with the entries
     */
    public void start() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        String nextStep = "Passer à l'étape suivante (o/n) ? ";
        for (int i = 0; i < hands.length; i++) {
            hands[i] = new Hand("Main " + (i + 1), new ArrayList<>());
            Card randomCardOne = deck.get(random.nextInt(deck.size()));
            Card randomCardTwo = deck.get(random.nextInt(deck.size()));
            hands[i].getCards().add(randomCardOne);
            hands[i].getCards().add(randomCardTwo);
            deck.remove(randomCardOne);
            deck.remove(randomCardTwo);
            outputStream.println(hands[i]);
        }
        do {
            outputStream.print(nextStep);
        } while (scanner.nextLine().equals("n"));
        ArrayList<Card> flop = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Card randomCard = deck.get(random.nextInt(deck.size()));
            for (Hand hand : hands) {
                hand.getCards().add(randomCard);
            }
            flop.add(randomCard);
            deck.remove(randomCard);
        }
        outputStream.print("Flop : ");
        for (Card card : flop) {
            outputStream.print(card + " ");
        }
        outputStream.print("\n");
        for (Hand value : hands) {
            outputStream.println(value);
        }

        do {
            outputStream.print(nextStep);
        } while (scanner.nextLine().equals("n"));
        Card turn = deck.get(random.nextInt(deck.size()));
        for (Hand hand : hands) {
            hand.getCards().add(turn);
        }
        deck.remove(turn);

        outputStream.println("Turn : " + turn);
        for (Hand value : hands) {
            outputStream.println(value);
        }

        Card river = deck.get(random.nextInt(deck.size()));
        for (Hand hand : hands) {
            hand.getCards().add(river);
        }
        deck.remove(river);

        outputStream.println("Turn : " + river);

    }

    public static void main(String[] args) {
        while (true) {
            try {
                new GameExtension().start();
                break;
            } catch (IllegalArgumentException e) {
                outputStream.println("ERROR: " + e.getMessage() + "\n");
            }
        }
    }
}

