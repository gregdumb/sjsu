package eventcalendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Greg on 9/3/2017.
 */
public class UI
{
	public static DateFormat eventDateFormat = new SimpleDateFormat("MM/dd/yyyy");

	public static void outputln(String text) {
		System.out.println(text);
	}

	public static void output(String text) {
		System.out.print(text);
	}

	public static String prompt(String text) {

		System.out.print(text);
		
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

		return s;
	}
	
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
	
	public static String promptTime(String text)
	{
		String s = "";
		boolean valid = false;
		
		while(!valid)
		{
			s = prompt(text);
			valid = isValidTime(s);
			
			if(!valid)
			{
				outputln("Must be a 24h time in the format of 12:24");
				//outputln("Must be a date in the format of mm/dd/yyyy");
			}
		}
		
		return s;
	}

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
				outputln("Please enter a date in the format " + dateFormat.toString());
			}
		}

		return returnDate;
	}
	
	/** Prompts for input and checks for validity with given array of possible choices */
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
	
	public static int promptInt(String text) {
		System.out.print(text);
		
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		
		return s;
	}
	
	public static void pause()
	{
		output("Press Enter to continue...");
		prompt("");
	}
	
	public static boolean isValidTime(String t)
	{
		DateFormat df = new SimpleDateFormat("k:mm");
		
		try
		{
			df.parse(t);
			return true;
		}
		catch ( ParseException exc )
		{
		}
		
		return false;
	}

	public static boolean isValidDate(String d)
	{
		try
		{
			eventDateFormat.parse(d);
			output("$$$ Date was valid");
			return true;
		}
		catch ( ParseException exc )
		{
		}

		return false;
	}
}
