package poker;

import java.util.List;

/**
 * Result of the comparison of two hands
 *
 * @param compareResult -1 if weaker, 0 if equals or 1 if stronger
 * @param pattern       How was the victory achieved
 * @param values        Values of the victorious card combinaison
 * @author Team B
 */
public record HandComparison(int compareResult, Patterns pattern, List<Value> values) {
    @Override
    public String toString() {
        if (compareResult == 0) return "Égalité";

        StringBuilder stringRes = new StringBuilder()
                .append("La main ")
                .append(compareResult() > 0 ? 1 : 2)
                .append(" gagne avec ");
        switch (pattern()) {
            case HIGHER -> stringRes.append("la carte la plus haute : ").append(values().get(0));
            case PAIR -> stringRes.append("une paire de : ").append(values().get(0));
            case DOUBLE_PAIR ->
                    stringRes.append("une double paire dont la paire la paire gagnante est paire de : ").append(values().get(0));
            case THREE_OF_A_KIND -> stringRes.append("un brelan de : ").append(values().get(0));
            case FOUR_OF_A_KIND -> stringRes.append("un carré de : ").append(values().get(0));
            case STRAIGHT -> stringRes.append("une suite dont la carte la plus haute est : ").append(values().get(0));
            case COLOR -> stringRes.append("une couleur dont la carte la plus haute est : ").append(values().get(0));
            case STRAIGHTFLUSH ->
                    stringRes.append("une quinte flush dont la carte la plus haute est : ").append(values().get(0));
            default -> stringRes.append(pattern);
        }
        return stringRes.toString();
    }
}
