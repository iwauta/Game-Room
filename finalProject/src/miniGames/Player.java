/** Required package class namespace */
package miniGames;

/** Required import statements */
import tools.Dialogs;

/**
 * Player.java - represents Player
 *
 * @author Utaha Iwai
 * @since 14-Dec-2022, 11:11:12 AM
 */
public class Player 
{

    // Properties.......
    private final String DEFAULT_NAME = "guest";
    private String name;
    private static double bet;
    private double score;
    private boolean win;
    
    /**
     * Default constructor, set class properties
     */
    public Player() {
        setName(DEFAULT_NAME);
        score = 2000;
        setStatus(false);
        bet   = 0;
    }
    
    /**
     * Constructor, sets class properties
     * 
     * @param name player's name to set 
     */
    public Player(String name) {
        setName(name);
        score = 2000;
        setStatus(false);
        bet   = 0;
    }
    
    
    /**
     * Mutator method. sets the name of the player
     * 
     * @param name player's name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Accessor method, gives the access to the name of the player
     * 
     * @return name of the player
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gives an access to the score
     * 
     * @return score (double)
     */
    public double getScore() {
        return score;
    }

    /**
     * Mutator method, add or subtract points from current score
     * 
     * @param points points to add
     */
    public void addScore(double points) {
        score += points;
    }
    
    /**
     * Accessor method, gives win value.
     * 
     * @return win(true) or lose(false)
     */
    public boolean getStatus() {
        return win;
    } 
    
    /**
     * Mutator method, sets win value.
     * 
     * @param winOrLose true(win) or false(lose)
     */
    public void setStatus(boolean winOrLose) {
        win = winOrLose;
    }    
    
    /**
     * Accessor method, gives the amount of bet
     * 
     * @return bet as a double
     */
    public double getBet() {
        return bet;
    }
    
    /**
     * User inputs the amount of points they want to bet in a range of 0.01 
     * and their current score.
     */
    public void inputBet() {
        double num = Dialogs.inputDouble
        ("You have " + score + "points.\n\nHow much do you want to bet?\n\n\t"
                + "Interest Rate: " + MiniGames.getRate(),
                0.01, score);
        bet = num;
    }
    
    /**
     * String representation of Player object
     *
     * @return The object represented as a String
     */
    @Override
    public String toString() {
        if (this == null) {
            return "---------------------------";
        }
        return "username: " + name + "     score: " + score;
    }
    
}