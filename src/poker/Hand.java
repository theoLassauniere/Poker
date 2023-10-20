package poker;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Hand
 *
 * @author Team B
 */

public class Hand implements Comparable<Hand> {
    private final Card[] cards;

    /**
     * Hand constructor
     *
     * @param hand a tab of cards
     */
    public Hand(Card[] hand) {
        this.cards = hand;
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
        string.setLength(string.length()-1);
        return string.toString();
    }

    /**
     * Parses hand
     *
     * @param text Hand input
     * @return The parsed hand
     * @throws IllegalArgumentException The input doesn't contain the exacts number of card that is expected for that game
     * @throws ParseException The input contains an invalid card
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
        for (int i = 0; i < cards.length; i++) {
            int swapIndex = i;
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[swapIndex].compareTo(cards[j]) < 0) {
                    swapIndex = j;
                }
            }
            swap(i, swapIndex);
        }
    }

    /**
     * occurrences method
     * @return an int tab
     */
    public int[] occurrences() {
        var values = new int[Value.values().length];
        for (Card card : cards) {
            values[card.getValue().ordinal()]++;
        }
        return values;
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare patterns and values of cards between the two hands
     */
    @Override
    public int compareTo(Hand otherHand) {
        int testPatterns = comparePatterns(otherHand);
        if(testPatterns!=0) return testPatterns;
        else return compareCards(otherHand);
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare the values of cards in hand with cards of otherHand (without cards in Patterns)
     */
    public int compareCards(Hand otherHand){
        int cardIndex = 0;
        int cardIndex2 = 0;
        while (cardIndex<cards.length) {
            if(cards[cardIndex]!=null){
                while (cardIndex2<otherHand.getCards().length && otherHand.getCards()[cardIndex]!=null){
                    cardIndex2++;
                }
                if(cards[cardIndex].compareTo(otherHand.getCards()[cardIndex])!=0){
                    return cards[cardIndex].compareTo(otherHand.getCards()[cardIndex]);
                }
                cardIndex2 ++;
            }
            cardIndex++;
        }
        return 0;
    }

    /**
     * @param patterns the HashMap obtained by the getPatterns() method
     * Delete all the cards in hand when also located in Pattern
     **/
    public void deleteCardInPattern(HashMap<Patterns,ArrayList<Integer>> patterns){
        for(int i=0;i<cards.length;i++){
            if(patterns.values().contains(cards[i])) cards[i] = null;
        }
    }

    /**
     * @param otherHand the other hand to be compared.
     * @return Compare the current hand with otherHand with the patterns
     **/
    public int comparePatterns(Hand otherHand){
        HashMap<Patterns,ArrayList<Integer>> HandOnePatterns = getPatterns();
        HashMap<Patterns,ArrayList<Integer>> HandTwoPatterns = otherHand.getPatterns();
        for(Patterns p: Patterns.values()){
            if(HandOnePatterns.containsKey(p)&&!HandTwoPatterns.containsKey(p)) return 1;
            else if(!HandOnePatterns.containsKey(p)&&HandTwoPatterns.containsKey(p)) return -1;
            else if(HandOnePatterns.containsKey(p)&&HandTwoPatterns.containsKey(p)){
                int res = HandOnePatterns.get(p).get(0).compareTo(HandTwoPatterns.get(p).get(0));
                if(res!=0) return res;
            }
        }
        deleteCardInPattern(HandOnePatterns);
        otherHand.deleteCardInPattern(HandTwoPatterns);
        return 0;
    }

    /**
     * @return Create an HashMap with occurrences by patterns
     * **/
    public HashMap<Patterns,ArrayList<Integer>> getPatterns(){
        HashMap<Patterns,ArrayList<Integer>> result = new HashMap<>();
        int[] occurrences = occurrences();
        for(int i=0;i< occurrences().length;i++){
            Patterns p = null;
            switch (occurrences[i]){
                case 2:
                    p = Patterns.Pair;
                    break;
                case 3:
                    p = Patterns.Brelan;
                    break;
            }
            if(p!=null){
                if(result.containsKey(p)) result.get(p).add(i);
                else result.put(p,new ArrayList<>(Arrays.asList(i)));
            }
        }
        return result;
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
