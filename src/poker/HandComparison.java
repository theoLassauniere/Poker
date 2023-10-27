package poker;

import java.util.List;

public record HandComparison(int compareResult, Patterns pattern, List<Value> values) {
}
