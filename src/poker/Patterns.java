package poker;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Card patterns
 *
 * @author Team B
 */
public enum Patterns {
    /**
     * Five cards in a row with the same color
     */
    STRAIGHT_FLUSH(5) {
        @Override
        public List<Card> reduceToMinAmountOfCards(List<Card> cards) {
            return cards.stream().limit(5).toList();
        }
    },

    /**
     * Four cards of the same value
     */
    FOUR_OF_A_KIND(4),

    /**
     * Three card of the same value and two cards of the same value
     */
    FULL(5) {
        @Override
        public List<Card> reduceToMinAmountOfCards(List<Card> cards) {
            return cards.stream().limit(5).toList();
        }
    },

    /**
     * Five cards have the same color
     */
    FLUSH(5) {
        @Override
        public List<Card> reduceToMinAmountOfCards(List<Card> cards) {
            return cards.stream().limit(5).toList();
        }
    },

    /**
     * Five cards in a row
     */
    STRAIGHT(5) {
        @Override
        public List<Card> reduceToMinAmountOfCards(List<Card> cards) {
            return cards.stream().collect(Collectors.toMap(Card::value, p -> p, (p, q) -> p)).values() // Only one card per value
                    .stream().sorted(Comparator.reverseOrder()).limit(5).toList(); // Only first 5 cards in reverse order
        }
    },

    /**
     * Three cards of the same value
     */
    THREE_OF_A_KIND(3),

    /**
     * Two sets of two cards of the same value
     */
    DOUBLE_PAIR(4),

    /**
     * Two cards of the same value
     */
    PAIR(2),

    /**
     * The highest card
     */
    HIGHER(1),

    /**
     * Complete equality
     */
    EQUALITY(0);


    private final int minCardNumber;

    Patterns(int minCardNumber) {
        this.minCardNumber = minCardNumber;
    }

    public int getMinCardNumber() {
        return minCardNumber;
    }

    public List<Card> reduceToMinAmountOfCards(List<Card> cards) {
        return cards;
    }
}
