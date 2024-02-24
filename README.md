# Sudoku Tools

Sudoku Tools is a Java-based application that allows users to play Sudoku with a graphical user interface (GUI). The game was
created to play around with Swing.

## Features

- Interactive Swing GUI: Play Sudoku in a user-friendly graphical interface.
- Difficulty Levels: The level of difficulty can be altered by changing the number of tiles removed in the source code.
- Move Validation: Checks and validates each move to ensure it complies with Sudoku rules.
- Win Detection: Automatically detects and announces when the puzzle is successfully solved.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher.

### Installation

1. Clone the repository:
```
  git clone https://github.com/TheRealSTM/SudokuTools.git
```
2. Navigate to the project directory:
```
  cd SudokuTools
```

### Running the Game

To start the game, compile the Java files and run the `SudokuGUI` class:

1. Compile the project:
```dtd
  ...
```

## How to Play

- Starting a New Game: Launch the application to automatically generate a new Sudoku puzzle.
- Making Moves: Click on a cell and select a move from the list of moves [1 - 9]. If the move is valid, the board will be updated. If the move
  cannot be made. A error will appear.
- Winning the Game: The game is won when all cells are correctly filled in according to Sudoku rules.

## Built With

- Java Swing - The GUI framework used.
- Java - Programming language.
