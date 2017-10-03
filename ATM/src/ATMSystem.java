import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Main class that starts ATMs
 * @author Greg Brisebois
 */
public class ATMSystem
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
		{} // They will for sure work
		
		ArrayList<Bank> banks = new ArrayList<>();
		banks.add(new Bank("Wells Fargo"));
		banks.add(new Bank("Bank of America"));
		
		banks.get(0).addMember("Jimmy John", "1234", "pwd", past, 4000);
		banks.get(0).addMember("Bruce Wayne", "5678", "batman", future, 1000000000000L);
		
		banks.get(1).addMember("Jessica Chavez", "2468", "yellow", future, 50000);
		banks.get(1).addMember("Capitan Haddock", "1357", "hunter2", future, 3500000);
		//banks.get(1).addMember()
		
		ATMUI atmInstance = new ATMUI(banks);
		
		atmInstance.start();
	}
}
