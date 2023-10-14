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
     *
     * @throws IllegalArgumentException
     * @throws ParseException
     */
    public void start() throws IllegalArgumentException, ParseException {
        System.out.print("Main 1:");
        Scanner hand1 = new Scanner(System.in);
        System.out.print("Main 2:");
        Scanner hand2 = new Scanner(System.in);
        Hand handOne = Hand.parse(hand1.nextLine(), handSize);
        Hand handTwo = Hand.parse(hand1.nextLine(), handSize);
    }
}
