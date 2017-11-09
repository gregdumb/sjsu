import javax.swing.*;
import java.util.GregorianCalendar;

/**
 * Created by Greg on 11/9/2017.
 */
public class EventCalendarGUI {

	private final EventCalendar cal;

	// Render calendar
	private final GregorianCalendar rc;

	// GUI elements
	private final JFrame frame;
	private final JPanel panel;

	private final String FRAME_TITLE = "Gregle Calendar";

	public EventCalendarGUI() {

		rc = new GregorianCalendar();
		cal = new EventCalendar();

		// Set up GUI
		frame = new JFrame();
		frame.setTitle(FRAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		CalendarPanel calPanel = new CalendarPanel(cal);

		panel.add(calPanel);
		frame.add(panel);

		frame.pack();
		frame.setVisible(true);
	}

}
