/** Required package class namespace */
package miniGames;

/** Required import statements */
import finalproject.FinalProject;
import tools.Dialogs;
import tools.Numbers;
import view.RSPUI;

 
/**
 * RSP.java - main structure of Rock Scissors Paper
 *
 * @author Utaha Iwai
 * @since 14-Dec-2022, 11:58:13 AM
 */
public class RSP extends MiniGames
{

    // Properties......
    private static int opp;
    private static int user;
    private static final double RATE = 1.2;
    
    /**
     * Default constructor, set class properties
     */
    public RSP() {
        super(RATE);
        opp = getOpponentChoice();
    }
    
    /**
     * Generates a random number between 1 and 3 as an opponent's choice
     * 
     * @return random number between 1 and 3
     */
    private static int getOpponentChoice() {
        return Numbers.random(1, 3);
    }
    
    /**
     * Determines if the user wins or loses
     * 
     * @return user wins (true) or user loses (false)
     */
    @Override
    public boolean didIWin() {
        user = RSPUI.getUserChoice(); 
        if      (user == 1 && opp == 2) return true; // user-rock, opp-scissors
        else if (user == 2 && opp == 3) return true; // user-scissors, opp-paper
        else if (user == 3 && opp == 1) return true; // user-paper, opp-rock
        return false;                                // user loses
    }

    /**
     * Outputs the opponent's hand and the user's hand. If they tie up forces
     * the user to play again.
     */
    public static void result() {
        String u = "";
        String o = "";
        user = RSPUI.getUserChoice();     
        if (user == 1) u = "ROCK";
        if (user == 2) u = "SCISSORS";
        if (user == 3) u = "PAPER";
        if (opp == 1)  o = "ROCK";
        if (opp == 2)  o = "SCISSORS";
        if (opp == 3)  o = "PAPER";
        String text = "You picked: " + u + "\nBot picked: " + o;
        // Outputs result
        Dialogs.output(text);
        // Ties up, force the user to play again
        if (user == opp) { 
            Dialogs.output("\n\tYou tied! Try again!");
            user = 0;
            opp = getOpponentChoice();
            RSPUI rspUI = new RSPUI();
        }
        // Game set, displays result
        else FinalProject.showResult();
    }
    

    
}