package constant;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public enum CellValue {
    EMPTY("-"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9");

    private String representation;
    CellValue(String representation) {
        this.representation = representation;
    }

    private static final Map<String, CellValue> representationCellValueMap = new HashMap<>();

    static {
        for (CellValue cellValue : values()) {
            representationCellValueMap.put(cellValue.representation, cellValue);
        }
    }

    public static Set<CellValue> allValuesExceptEmpty() {
        Set<CellValue> values = EnumSet.allOf(CellValue.class);
        values.remove(EMPTY);
        return values;
    }

    public static CellValue convertValue(String potentialRepresentation) {
        return representationCellValueMap.get(potentialRepresentation);
    }
}
