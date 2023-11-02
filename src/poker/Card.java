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
