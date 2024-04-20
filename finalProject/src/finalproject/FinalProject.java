/** Required package class namespace */
package finalproject;

/** Required import statements */
import collections.LinkedList;
import miniGames.MiniGames;
import miniGames.Player;
import miniGames.RSP;
import miniGames.NumberGuessing;
import tools.Dialogs;
import view.Home;
import view.RSPUI;
import view.RankUI;

/**
 * FinalProject - Final project for CS40S. User can play multiple mini games and
 * bet points to gain (if they win) or lose (if they lose). Introduces rank of
 * final scores.
 *
 * @author Utaha Iwai
 * @since 13-Dec-2022
 */
public class FinalProject {

    // global valuables................................................
    // Choice of games
    static final String CONTENTS[]
             = {"Rock Scissors Paper", // 0
                "Number Guessing", // 1
                "See the ranking", // 2
                "Quit"}; // 3

    public static Player player;     // Player object
    // Create a list of players
    public static LinkedList<Player> players = new LinkedList<>();
    // game objects
    public static RSP rsp;           // RSP object
    public static NumberGuessing gn;   // RSPBrickBreaker object

    /**
     * Main method for the project
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        welcome();
//        run();
    }

    /**
     * Welcome screen
     */
    public static void welcome() {
//        WelcomeUI welcomeUI = new WelcomeUI();   // welcome screen
        Home home = new Home();
    }

    /**
     * Initializes the propertie of the user.
     */
    public static void run() {
        // Create a Player object
        if (Dialogs.yesNo("Do you want to set your name?")) {
            String name = Dialogs.input("Input your name");
            player = new Player(name);    // Player sets name
        } else {
            player = new Player();       // Calls default constructor
        }
        Dialogs.output("Hello " + player.getName() + "!");
        play();
    }

    /**
     * User plays the game
     */
    public static void play() {
        rsp = null;
        gn = null;
        // User picks a game to play
        String toDo = Dialogs.choices("\nWhat would you like to do?\n", CONTENTS);
        // Starts the game
        if (toDo.equals(CONTENTS[0])) { // rsp
            rsp = new RSP();
            player.inputBet();                     // get user's bet
            RSPUI rspUI = new RSPUI();             // user interface for rsp
        } else if (toDo.equals(CONTENTS[1])) { // guessing number
            final String LEVEL[] = {"EASY", // 0
                                    "NORMAL", // 1
                                    "HARD"}; // 2
            int num = 0;
            String choice = Dialogs.choices("\nWhat would you like to do?\n",LEVEL);
            if      (choice.equals(LEVEL[0])) num = 0; // easy
            else if (choice.equals(LEVEL[1])) num = 1; // normal
            else if (choice.equals(LEVEL[2])) num = 2; // hard
            gn = new NumberGuessing(num);
            player.inputBet();                     // get user's bet
            numGuessing();
        } else if (toDo.equals(CONTENTS[2])) { // displays the rank
            System.out.println(addRank(player));
            showRank();
        } else if (toDo.equals(CONTENTS[3])) { // User wants to quit?
            if (Dialogs.yesNo("Are you sure you want to exit?")) {
                System.exit(0); // ends the program
            }
            play();
        }
    }

    /**
     * Adds or takes away points depending on the result of the game, displays
     * the current score.
     */
    public static void showResult() {
        String text = "";
        // Rock Scissors Paper
        if (rsp != null) {
            player.setStatus(rsp.didIWin());
        }
        // Number Guessing
        if (gn != null) {
            player.setStatus(gn.didIWin());
        }
        // Adds or subtracts points
        player.addScore 
        (MiniGames.bet(player.getStatus(), player.getBet()));
        // Outputs their result and the current score
        if (player.getStatus()) text += "YOU WIN!!";
        else                    text += "YOU LOSE!!";
        text += "\n\nYour current score: \n\t" + player.getScore();
        Dialogs.output(text);
        // Keep playing if the user doesn't want to see the rank
        if (!Dialogs.yesNo("Do you want to see the ranking?")) {
            play();
        } // Adds their final score to the list and display
        else {
            addRank(player);
            showRank();
        }
    }

    /**
     * Modifies the list of the score. Adds the current score so that the list
     * is in order of higher scores.
     *
     * @param player player to add to the list
     * @return true (data added to the list) or false (unable do add)
     */
    public static boolean addRank(Player player) {
        if (player == null) {
            return false;  // unvalid data
        } else { // valid data
            // The list is empty
            if (players.isEmpty()) {
                // Adds the player to the front of the list
                players.addFront(player);
                return true;   // Operation completed
            } // Traverse the list
            for (int i = 0; i < players.size(); i++) {
                if (player == players.get(i)){
                    // Player already exists on the list, replace the data
                    players.set(i, player);
                    return true;   // Operation completed
                }
            } // Traverse the list
            for (int i = 0; i < players.size(); i++) {
                // Compare the current player's score with ones on the list
                if (player.getScore() >= players.get(i).getScore()) {
                    // Add before if current score was greater than the one next
                    players.addBefore(player, i);
                    return true;   // Operation completed
                }
            } // Current score was the lowest so far
            if (player.getScore() < players.get(players.size()-1).getScore()) {
                // Adds to the tale of the list
                players.addBack(player);
                return true;   // Operation completed
            }
        }
        return false; // not able to add any data
    }

    /**
     * displays a ranking
     */
    public static void showRank() {
        RankUI ranking = new RankUI();
    }

    
    /**
     * runs the program for Number Guessing
     */
    private static void numGuessing() {
        NumberGuessing.run();
    }

}
