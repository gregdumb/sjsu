package decorator;

/**
 * Created by Greg on 10/29/2017.
 * Applies an offset to a string, specified by 'shift'
 */
public class StringCipher
{
	int shift = 0;
	
	public StringCipher(int shift) {
		this.shift = shift;
	}
	
	/**
	 * Encrypts a string using caesar cypher
	 * @param msg string to encrypt
	 * @return encrpyted string
	 */
	String encrypt(String msg)
	{
		String s = "";
		int len = msg.length();
		for (int x = 0; x < len; x++)
		{
			char c = (char) (msg.charAt(x) + shift);
			if (c > 'z') s += (char) (msg.charAt(x) - (26 - shift));
			else s += (char) (msg.charAt(x) + shift);
		}
		return s;
	}
	
	/**
	 * Decrypts a string that was encrypted with a caesar cypher
	 * @param msg string to decrypt
	 * @return decrypted string
	 */
	String decrypt(String msg)
	{
		String s = "";
		int reverseShift = 26 - shift;
		int len = msg.length();
		for (int x = 0; x < len; x++)
		{
			char c = (char) (msg.charAt(x) + reverseShift);
			if (c > 'z') s += (char) (msg.charAt(x) - (26 - reverseShift));
			else s += (char) (msg.charAt(x) + reverseShift);
		}
		return s;
	}
}
