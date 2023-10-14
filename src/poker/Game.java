package poker;

import java.text.ParseException;
import java.util.Scanner;

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
     * Start the game, create the hands with the entries
     */
    public void start() throws IllegalArgumentException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Main 1: ");
        Hand hand1 = Hand.parse(scanner.nextLine(), handSize);
        System.out.print("Main 2: ");
        Hand hand2 = Hand.parse(scanner.nextLine(), handSize);

        System.out.println(hand1.compareTo(hand2)); // TODO
    }
}
