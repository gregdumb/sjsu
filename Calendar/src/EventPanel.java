import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * EventPanel
 * Custom JPanel that shows a list of events on the current day, with buttons to add an event
 */
public class EventPanel extends JPanel
{
	private final EventCalendar cal;
	
	private final JTextArea eventTextArea;
	private final JButton newEventButton;
	
	private final EventDialog eventDialog;
	
	// UI stats
	private final int WIDTH = 300;
	private final int HEIGHT = 400;
	
	
	public EventPanel(EventCalendar cal) {
		
		this.cal = cal;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// Event text
		eventTextArea = new JTextArea();
		eventTextArea.setEditable(false);
		
		// Event dialog
		eventDialog = new EventDialog(cal);
		
		// New button
		newEventButton = new JButton("Create Event");
		
		newEventButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eventDialog.setVisible(true);
			}
		});
		
		// Close button
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(e -> {
			cal.exportEvents();
			
			System.exit(0);
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		this.add(eventTextArea);
		buttonPanel.add(newEventButton);
		buttonPanel.add(closeButton);
		this.add(buttonPanel);
		
		this.update();
	}
	
	/**
	 * Updates the view in case the model changed
	 */
	public void update() {
		
		ArrayList<Event> eventList = cal.getEvents(cal.getTime());
		
		String eventStr = "Events:\n";
		
		for(Event evt : eventList) {
			eventStr += evt.toAltString() + "\n";
		}
		
		if(eventList.isEmpty()) {eventStr += "No events today";}
		
		eventTextArea.setText(eventStr);
	}
}
