package poker;

import java.util.List;

public record HandComparison(int compareResult, Patterns pattern, List<Value> values) {
    @Override
    public String toString() {
        StringBuilder stringRes = new StringBuilder()
                .append("La main ")
                .append(compareResult() > 0 ? 1 : 2)
                .append(" gagne avec ");
        switch (pattern()) {
            case EQUALITY -> {
                return "Egalité";
            }
            case HIGHER -> stringRes.append("la carte la plus haute : ").append(values().get(0));
            case PAIR -> stringRes.append("une paire de : ").append(values().get(0));
            case DOUBLE_PAIR -> stringRes.append("une double paire de : ")
                    .append(values().get(0))
                    .append(" et de : ")
                    .append(values().get(1));
            case THREE_OF_A_KIND -> stringRes.append("un brelan de : ").append(values().get(0));

        }
        return stringRes.toString();

    }


}
