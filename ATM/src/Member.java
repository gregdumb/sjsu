import java.util.Date;

/**
 * Created by Greg on 9/26/2017.
 */
public class Member
{
	private String name;
	private String bank;
	private String card;
	private String password;
	private Date expires;
	private long balance;
	
	Member(String n, String b, String c, String p, Date e, long money)
	{
		name = n;
		bank = b;
		card = c;
		password = p;
		expires = e;
		balance = money;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getBank()
	{
		return bank;
	}
	
	public String getCard()
	{
		return card;
	}
	
	public boolean checkPassword(String pwd)
	{
		return pwd.equals(password);
	}
	
	public Date getExpirationDate()
	{
		return expires;
	}
	
	public long getBalance()
	{
		return balance;
	}
	
	public boolean withdraw(long amount)
	{
		if(amount > balance)
			return false;
		else
		{
			balance -= amount;
			return true;
		}
	}
}
