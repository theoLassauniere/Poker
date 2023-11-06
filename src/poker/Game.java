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
        while (true) {
            try {
                new Game(DEFAULT_HAND_SIZE).start();
                break;
            } catch (IllegalArgumentException | ParseException e) {
                System.out.println("ERROR: " + e.getMessage() + "\n");
            }
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
        try {
            hand1.cardDuplicationDetection(hand2);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage() + "\n");
            return;
        }
        System.out.println(hand1.comparePatterns(hand2));
    }
}