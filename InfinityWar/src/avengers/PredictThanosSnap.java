package avengers;

import java.lang.reflect.Array;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {
	 public static void traverse(int node, boolean[] beenThere, int[][] thanosSnap){
        beenThere[node] = true; 
        for(int i = 0; i < beenThere.length; i++){
            if(thanosSnap[node][i] == 1){
                if(!beenThere[i]){
                    traverse(i, beenThere, thanosSnap);
                }
            }
        }
     }
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }


        // I started here code above was given

        String PredictThanosSnapInputFile = args[0];
        String PredictThanosSnapOutputFile = args[1];

        StdIn.setFile(PredictThanosSnapInputFile);
        
        Long seed = StdIn.readLong();
        int numNodes = StdIn.readInt();
        int thanosSnap[][] = new int [numNodes][numNodes];

        for(int x=0; x < numNodes; x++){
            for(int y=0; y<numNodes; y++){
               thanosSnap[x][y] = StdIn.readInt();
               System.out.print(thanosSnap[x][y]);
            }
          System.out.println();
        }

        System.out.println("booooooooooooooooooooo");

//if theres  a 1 its an edge if its a  0 its not connected
//from 0 to first int have another array and set everything to 0
        StdRandom.setSeed(seed);
        boolean[] chekVer = new boolean[numNodes];

        for(int i=0; i<numNodes; i++){
            if(StdRandom.uniform() <= 0.5){
                for(int j = 0; j < numNodes ;j++){
                    thanosSnap[i][j] = 0; 
                    
                }
                chekVer[i] = true;
            }
        }

        StdOut.setFile(PredictThanosSnapOutputFile);

        boolean[] beenThere = new boolean[numNodes];
        for(int i = 0; i < numNodes; i++){
            if(chekVer[i]){
                continue;
            }
            for(int j = 0; j < numNodes; j++){
                chekVer[j] = false;
            }
            traverse(i, beenThere, thanosSnap);
            for(int j = 0; j < numNodes; j++){
                if(!beenThere[j]){
                    StdOut.print(false);
                    return;
                }
            }
        }
        StdOut.print(true);
            for(int x=0; x < numNodes; x++){
                for(int y=0; y<numNodes; y++){
                   System.out.print(thanosSnap[x][y]);
                }
              System.out.println();
            }

                    //intead of making it all 0 the connection still exists
                    //make a seperate array with deleted vertices so that way you cant checkt that specific vertex


                }
                //delete vertex -- work on this part
                //set entire row to 0
    }

// check for connectivity



        // then you have to check if all the vertices connect



    	// WRITE YOUR CODE HERE

