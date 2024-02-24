import game.BoardCreator;
import game.SudokuBoard;
import gui.SudokuGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        BoardCreator boardCreator = new BoardCreator();

        SudokuBoard board = null;
        int attempts = 0;
        while (board == null) {
            try {
                board = boardCreator.createCompleteBoard();
            } catch (Exception e) {
                System.out.println("Board creator was unable to create board. Time to try again. " + (attempts++) + " attempt failed.");
            }
        }
        board.createPuzzleWithRandomRemoval(2);
        SudokuBoard finalBoard = board;
        SwingUtilities.invokeLater(() -> {
            SudokuGUI frame = new SudokuGUI(finalBoard);
            frame.setVisible(true);
        });
    }
}
