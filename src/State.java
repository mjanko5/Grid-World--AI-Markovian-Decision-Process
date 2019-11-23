/**
 * Matt Jankowski
 * AI (CS 411) Hw 10
 * MDP GridWorld - State Class
 * To God be the Glory
 */

import javax.accessibility.AccessibleStateSet;

public class State {

    //properties of each state:
    private int x, y;
    private float reward;
    private String description; //a string such as "W", "T", "-1" to denote each cell's content
    private float utility = 0;  //updated as MDP run
    private float nextUtility = 0; //updated as well

    public State(int x, int y, float reward, String desciption) {
        this.x = x;
        this.y = y;
        this.reward = reward;
        this.description = desciption;
    }

    public State(Coord C) {
        this.x = C.x;
        this.y = C.y;
        this.reward = C.reward;
    }

    //getters:
    public float getImmediateReward() {
        return reward;
    }

    public float getUtility() {
        return utility;
    }

    public float getNextUtility() {
        return nextUtility;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDescription() {
        return description;
    }

    //setters:
    public void setUtility(float utility) {
        this.utility = utility;
    }

    public void setNextUtility(float nextUtility) {
        this.nextUtility = nextUtility;
    }


    //move left if possible
    public Coord moveLeft(Environment Env) {
        for (Coord wall : Env.walls) {
            if (x - 1 == wall.x && y == wall.y) return new Coord(x, y);  //don't move if hits a wall
        }
        if (x == 1) return new Coord(x, y);    //didn't move: boundary of environment
        else return new Coord(x - 1, y);    //moved left
    }

    //move right if possiblexxxx
    public Coord moveRight(Environment Env) {
        for (Coord wall : Env.walls) {
            if (x + 1 == wall.x && y == wall.y) return new Coord(x, y);  //don't move if hits a wall
        }
        if (x == Env.getWidth()) return new Coord(x, y);      //didn't move
        else return new Coord(x + 1, y); //moved right
    }

    //move up if possible
    public Coord moveUp(Environment Env) {
        for (Coord wall : Env.walls) {
            if (x == wall.x && y + 1 == wall.y) return new Coord(x, y);  //don't move if hits a wall
        }
        if (y == Env.getHeight()) return new Coord(x, y);      //didn't move
        else return new Coord(x, y + 1); //moved up
    }

    //move down if possible
    public Coord moveDown(Environment Env) {
        for (Coord wall : Env.walls) {
            if (x == wall.x && y - 1 == wall.y) return new Coord(x, y);  //don't move if hits a wall
        }
        if (y == 1) return new Coord(x, y);      //didn't move
        else return new Coord(x, y - 1);      //moved down
    }

}