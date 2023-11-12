package poker;

import java.io.PrintStream;
import java.text.ParseException;
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


    private Card getRandomCard() {
        Card randomCardOne = deck.get(random.nextInt(deck.size()));
        deck.remove(randomCardOne);
        return randomCardOne;
    }

    private void waiting(Scanner scanner) {
        outputStream.print("Pressez une touche pour continuer");
        scanner.nextLine();
        outputStream.println();
    }

    private ArrayList<Card> extraction(int numberOfCards) {
        ArrayList<Card> cardsArray = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) cardsArray.add(getRandomCard());
        for (Hand hand : hands) {
            hand.addCards(cardsArray);
        }
        return cardsArray;
    }

    private void printArray(ArrayList<Card> tab) {
        for (Card c : tab) {
            outputStream.print(c + " ");
        }
        outputStream.println();
        for (Hand hand : hands) {
            outputStream.println(hand);
        }
    }

    public void tournamentResult(Map<Hand, ArrayList<Winner>> result, List<Hand> tournament) {
        Winner mostImportantResult = result.get(tournament.get(0)).get(0);
        if (mostImportantResult.pattern().equals(Patterns.EQUALITY)) outputStream.println(mostImportantResult);
        else {
            result.get(tournament.get(0)).remove(mostImportantResult);
            for (Winner w : result.get(tournament.get(0))) {
                if (w.pattern().compareTo(mostImportantResult.pattern()) < 0) {
                    mostImportantResult = w;
                } else if (w.pattern().compareTo(mostImportantResult.pattern()) == 0 && w.decisiveCard().compareTo(mostImportantResult.decisiveCard()) < 0)
                    mostImportantResult = w;
            }
            outputStream.println(mostImportantResult);
        }
    }

    private void tournament() {
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
            }
        }
        tournamentResult(result, tournament);
    }

    public void texasHoldem() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < hands.length; i++) {
            hands[i] = new Hand("Main " + (i + 1), new ArrayList<>(List.of(getRandomCard(), getRandomCard())));
            outputStream.println(hands[i]);
        }
        waiting(scanner);
        ArrayList<Card> flop = extraction(3);
        outputStream.print("Flop : ");
        printArray(flop);
        waiting(scanner);
        ArrayList<Card> turn = extraction(1);
        outputStream.print("Turn : ");
        printArray(turn);
        waiting(scanner);
        ArrayList<Card> river = extraction(1);
        outputStream.print("River : ");
        printArray(river);

        tournament();
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
                else if (typeGame.equals("2")) new Game(5, 4).texasHoldem();
                else throw new IllegalArgumentException("The entry is not valid");
                break;
            } catch (IllegalArgumentException | ParseException e) {
                outputStream.println("ERROR: " + e.getMessage() + "\n");
            }
        }
    }
}
