package poker;

import java.text.ParseException;
import java.util.*;

/**
 * Hand
 *
 * @author Team B
 */

public class Hand implements Comparable<Hand> {
    private final Card[] cards;
    private boolean isSorted;
    private Map<Patterns, ArrayList<Value>> patterns;

    /**
     * Hand constructor
     *
     * @param hand a tab of cards
     */
    public Hand(Card[] hand) {
        this.cards = hand;
        isSorted = false;
        sortHand();
        this.patterns = getPatterns();
    }

    /**
     * Gets cards
     */
    public Card[] getCards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Card card : cards) {
            string.append(card.toString()).append(" ");
        }
        string.setLength(string.length() - 1);
        return string.toString();
    }

    /**
     * Parses hand
     *
     * @param text Hand input
     * @return The parsed hand
     * @throws IllegalArgumentException The input doesn't contain the exacts number of card that is expected for that game
     * @throws ParseException           The input contains an invalid card
     */
    public static Hand parse(String text, int handSize) throws IllegalArgumentException, ParseException {
        var cards = new Card[handSize];
        var cardsUnparsed = text.split(" ");

        if (cardsUnparsed.length != handSize)
            throw new IllegalArgumentException("Hand must contain exactly " + handSize + " cards");

        for (int i = 0; i < handSize; i++) {
            cards[i] = Card.tryParse(cardsUnparsed[i]);
            if (cards[i] == null)
                throw new ParseException("Couldn't parse card (" + cardsUnparsed[i] + ")", i);
        }
        return new Hand(cards);
    }


    /**
     * Swap method used in the sort method ; swap two cards based on their indexes
     */
    private void swap(int indexCard1, int indexCard2) {
        Card carteTest = this.cards[indexCard1];
        this.cards[indexCard1] = this.cards[indexCard2];
        this.cards[indexCard2] = carteTest;
    }

    /**
     * Sort the hand in descending order
     */
    public void sortHand() {
        if (isSorted()) return;
        for (int i = 0; i < cards.length; i++) {
            int swapIndex = i;
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[swapIndex].compareTo(cards[j]) < 0) {
                    swapIndex = j;
                }
            }
            swap(i, swapIndex);
        }
        isSorted = true;
    }

    /**
     * Are the cards sorted?
     */
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Gets number of occurrences of each Value
     */
    public Map<Value, Integer> occurrences() {

        var values = new EnumMap<Value, Integer>(Value.class);
        for (Card card : cards) values.merge(card.getValue(), 1, Integer::sum);
        return values;
    }

    /**
     * Gets the patterns and card values that realize them
     **/
    public Map<Patterns, ArrayList<Value>> getPatterns() {
        EnumMap<Patterns, ArrayList<Value>> result = new EnumMap<>(Patterns.class);
        for (var entry : occurrences().entrySet()) {
            Patterns p = switch (entry.getValue()) {
                case 1 -> Patterns.HIGHER;
                case 2 -> Patterns.PAIR;
                case 3 -> Patterns.THREE_OF_A_KIND;
                case 4 -> Patterns.FOUR_OF_A_KIND;
                default -> null;
            };
            if (p == null) continue;
            if (result.containsKey(p)) {
                if (p == Patterns.PAIR) {
                    result.put(Patterns.DOUBLE_PAIR, new ArrayList<>(List.of(entry.getKey(), result.get(p).get(0))));
                    result.remove(p);
                } else result.get(p).add(0, entry.getKey());
            } else result.put(p, new ArrayList<>(List.of(entry.getKey())));
        }
        return result;
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare patterns and values of cards between the two hands
     */
    public int compareTo(Hand otherHand) {
        return getResult(otherHand).compareResult();
    }

    public HandComparison getResult(Hand otherHand) {
        return comparePatterns(otherHand);
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare the current hand with otherHand with the patterns
     **/
    public HandComparison comparePatterns(Hand otherHand) {
        for (Patterns p : Patterns.values()) {
            if (patterns.containsKey(p) && !otherHand.patterns.containsKey(p)) {
                return new HandComparison(1, p, patterns.get(p));
            } else if (!patterns.containsKey(p) && otherHand.patterns.containsKey(p)) {
                return new HandComparison(-1, p, otherHand.patterns.get(p));
            } else if (patterns.containsKey(p) && otherHand.patterns.containsKey(p)) {
                var handOneList = patterns.get(p);
                var handTwoList = otherHand.patterns.get(p);
                for (int i = 0; (i < handOneList.size() && (i < handTwoList.size())); i++) {
                    int res = Math.max(Math.min(handOneList.get(i).compareTo(handTwoList.get(i)), 1), -1);
                    if (res != 0)
                        return new HandComparison(res, p, List.of((res > 0 ? patterns : otherHand.patterns).get(p).get(i)));
                }
            }
        }
        return new HandComparison(0, Patterns.EQUALITY, null);
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Hand hand && compareTo(hand) == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cards);
    }
}
