package decorator;

/**
 * Created by Greg on 10/29/2017.
 */
import java.io.Writer;
import java.io.IOException;

public class EncryptingWriter extends Writer
{
	/**
	 Construct an encrypting writer that decorates a given writer
	 @param writer the writer to decorate
	 */
	public EncryptingWriter(Writer writer)
	{
		this.writer = writer;
	}
	
	
	public void write(char[] cbuff, int off, int len) throws IOException
	{
		StringCipher cipher = new StringCipher(3);
		
		String toEncrypt = new String(cbuff);
		
		toEncrypt = cipher.encrypt(toEncrypt);
		
		char[] toWrite = toEncrypt.toCharArray();
		
		writer.write(toWrite, off, len);
	}
	
	public void close() throws IOException
	{
		writer.close();
	}
	
	/**
	 Flush the writer
	 */
	public void flush() throws IOException
	{
		writer.flush();
	}
	
	private Writer writer;
}