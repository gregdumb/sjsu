import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Greg on 11/9/2017.
 */
public class EventCalendarGUI implements ChangeListener {
	
	
	// Event calendar
	private final EventCalendar cal;

	// Render calendar
	private final GregorianCalendar rc;

	// GUI elements
	private final JFrame frame;
	private final JPanel panel;
	private final CalendarPanel calPanel;
	private final JTextArea eventArea;
	private final EventPanel eventPanel;

	// GUI properties
	private final String FRAME_TITLE = "Gregle Calendar";

	public EventCalendarGUI() {

		rc = new GregorianCalendar();
		cal = new EventCalendar();

		cal.importEvents("events.txt");
		
		// Attach to model
		cal.attach(this);

		// Set up GUI
		frame = new JFrame();
		frame.setTitle(FRAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Layout panel
		panel = new JPanel();

		// Calendar
		calPanel = new CalendarPanel(cal);

		// Event display
		eventArea = new JTextArea("Helloo");
		eventArea.setPreferredSize(new Dimension(300, 300));
		eventArea.setEditable(false);
		
		// Event panel
		eventPanel = new EventPanel(cal);

		panel.add(calPanel);
		//panel.add(eventArea);
		panel.add(eventPanel);
		frame.add(panel);

		frame.pack();
		frame.setVisible(true);

		stateChanged(null);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {

		ArrayList<Event> eventList = cal.getEvents(cal.getTime());

		String eventStr = "Events:\n";

		for(Event evt : eventList) {
			eventStr += evt.toAltString() + "\n";
		}

		if(eventList.isEmpty()) {eventStr += "No events today";}
		eventArea.setText(eventStr);
		
		calPanel.draw();
		eventPanel.update();
	}
}
