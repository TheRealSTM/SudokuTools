package gui;

import constant.BoardConstants;
import game.SudokuBoard;

import javax.swing.*;
import java.awt.*;

public class SudokuGUI extends JFrame {
    private SudokuBoard board; // Assume this is your Sudoku board class

    public SudokuGUI(SudokuBoard board) {
        this.board = board;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sudoku Board");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(BoardConstants.UNIT_SIZE, BoardConstants.UNIT_SIZE));

        for (int row = 0; row < BoardConstants.UNIT_SIZE; row++) {
            for (int col = 0; col < BoardConstants.UNIT_SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setText(board.getPositionValue(row, col).getRepresentation());
                cell.setHorizontalAlignment(SwingConstants.HORIZONTAL);
                cell.setEditable(false);
                add(cell);
            }
        }

    }

}
