import java.util.ArrayList;

/**
 * Matt Jankowski
 * AI (CS 411) Hw 10
 * MDP GridWorld - Main Class
 * To God be the Glory
 */

public class Main {
    public static int width;
    public static int height;
    public static ArrayList<Coord> walls = new ArrayList<>();
    public static ArrayList<Coord> terminals = new ArrayList<>();
    public static float nonTerminalReward;
    public static float[] transitionProbs = new float[4];
    public static float discountRate;
    public static float epsilon;
    public static Coord agentInit = new Coord(1,1);
    private static ArrayList<State> states = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Starting Program.");

        //scan file and assign values to Main's variables:
        new FileScanner("mdp_input.txt");


        //Create non-terminal states
        for (int i = 1; i <= width; i++){
            for (int j = 1; j <= height; j++){
                char cellType = 'N'; //non-terminal

                for (Coord C : walls){
                    if (C.x == i && C.y == j )  cellType = 'W';     //if i, j is a wall -> WALL
                }
                for (Coord C : terminals){
                    if (C.x == i && C.y == j ) {
                        cellType = 'T';     //if i, j is a terminal -> TERMINAL
                        states.add(new State(i, j, C.reward));
                    }
                }

                if (cellType == 'N') states.add(new State(i, j, nonTerminalReward));

            }
        }

        for (State S : states){
            System.out.println("State#\t(" + S.getX() + "," + S.getY() + ")\t reward: " + S.getImmediateReward());
        }








//    int initialState[] = new int[] {1,1};
//
//    new MDP(initialState);  //run MDP

    }
}