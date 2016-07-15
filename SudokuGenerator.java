public class SudokuGenerator {
    public SudokuGenerator(int dim) {
        // Create array the size of one dimension of the puzzle
        int[] topSeed = new int[dim];
        int[] sideSeed = new int[dim - 1];
        
        // Fill top Seed with all possible values
        for (int i = 0; i < topSeed.length; i++) {
            topSeed[i] = i + 1;
        }
        
        // Shuffle elements of top array
        shuffle(topSeed);
        
        // Fill side seed
        for (int i = 0; i < sideSeed.length; i++) {
            int seedVal = i + 1;
            if (seedVal == topSeed[0]) sideSeed[i] = dim;
            else sideSeed[i] = i + 1;
        }
        
        // shuffle side array
        shuffle(sideSeed);
        
        // create square matrix and insert seed arrays
        int[][] puzzleBuilder = new int[dim][dim];
        
        for (int i = 0; i < topSeed.length; i++) {
            puzzleBuilder[0][i] = topSeed[i];
        }
        
        for (int i = 0; i < sideSeed.length; i++) {
            puzzleBuilder[i + 1][0] = sideSeed[i];
        }
        
        SudokuPuzzle newPuzzle = new SudokuPuzzle(puzzleBuilder);
        
        System.out.println("Seed Puzzle:");
        System.out.println(newPuzzle + "\n");
        
        SudokuSolver ss = new SudokuSolver(newPuzzle);
        
        System.out.println("Generated Puzzle:");
        System.out.println(ss.results());
        
        // use solver to create unique puzzle
    }
    
    
    
//-- To shuffle an array a of n elements (indices 0..n-1):
//for i from 0 to n?2 do
//     j ? random integer such that i ? j < n
//     exchange a[i] and a[j]
    
    private int[] shuffle(int[] input) {
        
        int N = input.length;
        
        for (int i = 0; i < (N - 2); i++) {
            int swapIndex = (int) (Math.random() * (N - i)) + i;
            int temp = input[i];
            input[i] = input[swapIndex];
            input[swapIndex] = temp;
        }
        return input;
    }
    
    public static void main(String[] args) {
        int N = 9;        
        SudokuGenerator sg = new SudokuGenerator(N);
        
//        int[] shuffleTest = new int[N];
//        
//        for(int i = 0; i < N; i++) {
//            shuffleTest[i] = i*3;
//        }
//        
//        System.out.println("Input to shuffler");
//        for (int i : shuffleTest) System.out.print(i + " ");
//        System.out.println("");
//        
//        sg.shuffle(shuffleTest);
//
//        System.out.println("Output to shuffler");
//        for (int i : shuffleTest) System.out.print(i + " ");
//        System.out.println("");
        
    }
}