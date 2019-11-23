/**
 * Matt Jankowski
 * AI (CS 411) Hw 10
 * MDP GridWorld - Main Class
 * To God be the Glory
 */

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Program.");

        Environment Env = new Environment("mdp_input.txt"); //parse new Environment from file
        MDP mdp = new MDP(Env);      //init MDP
        mdp.valueIteration(Env);     //run MDP on parsed Environment in the form of value iteration
    }
}