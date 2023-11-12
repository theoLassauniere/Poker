package poker;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Hand
 *
 * @author Team B
 */

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private final Map<Patterns, List<List<Card>>> patterns;
    private String name;

    /**
     * Hand constructor
     *
     * @param hand a tab of cards
     */
    public Hand(List<Card> hand) {
        this("Main", hand);
    }

    public Hand(String name, List<Card> hand) {
        this.name = name;
        this.cards = hand;
        sortHand();
        patterns = getPatterns();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets cards
     */
    protected List<Card> getCards() {
        return cards;
    }

    /**
     * Gets a specific card
     *
     * @param index Index of the card in the hand
     */
    public Optional<Card> getCard(int index) {
        if (index < 0 || index >= cards.size()) return Optional.empty();
        return Optional.of(cards.get(index));
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder().append(getName()).append(" (");
        for (Card card : getCards())
            string.append(card.toString()).append(" ");
        string.setLength(string.length() - 1);
        return string.append(')').toString();
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
        ArrayList<Card> cards = new ArrayList<>();
        var cardsUnparsed = text.split(" ");

        if (cardsUnparsed.length != handSize)
            throw new IllegalArgumentException("Hand must contain exactly " + handSize + " cards");

        for (int i = 0; i < handSize; i++) {
            var parsedCard = Card.tryParse(cardsUnparsed[i]);
            if (parsedCard.isEmpty())
                throw new ParseException("Couldn't parse card (" + cardsUnparsed[i] + ")", i);
            cards.add(parsedCard.get());
        }
        return new Hand(cards);
    }

    /**
     * Sort the hand in descending order
     */
    private void sortHand() {
        for (int i = 0; i < cards.size(); i++) {
            int swapIndex = i;
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(swapIndex).compareTo(cards.get(j)) < 0) {
                    swapIndex = j;
                } else if (cards.get(swapIndex).equals(cards.get(j))) {
                    throw new IllegalArgumentException("Duplicated card in hand");
                }
            }
            Collections.swap(cards, i, swapIndex);
        }
    }

    /**
     * Gets number of occurrences of each Value
     */
    protected Map<Value, List<Card>> groupCardsByValue() {
        Map<Value, List<Card>> groups = new EnumMap<>(Value.class);
        for (Card card : getCards()) {
            groups.putIfAbsent(card.value(), new ArrayList<>());
            groups.get(card.value()).add(card);
        }
        return groups;
    }

    /**
     * Gets number of occurrences of each Color
     */
    protected Map<Color, List<Card>> groupCardsByColor() {
        Map<Color, List<Card>> groups = new EnumMap<>(Color.class);
        for (Card card : getCards()) {
            groups.putIfAbsent(card.color(), new ArrayList<>());
            groups.get(card.color()).add(card);
        }
        return groups;
    }

    /**
     * Find the best straight in hand and return an array of this straight
     */
    public List<Card> findStraight() {
        if (cards.size() < 5) return Collections.emptyList();
        if (cards.get(0).value().ordinal() < 4)
            return Collections.emptyList();
        // if the highest card of the hand is less than a six, there cannot be a straight

        List<Card> result = new ArrayList<>();

        for (int i = 0; i < cards.size() - 1; i++) {
            int currentCardsCompare = cards.get(i).compareOrdinal(cards.get(i + 1));
            if (currentCardsCompare == 1 || currentCardsCompare == 0) {
                //We add the card to result
                if (!result.contains(cards.get(i))) result.add(cards.get(i));
                // if the next card ends the straight we add it
                if (result.get(0).compareOrdinal(result.get(result.size() - 1)) >= 3) result.add(cards.get(i + 1));
            } else {
                // if result is not empty and the last card is not four cards below the first clear the result
                if (!result.isEmpty() && result.get(0).compareOrdinal(result.get(result.size() - 1)) != 4) {
                    result.clear();
                }
                //if the number of cards is not enough to create a sequence
                if (cards.size() - 1 - i < 5) return result;
            }
        }
        // if the size of the result is above 5 and the last card is four cards below the first return result else return empty list
        return result.size() >= 5 && result.get(0).compareOrdinal(result.get(result.size() - 1)) >= 4 ? result : Collections.emptyList();
    }

    /**
     * Is there a flush in the hand?
     *
     * @return Cards realising the flush or empty if there's no flush
     */
    public List<Card> flushDetection() {
        if (cards.size() < 5) return Collections.emptyList();
        for (var entry : groupCardsByColor().entrySet())
            if (entry.getValue().size() >= 5) return entry.getValue();
        return Collections.emptyList();
    }

    /**
     * Gets the patterns and card values that realize them
     **/
    public Map<Patterns, List<List<Card>>> getPatterns() {
        EnumMap<Patterns, List<List<Card>>> result = new EnumMap<>(Patterns.class);

        var flush = flushDetection();
        if (!flush.isEmpty()) {
            result.putIfAbsent(Patterns.FLUSH, new ArrayList<>());
            result.get(Patterns.FLUSH).add(flush);
        }
        var straight = findStraight();
        if (!straight.isEmpty()) {
            result.putIfAbsent(Patterns.STRAIGHT, new ArrayList<>());
            result.get(Patterns.STRAIGHT).add(straight);
        }

        var entries = groupCardsByValue();
        for (Value value : Arrays.stream(Value.values()).sorted((v1, v2) -> v2.ordinal() - v1.ordinal()).toList()) {
            var entry = entries.getOrDefault(value, Collections.emptyList());
            Patterns p = switch (entry.size()) {
                case 1 -> Patterns.HIGHER;
                case 2 -> Patterns.PAIR;
                case 3 -> Patterns.THREE_OF_A_KIND;
                case 4 -> Patterns.FOUR_OF_A_KIND;
                default -> null;
            };
            if (p == null) continue;
            result.putIfAbsent(p, new ArrayList<>());
            result.get(p).add(entry);
        }

        if (result.containsKey(Patterns.FLUSH) && result.containsKey(Patterns.STRAIGHT)) {
            result.putIfAbsent(Patterns.STRAIGHT_FLUSH, new ArrayList<>());
            var straightCards = result.get(Patterns.STRAIGHT).get(0);
            var commonCards = result.get(Patterns.FLUSH).get(0).stream().filter(straightCards::contains).toList();
            if (commonCards.size() >= 5) result.get(Patterns.STRAIGHT_FLUSH).add(commonCards);
        }
        if (result.containsKey(Patterns.PAIR) && result.containsKey(Patterns.THREE_OF_A_KIND)) {
            result.putIfAbsent(Patterns.FULL, new ArrayList<>());
            result.get(Patterns.FULL).add(Stream.of(result.get(Patterns.THREE_OF_A_KIND).get(0), result.get(Patterns.PAIR).get(0)).flatMap(Collection::stream).toList());
        }
        if (result.containsKey(Patterns.PAIR) && result.get(Patterns.PAIR).size() > 1) {
            result.putIfAbsent(Patterns.DOUBLE_PAIR, new ArrayList<>());
            result.get(Patterns.DOUBLE_PAIR).add(result.get(Patterns.PAIR).stream().flatMap(Collection::stream).toList());
        }

        return result;
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare patterns and values of cards between the two hands
     */
    public int compareTo(Hand otherHand) {
        var winningHand = comparePatterns(otherHand).winningHand();
        if (winningHand == this) return 1;
        if (winningHand == otherHand) return -1;
        return 0;
    }

    /**
     * Compare the two arrays of Values given by pattern parameter from both hands
     *
     * @param otherHand the other hand to be compared.
     * @param pattern   the pattern for which we will inspect the values
     **/
    public Winner comparePatternValues(Patterns pattern, Hand otherHand) {
        var handOneList = getPatterns().get(pattern).iterator();
        var handTwoList = otherHand.getPatterns().get(pattern).iterator();
        while (handOneList.hasNext() && handTwoList.hasNext()) {
            var firstHandCards = handOneList.next().iterator();
            var secondHandCards = handTwoList.next().iterator();
            while (firstHandCards.hasNext() && secondHandCards.hasNext()) {
                var firstHandCard = firstHandCards.next();
                var secondHandCard = secondHandCards.next();
                int res = firstHandCard.compareTo(secondHandCard);
                if (res != 0)
                    return new Winner(res > 0 ? this : otherHand, pattern, res > 0 ? firstHandCard : secondHandCard);
            }
        }
        return null;
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare the current hand with otherHand with the patterns
     **/
    public Winner comparePatterns(Hand otherHand) {
        for (Patterns p : Patterns.values()) {
            if (patterns.containsKey(p) && !otherHand.patterns.containsKey(p)) // Only this hand has the pattern
                return new Winner(this, p, patterns.get(p).get(0).get(0));
            else if (!patterns.containsKey(p) && otherHand.patterns.containsKey(p)) // Only the other hand has the pattern
                return new Winner(otherHand, p, otherHand.patterns.get(p).get(0).get(0));
            else if (patterns.containsKey(p) && otherHand.patterns.containsKey(p)) { // Both hands have the pattern
                Winner comparisonResult = comparePatternValues(p, otherHand);
                if (comparisonResult != null) return comparisonResult;
            }
        }
        return new Winner(null, Patterns.EQUALITY, null);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Hand hand && getCards().equals(hand.getCards());
    }

    @Override
    public int hashCode() {
        return cards.hashCode();
    }
}
