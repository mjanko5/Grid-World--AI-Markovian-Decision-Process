/**
 * Matt Jankowski
 * AI (CS 411) Hw 10
 * MDP GridWorld - Coord Class
 * To God be the Glory
 */

//coordinate class
class Coord {
    int x;
    int y;
    int reward; //used when scanning file

    Coord(int x, int y, int reward) {
        this.x = x;
        this.y = y;
        this.reward = reward;
    }

    Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}