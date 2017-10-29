package decorator;

import java.io.*;

/**
 * Created by Greg on 10/29/2017.
 */
public class DecoratorTester
{
	public static void main(String[] args) throws IOException
	{
		String testString = "abcdefghijklmnopqrstuvwxyz";
		int length = testString.length();
		
		String fileName = "encryptedText.txt";
		
		System.out.println("Original string:");
		System.out.println(testString);
		System.out.println("");
		
		// Use encryption to write
		EncryptingWriter eWriter = new EncryptingWriter(new FileWriter(fileName));
		eWriter.write(testString.toCharArray(), 0, length);
		eWriter.close();
		
		FileReader fReader = new FileReader(fileName);
		BufferedReader bReader = new BufferedReader(fReader);
		
		// Read back encrypted data
		String fromFile = bReader.readLine();
		System.out.println("File contains:");
		System.out.println(fromFile);
		System.out.println("");
		
		bReader.close();
		fReader.close();
		
		// Extract data from file and decrypt
		char inChars[] = "--------------------------".toCharArray();
		DecryptingReader dReader = new DecryptingReader(new FileReader(fileName));
		dReader.read(inChars, 0, length);
		System.out.println("Data read back after decryption:");
		System.out.println(inChars);
	}
	
}
