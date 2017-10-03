import java.util.ArrayList;
import java.util.Date;

/**
 * Representation of a bank, contains data on users
 * @author Greg Brisebois
 */
public class Bank
{
	private String bankName;
	private ArrayList<Member> members = new ArrayList<>();
	
	Bank(String newName)
	{
		bankName = newName;
	}
	
	/**
	 * Print a list of the bank's members (for debugging only)
	 */
	public void printMembers()
	{
		for(Member m : members)
		{
			UI.outputln(m.getName() + " " + m.getCard());
		}
	}
	
	/**
	 * Add a member to the bank
	 * @param memberName name
	 * @param card card number
	 * @param password password
	 * @param date expiration date
	 * @param initalBalance initial balance
	 * @return member successfully added
	 */
	public boolean addMember(String memberName, String card, String password, Date date, long initalBalance)
	{
		members.add(new Member(memberName, bankName, card, password, date, initalBalance));
		
		return true;
	}
	
	/**
	 * Log in to an account
	 * @param card number of account
	 * @param password password
	 * @return member reference of account
	 */
	public Member authorize(String card, String password)
	{
		for(Member m : members)
		{
			if(card.equals(m.getCard()) && m.checkPassword(password))
				return m;
		}
		
		return null;
	}
	
	/**
	 * Check if a bank has a user with specified card number
	 * @param card to check
	 * @return true if has card
	 */
	public boolean hasCard(String card)
	{
		for(Member m : members)
		{
			if(card.equals(m.getCard())) return true;
		}
		
		return false;
	}
	
	/**
	 * Get name of bank
	 * @return name of bank
	 */
	public String getName()
	{
		return bankName;
	}
}
