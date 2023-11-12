package poker;

/**
 * Result of the comparison of the hands
 *
 * @param winningHand  The hand that is winning (null if equality)
 * @param pattern      How was the victory achieved
 * @param decisiveCard The card that has been decisive of this winning situation
 * @author Team B
 */
public record Winner(Hand winningHand, Patterns pattern, Card decisiveCard) {
    /**
     * Is there an equality between hands ? (No winner)
     */
    public boolean isEquality() {
        return winningHand == null;
    }

    /**
     * Detect the full decisive pattern and return the corresponding string
     **/
    public String fullDecisivePattern() {
        return winningHand.getPatterns().get(Patterns.FULL).get(0).indexOf(decisiveCard()) >= 3 ? "une paire" : "un brelan";
    }

    @Override
    public String toString() {
        if (isEquality()) return "Égalité";

        StringBuilder stringRes = new StringBuilder()
                .append(winningHand())
                .append(" gagne avec ");
        switch (pattern()) {
            case HIGHER -> stringRes.append("la carte la plus haute : ").append(decisiveCard());
            case PAIR -> stringRes.append("une paire de : ").append(decisiveCard().value());
            case DOUBLE_PAIR ->
                    stringRes.append("une double paire dont la paire gagnante est ").append(decisiveCard().value());
            case THREE_OF_A_KIND -> stringRes.append("un brelan de : ").append(decisiveCard().value());
            case STRAIGHT -> stringRes.append("une suite dont la carte la plus haute est ").append(decisiveCard());
            case FLUSH -> stringRes.append("une couleur dont la carte décisive est ").append(decisiveCard());
            case FULL ->
                    stringRes.append("un full contenant ").append(fullDecisivePattern()).append(" de : ").append(decisiveCard().value());
            case FOUR_OF_A_KIND -> stringRes.append("un carré de : ").append(decisiveCard().value());
            case STRAIGHT_FLUSH ->
                    stringRes.append("une quinte flush dont la carte la plus haute est ").append(decisiveCard());
            default -> stringRes.append(pattern).append(decisiveCard());
        }
        return stringRes.toString();
    }
}
