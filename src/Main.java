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

        //new Environment
        Environment Env = new Environment("mdp_input.txt");

        MDP runMDP = new MDP(Env);  //run MDP
        runMDP.valueIteration(Env);
    }
}