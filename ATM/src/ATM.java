/**
 * Representation of an ATM (no UI)
 * @author Greg Brisebois
 */

public class ATM
{
	private String id;
	private Bank bank;
	private int maxTransaction;
	
	public ATM(String newId, Bank newBank, int maxTrans)
	{
		id = newId;
		bank = newBank;
		maxTransaction = maxTrans;
	}
	
	/**
	 * Gets ATM ID
	 * @return id
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * Gets bank associated with this ATM
	 * @return the bank
	 */
	public Bank getBank()
	{
		return bank;
	}
	
	/**
	 * Get how much money you can take out at one time with this ATM
	 * @return max money
	 */
	public int getMaxTransaction()
	{
		return maxTransaction;
	}
}
