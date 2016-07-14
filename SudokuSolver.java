public class SudokuSolver {
    private boolean[][] finalVal;
    private SudokuPuzzle sp;
    
    public SudokuSolver(SudokuPuzzle inputPuzzle) {
        sp = new SudokuPuzzle();        
        finalVal = new boolean[inputPuzzle.dim()][inputPuzzle.dim()];     

        int currentVal;
        
        for(int i = 0; i < inputPuzzle.dim(); i++) {
            for (int j = 0; j < inputPuzzle.dim(); j++) {
                currentVal = inputPuzzle.getVal(i, j);
                if (currentVal != 0) {
                    finalVal[i][j] = true;
                    sp.setVal(currentVal, i, j);
                }
            }
        }        

        solver(0);
    }
    
    private boolean solver(int element) {
        int row = element / sp.dim();
        int col = element % sp.dim();
        
        // return if out of bounds
        if (row >= sp.dim()) return true;

        // move on if solved

        if (finalVal[row][col]) return solver(element + 1);
        
        // if not solved, increment value by 1
        int currentVal = 0;
        int currBlockRow;
        int currBlockCol;
        boolean solved = false;
        while(currentVal < sp.dim()) {
            sp.setVal(++currentVal, row, col);

            currBlockRow = row / sp.blockDim();
            currBlockCol = col / sp.blockDim();
            if(sp.isRowValid(row) 
                   && sp.isColValid(col) 
                   && sp.isBlockValid(currBlockRow, currBlockCol)) {
                if(solver(element + 1)) {
                    solved = true;
                    break;
                }
            }
        }
        if(!solved) sp.setVal(0, row, col);
        return solved;
    }
    
    public SudokuPuzzle results() {
        return sp;
    }
    
    public static void main(String[] args) {
        
        int[][] starterPuzzle = new int[9][9];
        
//        int[][] starterPuzzle = {
//            { 5, 3, 0, 0, 7, 0, 0, 0, 0},
//            { 6, 0, 0, 1, 9, 5, 0, 0, 0},
//            { 0, 9, 8, 0, 0, 0, 0, 6, 0},
//            { 8, 0, 0, 0, 6, 0, 0, 0, 3},
//            { 4, 0, 0, 8, 0, 3, 0, 0, 1},
//            { 7, 0, 0, 0, 2, 0, 0, 0, 6},
//            { 0, 6, 0, 0, 0, 0, 2, 8, 0},   
//            { 0, 0, 0, 4, 1, 9, 0, 0, 5},
//            { 0, 0, 0, 0, 8, 0, 0, 7, 9}
//        };
        
        SudokuPuzzle inputSudoku = new SudokuPuzzle(starterPuzzle);
        
        SudokuSolver sudokuSolver = new SudokuSolver(inputSudoku);
        
        System.out.println("Input Puzzle:");
        
        System.out.println(inputSudoku);

        System.out.println("Is the input puzzle solved? " + inputSudoku.isSolved() + "\n");
        
        System.out.println("Run Solver!\n");
        
        System.out.println("Solved Puzzle:");
        
        System.out.println(sudokuSolver.results());
        
        System.out.println("Is the input puzzle solved? " + sudokuSolver.results().isSolved());
    }
}