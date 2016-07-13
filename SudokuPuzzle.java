/*
 * SudokuPuzzle Class
 */
class SudokuPuzzle {
    private int[] puzzle;
    
    private final int PUZZLE_DIM = 9;
    private final int BLANK_NUM = 0;
    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 9;
    
    // Create Empty Puzzle
    public SudokuPuzzle() {
        puzzle = new int[PUZZLE_DIM * PUZZLE_DIM];
    }
    
    // Create Puzzle from 2D Input Array
    public SudokuPuzzle(int[][] inputArray) {
        puzzle = new int[PUZZLE_DIM * PUZZLE_DIM];
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            for (int j = 0; j < PUZZLE_DIM; j++) {
                puzzle[get2DIndex(i,j)] = inputArray[i][j];
            }
        }  
        
    }
    
    // Create Puzzle from 1D Input Array
    public SudokuPuzzle(int[] inputArray) {
        puzzle = new int[PUZZLE_DIM * PUZZLE_DIM];
    }
    
    // Get Value at (R,C) Coordinates
    public int get2D(int row, int col) {
        return puzzle[PUZZLE_DIM * row + col];
    }
    
    // Check if (R, C) does not have a value
    public boolean isEmpty(int row, int col) {
        return get2D(row, col) == BLANK_NUM;
    }
    
    // Get 1D index using (R, C) Coordinates
    private int get2DIndex(int row, int col) {
        return PUZZLE_DIM * row + col;
    }
    
    // Check if a row contains 1 through 9
    public boolean isRowSolved(int row) {
        int[] tempArray = new int[PUZZLE_DIM];
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            tempArray[i] = get2D(row, i);
        }
        
        return isSolvedArray(tempArray);
    }
    
    // Code to check if a 1D array contains numbers from MIN_VALUE to MAX_VALUE
    private boolean isSolvedArray(int[] inputArray) {
        
        int[] elementCountArray = new int[MAX_VALUE + 1];
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            elementCountArray[inputArray[i]]++;
        }  
        
        for (int j = MIN_VALUE; j <= MAX_VALUE; j++) {
            if (elementCountArray[j] != 1) return false;
        }
        
        return true;
    }
    
    // Check if a Column contains all numbers
    public boolean isColSolved(int col) {
        int[] tempArray = new int[PUZZLE_DIM];
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            tempArray[i] = get2D(i, col);
        }
        
        return isSolvedArray(tempArray);
    }
    
    // Check if a block of numbers is solved
    public boolean isBlockSolved(int blockNum) {
        return false;
    }
    
    // Output 2D String representation of puzzle
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < PUZZLE_DIM; i++) {
            for (int j = 0; j < PUZZLE_DIM; j++) {
                outputString.append(get2D(i,j) + " ");
            }
            
            if (i != (PUZZLE_DIM - 1)) outputString.append('\n');
        }
        return outputString.toString();
    }
    
    // Unit Tests
    public static void main (String[] args) throws java.lang.Exception {
        
        // from https://en.wikipedia.org/wiki/Sudoku
        
        int[][] solvedPuzzle = {
            { 5, 3, 4, 6, 7, 8, 9, 1, 2},
            { 6, 7, 2, 1, 9, 5, 3, 4, 8},
            { 1, 9, 8, 3, 4, 2, 5, 6, 7},
            { 8, 5, 9, 7, 6, 1, 4, 2, 3},
            { 4, 2, 6, 8, 5, 3, 7, 9, 1},
            { 7, 1, 3, 9, 2, 4, 8, 5, 6},
            { 9, 6, 1, 5, 3, 7, 2, 8, 4},   
            { 2, 8, 7, 4, 1, 9, 6, 3, 5},
            { 3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        
        int[][] starterPuzzle = {
            { 5, 3, 0, 0, 7, 0, 0, 0, 0},
            { 6, 0, 0, 1, 9, 5, 0, 0, 0},
            { 0, 9, 8, 0, 0, 0, 0, 6, 0},
            { 8, 0, 0, 0, 6, 0, 0, 0, 3},
            { 4, 0, 0, 8, 0, 3, 0, 0, 1},
            { 7, 0, 0, 0, 2, 0, 0, 0, 6},
            { 0, 6, 0, 0, 0, 0, 2, 8, 0},   
            { 0, 0, 0, 4, 1, 9, 0, 0, 5},
            { 0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        
        SudokuPuzzle solvedSudoku = new SudokuPuzzle(solvedPuzzle);
        System.out.println("Solved Puzzle:");
        System.out.println(solvedSudoku);
        System.out.println("Is row 0 Solved? " + solvedSudoku.isRowSolved(0));
        System.out.println("Is col 0 Solved? " + solvedSudoku.isColSolved(0));
        
        System.out.println("\n");
        
        SudokuPuzzle starterSudoku = new SudokuPuzzle(starterPuzzle);  
        System.out.println("Starter Puzzle:");
        System.out.println(starterSudoku);
        System.out.println("Is row 0 Solved? " + starterSudoku.isRowSolved(0));
        System.out.println("Is col 0 Solved? " + starterSudoku.isColSolved(0));
    }
}
