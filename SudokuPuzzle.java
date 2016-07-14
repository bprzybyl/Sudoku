/*
 * SudokuPuzzle Class
 */
class SudokuPuzzle {
    private int[] puzzle;
    
    private final int PUZZLE_DIM = 9;
    private final int BLANK_NUM = 0;
    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 9;
    private final int BLOCK_DIM = 3;
    
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
    public int getVal(int row, int col) {
        return puzzle[PUZZLE_DIM * row + col];
    }
    
    // Set Value at (R,C) Coordinates
    public void setVal(int newVal, int row, int col) {
        puzzle[PUZZLE_DIM * row + col] = newVal;
    }
    
    public int dim() {
        return PUZZLE_DIM;
    }
    
    public int blockDim() {
        return BLOCK_DIM;
    }
    
    // Check if (R, C) does not have a value
    public boolean isEmpty(int row, int col) {
        return getVal(row, col) == BLANK_NUM;
    }
    
    // Get 1D index using (R, C) Coordinates
    private int get2DIndex(int row, int col) {
        return PUZZLE_DIM * row + col;
    }
    
    private int[] getRow(int row) {
        int[] tempArray = new int[PUZZLE_DIM];
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            tempArray[i] = getVal(row, i);
        }
        
        return tempArray;
    }
    
    // Check if a row contains 1 through 9
    public boolean isRowSolved(int row) {
        return isSolvedArray(getRow(row));
    }
    
    public boolean isRowValid(int row) {
        return isValidArray(getRow(row));
    }
    
    // Finds number of times a value shows up in a value array
    private int[] getValCounts(int[] inputArray) {
        int[] elementCountArray = new int[MAX_VALUE + 1];
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            elementCountArray[inputArray[i]]++;
        }  
        
        return elementCountArray;
        
    }
    
    // Code to check if a 1D array contains numbers from MIN_VALUE to MAX_VALUE
    private boolean isValidArray(int[] inputArray) {
        
        int[] valCounts = getValCounts(inputArray); 
        
        for (int j = MIN_VALUE; j <= MAX_VALUE; j++) {
            if (valCounts[j] > 1) return false;
        }
        
        return true;
    }
    
    // Code to check if a 1D array contains numbers from MIN_VALUE to MAX_VALUE
    private boolean isSolvedArray(int[] inputArray) {
        
        int[] valCounts = getValCounts(inputArray); 
        
        for (int j = MIN_VALUE; j <= MAX_VALUE; j++) {
            if (valCounts[j] != 1) return false;
        }
        
        return true;
    }    
    
    private int[] getCol(int col) {
        int[] tempArray = new int[PUZZLE_DIM];
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            tempArray[i] = getVal(i, col);
        }
        
        return tempArray;
    }
    
    // Check if a Column contains all numbers
    public boolean isColSolved(int col) {                               
        return isSolvedArray(getCol(col));
    }
    
    // Check if a Column is valid
    public boolean isColValid(int col) {
        return isValidArray(getCol(col));
    }
    
    // Check if a block of numbers is solved
    public boolean isBlockSolved(int blockRow, int blockCol) {
        return isSolvedArray(getBlock(blockRow, blockCol));
    }
    
    public boolean isBlockValid(int blockRow, int blockCol) {
        return isValidArray(getBlock(blockRow, blockCol));
    }    
    
    private int[] getBlock(int blockRow, int blockCol) {
        int[] tempArray = new int[PUZZLE_DIM];
        
        int valRow;
        int valCol;
        
        for (int i = 0; i < BLOCK_DIM; i++) {
            for (int j = 0; j < BLOCK_DIM; j++) {
                valRow = blockRow * BLOCK_DIM + i;
                valCol = blockCol * BLOCK_DIM + j;
                // convert RC to 1D Array
                tempArray[BLOCK_DIM * i + j] = getVal(valRow, valCol);
            }
        }            
        
        return tempArray;
    }
    
    public boolean isSolved() {
        for (int i = 0; i < PUZZLE_DIM; i++) {
            if(!isRowSolved(i)) return false;
        }
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            if(!isColSolved(i)) return false;
        }        
        
        int blockCount = PUZZLE_DIM / BLOCK_DIM;
        
        for (int i = 0; i < blockCount; i++) {
            for (int j = 0; j < blockCount; j++) {
                if(!isBlockSolved(i, j)) return false;
            }
        }
        
        return true;
    }
    
    // Try to combine with isSolved somehow
    public boolean isValid() {
        for (int i = 0; i < PUZZLE_DIM; i++) {
            if(!isRowValid(i)) return false;
        }
        
        for (int i = 0; i < PUZZLE_DIM; i++) {
            if(!isColValid(i)) return false;
        }        
        
        int blockCount = PUZZLE_DIM / BLOCK_DIM;
        
        for (int i = 0; i < blockCount; i++) {
            for (int j = 0; j < blockCount; j++) {
                if(!isBlockValid(i, j)) return false;
            }
        }
        
        return true;
    }    
    
    // Output 2D String representation of puzzle
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < PUZZLE_DIM; i++) {
            for (int j = 0; j < PUZZLE_DIM; j++) {
                outputString.append(getVal(i,j) + " ");
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
        System.out.println("        Is row 0 Valid? " + solvedSudoku.isRowValid(0));
        System.out.println("       Is row 0 Solved? " + solvedSudoku.isRowSolved(0));
        System.out.println("        Is col 0 Valid? " + solvedSudoku.isColValid(0));
        System.out.println("       Is col 0 Solved? " + solvedSudoku.isColSolved(0));
        System.out.println(" Is top-left 3x3 Valid? " + solvedSudoku.isBlockValid(0,0));
        System.out.println("Is top-left 3x3 Solved? " + solvedSudoku.isBlockSolved(0,0));
        System.out.println("       Is puzzle Valid? " + solvedSudoku.isValid());
        System.out.println("      Is puzzle Solved? " + solvedSudoku.isSolved());
        
        System.out.println("\n");
        
        SudokuPuzzle starterSudoku = new SudokuPuzzle(starterPuzzle);  
        System.out.println("Starter Puzzle:");
        System.out.println(starterSudoku);
        System.out.println("        Is row 0 Valid? " + starterSudoku.isRowValid(0));
        System.out.println("       Is row 0 Solved? " + starterSudoku.isRowSolved(0));
        System.out.println("        Is col 0 Valid? " + starterSudoku.isColValid(0));
        System.out.println("       Is col 0 Solved? " + starterSudoku.isColSolved(0));
        System.out.println(" Is top-left 3x3 Valid? " + starterSudoku.isBlockValid(0,0));
        System.out.println("Is top-left 3x3 Solved? " + starterSudoku.isBlockSolved(0,0));
        System.out.println("       Is puzzle Valid? " + starterSudoku.isValid());
        System.out.println("      Is puzzle Solved? " + starterSudoku.isSolved());
        
        System.out.println("\n");
        
        System.out.println("Get (0,0) = " + solvedSudoku.getVal(0,0));
        System.out.println("Set (0,0) of Solved Puzzle to 3 to invalidate");
        solvedSudoku.setVal(3,0,0);
        System.out.println("Solved Puzzle:");
        System.out.println(solvedSudoku);
        System.out.println("        Is row 0 Valid? " + solvedSudoku.isRowValid(0));
        System.out.println("       Is row 0 Solved? " + solvedSudoku.isRowSolved(0));
        System.out.println("        Is col 0 Valid? " + solvedSudoku.isColValid(0));
        System.out.println("       Is col 0 Solved? " + solvedSudoku.isColSolved(0));
        System.out.println(" Is top-left 3x3 Valid? " + solvedSudoku.isBlockValid(0,0));
        System.out.println("Is top-left 3x3 Solved? " + solvedSudoku.isBlockSolved(0,0));
        System.out.println("       Is puzzle Valid? " + solvedSudoku.isValid());
        System.out.println("      Is puzzle Solved? " + solvedSudoku.isSolved());
        
        System.out.println("\n");
        
        System.out.println("Get (0,0) = " + starterSudoku.getVal(0,0));
        System.out.println("Set (0,0) of Starter Puzzle to 3 to invalidate");
        starterSudoku.setVal(3,0,0);
        System.out.println("Starter Puzzle:");
        System.out.println(starterSudoku);
        System.out.println("        Is row 0 Valid? " + starterSudoku.isRowValid(0));
        System.out.println("       Is row 0 Solved? " + starterSudoku.isRowSolved(0));
        System.out.println("        Is col 0 Valid? " + starterSudoku.isColValid(0));
        System.out.println("       Is col 0 Solved? " + starterSudoku.isColSolved(0));
        System.out.println(" Is top-left 3x3 Valid? " + starterSudoku.isBlockValid(0,0));
        System.out.println("Is top-left 3x3 Solved? " + starterSudoku.isBlockSolved(0,0));
        System.out.println("       Is puzzle Valid? " + starterSudoku.isValid());
        System.out.println("      Is puzzle Solved? " + starterSudoku.isSolved());        
    }
}
