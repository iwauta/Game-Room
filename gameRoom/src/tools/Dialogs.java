
/** Required package class namespace */
package tools;

/** Required imports */
import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

 
/**
 * Dialogs.java - a collection of useful methods for working with dialogs
 *
 * @author Mr. Wachs
 * @since Nov. 21, 2019, 3:49:53 p.m.
 */
public class Dialogs 
{
    
    // Static (shared) private constants below.................................
    
    private static final  int    DEFAULT_FONT_SIZE   = 12;
    private static final  int    DEFAULT_FONT_TYPE   = Font.PLAIN;
    private static final  int    DEFAULT_STYLE       = JOptionPane.PLAIN_MESSAGE;
    private static final  int    DEFAULT_RED_COLOR   = 238;
    private static final  int    DEFAULT_GREEN_COLOR = 238;
    private static final  int    DEFAULT_BLUE_COLOR  = 238;
    private static final  String DEFAULT_TITLE       = "U Room";
    private static final  String DEFAULT_FONT_FACE   = "Consolas"; 
    
    private static final  String ERROR_1 = "Error, please try again\n\n";
    private static final  String ERROR_2 = "Error, invalid number\n\n";
    private static final  String ERROR_3 = "Error, number not inside range\n\n";
        
    private static final Font DEFAULT_FONT = new Font(
            DEFAULT_FONT_FACE,
            DEFAULT_FONT_TYPE,
            DEFAULT_FONT_SIZE);
    private static final Color DEFAULT_BACKGROUND = new Color(
            DEFAULT_RED_COLOR, 
            DEFAULT_GREEN_COLOR,
            DEFAULT_BLUE_COLOR);
    private static final Color DEFAULT_FOREGROUND = new Color(
            0,
            0,
            0);
    
    /** The title set at the top of the dialogs */
    public static String title = DEFAULT_TITLE;
    
    /** The style (use an icon or not) of the dialogs */
    public static int style = DEFAULT_STYLE;
    
    /** The font used when displaying text in the dialogs */
    public static Font font = DEFAULT_FONT;
    
    /** The background color used in the dialogs */
    public static Color background = DEFAULT_BACKGROUND;
    
