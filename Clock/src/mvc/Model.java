package mvc;

import java.util.ArrayList;

/**
 * Created by Greg on 10/29/2017.
 */
public class Model
{
	private ArrayList<String> lines;
	
	public Model() {
		
		lines = new ArrayList<String>();
		
		lines.add("Test line");
	}
	
	public void addLine(String s) {
		
		lines.add(s);
	}
	
	public ArrayList<String> getLines() {
		
		return lines;
	}
}
