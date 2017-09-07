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
	
	private String startTime;
	
	public Event(Date d, String t)
	{
		date = d;
		title = t;
	}

	public void draw()
	{
		String prettyDate = UI.eventDateFormat.format(date);

		UI.output("\t");
		UI.output(prettyDate);
		UI.output("\t\t");
		UI.outputln(title);
	}
}
