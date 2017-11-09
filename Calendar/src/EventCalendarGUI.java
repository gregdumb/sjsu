import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Greg on 11/9/2017.
 */
public class EventCalendarGUI {
	
	
	// Event calendar
	private final EventCalendar cal;

	// Render calendar
	private final GregorianCalendar rc;

	// GUI elements
	private final JFrame frame;
	private final JPanel panel;

	// GUI properties
	private final String FRAME_TITLE = "Gregle Calendar";

	public EventCalendarGUI() {

		rc = new GregorianCalendar();
		cal = new EventCalendar();

		// Set up GUI
		frame = new JFrame();
		frame.setTitle(FRAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Layout panel
		panel = new JPanel();

		// Calendar
		CalendarPanel calPanel = new CalendarPanel(cal);

		// Next button
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cal.next("m", "n");
				calPanel.draw();
			}
		});

		panel.add(calPanel);
		panel.add(nextButton);
		frame.add(panel);

		frame.pack();
		frame.setVisible(true);
	}

}
