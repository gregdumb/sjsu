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
		
		banks.get(0).addMember("Jimmy John", "1234", "hunter2", past, 4000);
		banks.get(0).addMember("Bruce Wayne", "5678", "batman", future, 1000000000000L);
		
		banks.get(1).addMember("Jessica Chavez", "2468", "yellow", future, 50000);
		banks.get(1).addMember("Captain Haddock", "1357", "whiskey", future, 3500000);
		//banks.get(1).addMember()

		UI.outputln("*****************************************************");
		UI.outputln("* FOR DEBUGGING/GRADING: All the bank users ");
		UI.outputln("* (This would of course not be in a real ATM)");

		for(Bank b : banks)
		{
			UI.outputln("*   " + b.getName() + ":");

			b.printMembers();
		}

		UI.outputln("*****************************************************\n");

		ATMUI atmInstance = new ATMUI(banks);
		
		atmInstance.start();
	}
}
