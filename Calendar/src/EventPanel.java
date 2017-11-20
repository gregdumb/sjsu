import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Greg on 11/18/2017.
 */
public class EventPanel extends JPanel
{
	private final EventCalendar cal;
	
	private final JTextArea eventTextArea;
	private final JButton newEventButton;
	
	private final EventDialog eventDialog;
	
	// UI stats
	private final int WIDTH = 300;
	
	
	public EventPanel(EventCalendar cal) {
		
		this.cal = cal;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(WIDTH, WIDTH));
		
		// Event text
		eventTextArea = new JTextArea();
		eventTextArea.setEditable(false);
		
		// Event dialog
		eventDialog = new EventDialog(cal);
		
		// New button
		newEventButton = new JButton("Create Event");
		
		newEventButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				eventDialog.setVisible(true);
			}
		});
		
		this.add(eventTextArea);
		this.add(newEventButton);
		
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		
		this.update();
	}
	
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
