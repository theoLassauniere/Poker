package poker;

/**
 * Card Color
 *
 * @author Team B
 */
public enum Color {
    HEARTS("Co"), DIAMONDS("Ca"), SPADES("Pi"), CLUBS("Tr");

    private final String symbol;

    Color(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }

}
