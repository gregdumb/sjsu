package eventcalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Greg on 9/5/2017.
 */
public class Event
{
	public Date date;
	public String title;

	private DateFormat eventDF = new SimpleDateFormat("MM/dd/yyyy k:mm");
	
	//private String startTime;
	public Date startTime;
	public Date endTime;
	
	public Event(Date d, String t)
	{
		date = d;
		title = t;
	}
	
	public Event(Date d, String t, Date start, Date end)
	{
		date = d;
		title = t;
		startTime = start;
		endTime = end;
	}

	public void draw()
	{
		String prettyDate = EventCalendarUI.inputDateFormat.format(date);
		String prettyStartTime = EventCalendarUI.inputTimeFormat.format(startTime);
		String prettyEndTime = EventCalendarUI.inputTimeFormat.format(endTime);

		UI.output("\t");
		UI.output(prettyDate);
		
		if(startTime != null)
			UI.output(" " + prettyStartTime);
		
		if(!prettyStartTime.equals(prettyEndTime))
			UI.output("-" + prettyEndTime);
		
		UI.output("\t\t");
		UI.outputln(title);
	}
	
	public String toString()
	{
		String prettyDate = UI.eventDateFormat.format(date);
		
		return prettyDate + "\t\t" + title;
	}
}
