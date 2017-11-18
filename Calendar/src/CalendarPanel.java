import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Greg on 11/9/2017.
 */
public class CalendarPanel extends JPanel {
	
	// Date formats
	public static final DateFormat monthDateFormat = new SimpleDateFormat("MMMMM yyyy");
	
	private GregorianCalendar rc;
	private EventCalendar cal;

	// UI Properties
	private final int DAY_WIDTH = 60;
	private final int DAY_HEIGHT = 50;
	private final int DAY_SPACING = 10;
	
	private final int MONTH_LABEL_SIZE = 20;
	private final int MONTH_LABEL_HEIGHT = 40;

	private final int BUTTON_PANEL_HEIGHT = 30;
	
	private JPanel dayGridPanel;
	private JLabel monthLabel;

	public CalendarPanel(EventCalendar cal) {

		this.rc = new GregorianCalendar();
		this.cal = cal;

		int calWidth = 7 * DAY_WIDTH + 6 * DAY_SPACING;
		int calHeight = 6 * DAY_HEIGHT + 5 * DAY_SPACING + 10;
		int totalHeight = calHeight + MONTH_LABEL_HEIGHT + BUTTON_PANEL_HEIGHT;

		// Container panel
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(calWidth, totalHeight));
		this.setAlignmentX(RIGHT_ALIGNMENT);
		
		// Grid panel
		JPanel dayGridWrapper = new JPanel();
		dayGridWrapper.setPreferredSize(new Dimension(calWidth, calHeight));
		dayGridWrapper.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		dayGridPanel = new JPanel();
		dayGridPanel.setLayout(new GridLayout(0, 7, DAY_SPACING, DAY_SPACING));
		dayGridWrapper.add(dayGridPanel);

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setPreferredSize(new Dimension(calWidth, BUTTON_PANEL_HEIGHT));
		buttonPanel.setMinimumSize(new Dimension(calWidth, BUTTON_PANEL_HEIGHT));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red));

		// Next button
		JButton nextButton = new JButton("Next >>");
		nextButton.addActionListener(e -> cal.next("m", "n"));

		// Previous button
		JButton previousButton = new JButton("<< Previous");
		previousButton.addActionListener(e -> cal.next("m", "p"));

		// Month label
		monthLabel = new JLabel("", SwingConstants.LEFT);
		monthLabel.setFont(new Font(monthLabel.getFont().getName(), Font.PLAIN, MONTH_LABEL_SIZE));
		monthLabel.setPreferredSize(new Dimension(calWidth, MONTH_LABEL_HEIGHT));
		monthLabel.setBorder(BorderFactory.createLineBorder(Color.blue));

		// Add components
		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);

		this.add(monthLabel);
		this.add(dayGridWrapper);
		this.add(buttonPanel);
		
		this.draw();
	}

	public void draw() {
		// Clear old grid
		dayGridPanel.removeAll();

		// Update Render Calendar
		rc.set(GregorianCalendar.MONTH, cal.get(GregorianCalendar.MONTH));
		rc.set(GregorianCalendar.YEAR, cal.get(GregorianCalendar.YEAR));
		rc.set(GregorianCalendar.DAY_OF_MONTH, 1);
		
		// Draw blank spaces on grid
		for(int i = 1; i < rc.get(GregorianCalendar.DAY_OF_WEEK); i++) {
			//JLabel blank = new JLabel(" ");
			//this.add(blank);
			DayComponent blankDay = new DayComponent(0, DAY_WIDTH, DAY_HEIGHT, false);
			dayGridPanel.add(blankDay);
		}

		int daysInMonth = rc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		// Draw days on grid
		for(int i = 1; i <= daysInMonth; i++) {
			rc.set(GregorianCalendar.DAY_OF_MONTH, i);
			boolean dayHasEvent = !(cal.getEvents(rc.getTime())).isEmpty();
			DayComponent day = new DayComponent(i, DAY_WIDTH, DAY_HEIGHT, dayHasEvent);
			dayGridPanel.add(day);

			// Add click event to day
			day.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					UI.outputln("Clicked on " + day.drawDay);
					cal.setDay(day.day);
				}
			});
		}
		
		// Update month label
		monthLabel.setText(monthDateFormat.format(cal.getTime()));

		this.revalidate();
	}
	
	private boolean dayIsVisible() {
		boolean yearMatches = (cal.get(GregorianCalendar.YEAR) == rc.get(GregorianCalendar.YEAR));
		boolean monthMatches = (cal.get(GregorianCalendar.MONTH) == rc.get(GregorianCalendar.MONTH));
		
		return yearMatches && monthMatches;
	}
	
	/**
	 * Represents a square day on the calendar
	 */
	class DayComponent extends JComponent {
		
		private int day;
		private String drawDay;
		private boolean hasEvent;

		public DayComponent(int day, int width, int height, boolean hasEvent) {

			this.day = day;
			this.drawDay = (day > 0) ? Integer.toString(day) : "";
			this.hasEvent = hasEvent;

			this.setSize(width, height);
			this.setPreferredSize(new Dimension(width, height));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;

			if(day > 0) {

				if(day == cal.get(GregorianCalendar.DAY_OF_MONTH)) {
					g2d.setColor(Color.RED);
				}

				if(hasEvent) {
					g2d.fillOval(this.getWidth() - 20, 10, 10, 10);
				}

				g2d.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
				g2d.drawString(drawDay, 10, 20);


			}
		}

	}
}
