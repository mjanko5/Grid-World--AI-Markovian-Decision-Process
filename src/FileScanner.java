/**
 * Matt Jankowski
 * AI (CS 411) Hw 10
 * MDP GridWorld - FileScanner Class
 * To God be the Glory
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileScanner {



    public FileScanner(String pathName) {

        File file = new File(pathName);




        try { //try scanning from file
            Scanner sc = new Scanner(file);  // set file as scanner object

            //GET SIZE OF GRID FROM FILE
            int[] size = getIntsFromFile(sc);
            Main.width = size[0];
            Main.height = size[1];
            System.out.println("Width: " + Main.width + "\tHeight: " + Main.height);


            //GET WALL COORDINATES FROM FILE
            while(true) {
                int[] wall = getIntsFromFile(sc);
                if (wall == null){
                    break; //end of walls in file
                }
                Coord C = new Coord(wall[0], wall[1]);
                Main.walls.add(C);
            }

            //print
            System.out.println("Walls:");
            for (Coord C : Main.walls){
                System.out.println("(" + C.x + ", " + C.y + ")");
            }


            //GET TERMINAL COORDINATES FROM FILE
            while(true) {
                int[] terminal = getIntsFromFile(sc);
                if (terminal == null){
                    break; //end of terminals in file
                }

                Coord C = new Coord(terminal[0], terminal[1], terminal[2]);
                Main.terminals.add(C);
            }

            //print
            System.out.println("Terminals:");
            for (Coord C : Main.terminals){
                System.out.println("(" + C.x + ", " + C.y  + ", " + C.reward + ")");
            }


            //GET REWARD OF NON-TERMINAL STATES FROM FILE
            Main.nonTerminalReward = Float.parseFloat(getCleanLine(sc));
            System.out.println("Non-terminal Reward: \n" + Main.nonTerminalReward);


            //GET TRANSITION PROBABILITIES FROM FILE
            System.out.println("Transition Probabilities: ");
            for (int i = 0; i < 4; i++){
                Main.transitionProbs[i] = Float.parseFloat(getCleanLine(sc));
               System.out.println(Main.transitionProbs[i]);
            }


            //GET DISCOUNT RATE FROM FILE
            Main.discountRate = Float.parseFloat(getCleanLine(sc));
            System.out.println("Discount Rate: \n" + Main.discountRate);


            //GET EPSILON FROM FILE
            Main.epsilon = Float.parseFloat(getCleanLine(sc));
            System.out.println("Epsilon: \n" + Main.epsilon);


        }
        catch (FileNotFoundException e) {
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
        int indexOfComment = str.indexOf("#");
        if (indexOfComment > -1)   str = str.substring(0, indexOfComment);
        str = str.trim(); //trim off leading and trailing spaces and tabs

        if (str.length() > 0) return str;
        else return getCleanLine(sc); //recurse until gets an actual clean line
    }


    /* Gets a clean line from file. Scrapes all integers from it. Returns them in a list. */

    public int[] getIntsFromFile(Scanner sc){
        int[] integers = new int[3];

        String line = getCleanLine(sc);                 //get clean line

        if (line.contains("~"))     return null;        //end of series

        String[] splitString = line.split(",");   //split on comma
        int i = 0;
        for (String stringInt : splitString){
            integers[i] = Integer.parseInt(stringInt.trim());  //add to integer array
            i++;
        }
        return integers;
    }

}