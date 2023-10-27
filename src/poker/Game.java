package poker;

import java.text.ParseException;
import java.util.Scanner;

/**
 * A game
 *
 * @author Team B
 */
public class Game {
    public static final int DEFAULT_HAND_SIZE = 5;

    public final int handSize;

    public Game(int handSize) {
        this.handSize = handSize;
    }

    public static void main(String[] args) {
        try {
            new Game(DEFAULT_HAND_SIZE).start();
        } catch (Exception e) {
            System.err.println("ERROR: " + e); // TODO
        }
    }

    /**
     * Start the game, create the hands with the entries
     */
    public void start() throws IllegalArgumentException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Main 1: ");
        Hand hand1 = Hand.parse(scanner.nextLine(), handSize);
        System.out.print("Main 2: ");
        Hand hand2 = Hand.parse(scanner.nextLine(), handSize);

        hand1.sortHand();
        hand2.sortHand();

        printResult(hand1.getResult(hand2));
    }

    public void printResult(HandComparison result) {
        switch (result.pattern()) {
            case EQUALITY -> System.out.println("Egalité");
            case HIGHER ->
                    System.out.println("La main " + (result.compareResult() > 0 ? 1 : 2) + " gagne avec la carte la plus haute : " + result.values().get(0));
            case PAIR ->
                    System.out.println("La main " + (result.compareResult() > 0 ? 1 : 2) + " gagne avec une paire de : " + result.values().get(0));
            case DOUBLE_PAIR ->
                    System.out.println("La main " + (result.compareResult() > 0 ? 1 : 2) + " gagne avec une double paire de : " + result.values().get(0) + " et de : " + result.values().get(1));
            case THREE_OF_A_KIND ->
                    System.out.println("La main " + (result.compareResult() > 0 ? 1 : 2) + " gagne avec un brelan de : " + result.values().get(0));

        }
    }
}