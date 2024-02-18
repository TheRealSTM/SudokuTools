package constant;

public class BoardCalculationUtil {

    public static boolean isValidPosition(int potentialPosition) {
        return potentialPosition >= 0 && potentialPosition < BoardConstants.UNIT_SIZE;
    }
    public static int getQuadrant(int row, int col) {
        if (!isValidPosition(row)) {
            throw new IllegalArgumentException(String.format("Row (%d) is not valid.", row));
        }
        if (!isValidPosition(col)) {
            throw new IllegalArgumentException(String.format("Col (%d) is not valid.", row));
        }
        return (row / BoardConstants.QUADRANT_SIZE) * BoardConstants.QUADRANT_SIZE + (col / BoardConstants.QUADRANT_SIZE);
    }
}
