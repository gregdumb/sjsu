import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Greg on 9/7/2017.
 */
public class EventCalendarUI
{
	private final GregorianCalendar TODAY = new GregorianCalendar();
	private final String DIVIDER = "\n____________________________";
	
	public static final DateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	public static final DateFormat inputTimeFormat = new SimpleDateFormat("k:mm");
	public static final DateFormat dayDateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy");
	public static final DateFormat monthDateFormat = new SimpleDateFormat("MMMMM yyyy");
	
	EventCalendar cal;
	String drawMode;
	
	// Render calendar
	GregorianCalendar rc = new GregorianCalendar();
	
	public EventCalendarUI()
	{
		cal = new EventCalendar();
		drawMode = "m";
	}
	
	/**
	 * Loop that runs the menus
	 */
	public void run()
	{
		drawCalendar("m");

		while(true)
		{
			String choice = drawMainMenu();
			
			// MAIN MENU
			switch (choice)
			{
				case "l":
					drawLoadMenu();
					break;
				
				case "v":
					drawViewMenu();
					break;
				
				case "c":
					drawCreateMenu();
					break;
				
				case "g":
					drawGotoMenu();
					break;
				
				case "e":
					drawListMenu();
					break;
				
				case "d":
					drawDeleteMenu();
					break;
				
				case "q":
					cal.exportEvents();
					UI.output("Thank you for using the Gregle Calendar");
					System.exit(0);
					break;
				
				default:
					break;
			}
		}
	}

