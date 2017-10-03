import java.util.Date;

/**
 * Member of a bank, contains user information and balance
 * @author Greg Brisebois
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

	/**
	 * Get password, VERY INSECURE FOR DEBUGGING ONLY!!!
	 * @return password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Get name
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Get bank of user
	 * @return bank
	 */
	public String getBank()
	{
		return bank;
	}
	
	/**
	 * Get card number
	 * @return card number
	 */
	public String getCard()
	{
		return card;
	}
	
	/**
	 * Check a user's password
	 * @param pwd password to check
	 * @return true if is correct password
	 */
	public boolean checkPassword(String pwd)
	{
		return pwd.equals(password);
	}
	
	/**
	 * Get expiration date of user's account
	 * @return expiration date
	 */
	public Date getExpirationDate()
	{
		return expires;
	}
	
	/**
	 * Get user's balance
	 * @return balance
	 */
	public long getBalance()
	{
		return balance;
	}
	
	/**
	 * Withdraw money from user's account
	 * @param amount amount to withdraw
	 * @return true if withdraw was successful
	 */
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
