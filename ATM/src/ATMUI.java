import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Greg on 9/26/2017.
 */
public class ATMUI
{
	private ArrayList<ATM> atms = new ArrayList<>();
	private ATM selectedATM;
	
	ATMUI(ArrayList<Bank> banks)
	{
		// Create atms based on bank inputs
		for(Bank b : banks)
		{
			ATM atm1 = new ATM(b.getName() + " 1", b, 5000);
			ATM atm2 = new ATM(b.getName() + " 2", b, 2000);
			
			atms.add(atm1);
			atms.add(atm2);
		}
	}
	
	public void start()
	{
		UI.outputln("Welcome! The following ATMs are available:");
		
		for(int i = 1; i <= atms.size(); i++)
		{
			UI.outputln("  " + Integer.toString(i) + ") " + atms.get(i - 1).getId());
		}
		
		int selATM = UI.promptIntRange("Select an ATM by number (1-" + Integer.toString(atms.size()) + "): ", 1, atms.size()) - 1;
		selectedATM = atms.get(selATM);
		
		UI.outputln("Using ATM: " + selectedATM.getId());
		
		UI.outputln("Bank has members: ");
		selectedATM.getBank().printMembers();
		
		Member m = loginMenu(selectedATM.getBank());
		
		transactionMenu(m);
	}
	
	private Member loginMenu(Bank bank)
	{
		boolean valid = false;
		
		while(!valid)
		{
			String card = UI.promptString("Enter your card number: ");
			if(bank.hasCard(card))
			{
				String pwd = UI.promptString("Enter your password: ");
				Member m = bank.authorize(card, pwd);
				
				if(m != null)
				{
					if(m.getExpirationDate().after(new Date()))
					{
						UI.outputln("Welcome, " + m.getName());
						valid = true;
						return m;
					}
					else
					{
						DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
						UI.outputln("Account expired on " + df.format(m.getExpirationDate()));
					}
				}
				else
				{
					UI.outputln("Invalid password, card returned");
				}
			}
			else
			{
				UI.outputln("This bank does not recognize this number, card returned");
			}
		}
		
		return null;
	}
	
	private void transactionMenu(Member member)
	{
		boolean keepGoing = true;
		
		while(keepGoing)
		{
			UI.outputln("__________________________________");
			UI.outputln("Your current balance is " + makeMoneyString(member.getBalance()));
			
			//float fwithdraw = UI.promptFloat("Enter amount to withdraw: $");
			//int iwithdraw = Math.round(fwithdraw * 100);
			
			String samount = UI.promptString("Enter amount to withdraw or 'quit': $");
			
			
			if(samount.toLowerCase().equals("quit"))
			{
				UI.outputln("Goodbye, thank you for using ATM " + selectedATM.getId());
				keepGoing = false;
			}
			else if(stringIsFloat(samount))
			{
				float famount = Float.parseFloat(samount);
				int iamount = Math.round(famount * 100);
				
				if(iamount < 0) iamount = 0;
				
				if (selectedATM.getMaxTransaction() < iamount)
				{
					UI.outputln("You're trying to take too much money");
				}
				else if (member.withdraw(iamount))
				{
					UI.outputln("Withdrew " + makeMoneyString(iamount));
				}
				else
				{
					UI.outputln("You don't have enough money you pleb");
				}
			}
			else
			{
				UI.outputln("Not a valid choice, please try again");
			}
		}
	}
	
	public static boolean stringIsFloat(String s)
	{
		try
		{
			float x = Float.parseFloat(s);
		}
		catch(NumberFormatException n)
		{
			return false;
		}
		
		return true;
	}
	
	public static String makeMoneyString(long i)
	{
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n.format(i / 100.0);
	}
}
