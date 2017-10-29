package observer;

import java.util.ArrayList;

/**
 * A class for testing an implementation of the Observer pattern.
 */
public class ObserverTest
{
	/**
	 * Creates a observer.DataModel and attaches barchart and textfield listeners
	 *
	 * @param args unused
	 */
	public static void main(String[] args)
	{
		ArrayList data = new ArrayList();
		
		data.add(new Double(33.0));
		data.add(new Double(44.0));
		data.add(new Double(22.0));
		data.add(new Double(22.0));
		
		DataModel model = new DataModel(data);
		
		TextFrame frame = new TextFrame(model);
		
		BarFrame barFrame = new BarFrame(model);
		
		model.attach(barFrame);
		model.attach(frame);
	}
}
