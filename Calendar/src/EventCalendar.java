/**
 * Homework #1: A calendar that you can store events on
 * @author Greg Brisebois
 * @version 1.0
 */

import java.io.*;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

public class EventCalendar
{
	/**********************************************
	 * VARIABLES
	 */
	
	/** Constants */
	private final GregorianCalendar TODAY = new GregorianCalendar();
	public static final String DIV = "%%%";
	
	/** Used for event manipulation */
	private GregorianCalendar gc;
	private ArrayList<Event> events;
	private String eventFilePath = "events.txt";
	
	/**
	 * Constructor
	 */
	public EventCalendar()
	{
		//Constructor
		gc = new GregorianCalendar();
		events = new ArrayList<>();
	}
	
	/**********************************************
	 * ACCESSORS
	 */
	
	/**
	 * Get the gregorian calendar referring to the day we are on
	 * @return The GregorianCalendar
	 */
	public GregorianCalendar getCal()
	{
		return gc;
	}
	
	/**
	 * Passthrough to the GregorianCalendar get() method
	 * @param input Input code
	 * @return Desired value from calendar
	 */
	public int get(int input)
	{
		return gc.get(input);
	}
	
	/**
	 * Passthrough to GregorianCalendar getTime() method
	 * @return Date from our gregorian Calendar
	 */
	public Date getTime()
	{
		return gc.getTime();
	}
	
	/**
	 * Set the time of our GregorianCalendar
	 * @param newDate new date/time
	 */
	public void setTime(Date newDate)
	{
		gc.setTime(newDate);
	}
	
	/**
	 * Get events from the calendar
	 * @return ArrayList of all events
	 */
	public ArrayList<Event> getEvents()
	{
		return getEvents(null);
	}
	
	/**
	 * Get events from the calendar
	 * @param date Date to look for events on
	 * @return ArrayList of events on that date
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

	public String eventConflicts(Event toCheck)
	{
		for(Event e : events)
		{
			if(toCheck.conflicts(e))
			{
				return e.title;
			}
		}

		return "";
	}
	
	/**********************************************
	 * MUTATORS
	 */

	/**
	 * Increments or decrements the current day/month
	 * @param dayMonth pass "m" for month or "day" for date
	 * @param direction pass "n" for next or "p" for previous
	 */
	public void next(String dayMonth, String direction)
	{
		int toModify = 0;

		int dir = (direction.equals("n")) ? 1 : -1;

		if(dayMonth.equals("m"))
			toModify = GregorianCalendar.MONTH;
		else
			toModify = GregorianCalendar.DAY_OF_YEAR;

		int current = gc.get(toModify);

		gc.set(toModify, (current + dir));
	}


	/**
	 * Adds an event to the calendar
	 * @param e New event
	 * @return success/fail
	 */
	public boolean addEvent(Event e)
	{
		events.add(e);
		sortEvents();
		return true;
	}

	/**
	 * Deletes all events from the calendar
	 * @return success
	 */
	public boolean deleteEvents()
	{
		events.clear();
		return true;
	}

	/**
	 * Deletes events on selected date
	 * @param date selected date
	 * @return success
	 */
	public boolean deleteEvents(Date date)
	{
		DateFormat seekFormat = new SimpleDateFormat("MM/dd/yyyy");
		String desiredDay = seekFormat.format(date);
		ArrayList<Event> toRemove = new ArrayList<>();

		for(Event e : events)
		{
			String thisDay = seekFormat.format(e.date);

			if(thisDay.equals(desiredDay))
			{
				toRemove.add(e);
			}
		}

		events.removeAll(toRemove);

		return true;
	}
	
	/**
	 * Converts Calendar month int to a string
	 * @param monthInt Integer of month
	 * @return String of month
	 */
	public static String monthNameFromInt(int monthInt) {

		if(monthInt > 11 || monthInt < 0) {
			monthInt = 0;
		}

		return new DateFormatSymbols().getMonths()[monthInt];
	}
	
	/**
	 * Converts Calendar weekday int to a string
	 * @param weekdayInt Integer of weekday
	 * @return String of weekday
	 */
	public static String weekdayNameFromInt(int weekdayInt)
	{
		if(weekdayInt > 7 || weekdayInt < 1)
		{
			weekdayInt = 1;
		}
		
		return new DateFormatSymbols().getWeekdays()[weekdayInt];
	}
	
	/**
	 * Loads events from file
	 * @param path Path to file
	 * @return success
	 */
	public boolean importEvents(String path)
	{
		try
		{
			FileReader freader = new FileReader(path);
			BufferedReader breader = new BufferedReader(freader);
			String line = "";
			
			while((line = breader.readLine()) != null) {
				Event e = parseEventString(line);
				events.add(e);
			}

			Collections.sort(events);
			
			eventFilePath = path;
			
			freader.close();
			return true;
		}
		catch (FileNotFoundException e)
		{
			UI.outputln("File could not be found");
		}
		catch (IOException e)
		{
			UI.outputln("Error reading file");
		}
		catch (ParseException e)
		{
			UI.outputln("FATAL ERROR PARSING FILE");
		}
		
		return false;
	}
	
	/**
	 * Writes events to file
	 * @return
	 */
	public boolean exportEvents()
	{
		if(!eventFilePath.equals(""))
		{
			try
			{
				PrintWriter writer = new PrintWriter(eventFilePath, "UTF-8");
				
				for (Event e : events)
				{
					writer.println(encodeEventString(e));
				}
				
				writer.close();
				return true;
			}
			catch (IOException e)
			{
				UI.outputln("FATAL ERROR OUTPUTTING FILE");
			}
		}
		else
		{
			UI.outputln("Error saving file, no path found");
		}
		
		return false;
	}

	/**********************************************
	 * UTILITIES
	 */

	/**
	 * Sorts all events
	 */
	private void sortEvents()
	{
		Collections.sort(events);
	}

	/**
	 * Converts a string of format title%%%date%%%starttime%%%endtime into an event
	 * @param s string to convert
	 * @return created event
	 * @throws ParseException
	 */
	private Event parseEventString(String s) throws ParseException
	{
		DateFormat df = EventCalendarUI.inputDateFormat;
		DateFormat tf = EventCalendarUI.inputTimeFormat;
		String[] elements = s.split(DIV);
		
		String title = elements[0];
		Date date = df.parse(elements[1]);
		Date startTime = tf.parse(elements[2]);
		Date endTime =  tf.parse(elements[3]);
		
		return new Event(date, title, startTime, endTime);
	}

	/**
	 * Turns an event into a string of the form title%%%date%%%starttime%%%endtime
	 * @param e event to convert
	 * @return converted string
	 */
	private String encodeEventString(Event e)
	{
		DateFormat df = EventCalendarUI.inputDateFormat;
		DateFormat tf = EventCalendarUI.inputTimeFormat;
		
		String title = e.title.replace("%%%", "");
		String date = df.format(e.date);
		String startTime = tf.format(e.startTime);
		String endTime = tf.format(e.endTime);
		
		return title + DIV + date + DIV + startTime + DIV + endTime;
	}
}
