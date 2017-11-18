package decorator; /**
 * Created by Greg on 10/29/2017.
 */
import java.io.Reader;
import java.io.IOException;

/**
 A decorator class for decrypting text after reading it.
 */
public class DecryptingReader extends Reader
{
	/**
	 Construct an decrypting reader that decorates a given reader
	 @param reader the reader to decorate
	 */
	public DecryptingReader(Reader reader)
	{
		this.reader = reader;
	}
	
	/**
	 Read the specified characters into a buffer
	 @param cbuf the buffer to read into
	 @param off the offset within the buffer to start reading
	 @param len the number of characters to read
	 */
	public int read(char[] cbuf, int off, int len) throws IOException
	{
		int result = reader.read(cbuf, off, len);
		
		String toDecrypt = new String(cbuf);
		
		StringCipher cipher = new StringCipher(3);
		
		toDecrypt = cipher.decrypt(toDecrypt);
		
		char[] decrypted = toDecrypt.toCharArray();
		System.arraycopy(decrypted, 0, cbuf, off, len);
		
		return result;
	}
	
	/**
	 Close the reader
	 */
	public void close() throws IOException
	{
		reader.close();
	}
	
	private Reader reader;
}
