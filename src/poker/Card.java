package poker;

import java.util.Objects;

/**
 * Card
 *
 * @param value The value of the card
 * @author Team B
 */
public record Card(Value value) implements Comparable<Card> {
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

    @Override
    public String toString() {
        return value.getValueSymbol();
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
            if (Objects.equals(text, value.getValueSymbol())) {
                cardValue = value;
                break;
            }
        }
        if (cardValue == null) return null;
        return new Card(cardValue);
    }
}
