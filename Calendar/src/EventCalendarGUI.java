import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	// GUI properties
	private final String FRAME_TITLE = "Gregle Calendar";

	public EventCalendarGUI() {

		rc = new GregorianCalendar();
		cal = new EventCalendar();
		
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

		JButton previousButton = new JButton("Previous");
		previousButton.addActionListener(e -> cal.next("m", "p"));
		
		// Next button
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(e -> cal.next("m", "n"));

		panel.add(previousButton);
		panel.add(calPanel);
		panel.add(nextButton);
		frame.add(panel);

		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		calPanel.draw();
	}
}
