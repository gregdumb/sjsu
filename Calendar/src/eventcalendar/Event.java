package eventcalendar;

import java.util.Date;

/**
 * Created by Greg on 9/5/2017.
 */
public class Event
{
	public Date date;
	public String title;
	
	private String startTime;
	
	public Event(Date d, String t)
	{
		date = d;
		title = t;
	}
}
