import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Homework #4
 * EventCalendarGUI
 * A GUI for the EventCalendar from the first homework assignment
 */
public class EventCalendarGUI implements ChangeListener {
	
	// Event calendar, the "model" of the program
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
		frame.setResizable(false);

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
		panel.add(eventPanel);
		frame.add(panel);

		frame.pack();
		frame.setVisible(true);
		
		// Save events on close
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				super.windowClosing(e);
				
				cal.exportEvents();
			}
		});

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
