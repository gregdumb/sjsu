package eventcalendar;

import java.text.DateFormat;
import java.text.ParseException;
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
					// @TODO THIS IS VERY BAD
					cal.exportEvents();
					break;
				
				case "q":
					UI.output("Thank you for using the Gregle Calendar");
					System.exit(0);
					break;
				
				default:
					break;
			}
		}
	}
	
	private String drawMainMenu()
	{
		drawCalendar();
		
		UI.outputln("Select one of the following options:");
		UI.outputln("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
		
		String[] valids = {"L", "V", "C", "G", "E", "D", "Q"};
		String choice = UI.promptChoice("", valids);
		
		return choice;
	}
	
	// @TODO does nothing at the moment
	private void drawLoadMenu()
	{
		//UI.outputln("Loading event list from '" + FILE_PATH + "'...");
		boolean success = cal.importEvents(); //importEvents();
		
		if(success)
			UI.outputln("Loaded events successfully.");
		else
			UI.outputln("Error loading events.");
		
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
		UI.outputln("Enter the end time of the event");
		Date endTime = UI.promptDate("(enter same time as start for no end time): ", inputTimeFormat);
		
		Event e = new Event(date, title, startTime, endTime);
		
		cal.addEvent(e);
		
		UI.outputln("Event added.");
		UI.pause();
	}
	
	private void drawGotoMenu()
	{
		UI.outputln(DIVIDER);
		Date goDate = UI.promptDate("Enter a date (mm/dd/yyyy): ", inputDateFormat);
		
		cal.setTime(goDate);
		UI.outputln("Date has been updated");
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
		
		String weekday = cal.weekdayNameFromInt(cal.get(GregorianCalendar.DAY_OF_WEEK));
		String month = cal.monthNameFromInt(cal.get(GregorianCalendar.MONTH));
		String day = Integer.toString(cal.get(GregorianCalendar.DAY_OF_MONTH));
		String year = Integer.toString(cal.get(GregorianCalendar.YEAR));
		
		//UI.outputln(weekday + ", " + month + " " + day + ", " + year);
		UI.outputln(dayDateFormat.format(cal.getTime()));
		
		drawEvents(cal.getEvents(cal.getTime()));
		
		UI.outputln("");
	}
	
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
		if(TODAY.get(GregorianCalendar.DATE) == rc.get(GregorianCalendar.DATE))
		{
			daystr = "[" + daystr + "]";
		}
		else
		{
			daystr = " " + daystr + " ";
		}
		
		return daystr;
	}
	
	private String getMonthStartBuffer(int startingDay)
	{
		String buffer = "";
		
		for(int i = 1; i < startingDay; i++) {
			buffer += "    ";
		}
		
		return buffer;
	}
}
