import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Greg on 9/3/2017.
 */
public class Main
{
	public static void main(String[] args) {

		GregorianCalendar gc = new GregorianCalendar();

		Date foo = gc.getTime();
		String month = monthName(gc.get(GregorianCalendar.MONTH));

		System.out.println(month);
	}

	private static String monthName(int monthInt) {

		if(monthInt > 11 || monthInt < 0) {
			monthInt = 0;
		}

		return new DateFormatSymbols().getMonths()[monthInt];
	}
}
