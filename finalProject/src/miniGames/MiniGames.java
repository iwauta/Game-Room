/** Required package class namespace */
package miniGames;

 
/**
 * MiniGames.java - abstract method. general structure of mini games.
 *
 * @author Utaha Iwai
 * @since 13-Dec-2022, 12:01:40 PM
 */
public abstract class MiniGames 
{
    // Properties.....
    protected static double rate;
    
    /**
     * Default constructor, set class properties
     */
    public MiniGames(double rate) {
        setRate(rate);
    }
    
    /**
     * User's bet gets increased or lost depending on the result of the game.Bet
     * gets multiplied by the interest rate if the user wins, the user loses bet
     * if they loses.
     *
     *
     * @param winOrLose result of the game (true for win, false for lose)
     * @param bet amount of the before the game
     * @return bet after the game
     */
    public static double bet(boolean winOrLose, double bet) {
        double points = bet;
        if(winOrLose == true) points *= rate;
        else                  points *= -1;        
        return points;
    }
    
    /**
     * Mutator method. sets the interest rate
     * 
     * @param rate rate to set
     */
    protected static void setRate(double rate) {
        MiniGames.rate = rate;
    }
        
    /**
     * Accessor method for the interest rate.
     * 
     * @return interest rate for the game
     */
    public static double getRate() {
        return rate;
    }
    
    /**
     * Determines if the user wins or loses
     * 
     * @return user wins (true) or user loses/ties (false)
     */
    public abstract boolean didIWin();
    
}