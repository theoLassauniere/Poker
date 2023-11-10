package poker;

/**
 * Result of the comparison of the hands
 *
 * @param winningHand  The hand that is winning (null if equality)
 * @param pattern      How was the victory achieved
 * @param decisiveValue The value that has been decisive of this winning situation
 * @author Team B
 */
public record Winner(Hand winningHand, Patterns pattern, Value decisiveValue) {
    /**
     * Is there an equality between hands ? (No winner)
     */
    public boolean isEquality() {
        return winningHand == null;
    }

    @Override
    public String toString() {
        if (isEquality()) return "Égalité";

        StringBuilder stringRes = new StringBuilder()
                .append(winningHand())
                .append(" gagne avec ");
        switch (pattern()) {
            case HIGHER -> stringRes.append("la carte la plus haute : ");
            case PAIR -> stringRes.append("une paire de : ");
            case DOUBLE_PAIR -> stringRes.append("une double paire dont la paire gagnante est ");
            case THREE_OF_A_KIND -> stringRes.append("un brelan de : ");
            case STRAIGHT -> stringRes.append("une suite dont la carte la plus haute est ");
            case FLUSH -> stringRes.append("une couleur dont la carte décisive est ");
            case FULL -> stringRes.append("un full contenant un brelan de : ");
            case FOUR_OF_A_KIND -> stringRes.append("un carré de : ");
            case STRAIGHT_FLUSH -> stringRes.append("une quinte flush dont la carte la plus haute est ");
            default -> stringRes.append(pattern);
        }
        return stringRes.append(decisiveValue()).toString();
    }
}
