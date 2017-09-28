/**
 * Created by Greg on 9/26/2017.
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
	
	public String getId()
	{
		return id;
	}
	
	public Bank getBank()
	{
		return bank;
	}
	
	public int getMaxTransaction()
	{
		return maxTransaction;
	}
}
