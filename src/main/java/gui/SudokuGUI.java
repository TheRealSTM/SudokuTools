package gui;

import constant.BoardConstants;
import constant.CellValue;
import game.SudokuBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuGUI extends JFrame {
    private SudokuBoard board;

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

                int finalRow = row;
                int finalCol = col;
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JPopupMenu popupMenu = createNumberSelectionPopup(cell, finalRow, finalCol);
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                });
                add(cell);
            }
        }

    }

    private JPopupMenu createNumberSelectionPopup(JTextField cell, int row, int col) {
        JPopupMenu numberSelectionPopup = new JPopupMenu();
        numberSelectionPopup.setLayout(new GridLayout(BoardConstants.QUADRANT_SIZE, BoardConstants.QUADRANT_SIZE));

        ActionListener menuActionListener = e -> {
            if (board.addValue(row, col, CellValue.convertValue(e.getActionCommand()), true)) {
                cell.setText(e.getActionCommand());
            } else {
                System.out.println("Invalid move suggested.");
            }
        };

        for (CellValue cellValue : CellValue.allValuesExceptEmpty()) {
            JMenuItem item = new JMenuItem(cellValue.getRepresentation());
            item.addActionListener(menuActionListener);
            numberSelectionPopup.add(item);
        }
        return numberSelectionPopup;
    }
}
