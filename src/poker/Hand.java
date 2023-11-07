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
    private final Map<Patterns, ArrayList<Value>> patterns;

    /**
     * Hand constructor
     *
     * @param hand a tab of cards
     */
    public Hand(Card[] hand) {
        this.cards = hand;
        sortHand();
        patterns = getPatterns();
    }

    /**
     * Gets cards
     */
    protected Card[] getCards() {
        return cards;
    }

    /**
     * Gets a specific card
     *
     * @param index Index of the card in the hand
     */
    public Optional<Card> getCard(int index) {
        if (index < 0 || index >= cards.length) return Optional.empty();
        return Optional.of(cards[index]);
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
            var parsedCard = Card.tryParse(cardsUnparsed[i]);
            if (parsedCard.isEmpty())
                throw new ParseException("Couldn't parse card (" + cardsUnparsed[i] + ")", i);
            cards[i] = parsedCard.get();
        }
        return new Hand(cards);
    }


    /**
     * Swap method used in the sort method ; swap two cards based on their indexes
     */
    private void swap(int indexCard1, int indexCard2) {
        if (indexCard1 == indexCard2) return;
        Card carteTest = this.cards[indexCard1];
        this.cards[indexCard1] = this.cards[indexCard2];
        this.cards[indexCard2] = carteTest;
    }

    /**
     * Sort the hand in descending order
     */
    private void sortHand() {
        for (int i = 0; i < cards.length; i++) {
            int swapIndex = i;
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[swapIndex].compareTo(cards[j]) < 0) {
                    swapIndex = j;
                } else if (cards[swapIndex].equals(cards[j])) {
                    throw new IllegalArgumentException("Duplicated card in hand");
                }
            }
            swap(i, swapIndex);
        }
    }

    /**
     * Gets number of occurrences of each Value
     */
    public Map<Value, Integer> occurrences() {
        var values = new EnumMap<Value, Integer>(Value.class);
        for (Card card : cards) values.merge(card.value(), 1, Integer::sum);
        return values;
    }

    /**
     * Is there a straight in the hand?
     */
    public boolean isStraight() {
        if (cards.length < 5) return false;
        if (cards[0].value().ordinal() < 4)
            return false; // if the highest card of the hand is less than a six, there cannot be a straight
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].value().ordinal() - cards[i + 1].value().ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Are all the cards the same color?
     */
    public boolean isSameColor() {
        if (cards.length < 5) return false;
        var color = cards[0].color();
        var i = 1;
        while (i < 5 && cards[i].color() == color) {
            i++;
        }
        return cards.length == i;
    }

    /**
     * Add the Color pattern if it exists
     */
    public void colorPatternDetection(Map<Patterns, ArrayList<Value>> result) {
        if (isSameColor()) result.put(Patterns.COLOR, new ArrayList<>(List.of(getCards()[0].value())));
    }

    /**
     * Detect the Straight pattern
     */
    public boolean straightPatternDetection(Map<Patterns, ArrayList<Value>> result) {
        if (isStraight()) {
            if (result.containsKey(Patterns.COLOR)) {
                result.remove(Patterns.COLOR);
                result.put(Patterns.STRAIGHTFLUSH, new ArrayList<>(List.of(getCards()[0].value())));
            } else {
                result.put(Patterns.STRAIGHT, new ArrayList<>(List.of(getCards()[0].value())));
            }
            return true;
        }
        return false;
    }

    /**
     * Detect Full pattern
     */
    public void fullPatternDetection(Map<Patterns, ArrayList<Value>> result) {
        if (result.containsKey(Patterns.PAIR) && result.containsKey(Patterns.THREE_OF_A_KIND)) {
            result.put(Patterns.FULL, result.get(Patterns.THREE_OF_A_KIND));
            result.remove(Patterns.PAIR);
            result.remove(Patterns.THREE_OF_A_KIND);
        }
    }

    /**
     * Gets the patterns and card values that realize them
     **/
    public Map<Patterns, ArrayList<Value>> getPatterns() {
        if (patterns != null) return patterns;
        EnumMap<Patterns, ArrayList<Value>> result = new EnumMap<>(Patterns.class);

        colorPatternDetection(result);
        if (straightPatternDetection(result)) return result;

        for (var entry : occurrences().entrySet()) {
            Patterns p = switch (entry.getValue()) {
                case 1 -> Patterns.HIGHER;
                case 2 -> Patterns.PAIR;
                case 3 -> Patterns.THREE_OF_A_KIND;
                case 4 -> Patterns.FOUR_OF_A_KIND;
                default -> null;
            };
            if (result.containsKey(p)) {
                if (p == Patterns.PAIR) {
                    result.put(Patterns.DOUBLE_PAIR, new ArrayList<>(List.of(entry.getKey(), result.get(p).get(0))));
                    result.remove(p);
                } else result.get(p).add(0, entry.getKey());
            } else result.put(p, new ArrayList<>(List.of(entry.getKey())));
        }

        fullPatternDetection(result);

        return result;
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare patterns and values of cards between the two hands
     */
    public int compareTo(Hand otherHand) {
        return comparePatterns(otherHand).compareResult();
    }

    /**
     * @param otherHand the other hand to be compared.
     * @param pattern   the pattern for which we will inspect the values
     * @return Compare the two arrays of Values given by pattern parameter from both hands
     **/
    public HandComparison comparePatternValues(Patterns pattern, Hand otherHand) {
        var handOneList = patterns.get(pattern);
        var handTwoList = otherHand.patterns.get(pattern);
        for (int i = 0; (i < handOneList.size() && (i < handTwoList.size())); i++) {
            int res = Math.max(Math.min(handOneList.get(i).compareTo(handTwoList.get(i)), 1), -1);
            if (res != 0)
                return new HandComparison(res, pattern, (res > 0 ? patterns : otherHand.patterns).get(pattern).get(i));
        }
        return null;
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare the current hand with otherHand with the patterns
     **/
    public HandComparison comparePatterns(Hand otherHand) {
        for (Patterns p : Patterns.values()) {
            if (patterns.containsKey(p) && !otherHand.patterns.containsKey(p)) // Only this hand has the pattern
                return new HandComparison(1, p, patterns.get(p).get(0));
            else if (!patterns.containsKey(p) && otherHand.patterns.containsKey(p)) // Only the other hand has the pattern
                return new HandComparison(-1, p, otherHand.patterns.get(p).get(0));
            else if (patterns.containsKey(p) && otherHand.patterns.containsKey(p)) { // Both hands have the pattern
                HandComparison comparisonResult = comparePatternValues(p, otherHand);
                if (comparisonResult != null) return comparisonResult;
            }
        }
        return new HandComparison(0, Patterns.EQUALITY, null);
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Hand hand && Arrays.equals(getCards(), hand.getCards());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cards);
    }
}
