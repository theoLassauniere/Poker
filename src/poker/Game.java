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

    public void printResult(int[] result){
        int valueWinning = result[2];
        switch (result[1]){
            case -2:// equality case
                System.out.println("Egalité");
                break;
            case -1: // without patterns case
                if (result[0] == 1) {
                    System.out.println("La main " + 1 + " gagne avec la carte la plus haute : " + valueWinning); //It is possible to take name of enum thanks to index to print the good value for exemple if we have an ACE for the moment print 14 not ACE, so if it is possible better than do if or switch
                }
                else{
                    System.out.println("La main " + 2 + " gagne avec la carte la plus haute : " + valueWinning);
                }
                break;
            case 2: //put the index of the Pair in enum
                if (result[0] == 1) {
                    System.out.println("La main " + 1 + " gagne avec une paire de : " + valueWinning);
                }
                else {
                    System.out.println("La main " + 2 + " gagne avec une paire de : " + valueWinning);
                }
                break;
            case 1://put the index of the Brelan in enum
                if (result[0] == 1) {
                    System.out.println("La main " + 1 + " gagne avec un brelan de : " + valueWinning);
                }
                else {
                    System.out.println("La main " + 2 + " gagne avec un brelan de : " + valueWinning);
                }
                break;
        }
    }
}