package poker;

/**
 * Card Color
 *
 * @author Team B
 */
public enum Color {
    HEARTS("Co"), DIAMONDS("Ca"), SPADES("Pi"), CLUBS("Tr");

    private final String colorSymbol;

    Color(String colorSymbol) {
        this.colorSymbol = colorSymbol;
    }

    public String getColorSymbol() {
        return this.colorSymbol;
    }

    @Override
    public String toString() {
        return getColorSymbol();
    }

}
