/**
 * Card
 *
 * @author Team B
 */
public class Card implements Comparable<Card> {
    private final Value value;

    /**
     * Card constructor
     *
     * @param value The value of the card
     */
    public Card(Value value) {
        this.value = value;
    }

    /**
     * Compares the value of the current card with another card
     */
    @Override
    public int compareTo(Card other) {
        return Math.max(Math.min(value.ordinal() - other.value.ordinal(), 1), -1);
    }

    /**
     * Check equality
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Card)) return false;
        return compareTo((Card) other) == 0;
    }

    /**
     * Card hash code
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.getSymbol();
    }
}
