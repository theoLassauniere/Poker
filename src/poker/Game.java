package poker;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
    private final List<Card> deck;
    private final Hand[] hands;
    private final int handSize;

    private final String type;
    private final Random random;


    public Game(String type) throws IllegalArgumentException {
        this(DEFAULT_HAND_SIZE, type);
    }

    public Game(int handSize, String type) throws IllegalArgumentException {
        this(handSize, DEFAULT_NUMBER_OF_PLAYERS, type);
    }

    public Game(int handSize, int numberOfPlayers, String type) throws IllegalArgumentException {
        if (numberOfPlayers < 2)
            throw new IllegalArgumentException("There must be at least two players");
        if (numberOfPlayers != 2) throw new UnsupportedOperationException("Only two players are supported");
        this.handSize = handSize;

        if (type.equals("Poker") || type.equals("Texas Hold'em")) this.type = type;
        else throw new IllegalArgumentException("The type of game is not valid (Poker or Texas Hold'em)");

        deck = Card.getDeck();
        random = new Random();
        hands = new Hand[numberOfPlayers];
    }

    public void texasHoldem() throws IllegalArgumentException {
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

        outputStream.println("River : " + river);

    }

    public void poker() throws IllegalArgumentException, ParseException {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < hands.length; i++) {
            var name = "Main " + (i + 1);
            outputStream.print(name + ": ");
            hands[i] = Hand.parse(scanner.nextLine(), handSize);
            hands[i].setName(name);

            for (int j = 0; j < handSize; j++) {
                var card = hands[i].getCard(j);
                if (card.isEmpty()) continue;
                if (deck.contains(card.get())) deck.remove(card.get());
                else throw new IllegalArgumentException("The same card can't be in two different hands");
            }
        }
        outputStream.println(hands[0].comparePatterns(hands[1]));
    }

    /**
     * Start the game, create the hands with the entries
     */
    public void start() throws IllegalArgumentException, ParseException {
        if (type.equals("Poker")) poker();
        else texasHoldem();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            outputStream.print("A quel jeu voulez vous jouez, Poker ou Texas Hold'em ? ");
            String typeGame;
            typeGame = scanner.nextLine();
            try {
                new Game(typeGame).start();
                break;
            } catch (IllegalArgumentException | ParseException e) {
                outputStream.println("ERROR: " + e.getMessage() + "\n");
            }
        }
    }
}
