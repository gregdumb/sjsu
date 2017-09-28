import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Greg on 9/26/2017.
 */
public class Bank
{
	private String bankName;
	private ArrayList<Member> members = new ArrayList<>();
	
	Bank(String newName)
	{
		bankName = newName;
	}
	
	public void printMembers()
	{
		for(Member m : members)
		{
			UI.outputln(m.getName() + " " + m.getCard());
		}
	}
	
	public boolean addMember(String memberName, String card, String password, Date date)
	{
		long amount = 4000000000L;
		members.add(new Member(memberName, bankName, card, password, date, 4000000000L));
		
		return true;
	}
	
	public Member authorize(String card, String password)
	{
		for(Member m : members)
		{
			if(card.equals(m.getCard()) && m.checkPassword(password))
				return m;
		}
		
		return null;
	}
	
	public boolean hasCard(String card)
	{
		for(Member m : members)
		{
			if(card.equals(m.getCard())) return true;
		}
		
		return false;
	}
	
	public String getName()
	{
		return bankName;
	}
}
