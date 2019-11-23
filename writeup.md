Matt Jankowski 2019
-------------------------------------------------------------
Readme file for Markovian Decision Process - AI Hw 10
-------------------------------------------------------------
Files: Main.java | Environment.java | MDP.java | State.java | Coord.java | mdp_input.txt | writeup.md

TO RUN: 

The data file (mdp_input.txt) can be modified to get different environment criteria:
 * In the file, tildas (~) denote the end of a list. 
 * Hashtags (#) are comments that are not run.
 * Everything else is grouped in sections (as is) to be scanned.
 
To run the program, put src code and data file into an IDE and run.

--------------------------------------------------------------
 
PROGRAM DESCRIPTION:

An agent moves across a 2D grid world. Each cell has a reward/penalty. Terminals allot the reward/penalty, but end the process.

The program:
First, it parses a text file for criteria and saves them in the Environment Class.
Then it runs MDP on this Environment using value iteration and the Bellman Equation.

Sample 7x4 Environment:
W = wall    +1,-1,-2 = terminals
 ___________________________
|___|___|___|___|_-1|___|___|
|___|_W_|_W_|___|___|___|_+1|
|___|___|_W_|___|_W_|___|_-2|
|___|___|___|___|___|___|___|

The MDP Algorithm finds a Final Policy for this Environment:
(the arrows indicate which direction is optimal for each cell)
 ____________________________
|_>_|_>_|_>_|_∨_|_-1|_>_|_∨_|
|_∧_|_W_|_W_|_>_|_>_|_>_|_+1|
|_∧_|_<_|_W_|_∧_|_W_|_∧_|_-2|
|_>_|_>_|_>_|_∧_|_<_|_<_|_<_|

// + Praise Jesus + //