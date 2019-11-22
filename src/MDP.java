/**
 * Matt Jankowski
 * AI (CS 411) Hw 10
 * MDP GridWorld - MDP Class
 * To God be the Glory
 */

import java.util.ArrayList;

public class MDP {
//    private ArrayList<State> states = new ArrayList<State>();
    private char[] actions = {'L', 'R', 'U', 'D'};
    private float delta;
//    Environment E?????

    public MDP(Environment Env) {

    }

    //calculate delta... starts at 0, becomes bigger and bigger
    public void updateDelta(State s){
        // if |U  [s] − U [s]| > δ then δ ← |U  [s] − U [s]|

        float utilityDifference = Math.abs(s.getNextUtility() - s.getUtility());
        if (utilityDifference > delta)  delta = utilityDifference; //update
    }

    public void valueIteration(Environment Env){
        float epsilon = Env.getEpsilon();
        float gamma = Env.getDiscountRate();


        do {
            delta = 0;

            for (State state : Env.states) {

                state.setUtility(state.getNextUtility());          //update utility
                state.setNextUtility(bellmanEquation(state, Env)); //call Bellman Equation for every state (cell)
                updateDelta(state);
            }
//            for ( int i = 1; i < Env.getWidth(); i++){
//                for (int j = 1; j < Env.getHeight(); j++){
//                    System.out.println();
//                }
//            }

//            Collections.sort(users, new Comparator<User>() {
//                @Override
//                public int compare(User u1, User u2) {
//                    return u1.getCreatedOn().compareTo(u2.getCreatedOn());
//                }
//            });


//          // printout the iterations results...
            int i = 1;
            for (State S : Env.states){
//                System.out.println(S.getX() + "," + S.getY());
                System.out.print(S.getDescription());
                if (Env.getHeight() == i){
                    System.out.println();
                    i = 0;
                }

                i++;
            }


//            System.out.println("delta: " + delta);
//            System.out.println("eqn: " + epsilon * (1 - gamma) / gamma);

        } while (delta >= epsilon * (1 - gamma) / gamma);

//        printout policy....

    }

    //BELLMAN EQUATION: find utility for the state (cell)
    public float bellmanEquation(State state, Environment Env){
        float nextUtility = state.getImmediateReward() + getFutureDiscountedReward(state, Env);
        return nextUtility;
    }


    //GET MAX FUNCTION
    public float getFutureDiscountedReward(State current, Environment Env){
        float[] sums = {0,0,0,0};
        for (int i = 0; i < 4; i++){ //for every action
            for (State state : Env.states){ //for every state (j)
                sums[i] += current.getUtility() * transitionFunction(Env, current, actions[i], state);
            }                                                                   //      ^one of the four actions...
        }
        return Env.getDiscountRate() * maxOfFour(sums[0], sums[1], sums[2], sums[3]);
    }




    //TRANSITION FUNCTION
    public float transitionFunction(Environment Env, State current, char action, State next) {
        //include boundary check here too.

        if (action == 'U'){
            if      ( match (next, current.moveUp(Env)) )    return 0.8f;
            else if ( match (next, current.moveLeft()) )     return 0.1f;
            else if ( match (next, current.moveRight(Env)) ) return 0.1f;
            else                                             return 0.0f;
        }
        else if (action == 'D'){
            if      ( match (next, current.moveDown()) )     return 0.8f;
            else if ( match (next, current.moveLeft()) )     return 0.1f;
            else if ( match (next, current.moveRight(Env)) ) return 0.1f;
            else                                             return 0.0f;
        }
        else if (action == 'L'){
            if      ( match (next, current.moveLeft()) )     return 0.8f;
            else if ( match (next, current.moveUp(Env)) )    return 0.1f;
            else if ( match (next, current.moveDown()) )     return 0.1f;
            else                                             return 0.0f;
        }
        else if (action == 'R'){
            if      ( match (next, current.moveRight(Env)) ) return 0.8f;
            else if ( match (next, current.moveUp(Env)) )    return 0.1f;
            else if ( match (next, current.moveDown()) )     return 0.1f;
            else                                             return 0.0f;
        }
        else {
            System.out.println("Shouldn't happen");
            return -1;
        }


    }

    //check if the coordinates of a state and coord match
    public boolean match(State S, Coord C){
        if (S.getX() == C.x && S.getY() == C.y) return true;
        else return false;
    }

    //return the max of 4 floats
    public float maxOfFour(float a, float b, float c, float d){
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

}