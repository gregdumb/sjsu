import eventcalendar.EventCalendar;

import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Greg on 9/3/2017.
 */
public class Main
{
	public static void main(String[] args) {

		EventCalendar myCal = new EventCalendar();

		myCal.run();
		//myCal.draw("month");
	}

	private static String monthName(int monthInt) {

		if(monthInt > 11 || monthInt < 0) {
			monthInt = 0;
		}

		return new DateFormatSymbols().getMonths()[monthInt];
	}
}
