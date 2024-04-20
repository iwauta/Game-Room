/** Required package class namespace */
package miniGames;

import finalproject.FinalProject;
import tools.Dialogs;
import tools.Numbers;

 
/**
 * NumberGuessing.java - main structure of Numer Guessing. User gets different 
 * number of guesses corresponds to a level they choose
 *
 * @author u.iwai
 * @since 2023/01/27, 12:52:55
 */
public class NumberGuessing extends MiniGames
{

    // Properties......
    private static final double EASY_RATE = 1.2;
    private static final double NORMAL_RATE = 1.5;
    private static final double HARD_RATE = 1.8;
    private static int count;
    private static int number;
    private static int userInput;
    // Range of the rangom number
    private static final int MIN = 1;
    private static final int MAX = 20;
    
    /**
     * Constructor, set class properties
     * 
     * @param num use's choice of level 0(easy) or 1(normal) or 2(hard)
     */
    public NumberGuessing(int num) {
        super(1);
        // Sets the rate depanding on the level
        if(num == 0){
            super.setRate(EASY_RATE);
            count = 7;
        }
        else if(num == 1){
            super.setRate(NORMAL_RATE);
            count = 5;
        }
        else{
            super.setRate(HARD_RATE);
            count = 3;
        }
        number = 0;
        userInput = 0;
    }
    
    /**
     * Main logic of the game
     */
    public static void run() {
        // Generates a random number between MIN and MAX as an opponent's choice
        number = Numbers.random(MIN, MAX);
        String text = "\tEnter your guess\n\tRange: " + MIN 
                + " - " + MAX + "\n\n\t" + count + " guess left";
        String text2= "";
        userInput = Dialogs.inputInteger(text, MIN, MAX);
        // user guessed incorrectly
        while(count >= 1 && userInput != number) {
            String result = "Your guess: " + userInput;
            if (userInput > number) text2 = "\n\nYour guess is too big!\n";
            else                    text2 = "\n\nYour guess is too small!\n";
            userInput = Dialogs.inputInteger(result + text2 + text, MIN, MAX);
            count --;
        }
        // user guessed correctly or used up their guesses
        FinalProject.showResult();
    }
    
    /**
     * Determines if the user wins or loses
     * 
     * @return user wins (true) or user loses (false)
     */
    @Override
    public boolean didIWin() {
        String result = "Your guess: " + userInput;
        // user guessed correctly
        if(userInput == number){
            Dialogs.output(result + "\n\nCongrats! You guessed right!");
            return true;
        }
        // user didn't guess correctly
        Dialogs.output(result + "\n\nYou have no guesses left!\n\nThe number was: " + number);
        return false;
    }
    
}