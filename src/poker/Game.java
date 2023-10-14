package poker;

/**
 * A game
 *
 * @author Team B
 */
public class Game {
    private Hand hand1;
    private Hand hand2;
    public final int handSize;

    public Game(int handSize) {
        this.handSize = handSize;
    }


    public void results(){
        System.out.println();
        System.out.println();
    }
}
