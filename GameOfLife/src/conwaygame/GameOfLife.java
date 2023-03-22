package conwaygame;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        // WRITE YOUR CODE HERE
        StdIn.setFile(file);
        int r= StdIn.readInt();
        int c = StdIn.readInt();
        totalAliveCells=0;
        grid = new boolean[r][c];
        


        for(int x=0; x< r; x++){
            for(int y=0; y < c; y++){
                grid[x][y] = StdIn.readBoolean();
                if(grid[x][y]){ //////////////
                    totalAliveCells ++; ///////
                } //
            }
        }

        
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        if(grid[row][col]== ALIVE){
            return true;
            //return grid[row][col];
        }
        // WRITE YOUR CODE HERE
        return false; // update this line, provided so that code compiles
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        int count =0;
            for(int row=0; row < grid.length; row++ ){
                for(int col=0; col < grid[0].length; col++){
                    if(getCellState(row,col)) count ++;
                        return true;
                }
            }  
        

            
             //can also do grid[x][i]== Alive instead of getCellState(row,col) 
        // WRITE YOUR CODE HERE
        if (count>0) return true;
        return false; // update this line, provided so that code compiles
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {

        if(row==0 && col==0){ //top left corner
            int count =0;
            if (getCellState(grid.length-1, grid[0].length-1)) count++; // bottom right corner 16
            if (getCellState(grid.length-1, col)) count++; //bottom left
            if (getCellState(grid.length-1, col+1)) count++; // next to bottom left
            if (getCellState(row, grid[0].length-1)) count++; // top right
            if (getCellState(row +1, grid[0].length-1)) count++;  //next to top right
            if (getCellState(row+1, col)) count++; // unde top left
            if (getCellState(row +1, col +1)) count++;// across top left
            if (getCellState(row , col+1)) count++; // right of top left
            return count;
                }
    
    
        else if(row ==grid.length-1 && col==0){ //bottom left corner
            int count =0;
            if (getCellState(row=0, col)) count ++;     //top left corner 1
            if (getCellState(row=0 , col+1)) count++;     //top left next to corner 2
            if (getCellState(row=0, grid[0].length - 1)) count++;    //top right corner 4
            if (getCellState(row, grid[0].length-1)) count++;    //bottom right corner 16
            if(getCellState(row - 1, grid[0].length -1)) count++;   //ontop of bottom right corner 12
            if(getCellState(row -1, col))  count++;                        //top of bottom left 9
            if (getCellState(row, col +1)) count++;       //right of bottom left 14
            if(getCellState(row - 1, col + 1)) count++;                //across from bottom left 10
            return count;
                }
    
    
        
    
        else if(row==0 && col==grid[0].length - 1){ //top right corner
            int count =0;
            if (getCellState(row, col=0)) count ++;     //top left corner 1
            if (getCellState(row + 1, col = 0)) count ++;     // under top left 5
            if (getCellState(row = grid.length - 1, col=0)) count ++;     //bottom left 13
            if (getCellState(row= grid.length - 1, col)) count ++;    // bottom right 16
            if (getCellState(row= grid.length -1, col - 1)) count ++;    //left of bottom right 15
            if (getCellState(row, col - 1)) count ++;    //left of top right 3
            if (getCellState(row -1 , col)) count ++;    //under top right 8
            if (getCellState(row - 1, col - 1)) count ++;    // across top right 7
            return count;
            }
    
        else if(row ==grid.length-1 && col==grid[0].length-1){ //bottom right corner
            int count =0;
            if (getCellState(row=0, col=0)) count ++;     //top left corner 1
            if (getCellState(row=0 , col-1)) count++;     //next to top right corner 3
            if (getCellState(row=0, col)) count++;    //top right corner 4
            if(getCellState(row - 1, col)) count++;    //ontop of bottom right corner 12
            if (getCellState(row, col=0)) count++;    //bottom left corner 13
            if(getCellState(row -1, col=0))  count++;                        //top of bottom left 9
            if (getCellState(row -1, col -1)) count++;       // across bottom right 11
            if(getCellState(row , col - 1)) count++;                //  left of bottom right 15
            return count;
             }
    
    
        else if((col!= grid[0].length -1 || col!= 0) && row == grid.length -1){ // bottom
            int count =0;
             if (getCellState(row, col -1)) count ++;     // left of circle 22
             if (getCellState(row - 1, col - 1)) count ++;     // top left of circle 17
             if (getCellState(row - 1, col)) count ++;     // top of circe 18
             if (getCellState(row - 1, col + 1)) count ++;    // top right of circle 19
             if (getCellState(row, col + 1)) count ++;    // right of circle 24
             if (getCellState(row = 0, col - 1)) count ++;    // other side left of circle 2
             if (getCellState(row = 0 , col)) count ++;    // other side middle of circle 3
             if (getCellState(row = 0, col + 1)) count ++;    // other side right of circle 4
            return count;
        }
        else if((col!= grid[0].length -1 || col!= 0) && row == 0){ // toppppppp
            int count =0;
            if (getCellState(row, col -1)) count ++;     // left of top 2
            if (getCellState(row + 1, col - 1)) count ++;     // under left of top 7
            if (getCellState(row + 1, col)) count ++;     // under top 8
            if (getCellState(row + 1, col + 1)) count ++;    // under right of top 9
            if (getCellState(row, col + 1)) count ++;    // right of top 4
            if (getCellState(row = grid.length - 1, col)) count ++;    // bottom of grid middle 23
            if (getCellState(row = grid.length - 1 , col - 1)) count ++;    // left of bottom of grid middle 22
            if (getCellState(row = grid.length - 1, col + 1)) count ++;    // right of bottom of grid middle 24
            return count;
                 }
                
        else if((row!= grid[0].length -1 || row!= 0) && col == grid[0].length - 1){ // righttttttttttt
            int count =0;
            if (getCellState(row -1 , col)) count ++;     // top of circle 10
            if (getCellState(row -1, col - 1)) count ++;     // top and left of circle 9
            if (getCellState(row, col - 1)) count ++;     //  left of cirlce 14
            if (getCellState(row + 1, col - 1)) count ++;    // under left of circle 19
            if (getCellState(row + 1, col)) count ++;    // under circle 20
            if (getCellState(row - 1, col=0)) count ++;    // other side top 6
            if (getCellState(row, col=0)) count ++;    // other side middle 11
            if (getCellState(row + 1, col=0)) count ++;    // other side bottom 16
            return count;
                   }
            
                else if((row!= grid[0].length -1 || row!= 0) && col == 0){ // leftttttttttt
                    int count =0;
                     if (getCellState(row -1, col)) count ++;     // top of cirlce 7
                     if (getCellState(row - 1, col +1)) count ++;     // right of top of circle 7
                     if (getCellState(row , col + 1)) count ++;     // right of circle 12
                     if (getCellState(row + 1, col + 1)) count ++;    // down and right of cirlce 17
                     if (getCellState(row + 1, col)) count ++;    //  under cirlce 16
                     if (getCellState(row -1, col = grid[0].length-1)) count ++;    // other side top 10
                     if (getCellState(row, grid[0].length-1)) count ++;    // other side middle 15
                     if (getCellState(row +1, grid[0].length-1)) count ++;    // other side bottom 20
                     return count;
                        }
            
        else {                                              // not corner - or sides
            int count =0;
            if (getCellState(row -1, col)) count ++;     // top
            if (getCellState(row - 1, col + 1)) count ++;     // top right 
            if (getCellState(row, col +1)) count ++;     // right
            if (getCellState(row + 1, col + 1)) count ++;    //  bottom right
            if (getCellState(row + 1, col)) count ++;    // bottom
            if (getCellState(row + 1, col - 1)) count ++;    // bottomm left
            if (getCellState(row, col - 1)) count ++;    // left 
            if (getCellState(row - 1, col - 1)) count ++;    // top left
            return count;
            }
        }
  

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
     
    public boolean[][] computeNewGrid () {

        boolean[][] newG= new boolean[grid.length][grid[0].length];
        for(int row=0; row < grid.length; row++ ){
            for(int col=0; col < grid[0].length; col++){
        if (numOfAliveNeighbors(row,col) < 2){
            newG[row][col] = DEAD;
         }
        else if (numOfAliveNeighbors(row,col) ==3 && grid[row][col] == DEAD){
            newG[row][col] = ALIVE;
            }
       else if (numOfAliveNeighbors(row,col) ==3 || numOfAliveNeighbors(row,col) ==2 && grid[row][col] == DEAD){
            newG[row][col] = ALIVE;
        } 
       else if (numOfAliveNeighbors(row,col) >= 4){
            newG[row][col] = DEAD;
         }
         else {newG[row][col] = DEAD;
         }
                                                     }
                                                 }
        // WRITE YOUR CODE HERE
        return newG;// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        grid = computeNewGrid();
        // WRITE YOUR CODE HERE
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {
        for(int x =0;x<n; x++){
        grid = computeNewGrid();
    }
        // WRITE YOUR CODE HERE
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {

        // WRITE YOUR CODE HERE
        //traverse through grid each row and col and if stumble upon one thats alive check if neighboring cells root is itself and if it is itself you would call union and if its not then you skip it because uts already part of a set basically
        return 0; // update this line, provided so that code compiles
    }
}
