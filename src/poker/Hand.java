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

    /**
     * Hand constructor
     *
     * @param hand a tab of cards
     */
    public Hand(Card[] hand) {
        this.cards = hand;
        isSorted = false;
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
                case 2 -> Patterns.PAIR;
                case 3 -> Patterns.THREE_OF_A_KIND;
                default -> null;
            };
            if (p != null) {
                if (result.containsKey(p)) result.get(p).add(0, entry.getKey());
                else result.put(p, new ArrayList<>(List.of(entry.getKey())));
            }
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
        var testPatterns = comparePatterns(otherHand);
        if (testPatterns.compareResult() != 0) return testPatterns;
        return compareCards(otherHand);
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare the values of cards in hand with cards of otherHand (without cards in Patterns)
     */
    public HandComparison compareCards(Hand otherHand) {
        int cardIndex = 0;
        int cardIndex2 = 0;
        while (cardIndex < cards.length) {

            if (cards[cardIndex] != null) {
                while (cardIndex2 < otherHand.getCards().length && otherHand.getCards()[cardIndex2] == null) {
                    cardIndex2++;
                }
                int compare = cards[cardIndex].compareTo(otherHand.getCards()[cardIndex2]);
                if (compare != 0)
                    return new HandComparison(compare, Patterns.HIGHER, (compare > 0 ? cards : otherHand.getCards())[cardIndex].getValue());
                cardIndex2++;
            }
            cardIndex++;
        }
        return new HandComparison(0, Patterns.EQUALITY, null);
    }

    /**
     * @param patterns the HashMap obtained by the getPatterns() method
     *                 Delete all the cards in hand when also located in Pattern
     **/
    public void deleteCardInPattern(Map<Patterns, ArrayList<Value>> patterns) {
        for (int i = 0; i < cards.length; i++) {
            for (Patterns p : patterns.keySet()) {
                if (cards[i] != null && patterns.get(p).contains(cards[i].getValue())) {
                    cards[i] = null;
                }
            }
        }
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare the current hand with otherHand with the patterns
     **/
    public HandComparison comparePatterns(Hand otherHand) {
        var handOnePatterns = getPatterns();
        var handTwoPatterns = otherHand.getPatterns();

        for (Patterns p : Patterns.values()) {
            if (handOnePatterns.containsKey(p) && !handTwoPatterns.containsKey(p)) {
                return new HandComparison(1, p, handOnePatterns.get(p).get(0));
            } else if (!handOnePatterns.containsKey(p) && handTwoPatterns.containsKey(p)) {
                return new HandComparison(-1, p, handTwoPatterns.get(p).get(0));
            } else if (handOnePatterns.containsKey(p) && handTwoPatterns.containsKey(p)) {
                int res = Math.max(Math.min(handOnePatterns.get(p).get(0).compareTo(handTwoPatterns.get(p).get(0)), 1), -1);
                if (res != 0)
                    return new HandComparison(res, p, (res > 0 ? handOnePatterns : handTwoPatterns).get(p).get(0));
            }
        }
        deleteCardInPattern(handOnePatterns);
        otherHand.deleteCardInPattern(handTwoPatterns);
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
