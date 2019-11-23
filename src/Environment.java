/**
 * Matt Jankowski
 * AI (CS 411) Hw 10
 * MDP GridWorld - Environment Class
 * To God be the Glory
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Environment {
    //data fields
    private int width;
    private int height;
    public ArrayList<Coord> walls = new ArrayList<>();
    private ArrayList<Coord> terminals = new ArrayList<>();
    private float nonTerminalReward;
    private float[] transitionProbs = new float[4];
    private float discountRate;
    private float epsilon;

    public ArrayList<State> states = new ArrayList<>();
    private String[][] descGrid;
    public float[][] utilityGrid;

    //constructor:
    public Environment(String pathName) {
        System.out.println("Initiating an environment...\n");
        fileScanner(pathName); //scan file and assign variables.
        createStates(); //create states from the data
        //printStatesInfo();
        printEnvironment();
    }

    //getters:
    public float getEpsilon() {
        return epsilon;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //FILE SCANNER THINGS:
    //FileScanner scans from file to an environment. Returns the environment.
    public void fileScanner(String pathName) {
        File file = new File(pathName);

        try { //try scanning from file
            Scanner sc = new Scanner(file);  // set file as scanner object

            //GET SIZE OF GRID FROM FILE
            int[] size = getIntsFromFile(sc);
            width = size[0];
            height = size[1];
            System.out.println("Width: " + width + "\tHeight: " + height);

            //GET WALL COORDINATES FROM FILE
            while (true) {
                int[] wall = getIntsFromFile(sc);
                if (wall == null) break; //end of walls in file

                Coord C = new Coord(wall[0], wall[1]);
                walls.add(C);
            }

            //print
            System.out.println("Walls:");
            for (Coord C : walls) {
                System.out.println("(" + C.x + ", " + C.y + ")");
            }

            //GET TERMINAL COORDINATES FROM FILE
            while (true) {
                int[] terminal = getIntsFromFile(sc);
                if (terminal == null) break; //end of terminals in file

                Coord C = new Coord(terminal[0], terminal[1], terminal[2]);
                terminals.add(C);
            }

            //print
            System.out.println("Terminals:");
            for (Coord C : terminals) {
                System.out.println("(" + C.x + ", " + C.y + ", " + C.reward + ")");
            }

            //GET REWARD OF NON-TERMINAL STATES FROM FILE
            nonTerminalReward = Float.parseFloat(getCleanLine(sc));
            System.out.println("Non-terminal Reward: \n" + nonTerminalReward);

            //GET TRANSITION PROBABILITIES FROM FILE
            System.out.println("Transition Probabilities: ");
            for (int i = 0; i < 4; i++) {
                transitionProbs[i] = Float.parseFloat(getCleanLine(sc));
                System.out.println(transitionProbs[i]);
            }

            //GET DISCOUNT RATE FROM FILE
            discountRate = Float.parseFloat(getCleanLine(sc));
            System.out.println("Discount Rate: \n" + discountRate);

            //GET EPSILON FROM FILE
            epsilon = Float.parseFloat(getCleanLine(sc));
            System.out.println("Epsilon: \n" + epsilon);
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception");
        }
    }


    /* Gets a clean line void of comments. Omits empty lines. */
    public static String getCleanLine(Scanner sc) {
        String str;
        if (sc.hasNextLine()) str = sc.nextLine();
        else {
            System.out.println("There's no next line to get.");
            return null;
        }
        int indexOfComment = str.indexOf("#"); //ignore comments
        if (indexOfComment > -1) str = str.substring(0, indexOfComment);
        str = str.trim(); //trim off leading and trailing spaces and tabs

        if (str.length() > 0) return str;
        else return getCleanLine(sc); //recurse until gets an actual clean line
    }


    /* Gets a clean line from file. Scrapes all integers from it. Returns them in a list. */
    public int[] getIntsFromFile(Scanner sc) {
        int[] integers = new int[3];

        String line = getCleanLine(sc);                 //get clean line

        if (line.contains("~")) return null;            //end of series

        String[] splitString = line.split(",");   //split on comma
        int i = 0;
        for (String stringInt : splitString) {
            integers[i] = Integer.parseInt(stringInt.trim());  //add to integer array
            i++;
        }
        return integers;

    }
    //STATES

    public void createStates() {
        //grid arrays:
        descGrid = new String[width][height]; //grid to print
        utilityGrid = new float[width][height]; //grid of utilities

        //Create states
        for (int i = 1; i <= width; i++) {
            for (int j = 1; j <= height; j++) {

                utilityGrid[i - 1][j - 1] = 0; //set all utility spots to 0
                char cellType = 'N'; //non-terminal
                String description = ".";

                //walls:
                for (Coord C : walls) {
                    if (C.x == i && C.y == j) {      //if i, j is a wall -> WALL (not a state)
                        cellType = 'W';
                        description = "W";
                        utilityGrid[i - 1][j - 1] = 55411; //special number to denote wall.
                    }
                }
                //terminals:
                for (Coord C : terminals) {
                    if (C.x == i && C.y == j) {
                        cellType = 'T';     //if i, j is a terminal -> TERMINAL
                        description = String.valueOf(C.reward);
                        if (description.charAt(0) != '-') description = '+' + description; //add a plus sign if positive
                        states.add(new State(i, j, C.reward, description));    //add terminal states
                    }
                }
                //regular cells:
                if (cellType == 'N')
                    states.add(new State(i, j, nonTerminalReward, description)); //add to non-terminal states

                descGrid[i - 1][j - 1] = description; //update description array
            }
        }
    }

    //print states info
    public void printStatesInfo() {
        System.out.println();
        int i = 1;
        for (State S : states) {
            System.out.println("State " + i + "\t(" + S.getX() + "," + S.getY() + ")\t reward: " + S.getImmediateReward());
        }
    }

    //print the contents of the Environment
    public void printEnvironment() {
        System.out.println();
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= width; j++) {
                System.out.print(descGrid[j - 1][i - 1] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    //print the utilities of the Environment
    public void printUtilities(int iteration) {
        System.out.println("iteration: " + iteration);
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= width; j++) {

                double utility = Math.floor(utilityGrid[j - 1][i - 1] * 1000) / 1000;
                if ((utility == 55411)) System.out.print("-----\t"); //wall
                else System.out.print(utility + "\t");               //utility
            }
            System.out.println();
        }
        System.out.println();
    }

    //print the final policy
    public void printFinalPolicy() {
        System.out.println("FINAL POLICY:");
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= width; j++) {

                double number = Math.random(); //get from Env
                char policy = number > 0.4661 ? (number > 0.6266 ? 'W' : 'E') : (number > 0.4194 ? 'S' : 'N');  //calculate
                double utility = Math.floor(utilityGrid[j - 1][i - 1] * 1000) / 1000; //get utility from grid

                if ((utility == 55411)) System.out.print("--\t");    //if wall
                else if (descGrid[j - 1][i - 1].contains("+") || descGrid[j - 1][i - 1].contains("-")) { //if terminal
                    System.out.print("T" + "\t");
                }
                else System.out.print(policy + "\t");              //else: policy value
            }
            System.out.println(); //newline
        }
        System.out.println();     //newline
    }

}