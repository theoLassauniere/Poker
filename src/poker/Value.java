package poker;

/**
 * Card value
 *
 * @author Team B
 */
public enum Value {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private final String valueSymbol;

    Value(String valueSymbol) {
        this.valueSymbol = valueSymbol;
    }

    /**
     * Get the representation symbol
     */
    public String getValueSymbol() {
        return this.valueSymbol;
    }

    @Override
    public String toString() {
        return getValueSymbol();
    }
}