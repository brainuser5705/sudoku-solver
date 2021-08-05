package sudoku;

import backtracking.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SudokuConfig implements Configuration {

    private final int[][] puzzle;
    private int row;
    private int col;

    public SudokuConfig(String filename){
        this.puzzle = new int[9][9];
        this.row = 0;
        this.col = -1;

        try ( BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            for (int i = 0; i < 9; i++){
                String[] numbers = reader.readLine().split(" ");
                for (int j = 0; j < 9; j++){
                    this.puzzle[i][j] = Integer.parseInt(numbers[j]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SudokuConfig(SudokuConfig other, int number){

        this.col = other.col;
        this.row = other.row;
        this.puzzle = new int[9][9];

        this.col += 1;
        if (this.col == 9){
            this.row += 1;
            this.col = 0;
        }

        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++){
                this.puzzle[r][c] = other.puzzle[r][c];
            }
        }

        this.puzzle[row][col] = number;

    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();

        int nextCol = this.col + 1;
        int nextRow = this.row;
        if (nextCol == 9){
            nextRow += 1;
            nextCol = 0;
        }

        if (nextRow < 9 && puzzle[nextRow][nextCol] == 0){
            for (int i = 1; i <= 9; i++){
                successors.add(new SudokuConfig(this, i));
            }
        }else{
            successors.add(new SudokuConfig(this, puzzle[nextRow][nextCol])); // add the same thing otherwise no successors
        }

        return successors;
    }

    @Override
    public boolean isValid() {
        return checkRows() && checkCols() && checkSquares();
    }

    public boolean checkRows(){
        for (int r = 0; r < 9; r++){
            Set<Integer> set = new TreeSet<>();
            for (int c = 0; c < 9; c++){
                if (puzzle[r][c] != 0 && set.contains(puzzle[r][c])){
                    return false;
                }
                set.add(puzzle[r][c]);
            }
        }
        return true;
    }

    public boolean checkCols(){
        for (int r = 0; r < 9; r++){
            Set<Integer> set = new TreeSet<>();
            for (int c = 0; c < 9; c++){
                if (puzzle[c][r] != 0 && set.contains(puzzle[c][r])){
                    return false;
                }
                set.add(puzzle[c][r]);
            }
        }
        return true;
    }

    public boolean checkSquares(){
        int[] indices = {0,3,6};

        for (int index : indices){
            for (int index2 : indices) {
                Set<Integer> set = new TreeSet<>();
                for (int r = index; r < index + 3; r++) {
                    for (int c = index2; c < index2 + 3; c++) {
                        if (puzzle[r][c] != 0 && set.contains(puzzle[r][c])) {
                            return false;
                        }
                        set.add(puzzle[r][c]);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isGoal() {
        return this.row == 8 && this.col == 8;
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < 9; i++){
            String prefix = "";
            for (int j = 0; j < 9; j++){
                result += prefix + puzzle[i][j];
                prefix = "|";
            }
            result += "\n";
            if ((i+1) % 3 == 0 && i < 8){
                result += "-----+-----+-----\n";
            }
        }
        return result;
    }
}

