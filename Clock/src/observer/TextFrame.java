package observer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * A class for displaying the model as a column of textfields in a frame.
 */
public class TextFrame extends JFrame implements ChangeListener
{
	/**
	 * Constructs a JFrame that contains the textfields containing the data
	 * in the model.
	 *
	 * @param d the model to display
	 */
	public TextFrame(DataModel d)
	{
		dataModel = d;
		
		final Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		ArrayList a = dataModel.getData();
		fieldList = new JTextField[a.size()];
		
		// A listener for action events in the text fields
		ActionListener l = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Figure out which field generated the event
				JTextField c = (JTextField) e.getSource();
				int i = 0;
				int count = fieldList.length;
				while (i < count && fieldList[i] != c)
					i++;
				
				String text = c.getText().trim();
				
				try
				{
					double value = Double.parseDouble(text);
					dataModel.update(i, value);
				} catch (Exception exc)
				{
					c.setText("Error.  No update");
				}
			}
		};
		
		final int FIELD_WIDTH = 11;
		for (int i = 0; i < a.size(); i++)
		{
			JTextField textField = new JTextField(FIELD_WIDTH);
			textField.setText(((Double) a.get(i)).toString());
			textField.addActionListener(l);
			contentPane.add(textField);
			fieldList[i] = textField;
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.show();
	}
	
	DataModel dataModel;
	JTextField[] fieldList;
	
	public void stateChanged(ChangeEvent e)
	{
		for (int i = 0; i < fieldList.length; i++)
		{
			fieldList[i].setText(dataModel.getData().get(i).toString());
		}
	}
}
