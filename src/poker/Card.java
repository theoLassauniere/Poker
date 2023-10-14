package poker;

import java.util.Objects;

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

    public Value getValue() {
        return value;
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

    /**
     * Try parsing a card
     *
     * @param text Text to be parsed
     * @return The parsed card or null if the parsing failed
     */
    public static Card tryParse(String text) {
        Value cardValue = null;
        for (var value : Value.values()) {
            if (Objects.equals(text, value.getSymbol())) {
                cardValue = value;
                break;
            }
        }
        if (cardValue == null) return null;
        return new Card(cardValue);
    }
}
