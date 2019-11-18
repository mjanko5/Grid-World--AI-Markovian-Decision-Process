/**
 * Matt Jankowski
 * AI (CS 411) Hw 10
 * MDP GridWorld - State Class
 * To God be the Glory
 */


public class State {

    private int x;
    private int y;
    private float reward;
    private float utility;

    public State(int x, int y, float reward) {
        this.x = x;
        this.y = y;
        this.reward = reward;
    }

    public State(Coord C){
        this.x = C.x;
        this.y = C.y;
        this.reward = C.reward;
    }

    //getters:
    public float getImmediateReward() { return reward; }
    public int getX(){ return x; }
    public int getY(){ return y; }

    //setters:
    public void setUtility(float utility){
        this.utility = utility;
    }



















//    //move 0 left if possible
//    public State moveLeft() {
//
//    }
//
//    //move 0 right if possible
//    public Node moveRight() {
//        int[] pattern = board.clone(); //new board
//        if (pattern[3] == 0 || pattern[7] == 0 || pattern[11] == 0 || pattern[15] == 0) {
//            //System.out.println("Cannot move right! Blank is in right column.");
//            return null;
//        } else { //move right
//            int zeroPosition = get0Position(pattern);
//            pattern[zeroPosition] = pattern[zeroPosition + 1]; //swap (slide)
//            pattern[zeroPosition + 1] = 0;
//            return new Node(pattern, this, 'R'); //create new node
//        }
//    }
//
//    //move 0 up if possible
//    public Node moveUp() {
//        int[] pattern = board.clone(); //new board
//        if (pattern[0] == 0 || pattern[1] == 0 || pattern[2] == 0 || pattern[3] == 0) {
//            //System.out.println("Cannot move up! Blank is in top row.");
//            return null;
//        } else { //move up
//            int zeroPosition = get0Position(pattern);
//            pattern[zeroPosition] = pattern[zeroPosition - 4]; //swap (slide)
//            pattern[zeroPosition - 4] = 0;
//            return new Node(pattern, this, 'U'); //create new node
//        }
//    }
//
//    //move 0 down if possible
//    public Node moveDown() {
//        int[] pattern = board.clone(); //new board
//        if (pattern[12] == 0 || pattern[13] == 0 || pattern[14] == 0 || pattern[15] == 0) {
//            //System.out.println("Cannot move down! Blank is in bottom row.");
//            return null;
//        } else { //move down
//            int zeroPosition = get0Position(pattern);
//            pattern[zeroPosition] = pattern[zeroPosition + 4]; //swap (slide)
//            pattern[zeroPosition + 4] = 0;
//            return new Node(pattern, this, 'D'); //create new node
//        }
//    }
//
//    //print data about the node
//    public void printNodeData() {
//        System.out.println("   depth = " + this.depth);
//        try {
//            Functions.printArray("   parent board", this.parent.board);
//        } catch (NullPointerException e) {
//            System.out.println("   parent board = This is the root and there is no parent.");
//        }
//        //Functions.printArray("   board", this.board);
//        printBoard();
//
//    }
//
//    //print just the board
//    public void printBoard() {
//        for (int i = 0; i < 16; i++) {
//            if (i % 4 == 3) { //if last character
//                System.out.print("\t" + this.board[i] + "\n");
//            } else {
//                System.out.print("\t" + this.board[i]);
//            }
//        }
//    }



}