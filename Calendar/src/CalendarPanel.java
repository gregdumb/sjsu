import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

/**
 * Created by Greg on 11/9/2017.
 */
public class CalendarPanel extends JPanel {

	private GregorianCalendar rc;
	private EventCalendar cal;

	private int DAY_WIDTH = 60;
	private int DAY_HEIGHT = 50;

	public CalendarPanel(EventCalendar cal) {

		this.rc = new GregorianCalendar();
		this.cal = cal;

		this.setLayout(new GridLayout(0, 7));

		this.draw();
	}

	public void draw() {
		this.removeAll();

		// Update Render Calendar
		rc.set(GregorianCalendar.MONTH, cal.get(GregorianCalendar.MONTH));
		rc.set(GregorianCalendar.YEAR, cal.get(GregorianCalendar.YEAR));
		rc.set(GregorianCalendar.DAY_OF_MONTH, 1);


		for(int i = 0; i < rc.get(GregorianCalendar.DAY_OF_WEEK); i++) {
			//JLabel blank = new JLabel(" ");
			//this.add(blank);
			DayComponent blankDay = new DayComponent(0, DAY_WIDTH, DAY_HEIGHT);
			this.add(blankDay);
		}

		int daysInMonth = rc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		for(int i = 1; i <= daysInMonth; i++) {

			//JLabel number = new JLabel(Integer.toString(i));
			//this.add(number);
			DayComponent day = new DayComponent(i, DAY_WIDTH, DAY_HEIGHT);
			this.add(day);
		}

		this.revalidate();
	}

	class DayComponent extends JComponent {

		private int day;
		private String drawDay;

		public DayComponent(int day, int width, int height) {

			this.day = day;
			this.drawDay = (day > 0) ? Integer.toString(day) : "";

			this.setSize(width, height);
			this.setPreferredSize(new Dimension(width, height));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;

			if(day > 0) {
				g2d.drawRect(0, 0, this.getWidth() - 2, this.getHeight() - 2);
				g2d.drawString(drawDay, 10, 20);
			}
		}

	}
}
