/**
 * Homework #1: A calendar that you can store events on
 * Copyright Gregory Brisebois 2017
 */

package eventcalendar;

import java.io.*;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class EventCalendar
{
	/** Constants */
	private final GregorianCalendar TODAY = new GregorianCalendar();
	private final String FILE_PATH = "src/eventcalendar/file.txt";
	private final String DIVIDER = "\n____________________________";
	
	/** Used for event manipulation */
	private GregorianCalendar gc;
	
	/** Used for rendering */
	private GregorianCalendar rc;
	private DateFormat dateFormat;
	
	/** Can be m(onth) or d(ay) */
	private String drawMode;
	
	private ArrayList<Event> events;
	
	public EventCalendar()
	{
		//Constructor
		gc = new GregorianCalendar();
		rc = new GregorianCalendar();
		dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		drawMode = "M";
		
		events = new ArrayList<Event>();
	}
	
	/**********************************************
	 * ACCESSORS
	 */
	
	public GregorianCalendar getCal()
	{
		return gc;
	}
	
	public int get(int input)
	{
		return gc.get(input);
	}
	
	public Date getTime()
	{
		return gc.getTime();
	}
	
	public void setTime(Date newDate)
	{
		gc.setTime(newDate);
	}
	
	public ArrayList<Event> getEvents()
	{
		return getEvents(null);
	}
	
	/**
	 * Get events from the calendar
	 * @param date Date to look for events on
	 * @return
	 */
	public ArrayList<Event> getEvents(Date date)
	{
		if(date != null)
		{
			ArrayList<Event> returnEvents = new ArrayList<>();
			DateFormat seekFormat = new SimpleDateFormat("MM/dd/yyyy");
			String desiredDay = seekFormat.format(date);
			
			for(Event e : events)
			{
				String eventDay = seekFormat.format(e.date);
				if(eventDay.equals(desiredDay))
				{
					returnEvents.add(e);
				}
			}
			
			return returnEvents;
		}
		else
		{
			return events;
		}
	}
	
	/**********************************************
	 * MUTATORS
	 */
	
	public boolean addEvent(Event e)
	{
		events.add(e);
		
		return true;
	}
	/*
	private String drawMainMenu()
	{
		drawCalendar();
		
		UI.outputln("Select one of the following options:");
		UI.outputln("[L]oad  [V]iew by  [C]reate  [G]o to  [E]vent list  [D]elete  [Q]uit");
		
		String[] valids = {"L", "V", "C", "G", "E", "D", "Q"};
		String choice = UI.promptChoice("", valids);
		
		return choice;
	}
	
	private void drawLoadMenu()
	{
		UI.outputln("Loading event list from '" + FILE_PATH + "'...");
		boolean success = importEvents();
		
		if(success)
			UI.outputln("Loaded events successfully.");
		else
			UI.outputln("Error loading events.");
		
		UI.pause();
		
		//File file = new File(FILE_PATH);
		//UI.outputln("File exists: " + Boolean.toString(file.exists()));
	}
	
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
	
	private void drawCreateMenu()
	{
		UI.outputln("");
		
		String title = UI.promptString("Enter the title of the event: ");

		// @TODO Add support for date inputting
		Event e = new Event(gc.getTime(), title);
		
		events.add(e);
		
		UI.outputln("Event added.");
		UI.pause();
	}

	private void drawGotoMenu()
	{
		UI.outputln(DIVIDER);
		String goDate = "";//UI.promptDate("Enter a date (mm/dd/yyyy): ");

		try
		{
			Date newDate = UI.eventDateFormat.parse(goDate);
			gc.setTime(newDate);
			UI.outputln("Date has been updated");
		}
		catch (ParseException e)
		{
			UI.output("Date parsing exception");
		}
	}

	/** Lists all events
	private void drawListMenu()
	{
		UI.outputln(DIVIDER);
		UI.outputln("All events");

		if(events.isEmpty())
			UI.outputln("You have no events scheduled.");
		else
			drawEvents();

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
		
		String weekday = weekdayNameFromInt(gc.get(GregorianCalendar.DAY_OF_WEEK));
		String month = monthNameFromInt(gc.get(GregorianCalendar.MONTH));
		String day = Integer.toString(gc.get(GregorianCalendar.DAY_OF_MONTH));
		String year = Integer.toString(gc.get(GregorianCalendar.YEAR));
		
		UI.outputln(weekday + ", " + month + " " + day + ", " + year);
		//UI.outputln("No events for this day.\n");

		drawEvents(gc.getTime());

		UI.outputln("");
	}
	
	/** Render calendar
	private void drawMonth()
	{
		String monthstr = "";//getMonth();
		
		UI.outputln(DIVIDER);
		UI.outputln(monthstr);
		UI.outputln(" S   M   T   W   T   F   S");

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
	}*/

	/** Returns formatted day of month with spaces */
	/*
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
	}*/

	/** Returns month string from our calendar */
	public String getMonthStr()
	{
		return monthNameFromInt(rc.get(GregorianCalendar.MONTH));
	}

	/** Converts 0-11 int to month string */
	public static String monthNameFromInt(int monthInt) {

		if(monthInt > 11 || monthInt < 0) {
			monthInt = 0;
		}

		return new DateFormatSymbols().getMonths()[monthInt];
	}
	
	public static String weekdayNameFromInt(int weekdayInt)
	{
		if(weekdayInt > 7 || weekdayInt < 1)
		{
			weekdayInt = 1;
		}
		
		return new DateFormatSymbols().getWeekdays()[weekdayInt];
	}
	
	/** Loads events from events.txt */
	public boolean importEvents()
	{
		try
		{
			FileReader freader = new FileReader("src/eventcalendar/file.txt");
			
			BufferedReader breader = new BufferedReader(freader);
			String line = "";
			
			while((line = breader.readLine()) != null) {
				Event e = parseEventString(line);
				events.add(e);
			}
			
			freader.close();
		}
		catch (IOException e)
		{
			return false;
		}
		catch (ParseException e)
		{
			UI.outputln("FATAL ERROR PARSING FILE");
			return false;
		}
		
		return true;
	}
	
	public boolean exportEvents()
	{
		try{
			PrintWriter writer = new PrintWriter("src/eventcalendar/file.txt", "UTF-8");
			
			for(Event e : events)
			{
				writer.println(encodeEventString(e));
			}
			
			writer.close();
			return true;
		}
		catch (IOException e) {
			UI.outputln("FATAL ERROR OUTPUTTING FILE");
		}
		
		return false;
	}
	
	private Event parseEventString(String s) throws ParseException
	{
		DateFormat df = EventCalendarUI.inputDateFormat;
		DateFormat tf = EventCalendarUI.inputTimeFormat;
		String[] elements = s.split("%%%");
		
		String title = elements[0];
		Date date = df.parse(elements[1]);
		Date startTime = tf.parse(elements[2]);
		Date endTime =  tf.parse(elements[3]);
		
		return new Event(date, title, startTime, endTime);
	}
	
	private String encodeEventString(Event e)
	{
		DateFormat df = EventCalendarUI.inputDateFormat;
		DateFormat tf = EventCalendarUI.inputTimeFormat;
		
		String title = e.title;
		String date = df.format(e.date);
		String startTime = tf.format(e.startTime);
		String endTime = tf.format(e.endTime);
		
		return title + "%%%" + date + "%%%" + startTime + "%%%" + endTime;
	}

	/* Draws all events */
	private void drawEvents()
	{
		drawEvents(null);
	}

	private void drawEvents(Date date)
	{
		String desiredDay = "";

		if(date != null)
			desiredDay = UI.eventDateFormat.format(date);

		for(Event e : events)
		{
			if(date != null)
			{
				String eventDay = UI.eventDateFormat.format(e.date);
				if(eventDay.equals(desiredDay))
				{
					e.draw();
				}
			}
			else
			{
				e.draw();
			}
		}
	}
}
