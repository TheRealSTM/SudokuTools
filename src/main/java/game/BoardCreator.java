package game;

import constant.BoardConstants;
import constant.CellValue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoardCreator {
    public SudokuBoard createCompleteBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        for (int row = 0; row < BoardConstants.UNIT_SIZE; row++) {
            for (int col = 0; col < BoardConstants.UNIT_SIZE;col ++) {
                Set<CellValue> intersection = new HashSet<>(sudokuBoard.getMissingValuesForRow(row));
                intersection.retainAll(sudokuBoard.getMissingValuesForCol(col));
                intersection.retainAll(sudokuBoard.getMissingValuesForQuadrant(row, col));
                if (intersection.size() == 0) {
                    sudokuBoard.printBoard();
                    throw new IllegalArgumentException("An error occurred no potential values can be assigned to position in board.");
                }
                int randomPosition = new Random().nextInt(intersection.size());
                CellValue potentialCellValue = intersection.stream()
                        .skip(randomPosition)
                        .findFirst()
                        .orElse(null);
                if (!sudokuBoard.addValue(row, col, potentialCellValue, false)) {
                    System.out.println("Attempted value: " + potentialCellValue + " in (" + row + ", " + col + ")");
                    sudokuBoard.printBoard();
                    throw new IllegalArgumentException("An error occurred creating the board.");
                }
            }
        }
        return sudokuBoard;
    }
}
