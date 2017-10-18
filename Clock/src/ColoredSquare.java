import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Greg on 10/17/2017.
 */
public class ColoredSquare implements Icon
{
	Color color;

	public ColoredSquare(Color color) {
		this.color = color;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Shape ellipse = new Ellipse2D.Double(x, y, getIconWidth(), getIconHeight());

		double centerx = x + getIconWidth() / 2;
		double centery = y + getIconHeight() / 2;

		double minuteHandLength = getIconWidth() / 2;
		double hourHandLength = minuteHandLength * 0.5;
		double secondHandLength = minuteHandLength * 0.75;

		GregorianCalendar nowCal = new GregorianCalendar();
		//nowCal.set(GregorianCalendar.HOUR_OF_DAY, 12);
		//nowCal.set(GregorianCalendar.MINUTE, 55);

		int second = nowCal.get(GregorianCalendar.SECOND);
		int minute = nowCal.get(GregorianCalendar.MINUTE);
		int hour = nowCal.get(GregorianCalendar.HOUR_OF_DAY);
		System.out.println(hour + " " + minute + " " + second);

		double rotSecond = (second / 60.0) * Math.PI * 2 - (Math.PI / 2);
		double rotMinute = (minute / 60.0) * Math.PI * 2 - (Math.PI / 2);
		double rotHour = ((hour + minute / 60.0) / 12.0) * Math.PI * 2 - (Math.PI / 2);

		System.out.println("Hour hand degrees: " + rotHour);

		double hourx = centerx + Math.cos(rotHour) * hourHandLength;
		double houry = centery + Math.sin(rotHour) * hourHandLength;

		double minutex = centerx + Math.cos(rotMinute) * minuteHandLength;
		double minutey = centery + Math.sin(rotMinute) * minuteHandLength;

		double secondx = centerx + Math.cos(rotSecond) * secondHandLength;
		double secondy = centery + Math.sin(rotSecond) * secondHandLength;

		Shape secondHand = new Line2D.Double(centerx, centery, secondx, secondy);
		Shape minuteHand = new Line2D.Double(centerx, centery, minutex, minutey);
		Shape hourHand = new Line2D.Double(centerx, centery, hourx, houry);

		Graphics2D g2 = (Graphics2D) g;
		g2.draw(ellipse);
		g2.draw(secondHand);
		g2.draw(minuteHand);
		g2.draw(hourHand);

		g.setColor(Color.BLACK);
	}

	public int getIconWidth() {
		return 400;
	}

	public int getIconHeight() {
		return 400;
	}
}
