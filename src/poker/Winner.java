package poker;

/**
 * Result of the comparison of two hands
 *
 * @param winningHand The hand that is winning (null if equality)
 * @param pattern     How was the victory achieved
 * @param value       Value of the decisive card
 * @author Team B
 */
public record Winner(Hand winningHand, Patterns pattern, Value value) {
    @Override
    public String toString() {
        if (winningHand == null) return "Égalité";

        StringBuilder stringRes = new StringBuilder()
                .append(winningHand())
                .append(" gagne avec ");
        switch (pattern()) {
            case HIGHER -> stringRes.append("la carte la plus haute : ");
            case PAIR -> stringRes.append("une paire de : ");
            case DOUBLE_PAIR -> stringRes.append("une double paire dont la paire gagnante est ");
            case THREE_OF_A_KIND -> stringRes.append("un brelan de : ");
            case FOUR_OF_A_KIND -> stringRes.append("un carré de : ");
            case STRAIGHT -> stringRes.append("une suite dont la carte la plus haute est ");
            case FLUSH -> stringRes.append("une couleur dont la carte décisive est ");
            case STRAIGHT_FLUSH -> stringRes.append("une quinte flush dont la carte la plus haute est ");
            case FULL -> stringRes.append("un full contenant un brelan de : ");
            default -> stringRes.append(pattern);
        }
        return stringRes.append(value()).toString();
    }
}
