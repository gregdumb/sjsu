/**
 * Created by Greg on 9/5/2017.
 */

package eventcalendar;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import eventcalendar.UI;

public class EventCalendar
{
	private GregorianCalendar gc;
	private DateFormat dateFormat;

	public EventCalendar()
	{
		//Constructor

		gc = new GregorianCalendar();
		dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	}

	public void draw(String type)
	{
		String monthstr = getMonth();

		UI.outputln(monthstr);
		UI.outputln("S  M  T  W  T  F  S");

		gc.set(GregorianCalendar.DAY_OF_MONTH, 1);
		UI.output(getMonthStartBuffer(gc.get(GregorianCalendar.DAY_OF_WEEK)));

		for(int i = 1; i <= gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++)
		{
			UI.output(getDayOfMonth(i));
			//UI.output(date.toString() + ' ');

			gc.set(GregorianCalendar.DAY_OF_MONTH, i);

			//UI.output(Integer.toString(gc.get(GregorianCalendar.DAY_OF_WEEK)));

			if(gc.get(GregorianCalendar.DAY_OF_WEEK) == 7)
			{
				UI.outputln("");
			}
		}
	}

	/** Returns formatted day of month with spaces */
	private String getDayOfMonth(int day) {
		String daystr = Integer.toString(day);

		if(daystr.length() == 1) {
			daystr = daystr + " ";
		}

		return daystr + " ";
	}

	private String getMonthStartBuffer(int startingDay)
	{
		String buffer = "";

		for(int i = 1; i < startingDay; i++) {
			buffer += "   ";
		}

		return buffer;
	}

	/** Returns month string from our calendar */
	private String getMonth()
	{
		return monthNameFromInt(gc.get(GregorianCalendar.MONTH));
	}

	/** Converts 0-11 int to month string */
	public static String monthNameFromInt(int monthInt) {

		if(monthInt > 11 || monthInt < 0) {
			monthInt = 0;
		}

		return new DateFormatSymbols().getMonths()[monthInt];
	}
}
