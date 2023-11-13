package poker;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;

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


    private final Random random;


    public Game() throws IllegalArgumentException {
        this(DEFAULT_HAND_SIZE);
    }

    public Game(int handSize) throws IllegalArgumentException {
        this(handSize, DEFAULT_NUMBER_OF_PLAYERS);
    }

    public Game(int handSize, int numberOfPlayers) throws IllegalArgumentException {
        if (numberOfPlayers < 2)
            throw new IllegalArgumentException("There must be at least two players");
        this.handSize = handSize;
        deck = Card.getDeck();
        random = new Random();
        hands = new Hand[numberOfPlayers];
    }

    public Game(int handSize, int numberOfPlayers, long seed) throws IllegalArgumentException {
        if (numberOfPlayers < 2)
            throw new IllegalArgumentException("There must be at least two players");
        this.handSize = handSize;
        deck = Card.getDeck();
        random = new Random(seed);
        hands = new Hand[numberOfPlayers];
    }

    public Hand[] getHand() {
        return hands;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public Card getRandomCard() {
        Card randomCardOne = deck.get(random.nextInt(deck.size()));
        deck.remove(randomCardOne);
        return randomCardOne;
    }


    public void displayAndWait(Scanner scanner, List<Card> tab) {
        for (Card c : tab) {
            outputStream.print(c + " ");
        }
        outputStream.println();
        for (Hand hand : hands) {
            outputStream.println(hand);
        }
        outputStream.print("Pressez une touche pour continuer");
        scanner.nextLine();
        outputStream.println();
    }


    public List<Card> extraction(int numberOfCards) {
        ArrayList<Card> cardsArray = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) cardsArray.add(getRandomCard());
        for (Hand hand : hands) hand.addCards(cardsArray);
        return cardsArray;
    }


    public Winner tournamentResult(SimpleEntry<ArrayList<Hand>, Map<Hand, ArrayList<Winner>>> entry) {
        Winner equality = new Winner(null, Patterns.EQUALITY, null);
        if (entry.getValue().get(entry.getKey().get(0)).contains(equality)) return equality;
        Winner mostImportantResult = entry.getValue().get(entry.getKey().get(0)).get(0);
        entry.getValue().get(entry.getKey().get(0)).remove(mostImportantResult);
        for (Winner w : entry.getValue().get(entry.getKey().get(0))) {
            if (w.pattern().compareTo(mostImportantResult.pattern()) > 0 || (w.pattern().compareTo(mostImportantResult.pattern()) == 0 && w.decisiveCard().compareTo(mostImportantResult.decisiveCard()) < 0))
                mostImportantResult = w;
        }
        return mostImportantResult;
    }

    public SimpleEntry<ArrayList<Hand>, Map<Hand, ArrayList<Winner>>> tournament() {
        Map<Hand, ArrayList<Winner>> result = new HashMap<>();
        ArrayList<Hand> tournament = new ArrayList<>(List.of(hands));
        while (tournament.size() > 1) {
            Hand hand1 = tournament.get(0);
            Hand hand2 = tournament.get(1);
            tournament.remove(hand1);
            tournament.remove(hand2);
            Winner compareResult = hand1.comparePatterns(hand2);
            if (compareResult.winningHand() != null) {
                tournament.add(0, compareResult.winningHand());
                if (result.containsKey(compareResult.winningHand()))
                    result.get(compareResult.winningHand()).add(compareResult);
                else result.put(compareResult.winningHand(), new ArrayList<>(List.of(compareResult)));
            } else {
                tournament.add(0, hand1);
                if (result.containsKey(hand1))
                    result.get(hand1).add(compareResult);
                else result.put(hand1, new ArrayList<>(List.of(compareResult)));
            }
        }
        return new SimpleEntry<>(tournament, result);
    }

    public void texasHoldem() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < hands.length; i++)
            hands[i] = new Hand("Main " + (i + 1), new ArrayList<>(List.of(getRandomCard(), getRandomCard())));
        displayAndWait(scanner, new ArrayList<>());
        for (var step : List.of(new SimpleEntry<>("Flop", 3), new SimpleEntry<>("Turn", 1), new SimpleEntry<>("River", 1))) {
            List<Card> extractionResult = extraction(step.getValue());
            outputStream.print(step.getKey() + " : ");
            displayAndWait(scanner, extractionResult);
        }
        outputStream.println(tournamentResult(tournament()));
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            outputStream.print("1.Poker\n2.Texas Hold'em\nEntrez le numéro souhaité : ");
            String typeGame = scanner.nextLine();
            try {
                if (typeGame.equals("1")) new Game().poker();
                else if (typeGame.equals("2")) new Game(5, 4, 89).texasHoldem();
                else throw new IllegalArgumentException("The entry is not valid");
                break;
            } catch (IllegalArgumentException | ParseException e) {
                outputStream.println("ERROR: " + e.getMessage() + "\n");
            }
        }
    }
}
