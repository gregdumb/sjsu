package observer;

import java.util.ArrayList;
import javax.swing.event.*;

/**
 * A Subject class for the observer pattern.
 */
public class DataModel
{
	/**
	 * Constructs a observer.DataModel object
	 *
	 * @param d the data to model
	 */
	public DataModel(ArrayList d)
	{
		data = d;
		listeners = new ArrayList();
	}
	
	/**
	 * Constructs a observer.DataModel object
	 *
	 * @return the data in an ArrayList
	 */
	public ArrayList getData()
	{
		return (ArrayList) data.clone();
	}
	
	/**
	 * Attach a listener to the Model
	 *
	 * @param c the listener
	 */
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	/**
	 * Change the data in the model at a particular location
	 *
	 * @param location the index of the field to change
	 * @param value    the new value
	 */
	public void update(int location, double value)
	{
		data.set(location, new Double(value));
		for (int i = 0; i < listeners.size(); i++)
		{
			ChangeListener l = (ChangeListener) listeners.get(i);
			l.stateChanged(new ChangeEvent(this));
		}
	}
	
	ArrayList data;
	ArrayList listeners;
}
