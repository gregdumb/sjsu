import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Greg on 11/19/2017.
 */
public class EventDialog extends JDialog
{
	private final EventCalendar cal;
	
	private final JDialog thisDialog = this;
	
	private final JPanel panel;
	private final JTextArea eventText;
	private final JSpinner startTime;
	private final JSpinner endTime;
	private final JSpinner dateSpinner;
	
	private int INNER_WIDTH = 400;
	private int LABEL_WIDTH = 100;
	private int INPUT_HEIGHT = 20;
	private int BUTTON_HEIGHT = 50;
	
	private final Date NOW = new Date();
	private final Date UNIX_START = new Date(70, 0, 1);
	
	public EventDialog(EventCalendar cal) {
		
		this.cal = cal;
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				super.windowActivated(e);
				dateSpinner.setValue(cal.getTime());
			}
		});
		
		// Set up frame
		this.setTitle("Create a new event");
		this.setResizable(false);
		this.setPreferredSize(new Dimension(INNER_WIDTH + 20, 150));
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		// Input label
		JLabel eventInputLabel = new JLabel("Event:");
		eventInputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		eventInputLabel.setPreferredSize(new Dimension(LABEL_WIDTH, INPUT_HEIGHT));
		
		// Event input
		eventText = new JTextArea("Foo");
		eventText.setEditable(true);
		eventText.setPreferredSize(new Dimension(INNER_WIDTH - LABEL_WIDTH, INPUT_HEIGHT));
		
		// Start time label
		JLabel startTimeLabel = new JLabel("Start time:");
		startTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		startTimeLabel.setPreferredSize(new Dimension(LABEL_WIDTH, INPUT_HEIGHT));
		
		// End time label
		JLabel endTimeLabel = new JLabel("End time:");
		endTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		endTimeLabel.setPreferredSize(new Dimension(LABEL_WIDTH, INPUT_HEIGHT));
		
		// Date label
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setPreferredSize(new Dimension(LABEL_WIDTH, INPUT_HEIGHT));
		
		// Start time input
		startTime = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTime, "HH:mm");
		startTime.setEditor(startTimeEditor);
		startTime.setValue(UNIX_START);
		
		// End time input
		endTime = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTime, "HH:mm");
		endTime.setEditor(endTimeEditor);
		endTime.setValue(UNIX_START);
		
		// Date input
		dateSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy");
		dateSpinner.setEditor(dateEditor);
		dateSpinner.setValue(cal.getTime());
		
		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(INNER_WIDTH, BUTTON_HEIGHT));
		
		// Add button
		JButton addButton = new JButton("Add event");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateFormat df = new SimpleDateFormat("HH:mm");
				
				Date date = (Date) dateSpinner.getValue();
				Date startT;
				Date endT;
				try {
					startT = df.parse(df.format((Date) startTime.getValue())); //new Date();
					endT = df.parse(df.format((Date) endTime.getValue()));
				} catch(ParseException exception) {
					startT = new Date();
					endT = new Date();
				}
				
				System.out.println(startT.toString());
				System.out.println(endT.toString());
				
				String eventStr = eventText.getText();
				
				Event newEvent = new Event(date, eventStr, startT, endT);
				
				if(startT.after(endT)) {
					JOptionPane.showMessageDialog(thisDialog, "Start time must be before end time", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(eventStr.isEmpty()) {
					JOptionPane.showMessageDialog(thisDialog, "Please enter an event title", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!cal.eventConflicts(newEvent).equals("")) {
					JOptionPane.showMessageDialog(thisDialog, "Conflicts with: " + cal.eventConflicts(newEvent), "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					cal.addEvent(newEvent);
				}
			}
		});
		
		// Cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			thisDialog.setVisible(false);
		});
		
		
		// Add components\
		panel.add(eventInputLabel);
		panel.add(eventText);
		panel.add(startTimeLabel);
		panel.add(startTime);
		panel.add(dateLabel);
		panel.add(dateSpinner);
		panel.add(endTimeLabel);
		panel.add(endTime);
		
		buttonPanel.add(cancelButton);
		buttonPanel.add(addButton);
		
		panel.add(buttonPanel);
		
		this.add(panel);
		this.pack();
	
	}
}