	/**
	 * Draws main menu
	 * @return user's selected choice
	 */
	private String drawMainMenu()
	{
		//drawCalendar();
		UI.outputln(DIVIDER);
		UI.outputln("Select one of the following options:");
		UI.outputln("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
		
		String[] valids = {"L", "V", "C", "G", "E", "D", "Q"};
		String choice = UI.promptChoice("", valids);
		
		return choice;
	}

	/**
	 * Draws menu to let user load a event file
	 */
	private void drawLoadMenu()
	{
		String filepath = UI.prompt("Enter your event file path: ");
		
		boolean success = cal.importEvents(filepath); //importEvents();
		
		if(success)
			UI.outputln("Loaded events successfully.");
		else
			UI.outputln("Error loading " + filepath);
		
		UI.pause();
	}
	
	/**
	 * Allows user to select between month/day view mode
	 */
	private void drawViewMenu()
	{
		UI.outputln("\nSelect one of the following view options:");
		UI.outputln("[D]ay  [M]onth");
		
		String[] valids = {"d", "m"};
		String choice = UI.promptChoice("", valids);
		
		switch(choice)
		{
			case "d":
				drawMode = "d";
				UI.outputln("Displaying by day");
				break;
			
			case "m":
				drawMode = "m";
				UI.outputln("Displaying by month");
				break;
		}

		choice = "";

		while(!choice.equals("m"))
		{
			drawCalendar();

			String[] valids2 = {"P", "N", "M"};
			choice = UI.promptChoice("[P]revious [N]ext [M]ain menu", valids2);

			if(choice.equals("p") || choice.equals("n"))
			{
				cal.next(drawMode, choice);
			}
		}
	}
	
	/**
	 * Allows user to create an event
	 */
	private void drawCreateMenu()
	{
		UI.outputln("");
		
		String title = UI.promptString("Enter the title of the event: ");
		Date date = UI.promptDate("Enter the date of the event (mm/dd/yyy): ", inputDateFormat);
		Date startTime = UI.promptDate("Enter the start time of the event (24:59): ", inputTimeFormat);
		Date endTime = null;
		
		boolean valid = false;
		
		while(!valid)
		{
			UI.outputln("Enter the end time of the event");
			endTime = UI.promptDate("(enter same time as start for no end time): ", inputTimeFormat);
			
			if(!endTime.before(startTime)) // Is not before, i.e. >=
			{
				valid = true;
			}
			else
			{
				UI.outputln("The end time must be after (or the same as) the start time.");
			}
		}
		
		Event e = new Event(date, title, startTime, endTime);

		String conflict = cal.eventConflicts(e);
		String cont = "y";

		if(!conflict.equals(""))
		{
			UI.outputln("New event conflicts with '" + conflict + "', continue adding?");
			String[] valids = {"Y", "N"};
			cont = UI.promptChoice("[Y/N] ", valids);
		}

		if(cont.equals("y"))
		{
			cal.addEvent(e);
			UI.outputln("Event added.");
		}
		else
		{
			UI.outputln("Adding event canceled");
		}
		UI.pause();
	}

	/**
	 * Draws menu that lets user select a date
	 */
	private void drawGotoMenu()
	{
		UI.outputln(DIVIDER);
		Date goDate = UI.promptDate("Enter a date (mm/dd/yyyy): ", inputDateFormat);
		
		cal.setTime(goDate);
		drawCalendar("d");
	}
	
	/** Lists all events */
	private void drawListMenu()
	{
		UI.outputln(DIVIDER);
		UI.outputln("All events");
		
		drawEvents(cal.getEvents());
		
		UI.outputln("");
		UI.pause();
	}

	/** Lets user delete events */
	private void drawDeleteMenu()
	{
		if(cal.getEvents().isEmpty())
		{
			UI.outputln("You don't have any events to delete.");
		}
		else
		{
			String[] valids = {"A", "S"};
			String choice = UI.promptChoice("Delete [A]ll or [S]elected day? ", valids);

			if(choice.equals("a"))
			{
				cal.deleteEvents();
			}
			else
			{
				cal.deleteEvents(cal.getTime());
			}

			UI.outputln("Events deleted");
		}
	}

	/**
	 * Draws calendar to the console
	 * @param type "m" or "d" for month or day
	 */
	private void drawCalendar(String type)
	{
		type = type.toLowerCase();
		
		switch(type)
		{
			case "m":
				drawMonth();
				break;
			
			case "d":
				drawDay();
				break;
			
			default:
				drawMonth();
				break;
		}
	}
	
	private void drawCalendar()
	{
		String type = drawMode.toLowerCase();
		
		drawCalendar(type);
	}
	
	private void drawDay()
	{
		UI.outputln(DIVIDER);

		UI.outputln(dayDateFormat.format(cal.getTime()));
		
		drawEvents(cal.getEvents(cal.getTime()));
		
		UI.outputln("");
	}

	/**
	 * Draws events
	 * @param events ArrayList of events to draw
	 */
	private void drawEvents(ArrayList<Event> events)
	{
		if(events == null || events.isEmpty())
		{
			UI.outputln("No events found.");
		}
		else
		{
			for(Event e : events)
			{
				e.draw();
			}
		}
	}
	
	/** Render calendar */
	private void drawMonth()
	{
		String monthstr = monthDateFormat.format(cal.getTime());
		
		UI.outputln(DIVIDER);
		UI.outputln(monthstr);
		UI.outputln(" S   M   T   W   T   F   S");
		
		rc.set(GregorianCalendar.MONTH, cal.get(GregorianCalendar.MONTH));
		rc.set(GregorianCalendar.DAY_OF_MONTH, 1);
		UI.output(getMonthStartBuffer(rc.get(GregorianCalendar.DAY_OF_WEEK)));
		
		int lastDay = rc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		for(int i = 1; i <= lastDay; i++)
		{
			rc.set(GregorianCalendar.DAY_OF_MONTH, i);
			
			UI.output(getDayOfMonth(i));
			
			if(rc.get(GregorianCalendar.DAY_OF_WEEK) == 7 && i < lastDay)
			{
				UI.outputln("");
			}
		}
		
		UI.output("\n\n");
	}
	
	/** Returns formatted day of month with spaces */
	private String getDayOfMonth(int day)
	{
		String daystr = Integer.toString(day);
		
		if(daystr.length() == 1) {
			daystr = daystr + " ";
		}
		
		// Check if today
		if(TODAY.getTime().equals(rc.getTime()))
		{
			daystr = "[" + daystr + "]";
		}
		// Check if date has any events
		else if(!cal.getEvents(rc.getTime()).isEmpty())
		{
			daystr = "{" + daystr + "}";
		}
		else
		{
			daystr = " " + daystr + " ";
		}
		
		return daystr;
	}

	/**
	 * Generates whitespace to fill in days of the week
	 * @param startingDay Day of the week of first day of month
	 * @return whitespace string
	 */
	private String getMonthStartBuffer(int startingDay)
	{
		String buffer = "";
		
		for(int i = 1; i < startingDay; i++) {
			buffer += "    ";
		}
		
		return buffer;
	}
}
