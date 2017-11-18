import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * General CLUI class
 * @author Greg Brisebois
 * @version 1.0
 */
public class UI
{
	public static DateFormat eventDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	public static final DateFormat timeFormat = new SimpleDateFormat("k:mm");
	
	/**
	 * Display output text with newline at end
	 * @param text to display
	 */
	public static void outputln(String text) {
		System.out.println(text);
	}
	
	/**
	 * Display output text on single line
	 * @param text to display
	 */
	public static void output(String text) {
		System.out.print(text);
	}
	
	/**
	 * Prompt user to input anything (can be empty string)
	 * @param text prompt text
	 * @return string entered by user
	 */
	public static String prompt(String text) {

		System.out.print(text);
		
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

		return s;
	}
	
	/**
	 * Prompt user to input a string
	 * @param text prompt text
	 * @return string entered by user
	 */
	public static String promptString(String text)
	{
		String s = "";
		
		while(s.trim().isEmpty())
		{
			System.out.print(text);
			
			// Get input
			Scanner sc = new Scanner(System.in);
			s = sc.nextLine();
			
			if(s.trim().isEmpty())
			{
				System.out.println("You can't leave this blank.");
			}
		}
		
		return s;
	}
	
	/**
	 * Prompt user to enter a date (or time)
	 * @param text prompt text
	 * @param dateFormat format of date they have to enter
	 * @return the date they entered
	 */
	public static Date promptDate(String text, DateFormat dateFormat)
	{
		Date returnDate = null;
		String s = "";
		boolean valid = false;

		while(!valid)
		{
			s = prompt(text);
			
			try
			{
				returnDate = dateFormat.parse(s);
				valid = true;
			}
			catch ( ParseException exc )
			{
				outputln("Please enter a date/time in the format specified ");
			}
		}

		return returnDate;
	}
	
	/**
	 * Prompt user to enter from a selection of choices
	 * @param text prompt text
	 * @param choices array of possible things they can enter
	 * @return string entered by user
	 */
	public static String promptChoice(String text, String[] choices)
	{
		// Show prompt
		System.out.print(text);
		boolean valid = false;
		String s = "";
		
		while(!valid)
		{
			// Get input
			Scanner sc = new Scanner(System.in);
			String raw = sc.nextLine();
			s = raw.toLowerCase();
			
			// Check validity
			for (String c : choices)
			{
				c = c.toLowerCase();
				
				if(c.equals(s))
				{
					valid = true;
					break;
				}
			}
			
			if(!valid)
			{
				System.out.print("'" + raw + "' is not a valid entry, please try again: ");
			}
		}
		
		return s;
	}
	
	/**
	 * Prompt user to enter an integer
	 * @param text prompt text
	 * @return integer they entered
	 */
	public static int promptInt(String text) {
		System.out.print(text);
		
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		
		return s;
	}
	
	/**
	 * Pause the CLUI and wait for user to press enter
	 */
	public static void pause()
	{
		output("Press Enter to continue...");
		prompt("");
	}
}
