package game;

import constant.BoardCalculationUtil;
import constant.BoardConstants;
import constant.CellValue;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Data
public class SudokuBoard {

    Map<Integer, Set<CellValue>> rowValidValuesMap;
    Map<Integer, Set<CellValue>> colValidValuesMap;
    Map<Integer, Set<CellValue>> quadrantValidValuesMap;


    CellValue[][] grid;

    public SudokuBoard() {
        grid = new CellValue[BoardConstants.UNIT_SIZE][BoardConstants.UNIT_SIZE];
        for (int row = 0; row < BoardConstants.UNIT_SIZE; row++) {
            for (int col = 0; col < BoardConstants.UNIT_SIZE; col++) {
                grid[row][col] = CellValue.EMPTY;
            }
        }
        rowValidValuesMap = new HashMap<>();
        for (int i = 0; i < BoardConstants.UNIT_SIZE; i++) {
            rowValidValuesMap.put(i, CellValue.allValuesExceptEmpty());
        }
        colValidValuesMap = new HashMap<>();
        for (int i = 0; i < BoardConstants.UNIT_SIZE; i++) {
            colValidValuesMap.put(i, CellValue.allValuesExceptEmpty());
        }
        quadrantValidValuesMap = new HashMap<>();
        for (int i = 0; i < BoardConstants.UNIT_SIZE; i++) {
            quadrantValidValuesMap.put(i, CellValue.allValuesExceptEmpty());
        }
    }

    public boolean addValue(int row, int col, CellValue newCellValue, boolean overWrite) {
        if (!isValidMove(row, col, newCellValue)) {
            return false;
        }
        if (!overWrite && grid[row][col] != CellValue.EMPTY) {
            return false;
        }
        CellValue currentValue = grid[row][col];
        if (newCellValue == currentValue) {
            return false;
        }
        int quadrant = BoardCalculationUtil.getQuadrant(row, col);

        grid[row][col] = newCellValue;
        if (newCellValue != CellValue.EMPTY) {
            rowValidValuesMap.get(row).remove(newCellValue);
            colValidValuesMap.get(col).remove(newCellValue);
            quadrantValidValuesMap.get(quadrant).remove(newCellValue);
        }

        if (currentValue != CellValue.EMPTY) {
            rowValidValuesMap.get(row).add(currentValue);
            colValidValuesMap.get(col).add(currentValue);
            quadrantValidValuesMap.get(quadrant).add(currentValue);
        }
        return true;
    }

    public boolean isValidMove(int row, int col, CellValue newCellValue) {
        if (!BoardCalculationUtil.isValidPosition(row)) {
            return false;
        }
        if (!BoardCalculationUtil.isValidPosition(col)) {
            return false;
        }
        if (newCellValue == CellValue.EMPTY) {
            return true;
        }
        if (!rowValidValuesMap.get(row).contains(newCellValue)) {
            return false;
        }
        if (!colValidValuesMap.get(col).contains(newCellValue)) {
            return false;
        }
        int quadrant = BoardCalculationUtil.getQuadrant(row, col);
        if (!quadrantValidValuesMap.get(quadrant).contains(newCellValue)) {
            return false;
        }
        return true;
    }

    public CellValue getPositionValue(int row, int col) {
        if (!(BoardCalculationUtil.isValidPosition(row) || BoardCalculationUtil.isValidPosition(col))) {
            throw new IllegalArgumentException("Invalid row, col (" + row + ", " + col + ") provided.");
        }
        return grid[row][col];
    }

    public boolean hasWinConditionBeenMet() {
        return hasNoValidMovesRemaining(rowValidValuesMap) &&
                hasNoValidMovesRemaining(colValidValuesMap) &&
                hasNoValidMovesRemaining(quadrantValidValuesMap);
    }

    private boolean hasNoValidMovesRemaining(Map<Integer, Set<CellValue>> groupValidValuesMap) {
        return groupValidValuesMap.values().stream().allMatch(Set::isEmpty);
    }

    public Set<CellValue> getMissingValuesForRow(int row) {
        return rowValidValuesMap.get(row);
    }

    public Set<CellValue> getMissingValuesForCol(int row) {
        return colValidValuesMap.get(row);
    }

    public Set<CellValue> getMissingValuesForQuadrant(int row, int col) {
        int quadrant = BoardCalculationUtil.getQuadrant(row, col);
        return quadrantValidValuesMap.get(quadrant);
    }

    public void printBoard() {
        for (int row = 0; row < BoardConstants.UNIT_SIZE; row++) {
            if (row % 3 == 0) {
                System.out.println(" -----------------------");
            }
            for (int col = 0; col < BoardConstants.UNIT_SIZE; col++) {
                if (col % 3 == 0) System.out.print("| ");
                System.out.print(grid[row][col].getRepresentation() + " ");
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    public void createPuzzleWithRandomRemoval(int numberOfSquaresToRemove) {
        Random random = new Random();
        int numbersToRemove = numberOfSquaresToRemove;
        while (numbersToRemove > 0) {
            System.out.println("Move to remove: " + numbersToRemove);
            int potentialRow = random.nextInt(BoardConstants.UNIT_SIZE);
            int potentialCol = random.nextInt(BoardConstants.UNIT_SIZE);
            if (addValue(potentialRow, potentialCol, CellValue.EMPTY, true)) {
                numbersToRemove--;
            } else {
                System.out.println("I was unable to remove the move (" + potentialRow + ", " + potentialCol + ")");
            }
        }
    }
}
