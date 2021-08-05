package sudoku;

import backtracking.Backtracker;

public class SudokuMain {

    public static void main(String[] args) {
        SudokuConfig sudoku = new SudokuConfig(args[0]);
//        System.out.println(sudoku.checkRows());
//        System.out.println(sudoku.checkCols());
//        System.out.println(sudoku.checkSquares());
        Backtracker sudokuSolver = new Backtracker(Boolean.parseBoolean(args[1]));
        System.out.println("initial:");
        System.out.println(sudoku);
        System.out.println("solution:");
        System.out.println(sudokuSolver.solve(sudoku).get());
    }

}
