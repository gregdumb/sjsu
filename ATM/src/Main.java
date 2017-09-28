import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Greg on 9/22/2017.
 */
public class Main
{
	public static DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	public static Date future, past;
	
	public static void main(String[] args)
	{
		try
		{
			future = df.parse("1/1/2018");
			past = df.parse("1/1/2017");
		}
		catch(ParseException e)
		{
		}
		
		ArrayList<Bank> banks = new ArrayList<>();
		banks.add(new Bank("Wells Fargo"));
		banks.add(new Bank("Bank of America"));
		
		banks.get(0).addMember("Jimmy John", "1234", "pwd", past);
		banks.get(0).addMember("Bruce Wayne", "5678", "batman", future);
		
		banks.get(1).addMember("Jessica Chavez", "2468", "hunter2", future);
		banks.get(1).addMember("Capitan ", "1357", "hunter2", future);
		//banks.get(1).addMember()
		
		ATMUI atmInstance = new ATMUI(banks);
		
		atmInstance.start();
	}
}
