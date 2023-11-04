package poker;

import java.util.Objects;
import java.util.Optional;

/**
 * Card
 *
 * @param value The value of the card
 * @author Team B
 */
public record Card(Value value, Color color) implements Comparable<Card> {
    /**
     * Compares the value of the current card with another card
     */
    @Override
    public int compareTo(Card other) {
        return Math.max(Math.min(value().ordinal() - other.value().ordinal(), 1), -1);
    }

    @Override
    public String toString() {
        return value().getSymbol() + color().getSymbol();
    }

    /**
     * Try parsing a card
     *
     * @param text Text to be parsed
     * @return The parsed card or empty if the parsing failed
     */
    public static Optional<Card> tryParse(String text) {
        Value cardValue = null;
        Color cardColor = null;
        for (var value : Value.values()) {
            for (var color : Color.values()) {
                if (Objects.equals(text, value.getSymbol() + color.getSymbol())) {
                    cardValue = value;
                    cardColor = color;
                    break;
                }
            }
        }
        if (cardValue == null) return Optional.empty();
        return Optional.of(new Card(cardValue, cardColor));
    }
}