    /** The foreground color (text color) used in the dialogs */
    public static Color foreground = DEFAULT_FOREGROUND;
        
    
    /**
     * Ask the user a yes and no question, in a confirm dialog box
     * 
     * @param text the yes no question to ask in the dialog
     * @return true (yes), false (no)
     */
    public static boolean yesNo(String text) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = formatArea(text);
        // Store the user's response in a variable from what they clicked on
        // when the dialog appears only showing "yes" and "no" buttons for 
        // the user to choose from  
        int response = JOptionPane.showConfirmDialog(null,area,title,
                JOptionPane.YES_NO_OPTION,style);
        if (response == JOptionPane.YES_OPTION) return true;
        else                                    return false;
    }
    
    /**
     * Asks the user for a string input in a input dialog box
     * 
     * @param text the text for the dialog box
     * @return a valid string
     */
    public static String input(String text) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = formatArea(text);
        // Store the user's response in a variable from what they typed into
        // a input dialog
        String value = JOptionPane.showInputDialog(null,area,title,style);
        // Force a loop if the user left the dialog empty and clicked "ok" or
        // they clicked "cancel" or the "X"
        while (value == null || value.equals("")) {
            // Recreate the graphical display area
            area  = formatArea(ERROR_1 + text);
            value = JOptionPane.showInputDialog(null,area,title,style);
        }
        return value;           // Once they have entered a value, return it
    }
    
    /**
     * Asks the user for a number (integer) in a input dialog box
     * 
     * @param text the text for the dialog box
     * @return a valid integer
     */
    public static int inputInteger(String text) {
        String value = input(text);     // Get user's response
        // Force a loop if the user did not enter a valid integer
        while (Numbers.isInteger(value) == false) {
            value = input(ERROR_1 + text);
        }
        int number = Integer.parseInt(value);       // Convert to integer
        return number;                              // Return number value       
    }
    
    /**
     * Asks the user for a number (double) in a input
     * dialog box
     * 
     * @param text the text for the dialog box
     * @return a valid integer
     */
    public static double inputDouble(String text) {
        String value = input(text);     // Get user's response
        // Force a loop if the user did not enter a valid double
        while (Numbers.isDouble(value) == false) {
            value = input(ERROR_1 + text);
        }
        double number = Double.parseDouble(value);       // Convert to double
        return number;                                   // Return number value
    }
    
    /**
     * Asks the user for a number (integer) in a input dialog box
     * 
     * @param text the text for the dialog box
     * @param minimum the lowest value in the input range
     * @param maximum the highest value in the input range
     * @return a valid integer
     */
    public static int inputInteger(String text, int minimum, int maximum) {
        String value = input(text);                 // Get user's response
        int number = 0;                             // Variable to store value
        boolean done = false;                       // Flag to keep trying
        while (!done) {                             // Loop until valid
            if (Numbers.isInteger(value) == false) {    // Not a valid integer
                value = input(ERROR_2 + text);      // Input again with message
            }
            else {
                number = Integer.parseInt(value);   // Convert to integer
                if (Numbers.inRange(number, minimum, maximum)) {
                    done = true;                    // Number is in range
                }
                else {
                    value = input(ERROR_3 + text); // Not in range, input again
                }
            }            
        }
        return number;                          // Return valid integer
    }
    
    /**
     * Asks the user for a number (double) in a input dialog box
     * 
     * @param text the text for the dialog box
     * @param minimum the lowest value in the input range
     * @param maximum the highest value in the input range
     * @return a valid double
     */
    public static double inputDouble(String text, int minimum, int maximum) {
        String value = input(text);                 // Get user's response
        double number = 0d;                         // Variable to store value
        boolean done = false;                       // Flag to keep trying
        while (!done) {                             // Loop until valid
            if (Numbers.isDouble(value) == false) {    // Not a valid double
                value = input(ERROR_2 + text);      // Input again with message
            }
            else {
                number = Double.parseDouble(value);   // Convert to double
                if (Numbers.inRange(number, minimum, maximum)) {
                    done = true;                    // Number is in range
                }
                else {
                    value = input(ERROR_3 + text); // Not in range, input again
                }
            }            
        }
        return number;                              // Return valid double       
    }
    
    /**
     * Asks the user for a number (double) in a input dialog box
     * 
     * @param text the text for the dialog box
     * @param minimum the lowest value in the input range
     * @param maximum the highest value in the input range
     * @return a valid double
     */
    public static double inputDouble(String text, double minimum, double maximum) {
        String value = input(text);                 // Get user's response
        double number = 0d;                         // Variable to store value
        boolean done = false;                       // Flag to keep trying
        while (!done) {                             // Loop until valid
            if (Numbers.isDouble(value) == false) {    // Not a valid double
                value = input(ERROR_2 + text);      // Input again with message
            }
            else {
                number = Double.parseDouble(value);   // Convert to double
                if (Numbers.inRange(number, minimum, maximum)) {
                    done = true;                    // Number is in range
                }
                else {
                    value = input(ERROR_3 + text); // Not in range, input again
                }
            }            
        }
        return number;                              // Return valid double       
    }
    
    /**
     * Outputs text in a more visual graphical dialog 
     * 
     * @param text the text to display
     */
    public static void output(String text) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = formatArea(text);
        // Add the display area to the dialog to show the user
        JOptionPane.showMessageDialog(null,area,title,style);       
    }
        
    /**
     * Outputs text in a more visual graphical dialog 
     * 
     * @param text the text to display
     * @param imageName the name of the image to display
     */
    public static void output(String text, String imageName) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = formatArea(text);
        // Create a icon picture from the name (and path) to an image file
        Icon picture = new ImageIcon(imageName);
        // Add the display area and icon to the dialog to show the user
        JOptionPane.showMessageDialog(null,area,title,style,picture);        
    }
        
    /**
     * Sets up a fancy display area for the text to display
     * 
     * @param text the string of text to format
     * @return the formatted text area for display
     */
    public static JTextArea formatArea(String text) {
        // Create a graphics object to display the graphical things
        JTextArea area = new JTextArea();
        // Assign the global variable font, colors, and passed text to the area
        area.setFont(font);
        area.setBackground(background);
        area.setForeground(foreground);
        area.setText(text);
        return area;
    }
        
    /**
     * Ask the user if they want to play again, in a dialog box
     * 
     * @return true (yes, play again), false (no)
     */
    public static boolean playAgain() {
        // Send the yesNo method the play again message
        return yesNo("Do you want to play again?");
    }
            
    /**
     * Presents an input dialog with a drop down selection of options
     * 
     * @param text the text to show in the dialog
     * @param options the array of option to have in the drop down
     * @return the option they choose
     */
    public static String choices(String text, String[] options) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = formatArea(text);
        // Add display area to dialog to show user which gives a drop-down
        // in the dialog and returns an "object"
        Object object = JOptionPane.showInputDialog(null, area, title, 
                                             style, null, options, options[0]);
        if (object == null)  return "";                 // User selects "X"
        else                 return object.toString();  // Return user's choice
    } 
    
    
    
}