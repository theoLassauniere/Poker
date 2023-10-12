/**
 * Card value
 *
 * @author Team B
 */
public enum Value {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private final String symbol;

    Value(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Get the representation symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}